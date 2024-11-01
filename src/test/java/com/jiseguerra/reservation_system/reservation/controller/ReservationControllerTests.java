package com.jiseguerra.reservation_system.reservation.controller;

import com.jiseguerra.reservation_system.reservation.controller.impl.ReservationControllerImpl;
import com.jiseguerra.reservation_system.reservation.dto.CreateReservationDTO;
import com.jiseguerra.reservation_system.reservation.dto.ReservationDTO;
import com.jiseguerra.reservation_system.reservation.dto.UpdateReservationDTO;
import com.jiseguerra.reservation_system.reservation.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

/**
 * @author John Ivan Seguerra
 * @version $Id: ReservationControllerTests.java, 2024-11-01 6:41 PM $$
 */
@ExtendWith(MockitoExtension.class)
class ReservationControllerTests {

	ReservationController reservationController;
	ReservationService reservationService;

	@BeforeEach
	void setup() {
		reservationService = Mockito.mock(ReservationService.class);
		reservationController = new ReservationControllerImpl(reservationService);
	}

	@Test
	void testCreateReservation() {
		CreateReservationDTO createReservationDTO =
					new CreateReservationDTO(
								"John",
								"09123456789",
								"john@example.com",
								new Date(),
								4);

		Mockito.when(reservationService.createReservation(any()))
					.thenReturn(CompletableFuture.completedFuture(new ReservationDTO()));

		assertNotNull(reservationController.createReservation(createReservationDTO));
	}

	@Test
	void testUpdateReservation() {
		UpdateReservationDTO updateDTO = new UpdateReservationDTO(new Date(), 4);

		Mockito.when(reservationService.updateReservation(any(), any()))
					.thenReturn(CompletableFuture.completedFuture(new ReservationDTO()));

		assertNotNull(reservationController.updateReservation(2L, updateDTO));
	}

	@Test
	void testGetReservations() {
		Mockito.when(reservationService.getReservations(any(), any()))
					.thenReturn(CompletableFuture.completedFuture(new ArrayList<>()));

		assertNotNull(reservationController.getReservations("John", "john@example.com"));
	}

	@Test
	void testCancelReservation() {
		Mockito.when(reservationService.cancelReservation(any()))
					.thenReturn(CompletableFuture.completedFuture(new ReservationDTO()));

		assertNotNull(reservationController.cancelReservation(1L));
	}
}
