package com.infogain.busticket.v1.Bus.Ticket.Reservation.System.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
