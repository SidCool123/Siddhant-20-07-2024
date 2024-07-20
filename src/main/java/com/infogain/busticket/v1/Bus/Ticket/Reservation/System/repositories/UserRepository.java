package com.infogain.busticket.v1.Bus.Ticket.Reservation.System.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infogain.busticket.v1.Bus.Ticket.Reservation.System.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
