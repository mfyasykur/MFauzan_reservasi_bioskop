package edu.Binar.challenge.CinemaTicketReservation.service.impl;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.Booking;
import edu.Binar.challenge.CinemaTicketReservation.repository.BookingRepository;
import edu.Binar.challenge.CinemaTicketReservation.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

    private static final String MESSAGE = "Booking not found for this id :: ";

    @Override
    public Booking getBookingById(Long bookingId) throws ResourceNotFoundException {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + bookingId));

        return bookingRepository.save(booking);
    }

    @Override
    public Booking createBooking(Booking booking){
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Long bookingId, Booking bookingDetails) throws ResourceNotFoundException {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + bookingId));

        booking.setNumberOfSeats(bookingDetails.getNumberOfSeats());
        booking.setTimeStamp(bookingDetails.getTimeStamp());
        booking.setStatus(bookingDetails.getStatus());
        booking.setUser(bookingDetails.getUser());
        booking.setShow(bookingDetails.getShow());

        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Long bookingId) throws ResourceNotFoundException {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + bookingId));

        bookingRepository.delete(booking);
    }
}
