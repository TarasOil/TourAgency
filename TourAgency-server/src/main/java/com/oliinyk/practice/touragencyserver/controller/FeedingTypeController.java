package com.oliinyk.practice.touragencyserver.controller;

import com.oliinyk.practice.touragencyserver.entity.FeedingType;
import com.oliinyk.practice.touragencyserver.repository.FeedingTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("feeding_type")
public class FeedingTypeController {

    private final FeedingTypeRepository feedingTypeRepository;

    public FeedingTypeController(FeedingTypeRepository feedingTypeRepository) {
        this.feedingTypeRepository = feedingTypeRepository;
    }

    @PostMapping
    public ResponseEntity<Void> addFeedingType(@RequestBody FeedingType feedingType) {
        feedingTypeRepository.save(feedingType);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FeedingType>> getCountries() {
        return new ResponseEntity<>(feedingTypeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{feedingTypeId}")
    public ResponseEntity<FeedingType> getFeedingTypeById(@PathVariable("feedingTypeId") int id) {
        Optional<FeedingType> feedingType = feedingTypeRepository.findById(id);
        if(feedingType.isPresent()) {
            return new ResponseEntity<>(feedingType.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{feedingTypeId}")
    public ResponseEntity<Void> editFeedingType(@PathVariable("feedingTypeId") int id, @RequestBody FeedingType feedingTypeIn) {
        Optional<FeedingType> feedingType = feedingTypeRepository.findById(id);
        if (feedingType.isPresent()) {
            feedingTypeIn.setId(feedingType.get().getId());
            feedingTypeRepository.save(feedingTypeIn);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{feedingTypeId}")
    public ResponseEntity<Void> deleteFeedingType(@PathVariable("feedingTypeId") int id) {
        feedingTypeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
