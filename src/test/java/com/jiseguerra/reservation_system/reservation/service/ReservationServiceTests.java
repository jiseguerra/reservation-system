package com.jiseguerra.reservation_system.reservation.service;

import com.jiseguerra.reservation_system.exceptions.NoRecordFoundException;
import com.jiseguerra.reservation_system.notification.service.NotificationService;
import com.jiseguerra.reservation_system.reservation.dto.CreateReservationDTO;
import com.jiseguerra.reservation_system.reservation.dto.ReservationDTO;
import com.jiseguerra.reservation_system.reservation.dto.UpdateReservationDTO;
import com.jiseguerra.reservation_system.reservation.entity.Reservation;
import com.jiseguerra.reservation_system.reservation.enums.PreferredChannel;
import com.jiseguerra.reservation_system.reservation.repository.ReservationRepository;
import com.jiseguerra.reservation_system.reservation.service.impl.ReservationServiceImpl;
import com.jiseguerra.reservation_system.reservation.utils.ReservationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

/**
 * @author John Ivan Seguerra
 * @version $Id: ReservationServiceTests.java, 2024-11-01 7:00 PM $$
 */
@ExtendWith(MockitoExtension.class)
class ReservationServiceTests {
	ReservationService reservationService;
	ReservationRepository reservationRepository;
	ReservationMapper reservationMapper;
	NotificationService notificationService;

	@BeforeEach
	void setup() {
		reservationRepository = Mockito.mock(ReservationRepository.class);
		reservationMapper = Mockito.mock(ReservationMapper.class);
		notificationService = Mockito.mock(NotificationService.class);
		reservationService = new ReservationServiceImpl(
					reservationRepository,
					reservationMapper,
					notificationService);
	}

	@Test
	void testCreateReservation() {
		CreateReservationDTO createDTO =
					new CreateReservationDTO(
								"John",
								"09123456789",
								"john@example.com",
								new Date(),
								4,
								PreferredChannel.PHONE);

		Mockito.when(reservationRepository.save(any())).thenReturn(new Reservation());
		Mockito.when(reservationMapper.dtoToEntity(any())).thenReturn(new Reservation());
		Mockito.when(reservationMapper.entityToDTO(any())).thenReturn(new ReservationDTO());

		assertNotNull(reservationService.createReservation(createDTO));
	}

	@Test
	void testUpdateReservationSuccess() {
		UpdateReservationDTO updateDTO = new UpdateReservationDTO(new Date(), 3);

		Mockito.when(reservationRepository.findById(any())).thenReturn(Optional.of(new Reservation()));
		Mockito.when(reservationRepository.save(any())).thenReturn(new Reservation());
		assertNotNull(reservationService.updateReservation(2L, updateDTO));
	}

	@Test
	void testUpdateReservationWhenReservationNotFound() {
		UpdateReservationDTO updateDTO = new UpdateReservationDTO(new Date(), 3);

		Mockito.when(reservationRepository.findById(any())).thenReturn(Optional.empty());
		assertThrows(NoRecordFoundException.class,
					() -> reservationService.updateReservation(2L, updateDTO));
	}

	@Test
	void testGetReservationsViaNameAndEmailAddress() {
		Mockito.when(reservationRepository.findByNameAndEmailAddress(any(), any())).thenReturn(new ArrayList<>());

		assertNotNull(reservationService.getReservations("John", "john@example.com"));
	}

	@Test
	void testGetReservationsViaName() {
		Mockito.when(reservationRepository.findByName(any())).thenReturn(new ArrayList<>());

		assertNotNull(reservationService.getReservations("John", null));
	}

	@Test
	void testGetReservationsViaEmailAddress() {
		Mockito.when(reservationRepository.findByEmailAddress(any())).thenReturn(new ArrayList<>());

		assertNotNull(reservationService.getReservations(null, "john@example.com"));
	}

	@Test
	void testGetReservations() {
		Mockito.when(reservationRepository.findByStatus(any())).thenReturn(new ArrayList<>());

		assertNotNull(reservationService.getReservations(null, null));
	}

	@Test
	void testCancelReservationSuccess() {
		Mockito.when(reservationRepository.findById(any()))
					.thenReturn(Optional.of(new Reservation()));

		assertNotNull(reservationService.cancelReservation(2L));
	}

	@Test
	void testCancelReservationWhenReservationNotFound() {
		Mockito.when(reservationRepository.findById(any())).thenReturn(Optional.empty());

		assertThrows(NoRecordFoundException.class, () -> reservationService.cancelReservation(1L));
	}
}
