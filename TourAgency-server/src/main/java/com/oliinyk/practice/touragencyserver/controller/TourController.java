package com.oliinyk.practice.touragencyserver.controller;

import com.oliinyk.practice.touragencyserver.entity.Tour;
import com.oliinyk.practice.touragencyserver.repository.TourRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("tour")
public class TourController {

    private final TourRepository tourRepository;

    public TourController(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @PostMapping
    public ResponseEntity<Void> addTour(@RequestBody Tour tour) {
        tourRepository.save(tour);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Tour>> getCountries() {
        return new ResponseEntity<>(tourRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{tourId}")
    public ResponseEntity<Tour> getTourById(@PathVariable("tourId") int id) {
        Optional<Tour> tour = tourRepository.findById(id);
        if(tour.isPresent()) {
            return new ResponseEntity<>(tour.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{tourId}")
    public ResponseEntity<Void> editTour(@PathVariable("tourId") int id, @RequestBody Tour tourIn) {
        Optional<Tour> tour = tourRepository.findById(id);
        if (tour.isPresent()) {
            tourIn.setId(tour.get().getId());
            tourRepository.save(tourIn);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{tourId}")
    public ResponseEntity<Void> deleteTour(@PathVariable("tourId") int id) {
        tourRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}