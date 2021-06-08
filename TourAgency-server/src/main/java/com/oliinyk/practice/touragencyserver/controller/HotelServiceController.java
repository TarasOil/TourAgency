package com.oliinyk.practice.touragencyserver.controller;

import com.oliinyk.practice.touragencyserver.entity.HotelService;
import com.oliinyk.practice.touragencyserver.repository.HotelServiceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("hotel_service")
public class HotelServiceController {

    private final HotelServiceRepository hotelServiceRepository;

    public HotelServiceController(HotelServiceRepository hotelServiceRepository) {
        this.hotelServiceRepository = hotelServiceRepository;
    }

    @PostMapping
    public ResponseEntity<Void> addHotelService(@RequestBody HotelService hotelService) {
        hotelServiceRepository.save(hotelService);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HotelService>> getHotelServices() {
        return new ResponseEntity<>(hotelServiceRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{hotelServiceId}")
    public ResponseEntity<HotelService> getHotelServiceById(@PathVariable("hotelServiceId") int id) {
        Optional<HotelService> hotelService = hotelServiceRepository.findById(id);
        if(hotelService.isPresent()) {
            return new ResponseEntity<>(hotelService.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{hotelServiceId}")
    public ResponseEntity<Void> editHotelService(@PathVariable("hotelServiceId") int id, @RequestBody HotelService hotelServiceIn) {
        Optional<HotelService> hotelService = hotelServiceRepository.findById(id);
        if (hotelService.isPresent()) {
            hotelServiceIn.setId(hotelService.get().getId());
            hotelServiceRepository.save(hotelServiceIn);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{hotelServiceId}")
    public ResponseEntity<Void> deleteHotelService(@PathVariable("hotelServiceId") int id) {
        hotelServiceRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
