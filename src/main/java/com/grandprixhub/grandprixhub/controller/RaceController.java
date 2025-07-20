package com.grandprixhub.grandprixhub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.grandprixhub.grandprixhub.model.Race;
import com.grandprixhub.grandprixhub.service.RaceRepository;

@RestController
@RequestMapping("/api/races") // Base path for race endpoints
public class RaceController {

    private final RaceRepository raceRepository;

    public RaceController(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    // GET all races
    @GetMapping
    public List<Race> getAllRaces() {
        return raceRepository.findAll();
    }

    // GET single race by ID
    @GetMapping("/{id}")
    public Race getRaceById(@PathVariable String id) {
        Race race = raceRepository.findById(id);
        if (race == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Race not found with ID: " + id);
        }
        return race;
    }
}