package com.jiseguerra.reservation_system.reservation.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author John Ivan Seguerra
 * @version $Id: StringFormatter.java, 2024-11-04 1:22 PM $$
 */
public class StringFormatter {

	private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd MMM yyyy 'at' hh:mm 'UTC'");
	private static final DateTimeFormatter TIME_PATTERN = DateTimeFormatter.ofPattern("hh:mm 'UTC'");

	public static String formatDateTime(ZonedDateTime dateTime) {
			return DATE_PATTERN.format(dateTime);
	}

	public static String formatTime(ZonedDateTime dateTime) {
			return TIME_PATTERN.format(dateTime);
	}
}
