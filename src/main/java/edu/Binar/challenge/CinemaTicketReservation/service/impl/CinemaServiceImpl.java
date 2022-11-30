package edu.Binar.challenge.CinemaTicketReservation.service.impl;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.Cinema;
import edu.Binar.challenge.CinemaTicketReservation.repository.CinemaRepository;
import edu.Binar.challenge.CinemaTicketReservation.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public List<Cinema> getAllCinemas(){
        return cinemaRepository.findAll();
    }

    private static final String MESSAGE = "CinemaHall not found for this id :: ";

    @Override
    public Cinema getCinemaById(Long cinemaId) throws ResourceNotFoundException {
        Cinema cinema = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + cinemaId));

        return cinemaRepository.save(cinema);
    }

    @Override
    public Cinema createCinema(Cinema cinema){
        return cinemaRepository.save(cinema);
    }

    @Override
    public Cinema updateCinema(Long cinemaId, Cinema cinemaDetails) throws ResourceNotFoundException {
        Cinema cinema = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + cinemaId));

        cinema.setName(cinemaDetails.getName());
        cinema.setTotalCinemaHalls(cinemaDetails.getTotalCinemaHalls());
        cinema.setCity(cinemaDetails.getCity());

        return cinemaRepository.save(cinema);
    }

    @Override
    public void deleteCinema(Long cinemaId) throws ResourceNotFoundException {
        Cinema cinema = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + cinemaId));

        cinemaRepository.delete(cinema);
    }
}
