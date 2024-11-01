package com.jiseguerra.reservation_system.notification.service.impl;

import com.jiseguerra.reservation_system.notification.service.NotificationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author John Ivan Seguerra
 * @version $Id: NotificationServiceImpl.java, 2024-11-01 3:38 PM $$
 */
@Service
public class NotificationServiceImpl implements NotificationService {

	@Override
	public void sendNotification(String message) {
		System.out.println(message);
	}
}
