package edu.Binar.challenge.CinemaTicketReservation.controller;

import edu.Binar.challenge.CinemaTicketReservation.converter.BookingConverter;
import edu.Binar.challenge.CinemaTicketReservation.dto.BookingDto;
import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.Booking;
import edu.Binar.challenge.CinemaTicketReservation.service.BookingService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api/mycinema-v1")
public class BookingController {
    
    @Autowired
    private BookingService bookingService;
    
    @GetMapping("/bookings/")
    public List<BookingDto> getAllBookings() {
        return bookingService.getAllBookings().stream().map(booking -> new ModelMapper().map(booking, BookingDto.class))
                .toList();
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable(value = "bookingId") Long bookingId)
            throws ResourceNotFoundException {

        Booking booking = bookingService.getBookingById(bookingId);
        BookingDto bookingResponse = BookingConverter.convertEntityToDto(booking);

        return ResponseEntity.ok().body(bookingResponse);
    }

    @PostMapping("/booking")
    public ResponseEntity<BookingDto> createBooking(@Valid @RequestBody BookingDto bookingDto){

        Booking bookingRequest = BookingConverter.convertDtoToEntity(bookingDto);
        Booking booking = bookingService.createBooking(bookingRequest);
        BookingDto bookingResponse = BookingConverter.convertEntityToDto(booking);

        return new ResponseEntity<>(bookingResponse, HttpStatus.CREATED);
    }

    @PutMapping("/booking/{bookingId}")
    public ResponseEntity<BookingDto> updateBooking(@PathVariable(value = "bookingId") Long bookingId, @Valid @RequestBody BookingDto bookingDto) throws ResourceNotFoundException {

        Booking bookingRequest = BookingConverter.convertDtoToEntity(bookingDto);
        Booking booking = bookingService.updateBooking(bookingId, bookingRequest);
        BookingDto bookingResponse = BookingConverter.convertEntityToDto(booking);

        return new ResponseEntity<>(bookingResponse, HttpStatus.OK);
    }

    @DeleteMapping("/booking/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable(value = "bookingId") Long bookingId) throws ResourceNotFoundException {
        bookingService.deleteBooking(bookingId);

        return ResponseEntity.ok().body("Booking with ID(" + bookingId + ") deleted successfully");
    }
}
