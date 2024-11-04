package com.jiseguerra.reservation_system.notification.service.impl;

import com.jiseguerra.reservation_system.common.AppContants;
import com.jiseguerra.reservation_system.exceptions.InvalidPreferredChannelException;
import com.jiseguerra.reservation_system.notification.service.NotificationService;
import com.jiseguerra.reservation_system.reservation.entity.Reservation;
import com.jiseguerra.reservation_system.reservation.enums.PreferredChannel;
import com.jiseguerra.reservation_system.reservation.repository.ReservationRepository;
import com.jiseguerra.reservation_system.reservation.utils.StringFormatter;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author John Ivan Seguerra
 * @version $Id: NotificationServiceImpl.java, 2024-11-01 3:38 PM $$
 */
@Service
public class NotificationServiceImpl implements NotificationService {

	private final ReservationRepository reservationRepository;
	private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

	// This is to track the scheduled tasks
	private final Map<Long, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();

	public NotificationServiceImpl(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@Override
	public void sendNotification(PreferredChannel methodOfCommunication, String message) {
		switch(methodOfCommunication) {
			case PHONE:
				System.out.printf("Channel: PHONE, Message: %s%n", message);
				break;
			case EMAIL:
				System.out.printf("Channel: EMAIL, Message: %s%n", message);
				break;
			default: throw new InvalidPreferredChannelException();
		}
	}

	@Override
	public void sendNotification(Long reservationId, PreferredChannel methodOfCommunication, String message) {
		this.sendNotification(methodOfCommunication, message);

		// Remove the task from the map
		scheduledTasks.remove(reservationId);
	}

	/**
	 * Creates a scheduled notification for a new reservation
	 * @param reservation The reservation entity
	 */
	public void createReminder(Reservation reservation) {
		ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);

		long delayInMillis = ChronoUnit.MILLIS.between(now, reservation.getDateTime().minusHours(4));

		ScheduledFuture<?> task = executorService.schedule(() -> {
			// Format time and setup message
			String formattedTime = StringFormatter.formatTime(reservation.getDateTime());
			String message = String.format(AppContants.REMINDER_NOTIFICATION_MESSAGE,
						reservation.getName(), formattedTime);

			sendNotification(reservation.getId(), reservation.getMethodOfCommunication(), message);
		}, delayInMillis, TimeUnit.MILLISECONDS);

		scheduledTasks.put(reservation.getId(), task);
	}

	/**
	 * Updates an existing scheduled notification if booking time has changed
	 * @param reservation The reservation entity
	 */
	public void updateReminder(Reservation reservation) {
		// Get the existing task
		ScheduledFuture<?> existingTask = scheduledTasks.get(reservation.getId());

		if (existingTask != null && !existingTask.isDone()) {
			// Cancel the current task to create a new one with the updated delay
			existingTask.cancel(false);
		}

		createReminder(reservation);
	}

	/**
	 * Cancels an existing scheduled notification if reservation is cancelled
	 * @param reservationId The ID of the reservation entity
	 */
	@Override
	public void cancelReminder(Long reservationId) {
		ScheduledFuture<?> task = scheduledTasks.get(reservationId);

		if (task != null && !task.isDone()) {
			task.cancel(false);
		}

		// Remove task from the map
		scheduledTasks.remove(reservationId);
	}
}
