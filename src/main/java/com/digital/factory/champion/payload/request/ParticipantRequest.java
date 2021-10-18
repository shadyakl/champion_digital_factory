package com.digital.factory.champion.payload.request;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ParticipantRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Cannot be null")
	@NotEmpty(message = "Cannot be empty")
	private String participantName;
	@Email(message = "Invalid Email")
	@NotNull(message = "Cannot be null")
	@NotEmpty(message = "Cannot be empty")
	private String participantEmail;

}
