package com.oliinyk.practice.touragencyserver.controller;

import com.oliinyk.practice.touragencyserver.entity.RoomType;
import com.oliinyk.practice.touragencyserver.repository.RoomTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("room_type")
public class RoomTypeController {

    private final RoomTypeRepository roomTypeRepository;

    public RoomTypeController(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }


    @PostMapping
    public ResponseEntity<Void> addRoomType(@RequestBody RoomType roomType) {
        roomTypeRepository.save(roomType);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoomType>> getCountries() {
        return new ResponseEntity<>(roomTypeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{roomTypeId}")
    public ResponseEntity<RoomType> getRoomTypeById(@PathVariable("roomTypeId") int id) {
        Optional<RoomType> roomType = roomTypeRepository.findById(id);
        if(roomType.isPresent()) {
            return new ResponseEntity<>(roomType.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{roomTypeId}")
    public ResponseEntity<Void> editRoomType(@PathVariable("roomTypeId") int id, @RequestBody RoomType roomTypeIn) {
        Optional<RoomType> roomType = roomTypeRepository.findById(id);
        if (roomType.isPresent()) {
            roomTypeIn.setId(roomType.get().getId());
            roomTypeRepository.save(roomTypeIn);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{roomTypeId}")
    public ResponseEntity<Void> deleteRoomType(@PathVariable("roomTypeId") int id) {
        roomTypeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}