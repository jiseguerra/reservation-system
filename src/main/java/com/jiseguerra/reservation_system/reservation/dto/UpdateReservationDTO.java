package com.jiseguerra.reservation_system.reservation.dto;

import java.util.Date;

/**
 * @author John Ivan Seguerra
 * @version $Id: UpdateReservationDTO.java, 2024-11-01 3:35 PM $$
 */
public record UpdateReservationDTO(Date dateTime, int numberOfGuests) {}
