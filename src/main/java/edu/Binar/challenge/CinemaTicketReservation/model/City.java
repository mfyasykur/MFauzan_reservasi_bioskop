package edu.Binar.challenge.CinemaTicketReservation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cityId;

    @Column(name = "name", length = 64, nullable = false)
    private String name;

    @Column(name = "state", length = 64)
    private String state;

    @Column(name = "zipCode", length = 16)
    private String zipCode;

    @Override
    public String toString(){
        return "City [cityId=" + cityId + ", name=" + name + ", state=" + state + ", zipCode=" + zipCode + "]";
    }
}
