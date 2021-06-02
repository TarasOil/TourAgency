package com.oliinyk.practice.touragencyserver.controller;

import com.oliinyk.practice.touragencyserver.entity.Feeding;
import com.oliinyk.practice.touragencyserver.repository.FeedingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("feeding")
public class FeedingController {

    private final FeedingRepository feedingRepository;

    public FeedingController(FeedingRepository feedingRepository) {
        this.feedingRepository = feedingRepository;
    }

    @PostMapping
    public ResponseEntity<Void> addFeeding(@RequestBody Feeding feeding) {
        feedingRepository.save(feeding);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Feeding>> getFeedings() {
        return new ResponseEntity<>(feedingRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{feedingId}")
    public ResponseEntity<Feeding> getFeedingById(@PathVariable("feedingId") int id) {
        Optional<Feeding> feeding = feedingRepository.findById(id);
        if(feeding.isPresent()) {
            return new ResponseEntity<>(feeding.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{feedingId}")
    public ResponseEntity<Void> editFeeding(@PathVariable("feedingId") int id, @RequestBody Feeding feedingIn) {
        Optional<Feeding> feeding = feedingRepository.findById(id);
        if (feeding.isPresent()) {
            feedingIn.setId(feeding.get().getId());
            feedingRepository.save(feedingIn);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{feedingId}")
    public ResponseEntity<Void> deleteFeeding(@PathVariable("feedingId") int id) {
        feedingRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}