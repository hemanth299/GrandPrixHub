package com.grandprixhub.grandprixhub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    private String id; // Unique identifier for the team
    private String name; // E.g., "Mercedes-AMG Petronas Formula One Team"
    private String nationality; // E.g., "German"
    private int championships; // Number of constructor championships won
}