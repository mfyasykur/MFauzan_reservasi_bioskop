package edu.Binar.challenge.CinemaTicketReservation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoice extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long invoiceId;

    @Column(name = "createdAt", nullable = false)
    public LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "invoiceNumber", length = 64, nullable = false)
    public String invoiceNumber;

    @Column(name = "title", length = 64, nullable = false)
    public String title;

    @Column(name = "date", nullable = false)
    public LocalDate date;

    @Column(name = "time", length = 8, nullable = false)
    public String time;

    @Column(name = "row", length = 8, nullable = false)
    public String row;

    @Column(name = "seatNumber", length = 8, nullable = false)
    public String seatNumber;

    @Column(name = "hall", length = 8, nullable = false)
    public String hall;

    @Column(name = "price", nullable = false)
    public BigDecimal price;

}
