package com.infogain.busticket.v1.Bus.Ticket.Reservation.System.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.entities.Seat;


@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

	List<Seat> findByBusId(Long busId);

	List<Seat> findByBusIdAndSeatNumberIn(Long busId, List<String> seatNumbers);
	
}
