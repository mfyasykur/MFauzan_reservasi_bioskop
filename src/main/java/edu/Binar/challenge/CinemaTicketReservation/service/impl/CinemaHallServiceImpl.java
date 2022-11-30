package edu.Binar.challenge.CinemaTicketReservation.service.impl;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.CinemaHall;
import edu.Binar.challenge.CinemaTicketReservation.repository.CinemaHallRepository;
import edu.Binar.challenge.CinemaTicketReservation.service.CinemaHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {

    @Autowired
    private CinemaHallRepository cinemaHallRepository;

    @Override
    public List<CinemaHall> getAllCinemaHalls(){
        return cinemaHallRepository.findAll();
    }

    private static final String MESSAGE = "CinemaHall not found for this id :: ";

    @Override
    public CinemaHall getCinemaHallById(Long cinemaHallId) throws ResourceNotFoundException {
        CinemaHall cinemaHall = cinemaHallRepository.findById(cinemaHallId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + cinemaHallId));

        return cinemaHallRepository.save(cinemaHall);
    }

    @Override
    public CinemaHall createCinemaHall(CinemaHall cinemaHall){
        return cinemaHallRepository.save(cinemaHall);
    }

    @Override
    public CinemaHall updateCinemaHall(Long cinemaHallId, CinemaHall cinemaHallDetails) throws ResourceNotFoundException {
        CinemaHall cinemaHall = cinemaHallRepository.findById(cinemaHallId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + cinemaHallId));

        cinemaHall.setName(cinemaHallDetails.getName());
        cinemaHall.setTotalSeats(cinemaHallDetails.getTotalSeats());
        cinemaHall.setCinema(cinemaHallDetails.getCinema());

        return cinemaHallRepository.save(cinemaHall);
    }

    @Override
    public void deleteCinemaHall(Long cinemaHallId) throws ResourceNotFoundException {
        CinemaHall cinemaHall = cinemaHallRepository.findById(cinemaHallId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + cinemaHallId));

        cinemaHallRepository.delete(cinemaHall);
    }
}
