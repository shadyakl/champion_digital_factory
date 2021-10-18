package com.digital.factory.champion.controller;

import java.util.List;

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

import com.digital.factory.champion.exceptions.BadRequestException;
import com.digital.factory.champion.exceptions.InvalidOperationException;
import com.digital.factory.champion.payload.request.MatchRequest;
import com.digital.factory.champion.payload.response.MatchResponse;
import com.digital.factory.champion.service.MatchServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/match")
@Slf4j
public class MatchController {

	@Autowired
	private MatchServiceImpl matchService;

	@ApiOperation(value = "Update a match result")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Match Result updated successfully") })
	@PostMapping(path = "/updateMatchResults")
	public ResponseEntity<?> updateMatchResults(@Valid @RequestBody MatchRequest request) throws BadRequestException {
		log.info("Calling updateMatchResults");

		return ResponseEntity.ok(matchService.updateMatchResults(request));

	}

	@GetMapping("/roundMatches/{leagueId}/{roundNo}")
	@ApiOperation(value = "Get list of all automatically created the n matches by league Id and round no")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get list of all automatically created the n matches returned successfully") })
	public ResponseEntity<List<MatchResponse>> getRoundMatches(@Valid @NotNull @PathVariable("leagueId") Long leagueId,
			@Valid @NotNull @PathVariable("roundNo") Integer roundNo) throws InvalidOperationException {
		log.info("Calling getRoundMatches");
		return ResponseEntity.ok(matchService.getRoundMatches(leagueId, roundNo));

	}

}
