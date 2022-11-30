package edu.Binar.challenge.CinemaTicketReservation.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.Binar.challenge.CinemaTicketReservation.model.Booking;
import edu.Binar.challenge.CinemaTicketReservation.model.Show;
import edu.Binar.challenge.CinemaTicketReservation.model.User;
import lombok.Data;

import java.time.LocalDateTime;

@JsonIgnoreProperties(
        value = {"bookingId"},
        allowGetters = true
)
@Data
public class BookingDto {

    @JsonProperty("bookingId")
    @JsonIgnore
    private Long bookingId;

    private int numberOfSeats;

    private LocalDateTime timeStamp;

    private Booking.BookingStatus status;

    private User user;

    private Show show;
}
