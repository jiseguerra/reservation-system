package com.jiseguerra.reservation_system.reservation.service;

import com.jiseguerra.reservation_system.reservation.dto.CreateReservationDTO;
import com.jiseguerra.reservation_system.reservation.dto.ReservationDTO;
import com.jiseguerra.reservation_system.reservation.dto.UpdateReservationDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author John Ivan Seguerra
 * @version $Id: ReservationService.java, 2024-10-31 2:42 PM $$
 */
public interface ReservationService {

	CompletableFuture<ReservationDTO> createReservation(CreateReservationDTO createDTO);

	CompletableFuture<ReservationDTO> updateReservation(Long id, UpdateReservationDTO updateDTO);

	CompletableFuture<List<ReservationDTO>> getReservations(String name, String emailAddress);

	CompletableFuture<ReservationDTO> cancelReservation(Long id);
}
