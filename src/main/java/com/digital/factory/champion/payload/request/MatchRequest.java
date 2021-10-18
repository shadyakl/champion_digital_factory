package com.digital.factory.champion.payload.request;

import java.util.Date;

import lombok.Data;

@Data
public class MatchRequest {

	private Long id;
	private Long participant1;
	private Long participant2;
	private Long matchWinner;
	private Integer participantScore1;
	private Integer participantScore2;
	private Date matchTime;

}
