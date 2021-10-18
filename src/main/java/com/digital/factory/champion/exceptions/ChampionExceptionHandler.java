package com.digital.factory.champion.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.digital.factory.champion.payload.response.ApiErrorResponse;
import com.digital.factory.champion.payload.response.ResponseEntityBuilder;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice(annotations = RestController.class)
@Slf4j
public class ChampionExceptionHandler {

//	@ExceptionHandler(InvalidOperationException.class)
//	public ResponseEntity<ApiErrorResponse> invalidOperationException(final InvalidOperationException e) {
//		log.error(String.format("Error : %s", e.getErrorMessage()));
//		return new ResponseEntity<>(new ApiErrorResponse(e.getErrorMessage()), HttpStatus.BAD_REQUEST);
//	}
//
//	@ExceptionHandler(BadRequestException.class)
//	public ResponseEntity<ApiErrorResponse> badRequestException(final BadRequestException e) {
//		log.error(String.format("Error : %s", e.getMessage()));
//		return new ResponseEntity<>(new ApiErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
//	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handle(Exception ex, HttpServletRequest request, HttpServletResponse response) {
		log.error(String.format("Error : %s", ex.getMessage()));

		if (ex instanceof InvalidOperationException) {
			ApiErrorResponse err = new ApiErrorResponse(HttpStatus.BAD_REQUEST,
					((InvalidOperationException) ex).getErrorMessage(), null);

			return ResponseEntityBuilder.build(err);
		}
		if (ex instanceof NullPointerException) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (ex instanceof MethodArgumentNotValidException) {

			List<String> details = new ArrayList<String>();
			((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors().forEach(error -> {
				details.add(error.getField() + ":" + error.getDefaultMessage());
			});

			ApiErrorResponse err = new ApiErrorResponse(HttpStatus.BAD_REQUEST, "Arguments not valid", details);

			return ResponseEntityBuilder.build(err);
		}
		if (ex instanceof ConstraintViolationException) {

			List<String> details = new ArrayList<String>();
			details.add(ex.getMessage());
			ApiErrorResponse err = new ApiErrorResponse(HttpStatus.BAD_REQUEST, "Constraint Violations", details);

			return ResponseEntityBuilder.build(err);

		}
		if (ex instanceof TransactionSystemException) {
			List<String> details = new ArrayList<String>();
			details.add(ex.getMessage());
			ApiErrorResponse err = new ApiErrorResponse(HttpStatus.BAD_REQUEST, "Transaction Exception", details);

			return ResponseEntityBuilder.build(err);

		}

		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		ApiErrorResponse err = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Exception", details);

		return ResponseEntityBuilder.build(err);
	}

}
