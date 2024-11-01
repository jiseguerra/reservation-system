package com.jiseguerra.reservation_system.reservation.dto;

import jakarta.validation.constraints.Email;

import java.util.Date;

/**
 * @author John Ivan Seguerra
 * @version $Id: CreateReservationDTO.java, 2024-11-01 2:47 PM $$
 */
public record CreateReservationDTO(
			String name,
			String mobileNumber,
			@Email String emailAddress,
			Date dateTime,
			int numberOfGuests) {
}
