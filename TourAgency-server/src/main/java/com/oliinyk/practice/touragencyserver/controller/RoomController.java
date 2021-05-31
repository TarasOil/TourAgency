package com.oliinyk.practice.touragencyserver.controller;

import com.oliinyk.practice.touragencyserver.entity.Room;
import com.oliinyk.practice.touragencyserver.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("room")
public class RoomController {

    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @PostMapping
    public ResponseEntity<Void> addRoom(@RequestBody Room room) {
        roomRepository.save(room);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Room>> getCountries() {
        return new ResponseEntity<>(roomRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getRoomById(@PathVariable("roomId") int id) {
        Optional<Room> room = roomRepository.findById(id);
        if(room.isPresent()) {
            return new ResponseEntity<>(room.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<Void> editRoom(@PathVariable("roomId") int id, @RequestBody Room roomIn) {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isPresent()) {
            roomIn.setId(room.get().getId());
            roomRepository.save(roomIn);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable("roomId") int id) {
        roomRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}