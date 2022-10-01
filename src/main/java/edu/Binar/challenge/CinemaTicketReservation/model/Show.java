package edu.Binar.challenge.CinemaTicketReservation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long showId;

    @Column(name = "date")
    private Date date;

    @Column(name = "startTime")
    private ZonedDateTime startTime;

    @Column(name = "endTime")
    private ZonedDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "cinemaHallId")
    private CinemaHall cinemaHall;

    @ManyToOne
    @JoinColumn(name = "movieId")
    private Movie movie;

    @Override
    public String toString(){
        return "Show [showId=" + showId + ", date=" + date + ", startTime=" + startTime + ", endTime=" + endTime + ", cinemaHallId=" + cinemaHall + ", movieId=" + movie + "]";
    }
}
