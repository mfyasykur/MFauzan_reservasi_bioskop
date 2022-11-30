package edu.Binar.challenge.CinemaTicketReservation.repository;

import edu.Binar.challenge.CinemaTicketReservation.model.Movie;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Data
@RequiredArgsConstructor
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void isFoundById() {

        Movie movie = new Movie(1, "Kancil dan Buaya", "Dewasa", 120, "Java", new Date(), "Indonesia", "Thriller", Movie.showingStatus.ON_SHOW);
        movieRepository.save(movie);

        Boolean actual = movieRepository.existsById(1L);
        assertThat(actual).isTrue();
    }
}
