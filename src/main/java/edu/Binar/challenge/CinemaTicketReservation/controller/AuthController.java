package edu.Binar.challenge.CinemaTicketReservation.controller;

import edu.Binar.challenge.CinemaTicketReservation.dto.LoginRequest;
import edu.Binar.challenge.CinemaTicketReservation.dto.MessageResponse;
import edu.Binar.challenge.CinemaTicketReservation.dto.SignupRequest;
import edu.Binar.challenge.CinemaTicketReservation.model.User;
import edu.Binar.challenge.CinemaTicketReservation.repository.UserRepository;
import edu.Binar.challenge.CinemaTicketReservation.security.JwtResponse;
import edu.Binar.challenge.CinemaTicketReservation.security.JwtUtils;
import edu.Binar.challenge.CinemaTicketReservation.security.Role;
import edu.Binar.challenge.CinemaTicketReservation.security.RoleRepository;
import edu.Binar.challenge.CinemaTicketReservation.service.impl.UserDetailsImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/mycinema-v1/auth")
@Tag(name = "Authentication", description = "The Authentication API. Contains SignUp and Login")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getName(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getPhone(),
                roles
        ));
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByUsername(signupRequest.getUsername()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (Boolean.TRUE.equals(userRepository.existsByEmail(signupRequest.getEmail()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(
                signupRequest.getName(),
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()),
                signupRequest.getPhone()
        );

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();
        final String errorMessage = "Error: Role is not found.";

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(Role.ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException(errorMessage));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if (role.equals("admin")) {
                    Role adminRole = roleRepository.findByName(Role.ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException(errorMessage));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName(Role.ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException(errorMessage));
                    roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
