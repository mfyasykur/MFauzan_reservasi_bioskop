package edu.Binar.challenge.CinemaTicketReservation.service;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.Show;

import java.util.List;

public interface ShowService {

    List<Show> getAllShows();
    Show getShowById(Long showId) throws ResourceNotFoundException;
    Show createShow(Show show);
    Show updateShow(Long showId, Show show) throws ResourceNotFoundException;
    void deleteShow(Long showId) throws ResourceNotFoundException;
}
