package edu.Binar.challenge.CinemaTicketReservation.service;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> getAllUsers();
    User getUserById(Long userId) throws ResourceNotFoundException;
    User createUser(User user);
    User updateUser(Long userId, User userDetails) throws ResourceNotFoundException;
    void deleteUser(Long userId) throws ResourceNotFoundException;
}
