package com.digital.factory.champion.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digital.factory.champion.exceptions.BadRequestException;
import com.digital.factory.champion.service.RoundServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/round")
@Slf4j
public class RoundController {

	@Autowired
	RoundServiceImpl roundService;

	@ApiOperation(value = "Get All Rounds By League Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Rounds Returned successfully") })
	@GetMapping(path = "/all/{leagueId}")
	public ResponseEntity<?> getAllRoundsByLeagueId(@Valid @NotNull @PathVariable("leagueId") Long leagueId)
			throws BadRequestException {
		log.info("Calling getAllRoundsByLeagueId");

		return ResponseEntity.ok(roundService.getRoundsByLeagueId(leagueId));

	}

	@ApiOperation(value = "Close Round")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Round Closed successfully") })
	@PostMapping(path = "/closeRound")
	public ResponseEntity<?> closeRound(@Valid @NotNull @RequestParam Long roundId) throws BadRequestException {
		log.info("Calling closeRound");

		return ResponseEntity.ok(roundService.closeRound(roundId));

	}

}
