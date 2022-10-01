package edu.Binar.challenge.CinemaTicketReservation.controller;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.Show;
import edu.Binar.challenge.CinemaTicketReservation.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cinema-v1")
public class ShowController {

    @Autowired
    private ShowRepository showRepository;

    @GetMapping("/shows/")
    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    @GetMapping("/shows/{showId}")
    public ResponseEntity<Show> getShowById(@PathVariable(value = "showId") Long showId)
            throws ResourceNotFoundException {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ResourceNotFoundException("Show not found for this id :: " + showId));

        return ResponseEntity.ok().body(show);
    }

    @PostMapping("/shows")
    public Show createShow(@Valid @RequestBody Show show){
        return showRepository.save(show);
    }

    @PutMapping("/shows/{showId}")
    public ResponseEntity<Show> updateShow(@PathVariable(value = "showId") Long showId, @Valid @RequestBody Show showDetails)
            throws ResourceNotFoundException {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ResourceNotFoundException("Show not found for this id :: " + showId));

        show.setDate(showDetails.getDate());
        show.setStartTime(showDetails.getStartTime());
        show.setEndTime(showDetails.getEndTime());
        show.setCinemaHall(showDetails.getCinemaHall());
        show.setMovie(showDetails.getMovie());
        final Show updatedShow = showRepository.save(show);

        return ResponseEntity.ok(updatedShow);
    }

    @DeleteMapping("/shows/{showId}")
    public Map<String, Boolean> deleteShow(@PathVariable(value = "showId") Long showId)
            throws ResourceNotFoundException {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ResourceNotFoundException("Show not found for this id :: " + showId));

        showRepository.delete(show);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
