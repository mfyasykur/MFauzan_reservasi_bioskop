package edu.Binar.challenge.CinemaTicketReservation.repository;

import edu.Binar.challenge.CinemaTicketReservation.model.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Data
@RequiredArgsConstructor
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShowRepositoryTest {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private CinemaHallRepository cinemaHallRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void isFoundById() {

        City city = new City(1, "Banyumas", "Purwokerto", "1212");
        cityRepository.save(city);

        Cinema cinema = new Cinema(1, "CGV", 4, city);
        cinemaRepository.save(cinema);

        CinemaHall cinemaHall = new CinemaHall(1, "A", 40, cinema);
        cinemaHallRepository.save(cinemaHall);

        Movie movie = new Movie(1, "Kancil dan Buaya", "Dewasa", 120, "Java", new Date(), "Indonesia", "Thriller", Movie.showingStatus.ON_SHOW);
        movieRepository.save(movie);

        Show show = new Show(1, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now(), cinemaHall, movie);
        showRepository.save(show);

        Boolean actual = showRepository.existsById(1L);
        assertThat(actual).isTrue();
    }
}
