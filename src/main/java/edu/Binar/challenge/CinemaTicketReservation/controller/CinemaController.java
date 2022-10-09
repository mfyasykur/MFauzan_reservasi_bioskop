package edu.Binar.challenge.CinemaTicketReservation.controller;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.Cinema;
import edu.Binar.challenge.CinemaTicketReservation.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mycinema-v1")
public class CinemaController {

    @Autowired
    private CinemaRepository cinemaRepository;

    @GetMapping("/cinemas/")
    public List<Cinema> getAllCinemas() {
        return cinemaRepository.findAll();
    }

    @GetMapping("/cinemas/{cinemaId}")
    public ResponseEntity<Cinema> getCinemaById(@PathVariable(value = "cinemaId") Long cinemaId)
            throws ResourceNotFoundException {
        Cinema cinema = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cinema not found for this id :: " + cinemaId));

        return ResponseEntity.ok().body(cinema);
    }

    @PostMapping("/cinemas")
    public Cinema createCinema(@Valid @RequestBody Cinema cinema){
        return cinemaRepository.save(cinema);
    }

    @PutMapping("/cinemas/{cinemaId}")
    public ResponseEntity<Cinema> updateCinema(@PathVariable(value = "cinemaId") Long cinemaId, @Valid @RequestBody Cinema cinemaDetails)
            throws ResourceNotFoundException {
        Cinema cinema = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cinema not found for this id :: " + cinemaId));

        cinema.setName(cinemaDetails.getName());
        cinema.setTotalCinemaHalls(cinemaDetails.getTotalCinemaHalls());
        cinema.setCity(cinemaDetails.getCity());
        final Cinema updatedCinema = cinemaRepository.save(cinema);

        return ResponseEntity.ok(updatedCinema);
    }

    @DeleteMapping("/cinemas/{cinemaId}")
    public Map<String, Boolean> deleteCinema(@PathVariable(value = "cinemaId") Long cinemaId)
            throws ResourceNotFoundException {
        Cinema cinema = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cinema not found for this id :: " + cinemaId));

        cinemaRepository.delete(cinema);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
