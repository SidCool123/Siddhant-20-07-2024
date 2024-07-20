package com.infogain.busticket.v1.Bus.Ticket.Reservation.System.services;

import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models.TicketReservationRequest;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models.TicketReservationResponse;

/**
 * Service to Ticket class
 */
public interface TicketService {

	/**
	 * Method to reserve tickets
	 * 
	 * @param request
	 * @return
	 */
	public TicketReservationResponse reserveTickets(TicketReservationRequest request);

}
