package com.digital.factory.champion.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.digital.factory.champion.entity.LeagueEntity;
import com.digital.factory.champion.entity.MatchEntity;
import com.digital.factory.champion.entity.ParticipantEntity;
import com.digital.factory.champion.entity.RoundEntity;
import com.digital.factory.champion.enums.RoundStatusEnum;
import com.digital.factory.champion.exceptions.BadRequestException;
import com.digital.factory.champion.payload.request.MatchRequest;
import com.digital.factory.champion.payload.response.LeagueResponse;
import com.digital.factory.champion.repository.LeagueRepository;
import com.digital.factory.champion.repository.MatchRepository;
import com.digital.factory.champion.repository.ParticipantRepository;
import com.digital.factory.champion.repository.RoundRepository;
import com.digital.factory.champion.util.ChampionModelMapper;
import com.digital.factory.champion.util.IAvaiableMatchTime;

@Service
public class LeagueServiceImpl {

	@Autowired
	LeagueRepository leagueRepo;

	@Autowired
	ParticipantRepository participantRepo;

	@Autowired
	MatchRepository matchRepo;

	@Autowired
	RoundRepository roundRepo;

	@Autowired
	ChampionModelMapper mapper;

	@Autowired
	EmailSenderService emailSenderService;

	@Value("${mail.from}")
	private String fromEmail;

	public List<LeagueResponse> getAllLeagues() {
		return leagueRepo.findAll().stream().map(league -> mapper.map(league, LeagueResponse.class))
				.collect(Collectors.toList());
	}

	public LeagueEntity createLeague(int participantsNo) {
		LinkedList<ParticipantEntity> participantList = new LinkedList<ParticipantEntity>();

		List<ParticipantEntity> randomParticipants = participantRepo
				.getRandomParticipants(Math.toIntExact(participantsNo));

		randomParticipants.forEach(partic -> {
			participantList.add(partic);
		});

		LeagueEntity leagueEntity = new LeagueEntity();

		LeagueEntity savedLeagueEntity = leagueRepo.save(leagueEntity);

		createRoundRobinLeague(participantList, savedLeagueEntity);

		return savedLeagueEntity;

	}

	public void createRoundRobinLeague(LinkedList<ParticipantEntity> pl, LeagueEntity league) {
		ArrayList<ParticipantEntity> participantsList = new ArrayList<ParticipantEntity>(pl);

		int participants = pl.size();

		for (int i = 0; i < participants - 1; i++) {
			RoundEntity roundEntity = new RoundEntity(RoundStatusEnum.OPEN, league);
			RoundEntity saveRoundEntity = roundRepo.save(roundEntity);
			for (int j = 0; j < participants / 2; j++) {

				matchRepo.save(new MatchEntity(participantsList.get(j), participantsList.get(participants - j - 1),
						checkDayAvailabilityForMatches(), saveRoundEntity));
			}

			Collections.rotate(participantsList.subList(1, participantsList.size()), 1);

		}

	}

	Date checkDayAvailabilityForMatches() {
		IAvaiableMatchTime matchesTime = matchRepo.getCountOfMatchesPerDate();
		Calendar calendar = Calendar.getInstance();

		if (matchesTime.getDailyCount() >= 3) {
			calendar.setTime(matchesTime.getLastMatchTime());
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			return calendar.getTime();

		} else {
			calendar.setTime(matchesTime.getLastMatchTime());
			calendar.add(Calendar.HOUR_OF_DAY, 1);
			return calendar.getTime();
		}

	}

	public Boolean createSingleLeagueMatch(MatchRequest matchRequest, Long leagueId) throws BadRequestException {

		LeagueEntity leagueEntity = leagueRepo.findById(leagueId)
				.orElseThrow(() -> new BadRequestException("League Id not existing"));

		RoundEntity newRound = new RoundEntity();
		newRound.setLeague(leagueEntity);

		newRound = roundRepo.save(newRound);

		MatchEntity newMatch = mapper.map(matchRequest, MatchEntity.class);
		newMatch.setRound(newRound);
		newMatch.setParticipant1(participantRepo.findById(matchRequest.getParticipant1())
				.orElseThrow(() -> new BadRequestException("Participant1 Id not existing")));
		newMatch.setParticipant2(participantRepo.findById(matchRequest.getParticipant2())
				.orElseThrow(() -> new BadRequestException("Participant2 Id not existing")));

		matchRepo.save(newMatch);

		return true;
	}

	public Boolean submitLeagueChampion(Long leagueWinnerId, Long leagueId) throws BadRequestException {

		LeagueEntity leagueEntity = leagueRepo.findById(leagueId)
				.orElseThrow(() -> new BadRequestException("League Id not existing"));

		ParticipantEntity participantEntity = participantRepo.findById(leagueWinnerId)
				.orElseThrow(() -> new BadRequestException("Winner Id not existing"));

		leagueEntity.setLeagueChampion(participantEntity);

		leagueRepo.save(leagueEntity);

		sendMailToChampion(participantEntity.getParticipantName(), participantEntity.getParticipantEmail());

		return true;

	}

	private void sendMailToChampion(String winnerName, String winnerEmail) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(winnerEmail);
		mailMessage.setSubject("You are the Champion!!");
		mailMessage.setFrom(fromEmail);
		mailMessage.setText("Dear " + winnerName + " Congratulations you are the Champion of Tennis League!!");
		emailSenderService.sendEmail(mailMessage);
	}

}
