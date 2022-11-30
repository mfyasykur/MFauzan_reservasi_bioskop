package edu.Binar.challenge.CinemaTicketReservation.service;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies();
    Movie getMovieById(Long movieId) throws ResourceNotFoundException;
    Movie createMovie(Movie movie);
    Movie updateMovie(Long movieId, Movie movie) throws ResourceNotFoundException;
    void deleteMovie(Long movieId) throws ResourceNotFoundException;
}
