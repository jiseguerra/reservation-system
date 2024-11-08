package com.jiseguerra.reservation_system.reservation.entity;

import com.jiseguerra.reservation_system.reservation.enums.PreferredChannel;
import com.jiseguerra.reservation_system.reservation.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.ZonedDateTime;

/**
 * @author John Ivan Seguerra
 * @version $Id: Reservation.java, 2024-10-31 2:23 PM $$
 */
@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@Column
	private String mobileNumber;

	@Column
	private String emailAddress;

	@Column
	private ZonedDateTime dateTime;

	@Column
	private int numberOfGuests;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Enumerated(EnumType.STRING)
	private PreferredChannel methodOfCommunication;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public ZonedDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(ZonedDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public int getNumberOfGuests() {
		return numberOfGuests;
	}

	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public PreferredChannel getMethodOfCommunication() {
		return methodOfCommunication;
	}

	public void setMethodOfCommunication(PreferredChannel methodOfCommunication) {
		this.methodOfCommunication = methodOfCommunication;
	}
}
