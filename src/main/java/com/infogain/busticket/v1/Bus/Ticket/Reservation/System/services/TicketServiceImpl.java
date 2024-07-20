package com.infogain.busticket.v1.Bus.Ticket.Reservation.System.services;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.entities.Seat;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.entities.Ticket;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.exceptions.TicketException;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models.TicketReservationRequest;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models.TicketReservationResponse;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.repositories.SeatRepository;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.repositories.TicketRepository;

/**
 * @author Siddhant
 * 
 *         Implementation class for Ticket
 * 
 */
@Service
public class TicketServiceImpl {

	private static final Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);

	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private TicketRepository ticketRepository;

	public TicketReservationResponse reserveTickets(TicketReservationRequest request) {
		log.info("Reserving tickets for bus ID: {} for user: {}", request.getBusId(), request.getUserEmail());

		CompletableFuture<TicketReservationResponse> future = CompletableFuture.supplyAsync(() -> {
			try {
				List<Seat> seats = seatRepository.findByBusIdAndSeatNumberIn(request.getBusId(),
						request.getSeatNumbers());

				if (seats.size() != request.getSeatNumbers().size()) {
					throw new TicketException("Some seats are not available or do not exist.");
				}

				// Check if all seats are available
				boolean allAvailable = seats.stream().allMatch(Seat::isAvailable);
				if (!allAvailable) {
					throw new TicketException("Some selected seats are already reserved.");
				}

				// Reserve the seats
				seats.forEach(seat -> seat.setAvailable(false));
				seatRepository.saveAll(seats);

				// Create a new ticket reservation
				Ticket ticket = new Ticket();
				ticket.setBusId(request.getBusId());
				ticket.setSeatNumbers(request.getSeatNumbers());
				ticket.setUserEmail(request.getUserEmail());

				Random rnd = new Random();
				int resID = 100000 + rnd.nextInt(900000);
				ticket.setResId("INFOSYS-RES-ID-" + resID);
				Ticket savedTicket = ticketRepository.save(ticket);

				TicketReservationResponse response = new TicketReservationResponse();
				response.setReservationId(savedTicket.getResId());
				response.setConfirmationMessage("Tickets successfully reserved!");

				return response;
			} catch (Exception e) {
				log.error("Error reserving tickets: {}", e.getMessage());
				throw new TicketException("Error reserving tickets: " + e.getMessage());
			}
		});

		try {
			return future.get(); // Blocking call to get the result
		} catch (InterruptedException | ExecutionException e) {
			log.error("Error reserving tickets: {}", e.getMessage());
			throw new TicketException("Error reserving tickets: " + e.getMessage());
		}
	}
}
