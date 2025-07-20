package com.grandprixhub.grandprixhub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping; // Import our DriverRepository
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; // For base path
import org.springframework.web.server.ResponseStatusException;

import com.grandprixhub.grandprixhub.model.Driver;
import com.grandprixhub.grandprixhub.service.DriverRepository;

@RestController // Marks this class as a REST controller
@RequestMapping("/api/drivers") // Sets a base path for all endpoints in this controller
public class DriverController {

    private final DriverRepository driverRepository; // Declare our repository

    // Constructor Injection: Spring automatically provides an instance of DriverRepository
    public DriverController(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    // This method handles HTTP GET requests to /api/drivers
    @GetMapping
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll(); // Call the findAll method from our repository
    }
    // New method to get a single driver by ID
    @GetMapping("/{id}") // This maps GET requests to /api/drivers/{id}
    public Driver getDriverById(@PathVariable String id) { // @PathVariable binds the URL segment to the 'id' parameter
        Driver driver = driverRepository.findById(id);
        if (driver == null) {
            // If driver is not found, throw an exception that results in a 404 Not Found response
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Driver not found with ID: " + id);
        }
        return driver;
    }
}