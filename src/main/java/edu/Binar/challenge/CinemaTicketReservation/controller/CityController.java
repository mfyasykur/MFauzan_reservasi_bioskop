package edu.Binar.challenge.CinemaTicketReservation.controller;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.City;
import edu.Binar.challenge.CinemaTicketReservation.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cinema-v1")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @GetMapping("/cities/")
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @GetMapping("/cities/{cityId}")
    public ResponseEntity<City> getCityById(@PathVariable(value = "cityId") Long cityId)
            throws ResourceNotFoundException {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City not found for this id :: " + cityId));

        return ResponseEntity.ok().body(city);
    }

    @PostMapping("/cities")
    public City createCity(@Valid @RequestBody City city){
        return cityRepository.save(city);
    }

    @PutMapping("/cities/{cityId}")
    public ResponseEntity<City> updateCity(@PathVariable(value = "cityId") Long cityId, @Valid @RequestBody City cityDetails)
            throws ResourceNotFoundException {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City not found for this id :: " + cityId));

        city.setName(cityDetails.getName());
        city.setState(cityDetails.getState());
        city.setZipCode(cityDetails.getZipCode());
        final City updatedCity = cityRepository.save(city);

        return ResponseEntity.ok(updatedCity);
    }

    @DeleteMapping("/cities/{cityId}")
    public Map<String, Boolean> deleteCity(@PathVariable(value = "cityId") Long cityId)
            throws ResourceNotFoundException {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City not found for this id :: " + cityId));

        cityRepository.delete(city);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
