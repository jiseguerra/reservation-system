package com.jiseguerra.reservation_system.reservation.dto;

import com.jiseguerra.reservation_system.reservation.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Date;

/**
 * @author John Ivan Seguerra
 * @version $Id: ReservationDTO.java, 2024-10-31 2:46 PM $$
 */
public class ReservationDTO {
	private Long id;
	private String name;
	private String mobileNumber;
	private String emailAddress;
	private Date dateTime;
	private int numberOfGuests;
	@Enumerated(EnumType.STRING)
	private Status status;

	public ReservationDTO() {
	}

	public ReservationDTO(Long id, String name, String mobileNumber, String emailAddress,
												Date dateTime, int numberOfGuests, Status status) {
		this.id = id;
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.emailAddress = emailAddress;
		this.dateTime = dateTime;
		this.numberOfGuests = numberOfGuests;
		this.status = status;
	}

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

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
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
}
