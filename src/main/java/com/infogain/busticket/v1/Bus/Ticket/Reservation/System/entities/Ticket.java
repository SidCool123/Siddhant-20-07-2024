package com.infogain.busticket.v1.Bus.Ticket.Reservation.System.entities;

import java.util.List;

/**
 * Entity class for Ticket
 * 
 */
public class Ticket {

	private String resId;
	private Long busId;
	private List<String> seatNumbers;
	private String userEmail;

	public Long getBusId() {
		return busId;
	}

	public void setBusId(Long busId) {
		this.busId = busId;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public List<String> getSeatNumbers() {
		return seatNumbers;
	}

	public void setSeatNumbers(List<String> seatNumbers) {
		this.seatNumbers = seatNumbers;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
