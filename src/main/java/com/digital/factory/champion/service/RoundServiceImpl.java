package com.digital.factory.champion.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.factory.champion.entity.RoundEntity;
import com.digital.factory.champion.enums.RoundStatusEnum;
import com.digital.factory.champion.exceptions.BadRequestException;
import com.digital.factory.champion.payload.response.RoundResponse;
import com.digital.factory.champion.repository.RoundRepository;
import com.digital.factory.champion.util.ChampionModelMapper;

@Service
public class RoundServiceImpl {

	@Autowired
	RoundRepository roundRepo;

	@Autowired
	ChampionModelMapper mapper;

	public List<RoundResponse> getRoundsByLeagueId(Long leagueId) {

		return roundRepo.findByLeagueId(leagueId).stream().map(round -> mapper.map(round, RoundResponse.class))
				.collect(Collectors.toList());
	}

	public Boolean closeRound(Long roundId) throws BadRequestException {
		RoundEntity roundEntity = roundRepo.findById(roundId)
				.orElseThrow(() -> new BadRequestException("Round Id not existing"));
		roundEntity.setRoundStatus(RoundStatusEnum.CLOSED);
		roundRepo.save(roundEntity);
		return true;
	}

}
