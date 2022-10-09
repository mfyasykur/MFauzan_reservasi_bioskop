package edu.Binar.challenge.CinemaTicketReservation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(name = "name", length = 64, nullable = false)
    private String name;

    @Column(name = "username", length = 64, nullable = false)
    private String username;

    @Column(name = "email", length = 64, nullable = false)
    private String email;

    @Column(name = "password", length = 20, nullable = false)
    private String password;

    @Column(name = "phone", length = 16, nullable = false)
    private String phone;

    @Override
    public String toString(){
        return "User [userId=" + userId + ", name=" + name + ", username=" + username + ", email=" + email + ", password=" + password + ", phone=" + phone + "]";
    }
}
