package com.infogain.busticket.v1.Bus.Ticket.Reservation.System.exceptions;

/**
 * Exception class for BUS
 * 
 * @author Siddhant
 * 
 */
public class BusException extends RuntimeException {

	/**
	 * Returning message for Bus Exception
	 * 
	 * @param message
	 */
	public BusException(String message) {
		super(message);
	}
}
