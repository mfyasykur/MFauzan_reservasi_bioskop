package edu.Binar.challenge.CinemaTicketReservation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long movieId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "language", length = 16, nullable = false)
    private String language;

    @Column(name = "release_date", nullable = false)
    private Date releaseDate;

    @Column(name = "country", length = 64, nullable = false)
    private String country;

    @Column(name = "genre", length = 20, nullable = false)
    private String genre;

    public enum showingStatus {
        ON_SHOW,
        COMING_SOON
    }

    @Enumerated
    @Column(name = "status", nullable = false)
    private showingStatus status;

    @Override
    public String toString(){
        return "Movie [movieId=" + movieId + ", title=" + title + ", description=" + description + ", duration=" + duration + ", language=" + language + ", releaseDate=" + releaseDate + ", country=" + country + ", genre=" + genre + ", status=" + status + "]";
    }
}
