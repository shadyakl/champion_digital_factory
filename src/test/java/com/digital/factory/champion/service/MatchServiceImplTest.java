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

import com.digital.factory.champion.entity.MatchEntity;
import com.digital.factory.champion.exceptions.BadRequestException;
import com.digital.factory.champion.payload.request.MatchRequest;
import com.digital.factory.champion.payload.response.MatchResponse;
import com.digital.factory.champion.repository.MatchRepository;
import com.digital.factory.champion.repository.ParticipantRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class MatchServiceImplTest {

	@Spy
	@InjectMocks
	private MatchServiceImpl matchServiceImpl;

	@Mock
	private MatchRepository matchRepo;

	@Mock
	private ParticipantRepository participantRepo;

	@Test
	public void updateMatchResults() {
		Mockito.when(matchRepo.save(new MatchEntity())).thenReturn(new MatchEntity());
		try {
			assertEquals(matchServiceImpl.updateMatchResults(new MatchRequest()), true);
			assertEquals(matchServiceImpl.updateMatchResults(new MatchRequest()), false);
		} catch (BadRequestException e) {

			if (e.getMessage().equals("Match Id not existing"))
				assertEquals(e.getMessage(), "Match Id not existing");
			else if (e.getMessage().equals("Participant Id not existing"))
				assertEquals(e.getMessage(), "Participant Id not existing");

		}

	}

	@Test
	public void getRoundMatches() {
		Mockito.when(matchRepo.getListOfMatchesWithRoundNumber(1L, 2)).thenReturn(new ArrayList<MatchEntity>());
		assertEquals(matchServiceImpl.getRoundMatches(1L, 2), new ArrayList<MatchResponse>());

	}

}
