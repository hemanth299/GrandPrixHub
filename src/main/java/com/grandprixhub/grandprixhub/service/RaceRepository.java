package com.grandprixhub.grandprixhub.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.grandprixhub.grandprixhub.model.Race;

import jakarta.annotation.PostConstruct;

@Component
public class RaceRepository {

    private final Map<String, Race> races = new HashMap<>();

    @PostConstruct
    public void init() {
        // Add some initial dummy race data
        Race monaco2024 = new Race(
            "monaco-2024", 2024, "Monaco Grand Prix", "Circuit de Monaco", LocalDate.of(2024, 5, 26),
            "charles-leclerc", "ferrari", "lewis-hamilton", "charles-leclerc",
            "Leclerc's dominant home win, crash in first lap involving Perez and Magnussen.",
            "Strategic tire management under Safety Car. Pit lane delta crucial.",
            List.of("charles-leclerc", "oscar-piastri", "carlos-sainz"), 78, 3.337
        );

        Race bahrain2024 = new Race(
            "bahrain-2024", 2024, "Bahrain Grand Prix", "Bahrain International Circuit", LocalDate.of(2024, 3, 2),
            "max-verstappen", "red-bull-racing", "max-verstappen", "max-verstappen",
            "Verstappen's commanding victory from pole. Albon's early retirement.",
            "Three-stop strategy for front runners. Tire degradation management.",
            List.of("max-verstappen", "sergio-perez", "carlos-sainz"), 57, 5.412
        );

        races.put(monaco2024.getId(), monaco2024);
        races.put(bahrain2024.getId(), bahrain2024);

        System.out.println("Initialized " + races.size() + " races.");
    }

    public List<Race> findAll() {
        return races.values().stream().collect(Collectors.toList());
    }

    public Race findById(String id) {
        return races.get(id);
    }

    public Race save(Race race) {
        races.put(race.getId(), race);
        return race;
    }

    public void deleteById(String id) {
        races.remove(id);
    }
}