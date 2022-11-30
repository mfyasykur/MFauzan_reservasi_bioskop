package edu.Binar.challenge.CinemaTicketReservation.service.impl;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.User;
import edu.Binar.challenge.CinemaTicketReservation.repository.UserRepository;
import edu.Binar.challenge.CinemaTicketReservation.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public static final String MESSAGE = "User not found for this id :: ";

    @Override
    public User getUserById(Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + userId));

        return userRepository.save(user);
    }

    @Override
    public User createUser(User user){
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + userId));

        user.setName(userDetails.getName());
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setPhone(userDetails.getPhone());

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + userId));

        userRepository.delete(user);
    }
}
