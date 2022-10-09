package edu.Binar.challenge.CinemaTicketReservation.controller;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.Booking;
import edu.Binar.challenge.CinemaTicketReservation.repository.BookingRepository;
import edu.Binar.challenge.CinemaTicketReservation.service.BookingService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api/mycinema-v1")
public class BookingController {
    
    @Autowired
    private BookingService bookingService;
    
    @GetMapping("/bookings/")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable(value = "bookingId") Long bookingId)
            throws ResourceNotFoundException {

        return ResponseEntity.ok().body(bookingService.getBookingById(bookingId));
    }

    @PostMapping("/booking")
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody Booking booking){

        return ResponseEntity.ok().body(bookingService.createBooking(booking));
    }

    @PutMapping("/booking/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@PathVariable(value = "bookingId") Long bookingId, @Valid @RequestBody Booking booking) throws ResourceNotFoundException {

        return ResponseEntity.ok().body(bookingService.updateBooking(bookingId, booking));
    }

    @DeleteMapping("/booking/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable(value = "bokingId") Long bookingId) throws ResourceNotFoundException {
        bookingService.deleteBooking(bookingId);

        return ResponseEntity.ok().body("Booking with ID(" + bookingId + ") deleted successfully");
    }
}
