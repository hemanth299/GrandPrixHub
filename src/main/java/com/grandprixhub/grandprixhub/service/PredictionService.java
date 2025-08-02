package com.grandprixhub.grandprixhub.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grandprixhub.grandprixhub.model.Driver;
import com.grandprixhub.grandprixhub.model.Race;

@Service
public class PredictionService {

    private final DriverRepository driverRepository;
    private final RaceRepository raceRepository;

    @Autowired
    public PredictionService(DriverRepository driverRepository, RaceRepository raceRepository) {
        this.driverRepository = driverRepository;
        this.raceRepository = raceRepository;
    }

    public String predictRaceWinner(Long raceId) {
        Optional<Race> raceOptional = raceRepository.findById(raceId);
        if (raceOptional.isEmpty()) {
            return "Race not found.";
        }

        Race race = raceOptional.get();

        // Basic Prediction Logic:
        // Rule 1: Prioritize the Pole Position Driver if available
        if (race.getPolePositionDriverId() != null && !race.getPolePositionDriverId().isEmpty()) {
            String[] nameParts = race.getPolePositionDriverId().split("-");
            StringBuilder formattedName = new StringBuilder();
            for (String part : nameParts) {
                if (part.length() > 0) {
                    formattedName.append(part.charAt(0)).append(part.substring(1).toLowerCase()).append(" ");
                }
            }
            return formattedName.toString().trim() + " (Pole Position)";
        }

        // Rule 2: If no pole position, or driver not found, look for highest
        // championship victories among known drivers
        List<Driver> allDrivers = driverRepository.findAll();
        Optional<Driver> mostChampionshipsDriver = allDrivers.stream()
                .filter(driver -> driver.getChampionshipVictories() > 0)
                .max(Comparator.comparingInt(Driver::getChampionshipVictories));

        if (mostChampionshipsDriver.isPresent()) {
            return mostChampionshipsDriver.get().getFirstName() + " " + mostChampionshipsDriver.get().getLastName()
                    + " (Most Championships)";
        }

        // Fallback: If no clear prediction, suggest a random known driver or a generic
        // message
        // ...existing code...
        // Fallback: If no clear prediction, suggest a random known driver or a generic
        // message
        if (!allDrivers.isEmpty()) {
            return allDrivers.get(0).getFirstName() + " " + allDrivers.get(0).getLastName() + " (Fallback)";
        }
        // Add this line to handle the case when allDrivers is empty
        return "No drivers available for prediction.";
    }
}
// ...existing code...