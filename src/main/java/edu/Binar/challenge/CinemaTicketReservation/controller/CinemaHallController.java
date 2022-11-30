package edu.Binar.challenge.CinemaTicketReservation.controller;

import edu.Binar.challenge.CinemaTicketReservation.converter.CinemaHallConverter;
import edu.Binar.challenge.CinemaTicketReservation.dto.CinemaHallDto;
import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.CinemaHall;
import edu.Binar.challenge.CinemaTicketReservation.service.CinemaHallService;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Setter
@RestController
@RequestMapping("/api/mycinema-v1")
public class CinemaHallController {

    @Autowired
    private CinemaHallService cinemaHallService;

    @GetMapping("/cinemaHalls/")
    public List<CinemaHallDto> getAllCinemaHalls() {
        return cinemaHallService.getAllCinemaHalls().stream().map(cinemaHall -> new ModelMapper().map(cinemaHall, CinemaHallDto.class))
                .toList();
    }

    @GetMapping("/cinemaHall/{cinemaHallId}")
    public ResponseEntity<CinemaHallDto> getCinemaHallById(@PathVariable(value = "cinemaHallId") Long cinemaHallId)
            throws ResourceNotFoundException {

        CinemaHall cinemaHall = cinemaHallService.getCinemaHallById(cinemaHallId);
        CinemaHallDto cinemaHallResponse = CinemaHallConverter.convertEntityToDto(cinemaHall);

        return ResponseEntity.ok().body(cinemaHallResponse);
    }

    @PostMapping("/cinemaHall")
    public ResponseEntity<CinemaHallDto> createCinemaHall(@Valid @RequestBody CinemaHallDto cinemaHallDto){

        CinemaHall cinemaHallRequest = CinemaHallConverter.convertDtoToEntity(cinemaHallDto);
        CinemaHall cinemaHall = cinemaHallService.createCinemaHall(cinemaHallRequest);
        CinemaHallDto cinemaHallResponse = CinemaHallConverter.convertEntityToDto(cinemaHall);

        return new ResponseEntity<>(cinemaHallResponse, HttpStatus.CREATED);
    }

    @PutMapping("/cinemaHall/{cinemaHallId}")
    public ResponseEntity<CinemaHallDto> updateCinemaHall(@PathVariable(value = "cinemaHallId") Long cinemaHallId, @Valid @RequestBody CinemaHallDto cinemaHallDto) throws ResourceNotFoundException {

        CinemaHall cinemaHallRequest = CinemaHallConverter.convertDtoToEntity(cinemaHallDto);
        CinemaHall cinemaHall = cinemaHallService.updateCinemaHall(cinemaHallId, cinemaHallRequest);
        CinemaHallDto cinemaHallResponse = CinemaHallConverter.convertEntityToDto(cinemaHall);

        return new ResponseEntity<>(cinemaHallResponse, HttpStatus.OK);
    }

    @DeleteMapping("/cinemaHall/{cinemaHallId}")
    public ResponseEntity<String> deleteCinemaHall(@PathVariable(value = "cinemaHallId") Long cinemaHallId) throws ResourceNotFoundException {
        cinemaHallService.deleteCinemaHall(cinemaHallId);

        return ResponseEntity.ok().body("CinemaHall with ID(" + cinemaHallId + ") deleted successfully");
    }
}
