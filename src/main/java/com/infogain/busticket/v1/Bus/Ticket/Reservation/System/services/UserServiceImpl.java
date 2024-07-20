package com.infogain.busticket.v1.Bus.Ticket.Reservation.System.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.constants.UserConstants;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.controllers.UserController;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.entities.User;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.exceptions.UserException;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models.UserRequest;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.models.UserResponse;
import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Siddhant
 * 
 *         Service Implementation class to User
 */

@Slf4j
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserRepository userRepository;

	@Override
	public Long saveUser(UserRequest userRequest) {
		if (userRequest == null) {
			log.error("UserServiceImpl -> saveUser -> UserRequest is null");
			throw new UserException(UserConstants.USER_POST_API_EXCEPTION_MESSAGE);
		}

		// Running threads to save user in repository
		var saveUserFuture = CompletableFuture.supplyAsync(() -> {
			try {
				User user = new User();
				user.setName(userRequest.getName());
				user.setEmail(userRequest.getEmail());
				user.setPassword(userRequest.getPassword());
				log.info("UserServiceImpl -> saveUser -> Saving User in repository");
				return userRepository.save(user);
			} catch (Exception e) {
				throw new UserException("Error saving user: " + e.getMessage());
			}
		});

		// returning UserResponse that was just created
		try {
			Long userID = saveUserFuture.get().getId();
			log.info("UserServiceImpl -> saveUser -> User has beend created with " + userID);
			return userID;

		} catch (Exception e) {
			throw new UserException("Error saving user: " + e.getMessage());
		}
	}

	public List<UserResponse> getAllUsers() {
		try {
			log.info("Fetching all users from repository");
			CompletableFuture<List<UserResponse>> future = CompletableFuture.supplyAsync(() -> {
				try {
					return userRepository.findAll().stream().map(this::convertToResponse).collect(Collectors.toList());
				} catch (Exception e) {
					log.error("Error fetching users: {}", e.getMessage());
					throw new UserException("Error fetching users: " + e.getMessage());
				}
			});
			return future.get(); // Blocking call to get the result
		} catch (InterruptedException | ExecutionException e) {
			log.error("Error fetching all users: {}", e.getMessage());
			throw new UserException("Error fetching all users: " + e.getMessage());
		}
	}

	public UserResponse getUserById(Long id) {
		try {
			log.info("Fetching user with id: {}", id);
			CompletableFuture<UserResponse> future = CompletableFuture.supplyAsync(() -> {
				try {
					User user = userRepository.findById(id)
							.orElseThrow(() -> new UserException("User not found with id: " + id));
					return convertToResponse(user);
				} catch (Exception e) {
					log.error("Error fetching user with id {}: {}", id, e.getMessage());
					throw new UserException("Error fetching user: " + e.getMessage());
				}
			});
			return future.get(); // Blocking call to get the result
		} catch (Exception e) {
			log.error("Error fetching user with id {}: {}", id, e.getMessage());
			throw new UserException("Error fetching user with id: " + e.getMessage());
		}
	}

	private UserResponse convertToResponse(User user) {
		UserResponse userResponse = new UserResponse();
		userResponse.setId(user.getId());
		userResponse.setName(user.getName());
		userResponse.setEmail(user.getEmail());
		return userResponse;
	}

}
