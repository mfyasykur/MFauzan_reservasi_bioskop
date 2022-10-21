package edu.Binar.challenge.CinemaTicketReservation.controller;

import edu.Binar.challenge.CinemaTicketReservation.converter.UserConverter;
import edu.Binar.challenge.CinemaTicketReservation.dto.UserDto;
import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.User;
import edu.Binar.challenge.CinemaTicketReservation.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin(maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/mycinema-v1")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @CrossOrigin(origins = "http://mfauzan-reservasibioskop-production.up.railway.app", allowedHeaders = {"Requestor-Type", "Authorization"}, exposedHeaders = "X-Get-Header")
    @GetMapping("/users/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Get-Header", "ExampleHeader");

        List<UserDto> body = userService.getAllUsers().stream().map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok().headers(headers).body(body);
    }
//    public List<UserDto> getAllUsers() {
//        return userService.getAllUsers().stream().map(user -> modelMapper.map(user, UserDto.class))
//                .collect(Collectors.toList());
//    }

//    @GetMapping("/users/")
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(value = "userId") Long userId) throws ResourceNotFoundException {
        User user = userService.getUserById(userId);
        UserDto userResponse = UserConverter.convertEntityToDto(user);

        return ResponseEntity.ok().body(userResponse);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        User userRequest = UserConverter.convertDtoToEntity(userDto);
        User user = userService.createUser(userRequest);

        UserDto userResponse = UserConverter.convertEntityToDto(user);

        return new ResponseEntity<UserDto>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(value = "userId") Long userId, @Valid @RequestBody UserDto userDto) throws ResourceNotFoundException {
        User userRequest = UserConverter.convertDtoToEntity(userDto);
        User user = userService.updateUser(userId, userRequest);

        UserDto userResponse = UserConverter.convertEntityToDto(user);

        return ResponseEntity.ok().body(userResponse);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "userId") Long userId) throws ResourceNotFoundException {
        userService.deleteUser(userId);

        return ResponseEntity.ok("User with ID(" + userId + ") deleted successfully");
    }
}
