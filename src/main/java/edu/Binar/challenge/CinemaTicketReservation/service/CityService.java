package edu.Binar.challenge.CinemaTicketReservation.service;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.City;

import java.util.List;

public interface CityService {

    List<City> getAllCities();
    City getCityById(Long cityId) throws ResourceNotFoundException;
    City createCity(City city);
    City updateCity(Long cityId, City city) throws ResourceNotFoundException;
    void deleteCity(Long cityId) throws ResourceNotFoundException;
}
