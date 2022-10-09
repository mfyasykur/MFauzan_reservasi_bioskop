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
@Table(name = "cinemaHalls")
public class CinemaHall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cinemaHallId;

    @Column(name = "name", length = 2, nullable = false)
    private String name;

    @Column(name = "totalSeats", nullable = false)
    private int totalSeats;

    @ManyToOne
    @JoinColumn(name = "cinemaId", nullable = false)
    private Cinema cinema;

    @Override
    public String toString(){
        return "CinemaHall [cinemaHallId=" + cinemaHallId + ", name=" + name + ", totalSeats=" + totalSeats + ", cinemaId=" + cinema + "]";
    }
}
