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

    @Column(name = "amount")
    private Number amount;

    @Column(name = "timeStamp")
    private ZonedDateTime timeStamp;

    @Column(name = "discountCouponId")
    private int discountCouponId;

    @Column(name = "remoteTransactionId")
    private int remoteTransactionId;

    public enum paymentMethod {
        cash,
        bank,
        eWallet
    }

    @Enumerated
    @Column(name = "method")
    private paymentMethod method;

    @ManyToOne
    @JoinColumn(name = "bookingId")
    private Booking booking;

    @Override
    public String toString(){
        return "Payment [paymentId=" + paymentId + ", amount=" + amount + ", timeStamp=" + timeStamp + ", discountCouponId=" + discountCouponId + ", remoteTransactionId=" + remoteTransactionId + ", method=" + method + ", bookingId=" + booking + "]";
    }
}
