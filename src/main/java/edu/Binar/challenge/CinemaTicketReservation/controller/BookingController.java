package edu.Binar.challenge.CinemaTicketReservation.controller;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.Booking;
import edu.Binar.challenge.CinemaTicketReservation.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mycinema-v1")
public class BookingController {
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @GetMapping("/booking/")
    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable(value = "bookingId") Long bookingId)
            throws ResourceNotFoundException {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found for this id :: " + bookingId));

        return ResponseEntity.ok().body(booking);
    }

    @PostMapping("/booking")
    public Booking createBooking(@Valid @RequestBody Booking booking){
        return bookingRepository.save(booking);
    }

    @PutMapping("/booking/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@PathVariable(value = "bookingId") Long bookingId, @Valid @RequestBody Booking bookingDetails)
            throws ResourceNotFoundException {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found for this id :: " + bookingId));

        booking.setNumberOfSeats(bookingDetails.getNumberOfSeats());
        booking.setTimeStamp(bookingDetails.getTimeStamp());
        booking.setStatus(bookingDetails.getStatus());
        booking.setUser(bookingDetails.getUser());
        booking.setShow(bookingDetails.getShow());
        final Booking updatedBooking = bookingRepository.save(booking);

        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/booking/{bookingId}")
    public Map<String, Boolean> deleteBooking(@PathVariable(value = "bookingId") Long bookingId)
            throws ResourceNotFoundException {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found for this id :: " + bookingId));

        bookingRepository.delete(booking);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
