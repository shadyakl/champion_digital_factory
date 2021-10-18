package com.digital.factory.champion.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.factory.champion.entity.MatchEntity;
import com.digital.factory.champion.entity.ParticipantEntity;
import com.digital.factory.champion.exceptions.BadRequestException;
import com.digital.factory.champion.payload.request.MatchRequest;
import com.digital.factory.champion.payload.response.MatchResponse;
import com.digital.factory.champion.repository.MatchRepository;
import com.digital.factory.champion.repository.ParticipantRepository;
import com.digital.factory.champion.util.ChampionModelMapper;

@Service
public class MatchServiceImpl {

	@Autowired
	private MatchRepository matchRepo;

	@Autowired
	private ParticipantRepository participantRepo;

	@Autowired
	private ChampionModelMapper mapper;

	public Boolean updateMatchResults(MatchRequest matchRequest) throws BadRequestException {
		MatchEntity matchEntity = matchRepo.findById(matchRequest.getId())
				.orElseThrow(() -> new BadRequestException("Match Id not existing"));

		ParticipantEntity participantEntity = participantRepo.findById(matchRequest.getMatchWinner())
				.orElseThrow(() -> new BadRequestException("Participant Id not existing"));

		matchEntity.setMatchWinner(participantEntity);
		matchEntity.setParticipantScore1(matchRequest.getParticipantScore1());
		matchEntity.setParticipantScore2(matchRequest.getParticipantScore2());

		MatchEntity updatedEntity = matchRepo.save(matchEntity);

		if (updatedEntity != null)
			return true;
		else
			return false;

	}

	public List<MatchResponse> getRoundMatches(Long leagueId, Integer roundNo) {
		return matchRepo.getListOfMatchesWithRoundNumber(leagueId, roundNo - 1).stream()
				.map(match -> mapper.map(match, MatchResponse.class)).collect(Collectors.toList());
	}

}
