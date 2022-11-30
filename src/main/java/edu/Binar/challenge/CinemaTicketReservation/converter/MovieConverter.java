package edu.Binar.challenge.CinemaTicketReservation.converter;

import edu.Binar.challenge.CinemaTicketReservation.dto.MovieDto;
import edu.Binar.challenge.CinemaTicketReservation.model.Movie;
import org.modelmapper.ModelMapper;

public class MovieConverter {

    private MovieConverter() {
        throw new IllegalStateException();
    }

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Movie convertDtoToEntity(MovieDto movieDto){
        return modelMapper.map(movieDto, Movie.class);
    }

    public static MovieDto convertEntityToDto(Movie movie){
        return modelMapper.map(movie, MovieDto.class);
    }
}
