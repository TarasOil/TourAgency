package com.oliinyk.practice.touragencyserver.controller;

import com.oliinyk.practice.touragencyserver.entity.Country;
import com.oliinyk.practice.touragencyserver.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Country>> getCountries() {
        return new ResponseEntity<List<Country>>(countryRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{countryId}")
    public ResponseEntity<Country> getCountryById(@PathVariable("countryId") int id) {
        Country country = countryRepository.findById(id).get();
        if(country != null) {
            return new ResponseEntity<Country>(country, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{countryId}")
    public ResponseEntity<Void> editCountry(@PathVariable("countryId") int id, @RequestBody Country countryIn) {
        Country country = countryRepository.findById(id).get();
        if (country != null) {
            countryIn.setId(country.getId());
            countryRepository.save(countryIn);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{countryId}")
    public ResponseEntity<Void> deleteCountry(@PathVariable("countryId") int id) {
        countryRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
