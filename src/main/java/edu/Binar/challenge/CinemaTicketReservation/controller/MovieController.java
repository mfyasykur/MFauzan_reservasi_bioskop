package edu.Binar.challenge.CinemaTicketReservation.controller;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.Movie;
import edu.Binar.challenge.CinemaTicketReservation.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mycinema-v1")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/movies/")
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/movies/{movieId}")
    public ResponseEntity<Movie> getMovieById(@PathVariable(value = "movieId") Long movieId)
        throws ResourceNotFoundException {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found for this id :: " + movieId));

        return ResponseEntity.ok().body(movie);
    }

    @PostMapping("/movies")
    public Movie createMovie(@Valid @RequestBody Movie movie){
        return movieRepository.save(movie);
    }

    @PutMapping("/movies/{movieId}")
    public ResponseEntity<Movie> updateMovie(@PathVariable(value = "movieId") Long movieId, @Valid @RequestBody Movie movieDetails)
        throws ResourceNotFoundException {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found for this id :: " + movieId));

        movie.setTitle(movieDetails.getTitle());
        movie.setDescription(movieDetails.getDescription());
        movie.setDuration(movieDetails.getDuration());
        movie.setLanguage(movieDetails.getLanguage());
        movie.setReleaseDate(movieDetails.getReleaseDate());
        movie.setCountry(movieDetails.getCountry());
        movie.setGenre(movieDetails.getGenre());
        movie.setStatus(movieDetails.getStatus());
        final Movie updatedMovie = movieRepository.save(movie);

        return ResponseEntity.ok(updatedMovie);
    }

    @DeleteMapping("/movies/{movieId}")
    public Map<String, Boolean> deleteMovie(@PathVariable(value = "movieId") Long movieId)
        throws ResourceNotFoundException {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found for this id :: " + movieId));

        movieRepository.delete(movie);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
