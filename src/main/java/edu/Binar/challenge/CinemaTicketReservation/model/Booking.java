package edu.Binar.challenge.CinemaTicketReservation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;

    @Column(name = "numberOfSeats")
    private int numberOfSeats;

    @Column(name = "timeStamp")
    private ZonedDateTime timeStamp;

    public enum bookingStatus {
        success,
        canceled
    }

    @Enumerated
    @Column(name = "status")
    private bookingStatus status;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "showId")
    private Show show;

    @Override
    public String toString() {
        return "Booking [bookingId=" + bookingId + ", numberOfSeats=" + numberOfSeats + ", timeStamp=" + timeStamp + ", status=" + status + ", userId=" + user + ", showId=" + show + "]";
    }
}
