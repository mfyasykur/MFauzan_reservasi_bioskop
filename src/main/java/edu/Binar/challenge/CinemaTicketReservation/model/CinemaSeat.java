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
@Table(name = "cinemaSeats")
public class CinemaSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cinemaSeatId;

    @Column(name = "seatNumber")
    private int seatNumber;

    public enum seatType {
        regular,
        sweetBox
    }

    @Enumerated
    @Column(name = "type")
    private seatType type;

    @ManyToOne
    @JoinColumn(name = "cinemaHallId")
    private CinemaHall cinemaHall;

    @Override
    public String toString(){
        return "CinemaSeat [cinemaSeatId=" + cinemaSeatId + ", seatNumber=" + seatNumber + ", type=" + type + ", cinemaHallId=" + cinemaHall + "]";
    }
}
