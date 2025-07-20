package com.grandprixhub.grandprixhub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    private String id;
    private String firstName;
    private String lastName;
    private String nationality;
    private int permanentNumber;
    private String team;
    private int grandPrixStarts; // Number of Grand Prix starts
    private int championshipVictories; // Number of World Championship victories
    private int grandPrixVictories; // New field: Number of individual Grand Prix wins
}