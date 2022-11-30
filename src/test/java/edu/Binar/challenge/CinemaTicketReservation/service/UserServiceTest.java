package edu.Binar.challenge.CinemaTicketReservation.service;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.User;
import edu.Binar.challenge.CinemaTicketReservation.repository.UserRepository;
import edu.Binar.challenge.CinemaTicketReservation.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@Transactional
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService = new UserServiceImpl(userRepository);

    @Test
    public void shouldSavedUserSuccessfully() {

        final User user = new User("Bedjo", "bedjo99", "bedjo99@mail.com", "83dj0", "08777777");

        given(userRepository.findByUsername(user.getUsername())).willReturn(Optional.empty());
        given(userRepository.save(user)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        User savedUser = userService.createUser(user);

        assertThat(savedUser).isNotNull();

        verify(userRepository).save(any(User.class));
    }

    @Test
    public void shouldReturnFindAll() {
        List<User> datas = new ArrayList<>();
        datas.add(new User("Bedjo", "bedjo99", "bedjo99@mail.com", "83dj0", "08777777"));
        datas.add(new User("Burhan", "burhan", "burhan@mail.com", "8urh4n", "098765432"));
        datas.add(new User("Udin", "udin", "udin@mail.com", "ud1n", "098789898"));

        given(userRepository.findAll()).willReturn(datas);

        List<User> expected = userService.getAllUsers();

        assertEquals(expected, datas);
    }

    @Test
    public void shouldBeFoundByUserId() throws ResourceNotFoundException {

        final Long userId = 1L;
        final User user = new User("Bedjo", "bedjo99", "bedjo99@mail.com", "83dj0", "08777777");

        given(userRepository.findById(userId)).willReturn(Optional.of(user));

        final Optional<User> expected = Optional.ofNullable(userService.getUserById(userId));

        assertThat(expected).isNotNull();
    }

    @Test
    public void exceptionThrownWhenUpdateByIdFailed() {

        Long userId = 11L;
        User user = new User("Bedjo", "bedjo99", "bedjo99@mail.com", "83dj0", "08777777");

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(userId, user));

        assertEquals("User not found for this id :: " + userId, exception.getMessage());
    }

    @Test
    public void exceptionThrownWhenDeleteByIdFailed() {

        Long userId = 1L;

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(userId));

        assertEquals("User not found for this id :: " + userId, exception.getMessage());
    }
}