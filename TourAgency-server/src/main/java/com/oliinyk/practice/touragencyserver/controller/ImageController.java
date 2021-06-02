package com.oliinyk.practice.touragencyserver.controller;

import com.oliinyk.practice.touragencyserver.entity.Image;
import com.oliinyk.practice.touragencyserver.repository.ImageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("image")
public class ImageController {

    private final ImageRepository imageRepository;

    public ImageController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @PostMapping
    public ResponseEntity<Void> addImage(@RequestBody Image image) {
        imageRepository.save(image);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Image>> getImages() {
        return new ResponseEntity<>(imageRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<Image> getImageById(@PathVariable("imageId") int id) {
        Optional<Image> image = imageRepository.findById(id);
        if(image.isPresent()) {
            return new ResponseEntity<>(image.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{imageId}")
    public ResponseEntity<Void> editImage(@PathVariable("imageId") int id, @RequestBody Image imageIn) {
        Optional<Image> image = imageRepository.findById(id);
        if (image.isPresent()) {
            imageIn.setId(image.get().getId());
            imageRepository.save(imageIn);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable("imageId") int id) {
        imageRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}