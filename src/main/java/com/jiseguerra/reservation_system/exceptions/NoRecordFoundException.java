package com.jiseguerra.reservation_system.exceptions;

/**
 * @author John Ivan Seguerra
 * @version $Id: NoRecordFoundException.java, 2024-11-01 3:50 PM $$
 */
public class NoRecordFoundException extends RuntimeException {

	private final String errorMessage;

	private final String failedKey;

	public NoRecordFoundException(String errorMessage, String failedKey) {
		super("NoRecordFound");
		this.errorMessage = errorMessage;
		this.failedKey = failedKey;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public String getFailedKey() {
		return this.failedKey;
	}
}
