package com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models;

import lombok.Data;

@Data
public class SeatResponse {
    private Long id;
    private String seatNumber;
    private boolean isAvailable;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
    
}