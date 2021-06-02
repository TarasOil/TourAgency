package com.oliinyk.practice.touragencyserver.controller;

import com.oliinyk.practice.touragencyserver.entity.Service;
import com.oliinyk.practice.touragencyserver.repository.ServiceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("service")
public class ServiceController {

    private final ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @PostMapping
    public ResponseEntity<Void> addService(@RequestBody Service service) {
        serviceRepository.save(service);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Service>> getServices() {
        return new ResponseEntity<>(serviceRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<Service> getServiceById(@PathVariable("serviceId") int id) {
        Optional<Service> service = serviceRepository.findById(id);
        if(service.isPresent()) {
            return new ResponseEntity<>(service.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{serviceId}")
    public ResponseEntity<Void> editService(@PathVariable("serviceId") int id, @RequestBody Service serviceIn) {
        Optional<Service> service = serviceRepository.findById(id);
        if (service.isPresent()) {
            serviceIn.setId(service.get().getId());
            serviceRepository.save(serviceIn);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{serviceId}")
    public ResponseEntity<Void> deleteService(@PathVariable("serviceId") int id) {
        serviceRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}