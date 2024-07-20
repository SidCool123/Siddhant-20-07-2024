package com.infogain.busticket.v1.Bus.Ticket.Reservation.System.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.constants.TicketConstants;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.exceptions.TicketException;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models.TicketReservationRequest;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models.TicketReservationResponse;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.services.TicketService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Siddhant
 * 
 * Controller for Ticket
 */
@Slf4j
@RestController
@RequestMapping(TicketConstants.TICKET_MAPPING)
public class TicketController {

    private static final Logger log = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    private TicketService ticketService;

    /**
     * API to reserve the seats 
     * 
     * @param request
     * @return
     */
    @PostMapping(TicketConstants.TICKET_POST_RESERVE)
    public ResponseEntity<TicketReservationResponse> reserveTickets(@RequestBody TicketReservationRequest request) {
        try {
            TicketReservationResponse response = ticketService.reserveTickets(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (TicketException e) {
            log.error("Error reserving tickets: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}