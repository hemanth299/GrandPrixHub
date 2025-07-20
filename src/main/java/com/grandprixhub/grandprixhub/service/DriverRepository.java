package com.grandprixhub.grandprixhub.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map; // Correct import for PostConstruct
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.grandprixhub.grandprixhub.model.Driver;

import jakarta.annotation.PostConstruct;

@Component // Marks this class as a Spring component, making it discoverable
public class DriverRepository {

    private final Map<String, Driver> drivers = new HashMap<>();

    // This method will be executed automatically after the component is initialized
    @PostConstruct
    public void init() {
        // Add some initial dummy data for testing
        Driver lewis = new Driver("lewis-hamilton", "Lewis", "Hamilton", "British", 44, "Mercedes", 336, 7, 103);
        Driver max = new Driver("max-verstappen", "Max", "Verstappen", "Dutch", 1, "Red Bull Racing", 195, 3, 61);
        Driver charles = new Driver("charles-leclerc", "Charles", "Leclerc", "Monegasque", 16, "Ferrari", 148, 0, 6);
        Driver fernando = new Driver("fernando-alonso", "Fernando", "Alonso", "Spanish", 14, "Aston Martin", 389, 2, 32);


        drivers.put(lewis.getId(), lewis);
        drivers.put(max.getId(), max);
        drivers.put(charles.getId(), charles);
        drivers.put(fernando.getId(), fernando);

        System.out.println("Initialized " + drivers.size() + " drivers.");
    }

    public List<Driver> findAll() {
        return drivers.values().stream().collect(Collectors.toList());
    }

    public Driver findById(String id) {
        return drivers.get(id);
    }

    public Driver save(Driver driver) {
        drivers.put(driver.getId(), driver);
        return driver;
    }

    public void deleteById(String id) {
        drivers.remove(id);
    }
}