package edu.Binar.challenge.CinemaTicketReservation.service;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.CinemaHall;

import java.util.List;

public interface CinemaHallService {

    List<CinemaHall> getAllCinemaHalls();
    CinemaHall getCinemaHallById(Long cinemaHallId) throws ResourceNotFoundException;
    CinemaHall createCinemaHall(CinemaHall cinemaHall);
    CinemaHall updateCinemaHall(Long cinemaHallId, CinemaHall cinemaHall) throws ResourceNotFoundException;
    void deleteCinemaHall(Long cinemaHallId) throws ResourceNotFoundException;
}
