package com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models;

import java.util.List;

import lombok.Data;

@Data
public class TicketReservationRequest {
	private Long busId;
	private List<String> seatNumbers; // List of selected seat numbers
	private String userEmail; // Email of the user reserving the tickets\

	public Long getBusId() {
		return busId;
	}

	public void setBusId(Long busId) {
		this.busId = busId;
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
