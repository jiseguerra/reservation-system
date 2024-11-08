package com.jiseguerra.reservation_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ReservationSystemApplication {
	public static final String MAIN_PACKAGE = ReservationSystemApplication.class.getPackageName();

	public static void main(String[] args) {
		SpringApplication.run(ReservationSystemApplication.class, args);
	}

}
