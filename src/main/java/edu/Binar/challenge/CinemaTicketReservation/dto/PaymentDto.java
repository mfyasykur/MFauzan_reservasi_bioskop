package edu.Binar.challenge.CinemaTicketReservation.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.Binar.challenge.CinemaTicketReservation.model.Booking;
import edu.Binar.challenge.CinemaTicketReservation.model.Payment;
import lombok.Data;

import java.time.ZonedDateTime;

@JsonIgnoreProperties(
        value = {"paymentId"},
        allowGetters = true
)
@Data
public class PaymentDto {

    @JsonProperty("paymentId")
    @JsonIgnore
    private long paymentId;

    private Number amount;

    private ZonedDateTime timeStamp;

    private Payment.paymentMethod method;

    private Booking booking;
}
