package edu.Binar.challenge.CinemaTicketReservation.converter;

import edu.Binar.challenge.CinemaTicketReservation.dto.ShowDto;
import edu.Binar.challenge.CinemaTicketReservation.model.Show;
import org.modelmapper.ModelMapper;

public class ShowConverter {

    private ShowConverter() {
        throw new IllegalStateException();
    }

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Show convertDtoToEntity(ShowDto showDto){
        return modelMapper.map(showDto, Show.class);
    }

    public static ShowDto convertEntityToDto(Show show){
        return modelMapper.map(show, ShowDto.class);
    }
}
