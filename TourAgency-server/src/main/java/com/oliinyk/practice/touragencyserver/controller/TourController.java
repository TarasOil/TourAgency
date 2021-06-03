package com.oliinyk.practice.touragencyserver.controller;

import com.oliinyk.practice.touragencyserver.entity.Tour;
import com.oliinyk.practice.touragencyserver.repository.TourRepository;
import com.oliinyk.practice.touragencyserver.response.TourResponse;
import com.oliinyk.practice.touragencyserver.service.TourService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("tour")
public class TourController {

    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @PostMapping
    public ResponseEntity<Void> addTour(@RequestBody Tour tour) {
        tourService.save(tour);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Tour>> getTours() {
        return new ResponseEntity<>(tourService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/prices")
    public ResponseEntity<List<TourResponse>> getToursWithPrices(@RequestParam("name") String name,
                              @RequestParam("nightsFrom") int nightsFrom, @RequestParam("nightsTo") int nightsTo,
                              @RequestParam("dateFrom") Date dateFrom, @RequestParam("dateTo") Date dateTo,
                              @RequestParam("city") String city, @RequestParam("adults") int adults,
                              @RequestParam("children") int children) {
        return new ResponseEntity<>(tourService.findAllWithPrices(name, nightsFrom, nightsTo, dateFrom, dateTo, city, adults, children), HttpStatus.OK);
    }

    @GetMapping("/{tourId}")
    public ResponseEntity<Tour> getTourById(@PathVariable("tourId") int id) {
        Optional<Tour> tour = tourService.findById(id);
        if(tour.isPresent()) {
            return new ResponseEntity<>(tour.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{tourId}")
    public ResponseEntity<Void> editTour(@PathVariable("tourId") int id, @RequestBody Tour tourIn) {
        Optional<Tour> tour = tourService.findById(id);
        if (tour.isPresent()) {
            tourIn.setId(tour.get().getId());
            tourService.save(tourIn);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{tourId}")
    public ResponseEntity<Void> deleteTour(@PathVariable("tourId") int id) {
        tourService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}