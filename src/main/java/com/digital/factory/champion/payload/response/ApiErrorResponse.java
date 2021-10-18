package com.digital.factory.champion.payload.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {
	private HttpStatus status;
	private String message;
	private List<?> errors;

}
