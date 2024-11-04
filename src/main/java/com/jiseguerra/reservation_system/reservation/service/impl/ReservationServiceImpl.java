package com.jiseguerra.reservation_system.reservation.service.impl;

import com.jiseguerra.reservation_system.common.AppContants;
import com.jiseguerra.reservation_system.exceptions.NoRecordFoundException;
import com.jiseguerra.reservation_system.notification.service.NotificationService;
import com.jiseguerra.reservation_system.reservation.dto.CreateReservationDTO;
import com.jiseguerra.reservation_system.reservation.dto.ReservationDTO;
import com.jiseguerra.reservation_system.reservation.dto.UpdateReservationDTO;
import com.jiseguerra.reservation_system.reservation.entity.Reservation;
import com.jiseguerra.reservation_system.reservation.enums.Status;
import com.jiseguerra.reservation_system.reservation.repository.ReservationRepository;
import com.jiseguerra.reservation_system.reservation.service.ReservationService;
import com.jiseguerra.reservation_system.reservation.utils.ReservationMapper;
import com.jiseguerra.reservation_system.reservation.utils.StringFormatter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author John Ivan Seguerra
 * @version $Id: ReservationServiceImpl.java, 2024-10-31 2:42 PM $$
 */
@Service
public class ReservationServiceImpl implements ReservationService {

	private final ReservationRepository reservationRepository;
	private final ReservationMapper reservationMapper;
	private final NotificationService notificationService;

	public ReservationServiceImpl(
				ReservationRepository reservationRepository,
				ReservationMapper reservationMapper,
				NotificationService notificationService) {
		this.reservationRepository = reservationRepository;
		this.reservationMapper = reservationMapper;
		this.notificationService = notificationService;
	}


	@Async
	@Transactional
	@Override
	public CompletableFuture<ReservationDTO> createReservation(CreateReservationDTO createDTO) {
		Reservation reservation = reservationRepository.save(reservationMapper.dtoToEntity(createDTO));

		// Notify customer through preferred method of communication
		// Call notification service

		// Transform dateTime first to readable format
		String formattedDate = StringFormatter.formatDateTime(reservation.getDateTime());
		String message = String.format(AppContants.NOTIFICATION_MESSAGE,
					reservation.getId(), reservation.getName(), formattedDate);
		notificationService.sendNotification(createDTO.methodOfCommunication(), message);

		// Create a reminder 4 hours prior
		notificationService.createReminder(reservation);

		return CompletableFuture.completedFuture(reservationMapper.entityToDTO(reservation));
	}

	/**
	 *
	 * @param id
	 * @param updateDTO
	 * @return
	 */
	@Async
	@Transactional
	@Override
	public CompletableFuture<ReservationDTO> updateReservation(Long id, UpdateReservationDTO updateDTO) {
		Reservation reservation = reservationRepository.findById(id)
					.orElseThrow(
								() ->
											new NoRecordFoundException("No record found", "Reservation")
					);
		if(updateDTO.dateTime() != null && !reservation.getDateTime().equals(updateDTO.dateTime())) {
			reservation.setDateTime(updateDTO.dateTime());
			notificationService.updateReminder(reservation);
		}
		if(updateDTO.numberOfGuests() != null
					&& (updateDTO.numberOfGuests() > 0 && !updateDTO.numberOfGuests().equals(reservation.getNumberOfGuests()))) {
			reservation.setNumberOfGuests(updateDTO.numberOfGuests());
		}
		reservationRepository.save(reservation);

		return CompletableFuture.completedFuture(reservationMapper.entityToDTO(reservation));
	}

	/**
	 * Find reservations based on name or email address (or both)
	 * If no query parameter is provided, return all active reservations
	 * @param name - Customer name
	 * @param emailAddress - Customer email address
	 * @return List of reservations
	 */
	@Async
	@Transactional(readOnly = true)
	@Override
	public CompletableFuture<List<ReservationDTO>> getReservations(String name, String emailAddress) {
		List<Reservation> reservations;
		if(StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(emailAddress)) {
			reservations = reservationRepository.findByNameAndEmailAddress(name, emailAddress);
		} else if(StringUtils.isNotEmpty(name)) {
			reservations = reservationRepository.findByName(name);
		} else if(StringUtils.isNotEmpty(emailAddress)) {
			reservations = reservationRepository.findByEmailAddress(emailAddress);
		} else {
			reservations = reservationRepository.findByStatus(Status.RESERVED);
		}
		return CompletableFuture.completedFuture(
					reservations.stream().map(reservationMapper::entityToDTO).toList());
	}

	@Async
	@Transactional
	@Override
	public CompletableFuture<ReservationDTO> cancelReservation(Long id) {
		Reservation reservation = reservationRepository.findById(id)
					.orElseThrow(
								() -> new NoRecordFoundException("No record found", "Reservation")
					);
		reservation.setStatus(Status.CANCELLED);
		reservationRepository.save(reservation);
		return CompletableFuture.completedFuture(reservationMapper.entityToDTO(reservation));
	}
}
