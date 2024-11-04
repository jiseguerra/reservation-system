package com.jiseguerra.reservation_system.reservation.controller.impl;

import com.jiseguerra.reservation_system.common.V1Controller;
import com.jiseguerra.reservation_system.reservation.controller.ReservationController;
import com.jiseguerra.reservation_system.reservation.dto.CreateReservationDTO;
import com.jiseguerra.reservation_system.reservation.dto.ReservationDTO;
import com.jiseguerra.reservation_system.reservation.dto.UpdateReservationDTO;
import com.jiseguerra.reservation_system.reservation.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author John Ivan Seguerra
 * @version $Id: ReservationControllerImpl.java, 2024-10-31 2:20 PM $$
 */
@V1Controller
@RequestMapping("/reservation")
public class ReservationControllerImpl implements ReservationController {

	private final ReservationService reservationService;

	public ReservationControllerImpl(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	@Override
	public CompletableFuture<ReservationDTO> createReservation(@RequestBody @Valid CreateReservationDTO createDTO) {
		return reservationService.createReservation(createDTO);
	}

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping("/{id}")
	@Override
	public CompletableFuture<ReservationDTO> updateReservation(
				@PathVariable Long id,
				@RequestBody @Valid UpdateReservationDTO updateDTO) {
		return reservationService.updateReservation(id, updateDTO);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	@Override
	public CompletableFuture<List<ReservationDTO>> getReservations(
				@RequestParam(required = false) String name,
				@RequestParam(required = false) String emailAddress) {
		return reservationService.getReservations(name, emailAddress);
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/{id}")
	@Override
	public CompletableFuture<ReservationDTO> cancelReservation(@PathVariable Long id) {
		return reservationService.cancelReservation(id);
	}
}
