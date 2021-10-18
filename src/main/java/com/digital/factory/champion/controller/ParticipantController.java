package com.digital.factory.champion.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.factory.champion.exceptions.InvalidOperationException;
import com.digital.factory.champion.payload.request.ParticipantRequest;
import com.digital.factory.champion.payload.response.ParticipantResponse;
import com.digital.factory.champion.service.ParticipantServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/participant")
@Slf4j
public class ParticipantController {
	@Autowired
	private ParticipantServiceImpl participantService;

	@GetMapping("/all")
	@ApiOperation(value = "Get a list of all participants")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get a list of all participants returned successfully") })
	ResponseEntity<List<ParticipantResponse>> getAllParticipants() {
		log.info("Calling getAllParticipants");
		return ResponseEntity.ok(participantService.getAllParticipants());
	}

	@GetMapping("/participantById/{id}")
	@ApiOperation(value = "Get participant by id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get participant by id returned successfully") })
	public ResponseEntity<ParticipantResponse> getParticipantById(@Valid @NotNull @PathVariable("id") Long id)
			throws InvalidOperationException {
		log.info("Calling getParticipantById");
		return ResponseEntity.ok(participantService.getParticipantById(id));

	}

	@ApiOperation(value = "Submit a participant request")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Participant created successfully") })
	@PostMapping(path = "/createParticipant")
	public ResponseEntity<ParticipantResponse> createParticipant(@Valid @RequestBody ParticipantRequest request)
			throws InvalidOperationException {
		log.info("Calling createParticipant");

		return ResponseEntity.ok(participantService.createParticipant(request));

	}

	@GetMapping("/groupParticipantsRandomly/{groupNo}")
	@ApiOperation(value = "Group randomly participants into (n) groups")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Group randomly participants into (n) groups returned successfully") })
	public ResponseEntity<Map<String, List<ParticipantResponse>>> randomParticipantGrouping(
			@Valid @NotNull @PathVariable(name = "groupNo") int groupNo) {
		log.info("Calling randomParticipantGrouping");

		Map<String, List<ParticipantResponse>> groups = participantService.randomParticipantGrouping(groupNo);

		return ResponseEntity.ok(groups);

	}

}
