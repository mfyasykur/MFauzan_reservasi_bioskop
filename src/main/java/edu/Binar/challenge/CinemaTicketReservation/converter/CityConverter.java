package edu.Binar.challenge.CinemaTicketReservation.converter;

import edu.Binar.challenge.CinemaTicketReservation.dto.CityDto;
import edu.Binar.challenge.CinemaTicketReservation.model.City;
import org.modelmapper.ModelMapper;

public class CityConverter {

    private CityConverter() {
        throw new IllegalStateException();
    }

    private static final ModelMapper modelMapper = new ModelMapper();

    public static City convertDtoToEntity(CityDto cityDto){
        return modelMapper.map(cityDto, City.class);
    }

    public static CityDto convertEntityToDto(City city){
        return modelMapper.map(city, CityDto.class);
    }
}
