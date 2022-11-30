package edu.Binar.challenge.CinemaTicketReservation.controller;

import edu.Binar.challenge.CinemaTicketReservation.converter.ShowSeatConverter;
import edu.Binar.challenge.CinemaTicketReservation.dto.ShowSeatDto;
import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.ShowSeat;
import edu.Binar.challenge.CinemaTicketReservation.service.ShowSeatService;
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
public class ShowSeatController {

    @Autowired
    private ShowSeatService showSeatService;

    @GetMapping("/showSeats/")
    public List<ShowSeatDto> getAllShowSeats() {
        return showSeatService.getAllShowSeats().stream().map(showSeat -> new ModelMapper().map(showSeat, ShowSeatDto.class))
                .toList();
    }

    @GetMapping("/showSeat/{showSeatId}")
    public ResponseEntity<ShowSeatDto> getShowSeatById(@PathVariable(value = "showSeatId") Long showSeatId)
            throws ResourceNotFoundException {

        ShowSeat showSeat = showSeatService.getShowSeatById(showSeatId);
        ShowSeatDto showSeatResponse = ShowSeatConverter.convertEntityToDto(showSeat);

        return ResponseEntity.ok().body(showSeatResponse);
    }

    @PostMapping("/showSeat")
    public ResponseEntity<ShowSeatDto> createShowSeat(@Valid @RequestBody ShowSeatDto showSeatDto){

        ShowSeat showSeatRequest = ShowSeatConverter.convertDtoToEntity(showSeatDto);
        ShowSeat showSeat = showSeatService.createShowSeat(showSeatRequest);
        ShowSeatDto showSeatResponse = ShowSeatConverter.convertEntityToDto(showSeat);

        return new ResponseEntity<>(showSeatResponse, HttpStatus.CREATED);
    }

    @PutMapping("/showSeat/{showSeatId}")
    public ResponseEntity<ShowSeatDto> updateShowSeat(@PathVariable(value = "showSeatId") Long showSeatId, @Valid @RequestBody ShowSeatDto showSeatDto) throws ResourceNotFoundException {

        ShowSeat showSeatRequest = ShowSeatConverter.convertDtoToEntity(showSeatDto);
        ShowSeat showSeat = showSeatService.updateShowSeat(showSeatId, showSeatRequest);
        ShowSeatDto showSeatResponse = ShowSeatConverter.convertEntityToDto(showSeat);

        return new ResponseEntity<>(showSeatResponse, HttpStatus.OK);
    }

    @DeleteMapping("/showSeat/{showSeatId}")
    public ResponseEntity<String> deleteShowSeat(@PathVariable(value = "showSeatId") Long showSeatId) throws ResourceNotFoundException {
        showSeatService.deleteShowSeat(showSeatId);

        return ResponseEntity.ok().body("ShowSeat with ID(" + showSeatId + ") deleted successfully");
    }
}
