package edu.Binar.challenge.CinemaTicketReservation.service.impl;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.City;
import edu.Binar.challenge.CinemaTicketReservation.repository.CityRepository;
import edu.Binar.challenge.CinemaTicketReservation.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> getAllCities(){
        return cityRepository.findAll();
    }

    private static final String MESSAGE = "CinemaHall not found for this id :: ";

    @Override
    public City getCityById(Long cityId) throws ResourceNotFoundException {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + cityId));

        return cityRepository.save(city);
    }

    @Override
    public City createCity(City city){
        return cityRepository.save(city);
    }

    @Override
    public City updateCity(Long cityId, City cityDetails) throws ResourceNotFoundException {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + cityId));

        city.setName(cityDetails.getName());
        city.setState(cityDetails.getState());
        city.setZipCode(cityDetails.getZipCode());

        return cityRepository.save(city);
    }

    @Override
    public void deleteCity(Long cityId) throws ResourceNotFoundException {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + cityId));

        cityRepository.delete(city);
    }
}
