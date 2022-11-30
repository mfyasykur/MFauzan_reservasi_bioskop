package edu.Binar.challenge.CinemaTicketReservation.converter;

import edu.Binar.challenge.CinemaTicketReservation.dto.CinemaSeatDto;
import edu.Binar.challenge.CinemaTicketReservation.model.CinemaSeat;
import org.modelmapper.ModelMapper;

public class CinemaSeatConverter {

    private CinemaSeatConverter() {
        throw new IllegalStateException();
    }

    private static final ModelMapper modelMapper = new ModelMapper();

    public static CinemaSeat convertDtoToEntity(CinemaSeatDto cinemaSeatDto){
        return modelMapper.map(cinemaSeatDto, CinemaSeat.class);
    }

    public static CinemaSeatDto convertEntityToDto(CinemaSeat cinemaSeat){
        return modelMapper.map(cinemaSeat, CinemaSeatDto.class);
    }
}
