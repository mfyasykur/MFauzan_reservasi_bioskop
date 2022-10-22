package edu.Binar.challenge.CinemaTicketReservation.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@NoArgsConstructor
@RestController
@RequestMapping("/api/mycinema-v1")
public class AuthController {

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser() {

        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser() {

        return null;
    }
}
