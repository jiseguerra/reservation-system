package com.jiseguerra.reservation_system.common.response;

import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * @author John Ivan Seguerra
 * @version $Id: CustomErrorResponse.java, 2024-11-01 5:30 PM $$
 */
public class CustomErrorResponse {
	private int status;
	private String exceptionType;
	private String timestampUTC;
	private String message;
	private Map<String, String> failedParameters;

	protected CustomErrorResponse(int rawStatusCode) {
		this.status = rawStatusCode;
	}

	public static CustomErrorResponse forStatus(int status) {
		return new CustomErrorResponse(status);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	public String getTimestampUTC() {
		return timestampUTC;
	}

	public void setTimestampUTC(String timestampUTC) {
		this.timestampUTC = timestampUTC;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getFailedParameters() {
		return failedParameters;
	}

	public void setFailedParameters(Map<String, String> failedParameters) {
		this.failedParameters = failedParameters;
	}
}
