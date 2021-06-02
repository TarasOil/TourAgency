package com.oliinyk.practice.touragencyserver.controller;

import com.oliinyk.practice.touragencyserver.entity.City;
import com.oliinyk.practice.touragencyserver.repository.CityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("city")
public class CityController {

    private final CityRepository cityRepository;

    public CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @PostMapping
    public ResponseEntity<Void> addCity(@RequestBody City city) {
        cityRepository.save(city);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<City>> getCities() {
        return new ResponseEntity<>(cityRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<City> getCityById(@PathVariable("cityId") int id) {
        Optional<City> city = cityRepository.findById(id);
        if(city.isPresent()) {
            return new ResponseEntity<>(city.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{cityId}")
    public ResponseEntity<Void> editCity(@PathVariable("cityId") int id, @RequestBody City cityIn) {
        Optional<City> city = cityRepository.findById(id);
        if (city.isPresent()) {
            cityIn.setId(city.get().getId());
            cityRepository.save(cityIn);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{cityId}")
    public ResponseEntity<Void> deleteCity(@PathVariable("cityId") int id) {
        cityRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}