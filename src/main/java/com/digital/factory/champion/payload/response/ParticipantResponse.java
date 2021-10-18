package com.digital.factory.champion.payload.response;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParticipantResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String participantName;
	private String participantEmail;

	public ParticipantResponse(Long id, String participantName, String participantEmail) {
		this.id = id;
		this.participantName = participantName;
		this.participantEmail = participantEmail;
	}

}
