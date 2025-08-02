package com.grandprixhub.grandprixhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grandprixhub.grandprixhub.service.PredictionService;

@RestController
@RequestMapping("/api/predict")
public class PredictionController {

    private final PredictionService predictionService;

    @Autowired
    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @GetMapping("/winner/{raceId}")
    public String getPredictedWinner(@PathVariable Long raceId) {
        return predictionService.predictRaceWinner(raceId);
    }
}