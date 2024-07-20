package com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models;

import lombok.Data;

@Data
public class TicketReservationResponse {
	private String reservationId;
	private String confirmationMessage;

	public String getReservationId() {
		return reservationId;
	}

	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}

	public String getConfirmationMessage() {
		return confirmationMessage;
	}

	public void setConfirmationMessage(String confirmationMessage) {
		this.confirmationMessage = confirmationMessage;
	}

}
