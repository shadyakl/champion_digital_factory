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

import com.digital.factory.champion.entity.LeagueEntity;
import com.digital.factory.champion.exceptions.BadRequestException;
import com.digital.factory.champion.payload.request.MatchRequest;
import com.digital.factory.champion.payload.response.LeagueResponse;
import com.digital.factory.champion.service.LeagueServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class LeagueControllerTest {
	@InjectMocks
	private LeagueController leagueController;

	@Mock
	private LeagueServiceImpl leagueServiceImpl;

	@Test
	public void getAllLeagues() {
		Mockito.when(leagueServiceImpl.getAllLeagues()).thenReturn(new ArrayList<LeagueResponse>());
		assertEquals(leagueController.getAllLeagues().getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void createLeague() {
		Mockito.when(leagueServiceImpl.createLeague(6)).thenReturn(new LeagueEntity());
		try {
			assertEquals(leagueController.createLeague(6).getStatusCode(), HttpStatus.OK);
			assertEquals(leagueController.createLeague(12).getStatusCode(), HttpStatus.OK);
			assertEquals(leagueController.createLeague(7).getStatusCode(), HttpStatus.OK);
		} catch (BadRequestException e) {
			assertEquals(e.getMessage(), "Odd Numbers Not allowed!");
		}
		try {
			assertEquals(leagueController.createLeague(13).getStatusCode(), HttpStatus.OK);
		} catch (BadRequestException e) {
			assertEquals(e.getMessage(), "Max allowed number is 12 participants!");

		}

	}

	@Test
	public void createNewLeagueMatch() {
		try {
			Mockito.when(leagueServiceImpl.createSingleLeagueMatch(new MatchRequest(), 8L)).thenReturn(true);
			assertEquals(leagueController.createNewLeagueMatch(new MatchRequest(), 8L).getStatusCode(), HttpStatus.OK);
		} catch (BadRequestException e) {
			System.out.println(e.getMessage());

		}
	}

	@Test
	public void submitLeagueChampion() {
		try {
			Mockito.when(leagueServiceImpl.submitLeagueChampion(10L, 20L)).thenReturn(true);
			assertEquals(leagueController.submitLeagueChampion(10L, 20L).getStatusCode(), HttpStatus.OK);
		} catch (BadRequestException e) {
			System.out.println(e.getMessage());

		}
	}
}
