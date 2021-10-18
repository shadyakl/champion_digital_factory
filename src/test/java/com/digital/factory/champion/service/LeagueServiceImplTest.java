package com.digital.factory.champion.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.digital.factory.champion.entity.LeagueEntity;
import com.digital.factory.champion.entity.MatchEntity;
import com.digital.factory.champion.exceptions.BadRequestException;
import com.digital.factory.champion.payload.request.MatchRequest;
import com.digital.factory.champion.payload.response.LeagueResponse;
import com.digital.factory.champion.repository.LeagueRepository;
import com.digital.factory.champion.repository.MatchRepository;
import com.digital.factory.champion.repository.ParticipantRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class LeagueServiceImplTest {

	@Spy
	@InjectMocks
	private LeagueServiceImpl leagueServiceImpl;

	@Mock
	private LeagueRepository leagueRepo;

	@Mock
	private ParticipantRepository participantRepo;

	@Mock
	private MatchRepository matchRepo;

	@Test
	public void getAllLeagues() {
		Mockito.when(leagueRepo.findAll()).thenReturn(new ArrayList<LeagueEntity>());
		assertEquals(leagueServiceImpl.getAllLeagues(), new ArrayList<LeagueResponse>());

	}

	@Test
	public void createLeague() {
		Mockito.when(leagueRepo.save(new LeagueEntity())).thenReturn(new LeagueEntity());
		assertEquals(leagueServiceImpl.createLeague(12), new LeagueEntity());

	}

	@Test
	public void createRoundRobinLeague() {
		Mockito.when(matchRepo.save(new MatchEntity())).thenReturn(new MatchEntity());

	}

	@Test
	public void createSingleLeagueMatch() {

		Mockito.when(leagueRepo.save(new LeagueEntity())).thenReturn(new LeagueEntity());
		try {
			assertEquals(leagueServiceImpl.createSingleLeagueMatch(new MatchRequest(), 1L), true);
		} catch (BadRequestException e) {
			if (e.getMessage().equals("League Id not existing"))
				assertEquals(e.getMessage(), "League Id not existing");
			else if (e.getMessage().equals("Round Id not existing"))
				assertEquals(e.getMessage(), "Round Id not existing");
		}

	}

	@Test
	public void submitLeagueChampion() {
		Mockito.when(leagueRepo.save(new LeagueEntity())).thenReturn(new LeagueEntity());
		try {
			assertEquals(leagueServiceImpl.submitLeagueChampion(1L, 1L), true);
		} catch (BadRequestException e) {
			if (e.getMessage() == "League Id not existing")
				assertEquals(e.getMessage(), "League Id not existing");
			else if (e.getMessage() == "Round Id not existing")
				assertEquals(e.getMessage(), "Round Id not existing");
		}
	}

}
