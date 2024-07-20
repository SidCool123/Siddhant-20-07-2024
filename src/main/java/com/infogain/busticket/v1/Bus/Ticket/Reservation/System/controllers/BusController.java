package com.infogain.busticket.v1.Bus.Ticket.Reservation.System.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.constants.BusConstants;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models.BusResponse;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models.BusRouteResponse;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models.BusSearchRequest;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.services.BusService;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller class for Bus
 */
@Slf4j
@RestController
@RequestMapping(BusConstants.BUS_MAPPING)
public class BusController {

	private static final Logger log = LoggerFactory.getLogger(BusController.class);

	@Autowired
	private BusService busService;

	/**
	 * API to search buses
	 * 
	 * @param searchRequest
	 * @return
	 */
	@GetMapping(BusConstants.GET_SEARCH_BUS)
	public ResponseEntity<List<BusResponse>> searchBuses(@RequestBody BusSearchRequest searchRequest) {
		try {
			List<BusResponse> buses = busService.searchBuses(searchRequest);
			return new ResponseEntity<>(buses, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error searching for buses: {}", e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * API to search available routes
	 * 
	 * @return
	 */
	@GetMapping(BusConstants.GET_AVAILABLE_ROUTES)
	public ResponseEntity<List<BusRouteResponse>> getAvailableBusRoutes() {
		try {
			List<BusRouteResponse> routes = busService.getAvailableBusRoutes();
			return new ResponseEntity<>(routes, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error fetching bus routes: {}", e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
