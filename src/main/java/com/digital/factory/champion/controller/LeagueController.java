package com.digital.factory.champion.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digital.factory.champion.exceptions.BadRequestException;
import com.digital.factory.champion.exceptions.InvalidOperationException;
import com.digital.factory.champion.payload.request.MatchRequest;
import com.digital.factory.champion.service.LeagueServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/league")
@Slf4j
public class LeagueController {

	@Autowired
	private LeagueServiceImpl leagueServiceImpl;

	@ApiOperation(value = "Get All Leagues")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Leagues Returned successfully") })
	@GetMapping(path = "/all")
	public ResponseEntity<?> getAllLeagues() {
		log.info("Calling getAllLeagues");

		return ResponseEntity.ok(leagueServiceImpl.getAllLeagues());

	}

	@ApiOperation(value = "Create League")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "League Created successfully") })
	@PostMapping(path = "/createLeague")
	public ResponseEntity<?> createLeague(@Valid @NotNull @RequestParam int participantsNo)
			throws InvalidOperationException, BadRequestException {
		log.info("Calling createLeague");
		if (participantsNo > 12) {
			throw new BadRequestException("Max allowed number is 12 participants!");
		}

		if (participantsNo % 2 != 0) {
			throw new BadRequestException("Odd Numbers Not allowed!");
		}

		return ResponseEntity.ok(leagueServiceImpl.createLeague(participantsNo));

	}

	@ApiOperation(value = "Submit a request of new match (players and time)")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "New Match Created successfully") })
	@PostMapping(path = "/createNewLeagueMatch")
	public ResponseEntity<?> createNewLeagueMatch(@Valid @NotNull @RequestBody MatchRequest matchRequest,
			@RequestParam Long leagueId) throws BadRequestException {
		log.info("Calling createNewLeagueMatch");

		return ResponseEntity.ok(leagueServiceImpl.createSingleLeagueMatch(matchRequest, leagueId));

	}

	@ApiOperation(value = "Submit League Champion")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "League Champion successfully") })
	@PostMapping(path = "/submitLeagueChampion")
	public ResponseEntity<?> submitLeagueChampion(@Valid @NotNull @RequestParam Long leagueWinnerId,
			@RequestParam Long leagueId) throws BadRequestException {
		log.info("Calling submitLeagueChampion");

		return ResponseEntity.ok(leagueServiceImpl.submitLeagueChampion(leagueWinnerId, leagueId));

	}

}
