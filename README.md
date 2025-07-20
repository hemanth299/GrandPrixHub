# Grand Prix Hub - The F1 Fan's Companion

## Overview

Grand Prix Hub is a web application designed to be the ultimate companion for both new and seasoned Formula 1 fans. It aims to provide easy access to F1 driver and race information, offer insights into race tactics and highlights, predict race winners, and foster a community through a live chat.

## Features

- **F1 Driver Profiles:** View key statistics for various Formula 1 drivers (e.g., GP Starts, Wins, Championships).
- **F1 Race Information:** Explore details about past Grand Prix events, including circuit, winner, pole position, highlights, and key tactics.
- **Basic Race Winner Prediction:** A simple backend logic to predict the winner of a given race based on predefined rules (e.g., pole position, championship history).
- **Live Fan Chat:** A basic real-time chat feature for F1 enthusiasts to connect and discuss races.
- **Responsive User Interface:** A modern and intuitive design, built with Tailwind CSS, that adapts to different screen sizes (desktop and mobile).

## Technologies Used

### Backend

- **Java 17+**
- **Spring Boot 3+:** For building robust RESTful APIs.
- **Lombok:** To reduce boilerplate Java code.
- **In-Memory Repositories:** Simple data storage using Java collections (designed for easy future migration to a real database).

### Frontend

- **HTML5**
- **CSS3 (Tailwind CSS v4):** For a utility-first, highly customizable, and responsive design.
- **JavaScript (Vanilla JS):** For fetching data from the backend APIs and dynamic content rendering.
- **npm:** For managing frontend dependencies and running Tailwind CSS build processes.

## How to Run the Project Locally

### Prerequisites

- Java Development Kit (JDK) 17 or newer (e.g., JDK 23)
- Apache Maven 3.x
- Node.js (LTS version) and npm
- Git
- A code editor like Visual Studio Code (with recommended extensions for Java, Spring Boot, and Tailwind CSS)

### Steps

1.  **Clone the Repository:**

    ```bash
    git clone [https://github.com/YOUR_USERNAME/GrandPrixHub.git](https://github.com/YOUR_USERNAME/GrandPrixHub.git)
    cd GrandPrixHub
    ```

    (Replace `YOUR_USERNAME` with your GitHub username)

2.  **Install Frontend Dependencies & Start Tailwind Watch:**
    In the project root directory, open a **new terminal** and run:

    ```bash
    npm install
    npm run build:tailwind
    ```

    Keep this terminal running.

3.  **Run the Spring Boot Backend:**
    In a **separate terminal** (also in the project root), run:

    ```bash
    ./mvnw spring-boot:run
    ```

    (Use `mvnw spring-boot:run` on Windows Command Prompt/PowerShell, or `cmd /c mvnw spring-boot:run` or `./mvnw.cmd spring-boot:run` in Git Bash)
    Keep this terminal running.

4.  **Access the Application:**
    Once both processes are running, open your web browser and navigate to:
    ```
    http://localhost:8080/
    ```
    You can also test the backend APIs directly:
    - `http://localhost:8080/api/drivers`
    - `http://localhost:8080/api/races`
    - `http://localhost:8080/api/predict/winner/{raceId}` (e.g., `/api/predict/winner/monaco-2024`)
    - `http://localhost:8080/api/chat` (GET)

## Future Enhancements

- Integration with a real database (e.g., H2, PostgreSQL, MySQL) using Spring Data JPA.
- More sophisticated AI/ML model for race prediction (e.g., using Python integration, or Java ML libraries).
- User authentication and profiles for personalized content.
- Real-time chat using WebSockets (e.g., Spring WebSockets).
- Enhanced UI/UX with interactive charts and more detailed pages for drivers/races.
- Integration with external F1 data APIs (e.g., Ergast API) to fetch real-time or historical data.

---

_Developed for the HSBC Technology India Hackathon 2025_
