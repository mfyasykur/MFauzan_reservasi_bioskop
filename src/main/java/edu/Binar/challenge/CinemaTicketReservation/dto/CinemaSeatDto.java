package edu.Binar.challenge.CinemaTicketReservation.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.Binar.challenge.CinemaTicketReservation.model.CinemaHall;
import edu.Binar.challenge.CinemaTicketReservation.model.CinemaSeat;
import lombok.Data;

@JsonIgnoreProperties(
        value = {"cinemaSeatId"},
        allowGetters = true
)
@Data
public class CinemaSeatDto {

    @JsonProperty("cinemaSeatId")
    @JsonIgnore
    private long cinemaSeatId;

    private int seatNumber;

    private CinemaSeat.seatType type;

    private CinemaHall cinemaHall;
}
