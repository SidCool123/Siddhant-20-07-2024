package com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BusSearchRequest {
	private String departureLocation;
	private String arrivalLocation;
	private Timestamp departureDate;

	public String getDepartureLocation() {
		return departureLocation;
	}

	public void setDepartureLocation(String departureLocation) {
		this.departureLocation = departureLocation;
	}

	public String getArrivalLocation() {
		return arrivalLocation;
	}

	public void setArrivalLocation(String arrivalLocation) {
		this.arrivalLocation = arrivalLocation;
	}

	public Timestamp getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Timestamp departureDate) {
		this.departureDate = departureDate;
	}
}
