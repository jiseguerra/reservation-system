package com.jiseguerra.reservation_system.reservation.dto;

import com.jiseguerra.reservation_system.reservation.enums.PreferredChannel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;

import java.time.ZonedDateTime;

/**
 * @author John Ivan Seguerra
 * @version $Id: CreateReservationDTO.java, 2024-11-01 2:47 PM $$
 */
public record CreateReservationDTO(
			String name,
			String mobileNumber,
			@Email String emailAddress,
			ZonedDateTime dateTime,
			int numberOfGuests,
			@Enumerated(EnumType.STRING) PreferredChannel methodOfCommunication) {
}
