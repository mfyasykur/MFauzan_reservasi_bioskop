package edu.Binar.challenge.CinemaTicketReservation.service;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.ShowSeat;

import java.util.List;

public interface ShowSeatService {

    List<ShowSeat> getAllShowSeats();
    ShowSeat getShowSeatById(Long showSeatId) throws ResourceNotFoundException;
    ShowSeat createShowSeat(ShowSeat showSeat);
    ShowSeat updateShowSeat(Long showSeatId, ShowSeat showSeat) throws ResourceNotFoundException;
    void deleteShowSeat(Long showSeatId) throws ResourceNotFoundException;
}
