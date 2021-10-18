package com.digital.factory.champion.exceptions;

import com.digital.factory.champion.enums.ErrorEnum;

public class InvalidOperationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public InvalidOperationException(ErrorEnum errorMessage) {
		super(errorMessage.getErrorMessage());
		this.errorMessage = errorMessage.getErrorMessage();
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}