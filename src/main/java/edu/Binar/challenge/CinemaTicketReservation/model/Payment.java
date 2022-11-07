package edu.Binar.challenge.CinemaTicketReservation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Setter
@Getter
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentId;

    @Column(name = "amount", nullable = false)
    private Number amount;

    @Column(name = "timeStamp", nullable = false)
    private ZonedDateTime timeStamp;

    public enum paymentMethod {
        CASH,
        BANK,
        E_WALLET
    }

    @Enumerated
    @Column(name = "method")
    private paymentMethod method;

    @ManyToOne
    @JoinColumn(name = "bookingId")
    private Booking booking;

    @Override
    public String toString(){
        return "Payment [paymentId=" + paymentId + ", amount=" + amount + ", timeStamp=" + timeStamp + ", method=" + method + ", bookingId=" + booking + "]";
    }
}
