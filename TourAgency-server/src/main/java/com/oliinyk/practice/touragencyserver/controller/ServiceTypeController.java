package com.oliinyk.practice.touragencyserver.controller;

import com.oliinyk.practice.touragencyserver.entity.ServiceType;
import com.oliinyk.practice.touragencyserver.repository.ServiceTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("service_type")
public class ServiceTypeController {

    private final ServiceTypeRepository serviceTypeRepository;

    public ServiceTypeController(ServiceTypeRepository serviceTypeRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
    }

    @PostMapping
    public ResponseEntity<Void> addServiceType(@RequestBody ServiceType serviceType) {
        serviceTypeRepository.save(serviceType);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ServiceType>> getServiceTypes() {
        return new ResponseEntity<>(serviceTypeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{serviceTypeId}")
    public ResponseEntity<ServiceType> getServiceTypeById(@PathVariable("serviceTypeId") int id) {
        Optional<ServiceType> serviceType = serviceTypeRepository.findById(id);
        if(serviceType.isPresent()) {
            return new ResponseEntity<>(serviceType.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{serviceTypeId}")
    public ResponseEntity<Void> editServiceType(@PathVariable("serviceTypeId") int id, @RequestBody ServiceType serviceTypeIn) {
        Optional<ServiceType> serviceType = serviceTypeRepository.findById(id);
        if (serviceType.isPresent()) {
            serviceTypeIn.setId(serviceType.get().getId());
            serviceTypeRepository.save(serviceTypeIn);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{serviceTypeId}")
    public ResponseEntity<Void> deleteServiceType(@PathVariable("serviceTypeId") int id) {
        serviceTypeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}