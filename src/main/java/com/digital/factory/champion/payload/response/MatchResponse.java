package com.digital.factory.champion.payload.response;

import java.util.Date;

import lombok.Data;

@Data
public class MatchResponse {

	private Long id;

	private ParticipantResponse participant1;

	private ParticipantResponse participant2;

	private ParticipantResponse matchWinner;

	private Integer participantScore1;

	private Integer participantScore2;

	private Date matchTime;

}
