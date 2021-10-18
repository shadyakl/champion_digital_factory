package com.digital.factory.champion.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.digital.factory.champion.entity.RoundEntity;
import com.digital.factory.champion.exceptions.BadRequestException;
import com.digital.factory.champion.payload.response.RoundResponse;
import com.digital.factory.champion.repository.RoundRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class RoundServiceImplTest {

	@Spy
	@InjectMocks
	private RoundServiceImpl roundServiceImpl;

	@Mock
	private RoundRepository roundRepo;

	@Test
	public void getRoundsByLeagueId() {
		Mockito.when(roundRepo.findByLeagueId(1L)).thenReturn(new ArrayList<RoundEntity>());
		assertEquals(roundServiceImpl.getRoundsByLeagueId(1L), new ArrayList<RoundResponse>());

	}

	@Test
	public void closeRound() {
		Mockito.when(roundRepo.findById(1L)).thenReturn(Optional.of(new RoundEntity()));
		Mockito.when(roundRepo.save(new RoundEntity())).thenReturn(new RoundEntity());
		try {
			assertEquals(roundServiceImpl.closeRound(1L), true);
		} catch (BadRequestException e) {
			assertEquals(e.getMessage(), "Round Id not existing");
		}

	}

}
