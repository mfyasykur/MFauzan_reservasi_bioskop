package edu.Binar.challenge.CinemaTicketReservation.repository;

import edu.Binar.challenge.CinemaTicketReservation.model.User;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RequiredArgsConstructor
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private final UserRepository userRepository;

    @Test
    public void isUserExistsByUsername() {

        User user = new User("Bedjo", "bedjo99", "bedjo99@mail.com", "83dj0", "08777777");
        userRepository.save(user);
        Boolean actualResult = userRepository.existsByUsername("bedjo99");
        assertThat(actualResult).isTrue();
    }

    @Test
    public void isUserExistsByEmail() {

        User user1 = new User("Mojo Jojo", "mojojojo", "mojojojo@mail.com", "m0j0j0j0", "085221112");
        userRepository.save(user1);
        Boolean actualResult = userRepository.existsByEmail("mojojojo@mail.com");
        assertThat(actualResult).isTrue();
    }
}
