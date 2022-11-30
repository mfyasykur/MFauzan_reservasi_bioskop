package edu.Binar.challenge.CinemaTicketReservation.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.Binar.challenge.CinemaTicketReservation.model.Booking;
import edu.Binar.challenge.CinemaTicketReservation.model.CinemaSeat;
import edu.Binar.challenge.CinemaTicketReservation.model.Show;
import edu.Binar.challenge.CinemaTicketReservation.model.ShowSeat;
import lombok.Data;

@JsonIgnoreProperties(
        value = {"showSeatId"},
        allowGetters = true
)
@Data
public class ShowSeatDto {

    @JsonProperty("showSeatId")
    @JsonIgnore
    private Long showSeatId;

    private ShowSeat.showSeatStatus status;

    private CinemaSeat cinemaSeat;

    private Show show;

    private Booking booking;

}
