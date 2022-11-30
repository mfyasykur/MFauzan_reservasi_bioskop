package edu.Binar.challenge.CinemaTicketReservation.service.impl;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.ShowSeat;
import edu.Binar.challenge.CinemaTicketReservation.repository.ShowSeatRepository;
import edu.Binar.challenge.CinemaTicketReservation.service.ShowSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowSeatServiceImpl implements ShowSeatService {

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Override
    public List<ShowSeat> getAllShowSeats(){
        return showSeatRepository.findAll();
    }

    private static final String MESSAGE = "CinemaHall not found for this id :: ";

    @Override
    public ShowSeat getShowSeatById(Long showSeatId) throws ResourceNotFoundException {
        ShowSeat showSeat = showSeatRepository.findById(showSeatId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + showSeatId));

        return showSeatRepository.save(showSeat);
    }

    @Override
    public ShowSeat createShowSeat(ShowSeat showSeat){
        return showSeatRepository.save(showSeat);
    }

    @Override
    public ShowSeat updateShowSeat(Long showSeatId, ShowSeat showSeatDetails) throws ResourceNotFoundException {
        ShowSeat showSeat = showSeatRepository.findById(showSeatId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + showSeatId));

        showSeat.setStatus(showSeatDetails.getStatus());
        showSeat.setPrice(showSeatDetails.getPrice());
        showSeat.setCinemaSeat(showSeatDetails.getCinemaSeat());
        showSeat.setShow(showSeatDetails.getShow());
        showSeat.setBooking(showSeatDetails.getBooking());

        return showSeatRepository.save(showSeat);
    }

    @Override
    public void deleteShowSeat(Long showSeatId) throws ResourceNotFoundException {
        ShowSeat showSeat = showSeatRepository.findById(showSeatId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + showSeatId));

        showSeatRepository.delete(showSeat);
    }
}
