package edu.Binar.challenge.CinemaTicketReservation.converter;

import edu.Binar.challenge.CinemaTicketReservation.dto.BookingDto;
import edu.Binar.challenge.CinemaTicketReservation.model.Booking;
import org.modelmapper.ModelMapper;

public class BookingConverter {

    private BookingConverter() {
        throw new IllegalStateException();
    }

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Booking convertDtoToEntity(BookingDto bookingDto){
        return modelMapper.map(bookingDto, Booking.class);
    }

    public static BookingDto convertEntityToDto(Booking booking){
        return modelMapper.map(booking, BookingDto.class);
    }
}
