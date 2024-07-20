package com.infogain.busticket.v1.Bus.Ticket.Reservation.System.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.entities.Bus;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.entities.BusRoute;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.entities.Seat;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.exceptions.BusException;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models.AvailableSeatsResponse;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models.BusResponse;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models.BusRouteResponse;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models.BusSearchRequest;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models.SeatResponse;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.repositories.BusRepository;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.repositories.BusRouteRepository;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.repositories.SeatRepository;

/**
 * Implementation class for Bus
 */
public class BusServiceImpl implements BusService {

	private static final Logger log = LoggerFactory.getLogger(BusServiceImpl.class);

	@Autowired
	private BusRepository busRepository;

	@Autowired
	private BusRouteRepository busRouteRepository;

	@Autowired
	private SeatRepository seatRepository;

	public List<BusResponse> searchBuses(BusSearchRequest searchRequest) {
		log.info("Searching buses with departure location: {}, arrival location: {}, and date: {}",
				searchRequest.getDepartureLocation(), searchRequest.getArrivalLocation(),
				searchRequest.getDepartureDate());

		CompletableFuture<List<BusResponse>> future = CompletableFuture.supplyAsync(() -> {
			try {
				return busRepository
						.findByDepartureLocationAndArrivalLocationAndDepartureDate(searchRequest.getDepartureLocation(),
								searchRequest.getArrivalLocation(), searchRequest.getDepartureDate())
						.stream().map(this::convertToResponse).collect(Collectors.toList());
			} catch (Exception e) {
				log.error("Error searching buses: {}", e.getMessage());
				throw new BusException("Error searching buses: " + e.getMessage());
			}
		});

		try {
			return future.get();
		} catch (InterruptedException | ExecutionException e) {
			log.error("Error fetching search results: {}", e.getMessage());
			throw new BusException("Error fetching search results: " + e.getMessage());
		}
	}

	public List<BusRouteResponse> getAvailableBusRoutes() {
		log.info("Fetching available bus routes");

		CompletableFuture<List<BusRouteResponse>> future = CompletableFuture.supplyAsync(() -> {
			try {
				return busRouteRepository.findAll().stream().map(this::convertToResponse).collect(Collectors.toList());
			} catch (Exception e) {
				log.error("Error fetching bus routes: {}", e.getMessage());
				throw new BusException("Error fetching bus routes: " + e.getMessage());
			}
		});

		try {
			return future.get();
		} catch (InterruptedException | ExecutionException e) {
			log.error("Error fetching bus routes results: {}", e.getMessage());
			throw new BusException("Error fetching bus routes results: " + e.getMessage());
		}
	}

	public AvailableSeatsResponse getAvailableSeats(Long busId) {
		log.info("Fetching available seats for bus with ID: {}", busId);

		CompletableFuture<AvailableSeatsResponse> future = CompletableFuture.supplyAsync(() -> {
			try {
				// Fetch the bus entity to ensure it exists
				Bus bus = busRepository.findById(busId)
						.orElseThrow(() -> new BusException("Bus not found with id: " + busId));

				// Fetch available seats for the specified bus
				List<SeatResponse> seatResponses = seatRepository.findByBusId(busId).stream()
						.map(this::convertToSeatResponse).collect(Collectors.toList());

				AvailableSeatsResponse response = new AvailableSeatsResponse();
				response.setBusId(busId);
				response.setSeats(seatResponses);

				return response;
			} catch (Exception e) {
				log.error("Error fetching available seats: {}", e.getMessage());
				throw new BusException("Error fetching available seats: " + e.getMessage());
			}
		});

		try {
			return future.get(); // Blocking call to get the result
		} catch (InterruptedException | ExecutionException e) {
			log.error("Error fetching available seats: {}", e.getMessage());
			throw new BusException("Error fetching available seats: " + e.getMessage());
		}
	}

	private SeatResponse convertToSeatResponse(Seat seat) {
		if (seat == null) {
			return null;
		}

		SeatResponse response = new SeatResponse();
		response.setId(seat.getId());
		response.setSeatNumber(seat.getSeatNumber());
		response.setAvailable(seat.isAvailable());
		return response;

	}

	private BusResponse convertToResponse(Bus bus) {
		if (bus == null) {
			return null;
		}

		BusResponse response = new BusResponse();
		response.setId(bus.getId() != null ? bus.getId() : 0L);
		response.setDepartureLocation(bus.getDepartureLocation() != null ? bus.getDepartureLocation() : "Unknown");
		response.setArrivalLocation(bus.getArrivalLocation() != null ? bus.getArrivalLocation() : "Unknown");
		response.setDepartureDate(bus.getDepartureDate());
		response.setBusRoute(bus.getBusRoute() != null ? bus.getBusRoute() : "Not Available");
		response.setSchedule(bus.getSchedule() != null ? bus.getSchedule() : "No Schedule");
		return response;
	}

	private BusRouteResponse convertToResponse(BusRoute route) {
		if (route == null) {
			return null;
		}

		BusRouteResponse response = new BusRouteResponse();
		response.setId(route.getId());
		response.setRouteName(route.getRouteName());
		response.setDepartureLocation(route.getDepartureLocation());
		response.setArrivalLocation(route.getArrivalLocation());
		response.setSchedule(route.getSchedule());
		return response;
	}

}
