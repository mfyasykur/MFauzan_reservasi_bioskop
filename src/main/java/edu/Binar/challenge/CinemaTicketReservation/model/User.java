package edu.Binar.challenge.CinemaTicketReservation.model;

import edu.Binar.challenge.CinemaTicketReservation.security.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(name = "name", length = 64, nullable = false)
    private String name;

    @Column(name = "username", length = 64, nullable = false)
    @NotBlank
    private String username;

    @Column(name = "email", length = 64, nullable = false)
    @NotBlank
    @Email
    private String email;

    @Column(name = "password", length = 64, nullable = false)
    @NotBlank
    private String password;

    @Column(name = "phone", length = 16, nullable = false)
    private String phone;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String requestName, String requestUsername, String requestEmail, String requestPassword, String requestPhone) {
        name = requestName;
        username = requestUsername;
        email = requestEmail;
        password = requestPassword;
        phone = requestPhone;
    }

    @Override
    public String toString(){
        return "User [userId=" + userId + ", name=" + name + ", username=" + username + ", email=" + email + ", password=" + password + ", phone=" + phone + "]";
    }
}
