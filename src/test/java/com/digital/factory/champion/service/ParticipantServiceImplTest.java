package com.digital.factory.champion.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.digital.factory.champion.entity.ParticipantEntity;
import com.digital.factory.champion.exceptions.InvalidOperationException;
import com.digital.factory.champion.payload.request.ParticipantRequest;
import com.digital.factory.champion.payload.response.MatchResponse;
import com.digital.factory.champion.payload.response.ParticipantResponse;
import com.digital.factory.champion.repository.ParticipantRepository;
import com.digital.factory.champion.util.ChampionModelMapper;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ParticipantServiceImplTest {

	@Spy
	@InjectMocks
	private ParticipantServiceImpl participantServiceImpl;

	@Mock
	private ParticipantRepository participantRepo;

	@Mock
	private ChampionModelMapper mapper;

	@Test
	public void getAllParticipants() {
		Mockito.when(participantRepo.findAll()).thenReturn(new ArrayList<ParticipantEntity>());
		try {
			assertEquals(participantServiceImpl.createParticipant(new ParticipantRequest()),
					new ArrayList<MatchResponse>());
		} catch (InvalidOperationException e) {
			assertEquals(e.getMessage(), "Entity not created");
		}

	}

	@Test
	public void getParticipantById() {
		Mockito.when(participantRepo.findById(1L)).thenReturn(Optional.of(new ParticipantEntity()));
		try {
			assertEquals(participantServiceImpl.getParticipantById(1L), new ParticipantResponse(null, null, null));
		} catch (InvalidOperationException e) {
			assertEquals(e.getMessage(), "Id is not existing");
		}
	}

	@Test
	public void randomParticipantGrouping() {
		Mockito.when(participantRepo.count()).thenReturn(5L);
		Mockito.when(participantRepo.getRandomParticipants(new ArrayList<Long>(), 10))
				.thenReturn(new ArrayList<ParticipantEntity>());
		Mockito.when(participantRepo.getRandomParticipants(10)).thenReturn(new ArrayList<ParticipantEntity>());

		Map<String, List<ParticipantResponse>> groups = new HashMap<String, List<ParticipantResponse>>();
		groups.put("Group 1", new ArrayList<ParticipantResponse>());
		groups.put("Group 2", new ArrayList<ParticipantResponse>());
		groups.put("Group 3", new ArrayList<ParticipantResponse>());

		assertEquals(participantServiceImpl.randomParticipantGrouping(3), groups);
	}

}
