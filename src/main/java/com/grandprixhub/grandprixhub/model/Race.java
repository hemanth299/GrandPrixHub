package com.grandprixhub.grandprixhub.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Changed from String to Long

    private int raceYear; // Changed from 'year' to 'raceYear' to avoid SQL reserved keyword
    private String name; // E.g., "Monaco Grand Prix"
    private String circuit; // E.g., "Circuit de Monaco"
    private LocalDate date;
    private String winnerDriverId;
    private String winningTeamId;
    private String fastestLapDriverId;
    private String polePositionDriverId; // New: Driver who got pole position
    private String raceHighlights; // New: Brief text description of key race events/highlights
    private String keyTactics; // New: Explanation of major tactical decisions

    @ElementCollection
    private List<String> podiumDriverIds; // New: List of driver IDs on the podium (1st, 2nd, 3rd)

    private int numberOfLaps; // New: Total number of laps in the race
    private double circuitLengthKm; // New: Length of the circuit in kilometers
}