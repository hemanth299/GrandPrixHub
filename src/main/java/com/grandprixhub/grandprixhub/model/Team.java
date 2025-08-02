package com.grandprixhub.grandprixhub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Changed from String to Long

    private String name; // E.g., "Mercedes-AMG Petronas Formula One Team"
    private String nationality; // E.g., "German"
    private int championships; // Number of constructor championships won
}