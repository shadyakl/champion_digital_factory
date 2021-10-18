package com.digital.factory.champion.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.digital.factory.champion.exceptions.BadRequestException;
import com.digital.factory.champion.payload.request.MatchRequest;
import com.digital.factory.champion.payload.response.MatchResponse;
import com.digital.factory.champion.service.MatchServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class MatchControllerTest {

	@InjectMocks
	private MatchController matchController;

	@Mock
	private MatchServiceImpl matchServiceImpl;

	@Test
	public void updateMatchResults() {

		try {
			Mockito.when(matchServiceImpl.updateMatchResults(new MatchRequest())).thenReturn(true);
			assertEquals(matchController.updateMatchResults(new MatchRequest()).getStatusCode(), HttpStatus.OK);
		} catch (BadRequestException e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void getRoundMatches() {
		Mockito.when(matchServiceImpl.getRoundMatches(10L, 1)).thenReturn(new ArrayList<MatchResponse>());
		assertEquals(matchController.getRoundMatches(10L, 1).getStatusCode(), HttpStatus.OK);
	}

}
