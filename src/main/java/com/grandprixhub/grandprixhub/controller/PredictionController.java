package com.grandprixhub.grandprixhub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grandprixhub.grandprixhub.service.PredictionService;

@RestController
@RequestMapping("/api/predict") // Base path for prediction endpoints
public class PredictionController {

    private final PredictionService predictionService;

    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    // GET prediction for a specific race
    @GetMapping("/winner/{raceId}")
    public String getPredictedWinner(@PathVariable String raceId) {
        return predictionService.predictRaceWinner(raceId);
    }
}