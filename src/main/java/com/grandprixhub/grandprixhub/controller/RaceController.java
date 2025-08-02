package com.grandprixhub.grandprixhub.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grandprixhub.grandprixhub.model.Race;
import com.grandprixhub.grandprixhub.service.RaceRepository;

@RestController
@RequestMapping("/api/races")
public class RaceController {

    private final RaceRepository raceRepository;

    @Autowired
    public RaceController(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @GetMapping
    public List<Race> getAllRaces() {
        return raceRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Race> getRaceById(@PathVariable Long id) {
        Optional<Race> race = raceRepository.findById(id);

        if (race.isPresent()) {
            return ResponseEntity.ok(race.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}