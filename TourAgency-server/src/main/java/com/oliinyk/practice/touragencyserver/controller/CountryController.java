package com.oliinyk.practice.touragencyserver.controller;

import com.oliinyk.practice.touragencyserver.entity.Country;
import com.oliinyk.practice.touragencyserver.repository.CountryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("country")
public class CountryController {

    private final CountryRepository countryRepository;

    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PostMapping
    public ResponseEntity<Void> addCountry(@RequestBody Country country) {
        countryRepository.save(country);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Country>> getCountries() {
        return new ResponseEntity<>(countryRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{countryId}")
    public ResponseEntity<Country> getCountryById(@PathVariable("countryId") int id) {
        Optional<Country> country = countryRepository.findById(id);
        if(country.isPresent()) {
            return new ResponseEntity<>(country.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{countryId}")
    public ResponseEntity<Void> editCountry(@PathVariable("countryId") int id, @RequestBody Country countryIn) {
        Optional<Country> country = countryRepository.findById(id);
        if (country.isPresent()) {
            countryIn.setId(country.get().getId());
            countryRepository.save(countryIn);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{countryId}")
    public ResponseEntity<Void> deleteCountry(@PathVariable("countryId") int id) {
        countryRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
