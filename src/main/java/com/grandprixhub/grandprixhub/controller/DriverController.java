package com.grandprixhub.grandprixhub.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grandprixhub.grandprixhub.model.Driver;
import com.grandprixhub.grandprixhub.service.DriverRepository; // We will use this to handle the case where a driver is not found

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverController(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @GetMapping
    public List<Driver> getAllDrivers() {
        // The findAll() method now gets data from the database
        return driverRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long id) {
        // Spring Data JPA's findById returns an Optional<Driver>
        Optional<Driver> driver = driverRepository.findById(id);

        // We check if the Optional contains a driver
        if (driver.isPresent()) {
            return ResponseEntity.ok(driver.get());
        } else {
            // If the driver is not found, return a 404 Not Found status
            return ResponseEntity.notFound().build();
        }
    }
}