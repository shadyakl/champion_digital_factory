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
import com.digital.factory.champion.payload.response.RoundResponse;
import com.digital.factory.champion.service.RoundServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class RoundControllerTest {

	@InjectMocks
	private RoundController roundController;

	@Mock
	private RoundServiceImpl roundServiceImpl;

	@Test
	public void getAllRoundsByLeagueId() {

		Mockito.when(roundServiceImpl.getRoundsByLeagueId(1L)).thenReturn(new ArrayList<RoundResponse>());
		try {
			assertEquals(roundController.getAllRoundsByLeagueId(1L).getStatusCode(), HttpStatus.OK);
		} catch (BadRequestException e) {
			assertEquals(e.getMessage(), "Round Id not existing");
		}
	}

	@Test
	public void closeRound() {

		try {
			Mockito.when(roundServiceImpl.closeRound(5L)).thenReturn(true);
			Mockito.when(roundServiceImpl.closeRound(555L)).thenReturn(false);
			assertEquals(roundController.closeRound(5L).getStatusCode(), HttpStatus.OK);
		} catch (BadRequestException e) {
			assertEquals(e.getMessage(), "Round Id not existing");
		}

	}

}
