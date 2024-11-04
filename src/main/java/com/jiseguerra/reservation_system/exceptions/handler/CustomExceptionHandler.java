package com.jiseguerra.reservation_system.exceptions.handler;

import com.jiseguerra.reservation_system.common.response.CustomErrorResponse;
import com.jiseguerra.reservation_system.common.response.CustomErrorResponseBuilder;
import com.jiseguerra.reservation_system.exceptions.NoRecordFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

/**
 * @author John Ivan Seguerra
 * @version $Id: CustomExceptionHandler.java, 2024-11-04 2:14 PM $$
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomExceptionHandler {

	@ExceptionHandler(NoRecordFoundException.class)
	public ResponseEntity<CustomErrorResponse> genericHandler(
				NoRecordFoundException e, final HttpServletRequest httpServletRequest) {
		HashMap<String, String> failedParameterMap = new HashMap<>();
		failedParameterMap.put(e.getFailedKey(), "NO_RECORD_FOUND");

		return buildResponse(e, HttpStatus.BAD_REQUEST, e.getMessage(), failedParameterMap);
	}

	private <T extends Exception> ResponseEntity<CustomErrorResponse> buildResponse(
				T e,
				HttpStatus status,
				String message,
				HashMap<String, String> failedParameters) {
		CustomErrorResponseBuilder customErrorResponseBuilder =
					new CustomErrorResponseBuilder(status)
								.setExceptionType(e.getClass().getSimpleName())
								.setMessage(message)
								.setFailedParameters(failedParameters);
		return new ResponseEntity<>(customErrorResponseBuilder.build(), status);
	}
}
