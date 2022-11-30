package edu.Binar.challenge.CinemaTicketReservation.repository;

import edu.Binar.challenge.CinemaTicketReservation.model.City;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Data
@RequiredArgsConstructor
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void isFoundById() {

        City city = new City(1, "Banyumas", "Purwokerto", "1212");
        cityRepository.save(city);

        Boolean actual = cityRepository.existsById(1L);
        assertThat(actual).isTrue();
    }
}
