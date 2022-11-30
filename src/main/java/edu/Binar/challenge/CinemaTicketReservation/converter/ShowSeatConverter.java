package edu.Binar.challenge.CinemaTicketReservation.converter;

import edu.Binar.challenge.CinemaTicketReservation.dto.ShowSeatDto;
import edu.Binar.challenge.CinemaTicketReservation.model.ShowSeat;
import org.modelmapper.ModelMapper;

public class ShowSeatConverter {

    private ShowSeatConverter() {
        throw new IllegalStateException();
    }

    private static final ModelMapper modelMapper = new ModelMapper();

    public static ShowSeat convertDtoToEntity(ShowSeatDto showSeatDto){
        return modelMapper.map(showSeatDto, ShowSeat.class);
    }

    public static ShowSeatDto convertEntityToDto(ShowSeat showSeat){
        return modelMapper.map(showSeat, ShowSeatDto.class);
    }
}
