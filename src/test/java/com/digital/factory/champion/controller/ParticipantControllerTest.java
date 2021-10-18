package com.digital.factory.champion.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.digital.factory.champion.exceptions.InvalidOperationException;
import com.digital.factory.champion.payload.request.ParticipantRequest;
import com.digital.factory.champion.payload.response.ParticipantResponse;
import com.digital.factory.champion.service.ParticipantServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ParticipantControllerTest {

	@InjectMocks
	private ParticipantController participantController;

	@Mock
	private ParticipantServiceImpl participantServiceImpl;

	@Test
	public void getAllParticipants() {
		Mockito.when(participantServiceImpl.getAllParticipants()).thenReturn(new ArrayList<ParticipantResponse>());
		assertEquals(participantController.getAllParticipants().getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getParticipantById() {
		Mockito.when(participantServiceImpl.getParticipantById(1L)).thenReturn(new ParticipantResponse());
		assertEquals(participantController.getParticipantById(10L).getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void createParticipant() {
		Mockito.when(participantServiceImpl.createParticipant(new ParticipantRequest()))
				.thenReturn(new ParticipantResponse());
		try {
			assertEquals(participantController.createParticipant(new ParticipantRequest()).getStatusCode(),
					HttpStatus.OK);
		} catch (InvalidOperationException e) {
			assertEquals(e.getMessage(), "Entity not created");
		}

	}

	@Test
	public void randomParticipantGrouping() {
		Mockito.when(participantServiceImpl.randomParticipantGrouping(4))
				.thenReturn(new HashMap<String, List<ParticipantResponse>>());
		assertEquals(participantController.randomParticipantGrouping(4).getStatusCode(), HttpStatus.OK);

	}

}
