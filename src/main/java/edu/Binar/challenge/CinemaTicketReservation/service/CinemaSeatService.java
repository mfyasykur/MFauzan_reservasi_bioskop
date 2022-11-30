package edu.Binar.challenge.CinemaTicketReservation.service;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.CinemaSeat;

import java.util.List;

public interface CinemaSeatService {

    List<CinemaSeat> getAllCinemaSeats();
    CinemaSeat getCinemaSeatById(Long cinemaSeatId) throws ResourceNotFoundException;
    CinemaSeat createCinemaSeat(CinemaSeat cinemaSeat);
    CinemaSeat updateCinemaSeat(Long cinemaSeatId, CinemaSeat cinemaSeat) throws ResourceNotFoundException;
    void deleteCinemaSeat(Long cinemaSeatId) throws ResourceNotFoundException;
}
