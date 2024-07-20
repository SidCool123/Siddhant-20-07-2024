package com.infogain.busticket.v1.Bus.Ticket.Reservation.System.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models.BusResponse;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models.BusRouteResponse;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models.BusSearchRequest;

/**
 * @author Siddhant
 * 
 *         Service for Bus
 * 
 */
@Service
public interface BusService {

	/**
	 * method to search buses
	 * 
	 * @param searchRequest
	 * @return
	 */
	List<BusResponse> searchBuses(BusSearchRequest searchRequest);

	/**
	 * method to fetch AvailableSeatsResponse routes
	 * 
	 * @return
	 */
	List<BusRouteResponse> getAvailableBusRoutes();

}
