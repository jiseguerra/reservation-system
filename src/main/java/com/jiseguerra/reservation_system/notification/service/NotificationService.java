package com.jiseguerra.reservation_system.notification.service;

import com.jiseguerra.reservation_system.reservation.entity.Reservation;
import com.jiseguerra.reservation_system.reservation.enums.PreferredChannel;

/**
 * @author John Ivan Seguerra
 * @version $Id: NotificationService.java, 2024-11-01 3:39 PM $$
 */
public interface NotificationService {

	// Method to notify the customer, returns void since we are only printing in console
	void sendNotification(PreferredChannel methodOfCommunication, String message);

	void sendNotification(Long reservationId, PreferredChannel methodOfCommunication, String message);

	void createReminder(Reservation reservation);

	void updateReminder(Reservation reservation);

	void cancelReminder(Long reservationId);
}
