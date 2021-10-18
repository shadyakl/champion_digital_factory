package com.digital.factory.champion.payload.response;

import lombok.Data;

@Data
public class LeagueResponse {

	private Long id;
	private ParticipantResponse leagueChampion;
//	private List<RoundResponse> rounds;

}
