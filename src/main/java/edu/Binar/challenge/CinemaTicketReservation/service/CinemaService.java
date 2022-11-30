package edu.Binar.challenge.CinemaTicketReservation.service;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.Cinema;

import java.util.List;

public interface CinemaService {

    List<Cinema> getAllCinemas();
    Cinema getCinemaById(Long cinemaId) throws ResourceNotFoundException;
    Cinema createCinema(Cinema cinema);
    Cinema updateCinema(Long cinemaId, Cinema cinema) throws ResourceNotFoundException;
    void deleteCinema(Long cinemaId) throws ResourceNotFoundException;
}
