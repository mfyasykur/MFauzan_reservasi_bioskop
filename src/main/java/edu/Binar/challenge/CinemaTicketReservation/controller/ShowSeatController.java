package edu.Binar.challenge.CinemaTicketReservation.controller;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.ShowSeat;
import edu.Binar.challenge.CinemaTicketReservation.repository.ShowSeatRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@RestController
@RequestMapping("/api/mycinema-v1")
public class ShowSeatController {

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @GetMapping("/showSeats/")
    public List<ShowSeat> getAllShowSeats() {
        return showSeatRepository.findAll();
    }

    public static final String MESSAGE = "ShowSeat not found for this id :: ";

    @GetMapping("/showSeats/{showSeatId}")
    public ResponseEntity<ShowSeat> getShowSeatById(@PathVariable(value = "showSeatId") Long showSeatId)
            throws ResourceNotFoundException {
        ShowSeat showSeat = showSeatRepository.findById(showSeatId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + showSeatId));

        return ResponseEntity.ok().body(showSeat);
    }

    @PostMapping("/showSeats")
    public ShowSeat createShowSeat(@Valid @RequestBody ShowSeat showSeat){
        return showSeatRepository.save(showSeat);
    }

    @PutMapping("/showSeats/{showSeatId}")
    public ResponseEntity<ShowSeat> updateShowSeat(@PathVariable(value = "showSeatId") Long showSeatId, @Valid @RequestBody ShowSeat showSeatDetails)
            throws ResourceNotFoundException {
        ShowSeat showSeat = showSeatRepository.findById(showSeatId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + showSeatId));

        showSeat.setStatus(showSeatDetails.getStatus());
        showSeat.setPrice(showSeatDetails.getPrice());
        showSeat.setCinemaSeat(showSeatDetails.getCinemaSeat());
        showSeat.setShow(showSeatDetails.getShow());
        showSeat.setBooking(showSeatDetails.getBooking());
        final ShowSeat updatedShowSeat = showSeatRepository.save(showSeat);

        return ResponseEntity.ok(updatedShowSeat);
    }

    @DeleteMapping("/showSeats/{showSeatId}")
    public Map<String, Boolean> deleteShowSeat(@PathVariable(value = "showSeatId") Long showSeatId)
            throws ResourceNotFoundException {
        ShowSeat showSeat = showSeatRepository.findById(showSeatId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + showSeatId));

        showSeatRepository.delete(showSeat);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
