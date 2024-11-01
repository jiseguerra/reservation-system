package com.jiseguerra.reservation_system.notification.service;

/**
 * @author John Ivan Seguerra
 * @version $Id: NotificationService.java, 2024-11-01 3:39 PM $$
 */
public interface NotificationService {

	// Method to notify the customer, returns void since we are only printing in console
	void sendNotification(String message);
}