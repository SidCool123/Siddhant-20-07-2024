package com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models;

import java.util.List;


import lombok.Data;

@Data
public class AvailableSeatsResponse {
	
	private Long busId;
	
	private List<SeatResponse> seats;

	public Long getBusId() {
		return busId;
	}

	public void setBusId(Long busId) {
		this.busId = busId;
	}

	public List<SeatResponse> getSeats() {
		return seats;
	}

	public void setSeats(List<SeatResponse> seats) {
		this.seats = seats;
	}

}
