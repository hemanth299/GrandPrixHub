package com.grandprixhub.grandprixhub;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.grandprixhub.grandprixhub.model.ChatMessage;
import com.grandprixhub.grandprixhub.model.Driver;
import com.grandprixhub.grandprixhub.model.Race;
import com.grandprixhub.grandprixhub.model.Team;
import com.grandprixhub.grandprixhub.service.ChatRepository;
import com.grandprixhub.grandprixhub.service.DriverRepository;
import com.grandprixhub.grandprixhub.service.RaceRepository;
import com.grandprixhub.grandprixhub.service.TeamRepository;

@SpringBootApplication
public class GrandPrixHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrandPrixHubApplication.class, args);
    }

    @Bean
    public CommandLineRunner populateDatabase(
            DriverRepository driverRepository,
            RaceRepository raceRepository,
            TeamRepository teamRepository,
            ChatRepository chatRepository) {
        return args -> {
            // Populate Teams
            Team redBull = teamRepository.save(new Team(null, "Red Bull Racing", "Austrian", 6));
            Team mercedes = teamRepository.save(new Team(null, "Mercedes-AMG Petronas Formula One Team", "German", 8));
            Team ferrari = teamRepository.save(new Team(null, "Scuderia Ferrari", "Italian", 16));
            Team mclaren = teamRepository.save(new Team(null, "McLaren Racing", "British", 8));
            Team astonMartin = teamRepository
                    .save(new Team(null, "Aston Martin Aramco Formula One Team", "British", 0));

            // Populate Drivers
            driverRepository.save(new Driver(null, "Max", "Verstappen", "Dutch", 33, "Red Bull Racing", 201, 3, 58));
            driverRepository.save(new Driver(null, "Lewis", "Hamilton", "British", 44, "Mercedes", 341, 7, 103));
            driverRepository.save(new Driver(null, "Charles", "Leclerc", "Monegasque", 16, "Ferrari", 137, 0, 5));
            driverRepository.save(new Driver(null, "Carlos", "Sainz", "Spanish", 55, "Ferrari", 195, 0, 2));
            driverRepository.save(new Driver(null, "Lando", "Norris", "British", 4, "McLaren", 110, 0, 0));
            driverRepository.save(new Driver(null, "Oscar", "Piastri", "Australian", 81, "McLaren", 44, 0, 0));
            driverRepository.save(new Driver(null, "Fernando", "Alonso", "Spanish", 14, "Aston Martin", 381, 2, 32));

            // Populate Races
            Race monaco2024 = new Race(
                    null, 2024, "Monaco Grand Prix", "Circuit de Monaco", LocalDate.of(2024, 5, 26),
                    "charles-leclerc", "ferrari", "lewis-hamilton", "charles-leclerc",
                    "Leclerc's dominant home win, crash in first lap involving Perez and Magnussen.",
                    "Strategic tire management under Safety Car. Pit lane delta crucial.",
                    List.of("charles-leclerc", "oscar-piastri", "carlos-sainz"), 78, 3.337);
            raceRepository.save(monaco2024);

            Race bahrain2024 = new Race(
                    null, 2024, "Bahrain Grand Prix", "Bahrain International Circuit", LocalDate.of(2024, 3, 2),
                    "max-verstappen", "red-bull-racing", "max-verstappen", "max-verstappen",
                    "Verstappen's commanding victory from pole. Albon's early retirement.",
                    "Three-stop strategy for front runners. Tire degradation management.",
                    List.of("max-verstappen", "sergio-perez", "carlos-sainz"), 57, 5.412);
            raceRepository.save(bahrain2024);

            // Populate Chat Messages
            chatRepository.save(new ChatMessage(null, "F1Fanatic", "Excited for the next race!",
                    LocalDateTime.now().minusMinutes(10)));
            chatRepository.save(new ChatMessage(null, "MotorheadMax", "Who do you think will win in Silverstone?",
                    LocalDateTime.now().minusMinutes(5)));
            chatRepository.save(new ChatMessage(null, "GrandPrixGuru", "My prediction is always the pole sitter!",
                    LocalDateTime.now().minusMinutes(2)));

            System.out.println("Database populated with sample data!");
        };
    }
}