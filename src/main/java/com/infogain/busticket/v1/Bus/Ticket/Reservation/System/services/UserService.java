package com.infogain.busticket.v1.Bus.Ticket.Reservation.System.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.entities.User;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models.UserRequest;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models.UserResponse;

/**
 * @author Sidhant
 * 
 * Service class to user
 * 
 */
@Service
public interface UserService {

	/**
	 * Creating User in repository
	 * 
	 * @param user
	 * @return
	 */
	public Long saveUser(UserRequest userRequest);
	
	/**
	 * Fetching List of Users
	 * 
	 * @return
	 */

	public List<UserResponse> getAllUsers();

	/**
	 * Fetching User by it's ID
	 * 
	 * @param id
	 * @return
	 */
	public UserResponse getUserById(Long id);
}
