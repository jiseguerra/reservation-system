package com.jiseguerra.reservation_system.common.response;

import org.springframework.http.HttpStatus;
import reactor.util.annotation.NonNull;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Map;

/**
 * @author John Ivan Seguerra
 * @version $Id: CustomErrorResponse.java, 2024-11-01 5:09 PM $$
 */
public class CustomErrorResponseBuilder {
	private final @NonNull HttpStatus status;
	private String exceptionType;
	private String message;
	private Map<String, String> failedParameters;

	public CustomErrorResponseBuilder(@NonNull HttpStatus status) {
		this.status = status;
	}

	public CustomErrorResponseBuilder setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
		return this;
	}

	public CustomErrorResponseBuilder setMessage(String message) {
		this.message = message;
		return this;
	}

	public CustomErrorResponseBuilder setFailedParameters(Map<String, String> failedParameters) {
		this.failedParameters = failedParameters;
		return this;
	}

	private String getTimestampUTC() {
		ZoneOffset utcOffset = ZoneOffset.UTC;
		ZonedDateTime date = ZonedDateTime.now(utcOffset);
		return date.toString();
	}

	public CustomErrorResponse build() {
		CustomErrorResponse customErrorResponse =
					CustomErrorResponse.forStatus(this.status.value());

		customErrorResponse.setExceptionType(this.exceptionType);
		customErrorResponse.setTimestampUTC(this.getTimestampUTC());
		customErrorResponse.setMessage(this.message);
		customErrorResponse.setFailedParameters(this.failedParameters);

		return customErrorResponse;
	}
}