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

    @Column(name = "seatNumber", nullable = false)
    private int seatNumber;

    public enum seatType {
        REGULAR,
        SWEET_BOX
    }

    @Enumerated
    @Column(name = "type", nullable = false)
    private seatType type;

    @ManyToOne
    @JoinColumn(name = "cinemaHallId", nullable = false)
    private CinemaHall cinemaHall;

    @Override
    public String toString(){
        return "CinemaSeat [cinemaSeatId=" + cinemaSeatId + ", seatNumber=" + seatNumber + ", type=" + type + ", cinemaHallId=" + cinemaHall + "]";
    }
}
