package com.digital.factory.champion.enums;

public enum ErrorEnum {

	NOT_EXISTING("Id is not existing"),NOT_CREATED("Entity not created"), INVALID_JSON_FORMAT("Invalid json format"), INVALID_DATA("Invalid Data");

	private String errorMessage;

	ErrorEnum(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}
}
