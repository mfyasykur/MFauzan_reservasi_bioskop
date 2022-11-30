package edu.Binar.challenge.CinemaTicketReservation.service.impl;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.Show;
import edu.Binar.challenge.CinemaTicketReservation.repository.ShowRepository;
import edu.Binar.challenge.CinemaTicketReservation.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowServiceImpl implements ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Override
    public List<Show> getAllShows(){
        return showRepository.findAll();
    }

    private static final String MESSAGE = "CinemaHall not found for this id :: ";

    @Override
    public Show getShowById(Long showId) throws ResourceNotFoundException {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + showId));

        return showRepository.save(show);
    }

    @Override
    public Show createShow(Show show){
        return showRepository.save(show);
    }

    @Override
    public Show updateShow(Long showId, Show showDetails) throws ResourceNotFoundException {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + showId));

        show.setDate(showDetails.getDate());
        show.setStartTime(showDetails.getStartTime());
        show.setEndTime(showDetails.getEndTime());
        show.setCinemaHall(showDetails.getCinemaHall());
        show.setMovie(showDetails.getMovie());

        return showRepository.save(show);
    }

    @Override
    public void deleteShow(Long showId) throws ResourceNotFoundException {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + showId));

        showRepository.delete(show);
    }
}
