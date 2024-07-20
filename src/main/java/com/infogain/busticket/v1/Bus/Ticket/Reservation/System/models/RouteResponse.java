package com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models;

import lombok.Data;

@Data
public class RouteResponse {
	
	    private Long id;
	    private String routeName;
	    private String departureLocation;
	    private String arrivalLocation;
	    private String schedule;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getRouteName() {
			return routeName;
		}
		public void setRouteName(String routeName) {
			this.routeName = routeName;
		}
		public String getDepartureLocation() {
			return departureLocation;
		}
		public void setDepartureLocation(String departureLocation) {
			this.departureLocation = departureLocation;
		}
		public String getArrivalLocation() {
			return arrivalLocation;
		}
		public void setArrivalLocation(String arrivalLocation) {
			this.arrivalLocation = arrivalLocation;
		}
		public String getSchedule() {
			return schedule;
		}
		public void setSchedule(String schedule) {
			this.schedule = schedule;
		}
	    
}
