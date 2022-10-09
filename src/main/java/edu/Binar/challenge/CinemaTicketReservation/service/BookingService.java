package edu.Binar.challenge.CinemaTicketReservation.service;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {

    List<Booking> getAllBookings();
    Booking getBookingById(Long bookingId) throws ResourceNotFoundException;
    Booking createBooking(Booking booking);
    Booking updateBooking(Long bookingId, Booking booking) throws ResourceNotFoundException;
    void deleteBooking(Long bookingId) throws ResourceNotFoundException;
}
