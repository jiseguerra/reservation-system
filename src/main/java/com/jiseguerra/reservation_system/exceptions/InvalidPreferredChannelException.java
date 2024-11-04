package com.jiseguerra.reservation_system.exceptions;

/**
 * @author John Ivan Seguerra
 * @version $Id: InvalidPreferredChannelException.java, 2024-11-04 12:44 PM $$
 */
public class InvalidPreferredChannelException extends RuntimeException {

	public InvalidPreferredChannelException() {
	}

	public InvalidPreferredChannelException(String message) {
		super(message);
	}
}
