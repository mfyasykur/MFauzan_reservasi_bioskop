package edu.Binar.challenge.CinemaTicketReservation.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(
        value = {"cityId"},
        allowGetters = true
)
@Data
public class CityDto {

    @JsonProperty("cityId")
    @JsonIgnore
    private long cityId;

    private String name;

    private String state;

    private String zipCode;
}
