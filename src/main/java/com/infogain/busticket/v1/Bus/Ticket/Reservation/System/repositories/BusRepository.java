package com.infogain.busticket.v1.Bus.Ticket.Reservation.System.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.entities.Bus;

/**
 * @author Siddhant
 * 
 * Repository class for Bus
 */
@Repository
public interface BusRepository  extends JpaRepository<Bus, Long>{

	List<Bus> findByDepartureLocationAndArrivalLocationAndDepartureDate(String departureLocation,
			String arrivalLocation, Timestamp departureDate);
	

}
