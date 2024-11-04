package com.jiseguerra.reservation_system.exceptions.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.jiseguerra.reservation_system.common.response.CustomErrorResponse;
import com.jiseguerra.reservation_system.common.response.CustomErrorResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
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

		return buildResponse(e, HttpStatus.BAD_REQUEST, "Invalid parameters", failedParameterMap);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<CustomErrorResponse> genericHandler(
				HttpMessageNotReadableException e, HttpServletRequest httpServletRequest) {

		HashMap<String, String> failedParameterMap = new HashMap<>();
		String errorDetails;
		if(e.getCause() instanceof InvalidFormatException ifx
					&& (ifx.getTargetType() != null && ifx.getTargetType().isEnum())) {
			String fieldName = ifx.getPath().getLast().getFieldName();
			errorDetails = String.format("Invalid enum value: '%s' for the field: '%s'. The value must be one of: %s.",
						ifx.getValue(), fieldName, Arrays.toString(ifx.getTargetType().getEnumConstants()));

			failedParameterMap.put(fieldName, errorDetails);
		}

		return buildResponse(e, HttpStatus.BAD_REQUEST, "Invalid parameters", failedParameterMap);
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
