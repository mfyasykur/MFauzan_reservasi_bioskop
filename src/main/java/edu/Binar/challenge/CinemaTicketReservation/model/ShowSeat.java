package edu.Binar.challenge.CinemaTicketReservation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "showSeats")
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long showSeatId;

    public enum showSeatStatus {
        available,
        sold
    }

    @Enumerated
    @Column(name = "status")
    private showSeatStatus status;

    @Column(name = "price")
    private Number price;

    @ManyToOne
    @JoinColumn(name = "cinemaSeatId")
    private CinemaSeat cinemaSeat;

    @ManyToOne
    @JoinColumn(name = "showId")
    private Show show;

    @ManyToOne
    @JoinColumn(name = "bookingId")
    private Booking booking;

    @Override
    public String toString(){
        return "ShowSeat [showSeatId=" + showSeatId + ", status=" + status + ", price=" + price + ", cinemaSeatId=" + cinemaSeat + ", showId=" + show + ", bookingId=" + booking + "]";
    }
}
