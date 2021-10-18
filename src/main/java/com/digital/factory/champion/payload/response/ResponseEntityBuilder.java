package com.digital.factory.champion.payload.response;

import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {
	public static ResponseEntity<Object> build(ApiErrorResponse apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}
