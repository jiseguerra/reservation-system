package com.jiseguerra.reservation_system.exceptions.handler;

import com.jiseguerra.reservation_system.common.response.CustomErrorResponse;
import com.jiseguerra.reservation_system.common.response.CustomErrorResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

/**
 * @author John Ivan Seguerra
 * @version $Id: RestFallbackExceptionHandler.java, 2024-11-01 4:59 PM $$
 */
@RestControllerAdvice
public class RestFallbackExceptionHandler {

	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<CustomErrorResponse> genericHandler(
				MethodArgumentNotValidException e, final HttpServletRequest httpServletRequest) {

		HashMap<String, String> failedParameterMap = new HashMap<>();
		e.getBindingResult()
					.getFieldErrors()
					.forEach(
								fieldError ->
											failedParameterMap.put(
														fieldError.getField(),
														fieldError.getDefaultMessage() != null
																	? fieldError.getDefaultMessage()
																	: "Empty field"));

		return buildResponse(e, e.getStatusCode(), "Invalid parameters", failedParameterMap);
	}

	private <T extends Exception> ResponseEntity<CustomErrorResponse> buildResponse(
				T e,
				HttpStatusCode status,
				String message,
				HashMap<String, String> failedParameters) {
		CustomErrorResponseBuilder customErrorResponseBuilder =
					new CustomErrorResponseBuilder((HttpStatus) status)
								.setExceptionType(e.getClass().getSimpleName())
								.setMessage(message)
								.setFailedParameters(failedParameters);
		return new ResponseEntity<>(customErrorResponseBuilder.build(), status);
	}
}
