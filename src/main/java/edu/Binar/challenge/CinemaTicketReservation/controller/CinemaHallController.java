package edu.Binar.challenge.CinemaTicketReservation.controller;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.CinemaHall;
import edu.Binar.challenge.CinemaTicketReservation.repository.CinemaHallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mycinema-v1")
public class CinemaHallController {

    @Autowired
    private CinemaHallRepository cinemaHallRepository;

    @GetMapping("/cinemaHalls/")
    public List<CinemaHall> getAllCinemaHalls() {
        return cinemaHallRepository.findAll();
    }

    @GetMapping("/cinemaHalls/{cinemaHallId}")
    public ResponseEntity<CinemaHall> getCinemaHallById(@PathVariable(value = "cinemaHallId") Long cinemaHallId)
            throws ResourceNotFoundException {
        CinemaHall cinemaHall = cinemaHallRepository.findById(cinemaHallId)
                .orElseThrow(() -> new ResourceNotFoundException("CinemaHall not found for this id :: " + cinemaHallId));

        return ResponseEntity.ok().body(cinemaHall);
    }

    @PostMapping("/cinemaHalls")
    public CinemaHall createCinemaHall(@Valid @RequestBody CinemaHall cinemaHall){
        return cinemaHallRepository.save(cinemaHall);
    }

    @PutMapping("/cinemaHalls/{cinemaHallId}")
    public ResponseEntity<CinemaHall> updateCinemaHall(@PathVariable(value = "cinemaHallId") Long cinemaHallId, @Valid @RequestBody CinemaHall cinemaHallDetails)
            throws ResourceNotFoundException {
        CinemaHall cinemaHall = cinemaHallRepository.findById(cinemaHallId)
                .orElseThrow(() -> new ResourceNotFoundException("CinemaHall not found for this id :: " + cinemaHallId));

        cinemaHall.setName(cinemaHallDetails.getName());
        cinemaHall.setTotalSeats(cinemaHallDetails.getTotalSeats());
        cinemaHall.setCinema(cinemaHallDetails.getCinema());
        final CinemaHall updatedCinemaHall = cinemaHallRepository.save(cinemaHall);

        return ResponseEntity.ok(updatedCinemaHall);
    }

    @DeleteMapping("/cinemaHalls/{cinemaHallId}")
    public Map<String, Boolean> deleteCinemaHall(@PathVariable(value = "cinemaHallId") Long cinemaHallId)
            throws ResourceNotFoundException {
        CinemaHall cinemaHall = cinemaHallRepository.findById(cinemaHallId)
                .orElseThrow(() -> new ResourceNotFoundException("CinemaHall not found for this id :: " + cinemaHallId));

        cinemaHallRepository.delete(cinemaHall);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
