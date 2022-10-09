package edu.Binar.challenge.CinemaTicketReservation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonIgnoreProperties(
        value = {"invoiceId", "createdAt"},
        allowGetters = true
)

@Setter
@Getter
public class InvoiceDto {

    @JsonProperty("invoiceId")
    @JsonIgnore
    public Long invoiceId;

    @JsonProperty("invoiceNumber")
    @NotNull
    public String invoiceNumber;

    @JsonProperty("date")
    @NotNull
    public LocalDate date;

    @JsonProperty("time")
    @JsonFormat(pattern = "dd-MMM-yyyy")
    @NotNull
    public String time;

    @JsonProperty("title")
    @NotNull
    public String title;

    @JsonProperty("row")
    @NotNull
    public String row;

    @JsonProperty("seatNumber")
    @NotNull
    public String seatNumber;

    @JsonProperty("hall")
    @NotNull
    public String hall;

    @JsonProperty("price")
    @NotNull
    public BigDecimal price;

    @JsonProperty("createdAt")
    @JsonIgnore
    public LocalDateTime createdAt = LocalDateTime.now();
}
