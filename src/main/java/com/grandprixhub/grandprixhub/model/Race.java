package com.grandprixhub.grandprixhub.model;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; // For podium finishers

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Race {
    private String id;
    private int year;
    private String name;        // E.g., "Monaco Grand Prix"
    private String circuit;     // E.g., "Circuit de Monaco"
    private LocalDate date;
    private String winnerDriverId;
    private String winningTeamId;
    private String fastestLapDriverId;
    private String polePositionDriverId; // New: Driver who got pole position
    private String raceHighlights;       // New: Brief text description of key race events/highlights
    private String keyTactics;           // New: Explanation of major tactical decisions
    private List<String> podiumDriverIds; // New: List of driver IDs on the podium (1st, 2nd, 3rd)
    private int numberOfLaps;            // New: Total number of laps in the race
    private double circuitLengthKm;      // New: Length of the circuit in kilometers
}