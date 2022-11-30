package edu.Binar.challenge.CinemaTicketReservation.service.impl;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.Movie;
import edu.Binar.challenge.CinemaTicketReservation.repository.MovieRepository;
import edu.Binar.challenge.CinemaTicketReservation.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    private static final String MESSAGE = "CinemaHall not found for this id :: ";

    @Override
    public Movie getMovieById(Long movieId) throws ResourceNotFoundException {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + movieId));

        return movieRepository.save(movie);
    }

    @Override
    public Movie createMovie(Movie movie){
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Long movieId, Movie movieDetails) throws ResourceNotFoundException {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + movieId));

        movie.setTitle(movieDetails.getTitle());
        movie.setDescription(movieDetails.getDescription());
        movie.setDuration(movieDetails.getDuration());
        movie.setLanguage(movieDetails.getLanguage());
        movie.setReleaseDate(movieDetails.getReleaseDate());
        movie.setCountry(movieDetails.getCountry());
        movie.setGenre(movieDetails.getGenre());
        movie.setStatus(movieDetails.getStatus());

        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Long movieId) throws ResourceNotFoundException {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + movieId));

        movieRepository.delete(movie);
    }
}
