package com.oliinyk.practice.touragencyserver.controller;

import com.oliinyk.practice.touragencyserver.entity.Hotel;
import com.oliinyk.practice.touragencyserver.entity.Image;
import com.oliinyk.practice.touragencyserver.repository.HotelRepository;
import com.oliinyk.practice.touragencyserver.repository.ImageRepository;
import com.oliinyk.practice.touragencyserver.service.CloudinaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("hotel")
public class HotelController {

    private final HotelRepository hotelRepository;
    private final CloudinaryService cloudinaryService;
    private final ImageRepository imageRepository;

    public HotelController(HotelRepository hotelRepository, CloudinaryService cloudinaryService, ImageRepository imageRepository) {
        this.hotelRepository = hotelRepository;
        this.cloudinaryService = cloudinaryService;
        this.imageRepository = imageRepository;
    }

    @PostMapping
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) {
        Hotel saved = hotelRepository.save(hotel);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PostMapping(value = "/{hotelId}/photo", consumes = "multipart/form-data")
    public ResponseEntity<Void> addPhoto(@PathVariable("hotelId") int id, @RequestPart MultipartFile[] images) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if(hotel.isPresent()) {
            for(MultipartFile image : images) {
                String url = cloudinaryService.uploadFile(image, "hotels");
                imageRepository.save(new Image(hotel.get(), url));
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getHotels() {
        return new ResponseEntity<>(hotelRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable("hotelId") int id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if(hotel.isPresent()) {
            return new ResponseEntity<>(hotel.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<Void> editHotel(@PathVariable("hotelId") int id, @RequestBody Hotel hotelIn) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isPresent()) {
            hotelIn.setId(hotel.get().getId());
            hotelRepository.save(hotelIn);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> deleteHotel(@PathVariable("hotelId") int id) {
        hotelRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}