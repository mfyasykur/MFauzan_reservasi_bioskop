package edu.Binar.challenge.CinemaTicketReservation.controller;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.CinemaSeat;
import edu.Binar.challenge.CinemaTicketReservation.repository.CinemaSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cinema-v1")
public class CinemaSeatController {

    @Autowired
    private CinemaSeatRepository cinemaSeatRepository;

    @GetMapping("/cinemaSeats/")
    public List<CinemaSeat> getAllCinemaSeats() {
        return cinemaSeatRepository.findAll();
    }

    @GetMapping("/cinemaSeats/{cinemaSeatId}")
    public ResponseEntity<CinemaSeat> getCinemaSeatById(@PathVariable(value = "cinemaSeatId") Long cinemaSeatId)
            throws ResourceNotFoundException {
        CinemaSeat cinemaSeat = cinemaSeatRepository.findById(cinemaSeatId)
                .orElseThrow(() -> new ResourceNotFoundException("CinemaSeat not found for this id :: " + cinemaSeatId));

        return ResponseEntity.ok().body(cinemaSeat);
    }

    @PostMapping("/cinemaSeats")
    public CinemaSeat createCinemaSeat(@Valid @RequestBody CinemaSeat cinemaSeat){
        return cinemaSeatRepository.save(cinemaSeat);
    }

    @PutMapping("/cinemaSeats/{cinemaSeatId}")
    public ResponseEntity<CinemaSeat> updateCinemaSeat(@PathVariable(value = "cinemaSeatId") Long cinemaSeatId, @Valid @RequestBody CinemaSeat cinemaSeatDetails)
            throws ResourceNotFoundException {
        CinemaSeat cinemaSeat = cinemaSeatRepository.findById(cinemaSeatId)
                .orElseThrow(() -> new ResourceNotFoundException("CinemaSeat not found for this id :: " + cinemaSeatId));

        cinemaSeat.setSeatNumber(cinemaSeatDetails.getSeatNumber());
        cinemaSeat.setType(cinemaSeatDetails.getType());
        cinemaSeat.setCinemaHall(cinemaSeatDetails.getCinemaHall());
        final CinemaSeat updatedCinemaSeat = cinemaSeatRepository.save(cinemaSeat);

        return ResponseEntity.ok(updatedCinemaSeat);
    }

    @DeleteMapping("/cinemaSeats/{cinemaSeatId}")
    public Map<String, Boolean> deleteCinemaSeat(@PathVariable(value = "cinemaSeatId") Long cinemaSeatId)
            throws ResourceNotFoundException {
        CinemaSeat cinemaSeat = cinemaSeatRepository.findById(cinemaSeatId)
                .orElseThrow(() -> new ResourceNotFoundException("CinemaSeat not found for this id :: " + cinemaSeatId));

        cinemaSeatRepository.delete(cinemaSeat);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
