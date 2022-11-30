package edu.Binar.challenge.CinemaTicketReservation.converter;

import edu.Binar.challenge.CinemaTicketReservation.dto.CinemaHallDto;
import edu.Binar.challenge.CinemaTicketReservation.model.CinemaHall;
import org.modelmapper.ModelMapper;

public class CinemaHallConverter {

    private CinemaHallConverter() {
        throw new IllegalStateException();
    }

    private static final ModelMapper modelMapper = new ModelMapper();

    public static CinemaHall convertDtoToEntity(CinemaHallDto cinemaHallDto){
        return modelMapper.map(cinemaHallDto, CinemaHall.class);
    }

    public static CinemaHallDto convertEntityToDto(CinemaHall cinemaHall){
        return modelMapper.map(cinemaHall, CinemaHallDto.class);
    }
}
