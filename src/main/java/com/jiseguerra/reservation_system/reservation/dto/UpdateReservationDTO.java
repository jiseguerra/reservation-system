package com.jiseguerra.reservation_system.reservation.dto;

import java.time.ZonedDateTime;

/**
 * @author John Ivan Seguerra
 * @version $Id: UpdateReservationDTO.java, 2024-11-01 3:35 PM $$
 */
public record UpdateReservationDTO(ZonedDateTime dateTime, Integer numberOfGuests) {}
