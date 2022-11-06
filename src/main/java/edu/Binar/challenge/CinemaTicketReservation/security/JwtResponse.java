package edu.Binar.challenge.CinemaTicketReservation.security;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private List<String> roles;

    public JwtResponse(String token, Long id, String name, String username, String email, String phone, List<String> roles) {
        this.token = token;
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.roles = roles;
    }
}
