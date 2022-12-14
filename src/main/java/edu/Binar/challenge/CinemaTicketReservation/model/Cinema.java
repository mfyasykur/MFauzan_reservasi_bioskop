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
@Table(name = "cinemas")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cinemaId;

    @Column(name = "name", length = 64, nullable = false)
    private String name;

    @Column(name = "totalCinemaHalls", nullable = false)
    private int totalCinemaHalls;

    @ManyToOne
    @JoinColumn(name = "cityId", nullable = false)
    private City city;

    @Override
    public String toString(){
        return "Cinema [cinemaId=" + cinemaId + ", name=" + name + ", totalCinemaHalls=" + totalCinemaHalls + ", cityId=" + city + "]";
    }
}
