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
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Changed from String to Long
    
    private String firstName;
    private String lastName;
    private String nationality;
    private int permanentNumber;
    private String team;
    private int grandPrixStarts;
    private int championshipVictories;
    private int grandPrixVictories;
}