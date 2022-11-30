package edu.Binar.challenge.CinemaTicketReservation.converter;

import edu.Binar.challenge.CinemaTicketReservation.dto.CinemaDto;
import edu.Binar.challenge.CinemaTicketReservation.model.Cinema;
import org.modelmapper.ModelMapper;

public class CinemaConverter {

    private CinemaConverter() {
        throw new IllegalStateException();
    }

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Cinema convertDtoToEntity(CinemaDto dto){
        return modelMapper.map(dto, Cinema.class);
    }

    public static CinemaDto convertEntityToDto(Cinema entity){
        return modelMapper.map(entity, CinemaDto.class);
    }
}
