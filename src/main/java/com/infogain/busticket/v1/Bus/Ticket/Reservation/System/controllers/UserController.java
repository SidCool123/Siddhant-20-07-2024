package com.infogain.busticket.v1.Bus.Ticket.Reservation.System.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.constants.UserConstants;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models.UserRequest;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models.UserResponse;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.services.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Siddhant
 * 
 *         Controller for User class
 * 
 */
@Slf4j
@RestController
@RequestMapping(UserConstants.USER_MAPPING)
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	/**
	 * POST API to create user
	 * 
	 * @param userRequest
	 * @return
	 */
	@PostMapping(UserConstants.USER_POST_API)
	public ResponseEntity<Long> createUser(@RequestBody UserRequest userRequest) {
		try {
			log.info(" UserController->createUser -> User starting to create");
			Long userID = userService.saveUser(userRequest);
			log.info("User added successfully");
			return new ResponseEntity<>(userID, HttpStatus.CREATED);
		} catch (Exception e) {
			log.info("Getting error in creating user");
			log.error("Error creating user: {}", e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	/**
	 * GET API to fetch all list of Users
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<UserResponse>> getAllUsers() {
		try {
			List<UserResponse> users = userService.getAllUsers();
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			// Log error and return INTERNAL_SERVER_ERROR status
			log.error("Error fetching all users: {}", e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * GET API to fetch user by it's ID
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
		try {
			UserResponse user = userService.getUserById(id);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			// Log error and return NOT_FOUND status
			log.error("Error fetching user with id {}: {}", id, e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
