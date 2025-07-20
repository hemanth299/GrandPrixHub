package com.grandprixhub.grandprixhub.service;

import com.grandprixhub.grandprixhub.model.Driver;
import com.grandprixhub.grandprixhub.model.Race;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service // Marks this as a Spring service component
public class PredictionService {

    private final DriverRepository driverRepository;
    private final RaceRepository raceRepository;

    // Spring will inject instances of DriverRepository and RaceRepository
    public PredictionService(DriverRepository driverRepository, RaceRepository raceRepository) {
        this.driverRepository = driverRepository;
        this.raceRepository = raceRepository;
    }

    public String predictRaceWinner(String raceId) {
        Race race = raceRepository.findById(raceId);
        if (race == null) {
            return "Race not found.";
        }

        // Basic Prediction Logic:
        // Rule 1: Prioritize the Pole Position Driver if available
        if (race.getPolePositionDriverId() != null && !race.getPolePositionDriverId().isEmpty()) {
            Driver poleDriver = driverRepository.findById(race.getPolePositionDriverId());
            if (poleDriver != null) {
                return poleDriver.getFirstName() + " " + poleDriver.getLastName() + " (Pole Position)";
            }
        }

        // Rule 2: If no pole position, or driver not found, look for highest championship victories among known drivers
        List<Driver> allDrivers = driverRepository.findAll();
        Optional<Driver> mostChampionshipsDriver = allDrivers.stream()
            .filter(driver -> driver.getChampionshipVictories() > 0)
            .max(Comparator.comparingInt(Driver::getChampionshipVictories));

        if (mostChampionshipsDriver.isPresent()) {
            return mostChampionshipsDriver.get().getFirstName() + " " + mostChampionshipsDriver.get().getLastName() + " (Most Championships)";
        }

        // Fallback: If no clear prediction, suggest a random known driver or a generic message
        if (!allDrivers.isEmpty()) {
            return allDrivers.get(0).getFirstName() + " " + allDrivers.get(0).getLastName() + " (Fallback)";
        }

        return "No prediction available at this time.";
    }
}