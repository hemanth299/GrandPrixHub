document.addEventListener('DOMContentLoaded', () => {
    const driversListContainer = document.getElementById('drivers-list');
    const racesListContainer = document.getElementById('races-list');

    // Function to fetch and display drivers (existing)
    async function fetchDrivers() {
        try {
            const response = await fetch('/api/drivers');
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const drivers = await response.json();

            if (drivers.length === 0) {
                driversListContainer.innerHTML = '<p class="text-center text-gray-500">No drivers found.</p>';
                return;
            }

            driversListContainer.innerHTML = ''; // Clear previous content

            drivers.forEach(driver => {
                const driverCard = document.createElement('div');
                driverCard.className = 'bg-white p-6 rounded-lg shadow-lg hover:shadow-xl transition-shadow duration-300';

                driverCard.innerHTML = `
                    <h3 class="text-xl font-semibold mb-2 text-gray-800">${driver.firstName} ${driver.lastName}</h3>
                    <p class="text-gray-600">Nationality: <span class="font-medium">${driver.nationality}</span></p>
                    <p class="text-gray-600">Team: <span class="font-medium">${driver.team}</span></p>
                    <p class="text-gray-600">Permanent Number: <span class="font-medium">${driver.permanentNumber}</span></p>
                    <p class="text-gray-600">GP Starts: <span class="font-medium">${driver.grandPrixStarts}</span></p>
                    <p class="text-gray-600">GP Victories: <span class="font-medium">${driver.grandPrixVictories}</span></p>
                    <p class="text-gray-600">Championships: <span class="font-medium">${driver.championshipVictories}</span></p>
                `;
                driversListContainer.appendChild(driverCard);
            });

        } catch (error) {
            console.error('Error fetching drivers:', error);
            driversListContainer.innerHTML = '<p class="text-center text-red-500">Failed to load drivers.</p>';
        }
    }

    // Function to fetch and display races (updated to include prediction)
    async function fetchRaces() {
        try {
            const response = await fetch('/api/races');
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const races = await response.json();

            if (races.length === 0) {
                racesListContainer.innerHTML = '<p class="text-center text-gray-500">No races found.</p>';
                return;
            }

            racesListContainer.innerHTML = ''; // Clear previous content

            for (const race of races) { // Use for...of loop to allow await inside
                const raceCard = document.createElement('div');
                raceCard.className = 'bg-white p-6 rounded-lg shadow-lg hover:shadow-xl transition-shadow duration-300';

                // --- Fetch Prediction for the current race ---
                let predictionText = 'Loading prediction...';
                try {
                    const predictionResponse = await fetch(`/api/predict/winner/${race.id}`);
                    if (predictionResponse.ok) {
                        predictionText = await predictionResponse.text(); // Prediction API returns plain text
                    } else {
                        predictionText = 'Prediction N/A';
                    }
                } catch (predictionError) {
                    console.error('Error fetching prediction for race ' + race.id + ':', predictionError);
                    predictionText = 'Prediction Error';
                }
                // --- End Fetch Prediction ---

                raceCard.innerHTML = `
                    <h3 class="text-xl font-semibold mb-2 text-gray-800">${race.name} (${race.year})</h3>
                    <p class="text-gray-600">Circuit: <span class="font-medium">${race.circuit}</span></p>
                    <p class="text-gray-600">Date: <span class="font-medium">${race.date}</span></p>
                    <p class="text-gray-600">Winner: <span class="font-medium">${race.winnerDriverId ? race.winnerDriverId.split('-').map(s => s.charAt(0).toUpperCase() + s.slice(1)).join(' ') : 'N/A'}</span></p>
                    <p class="text-gray-600">Pole Position: <span class="font-medium">${race.polePositionDriverId ? race.polePositionDriverId.split('-').map(s => s.charAt(0).toUpperCase() + s.slice(1)).join(' ') : 'N/A'}</span></p>
                    <p class="text-gray-600">Laps: <span class="font-medium">${race.numberOfLaps}</span></p>
                    <p class="text-gray-600">Circuit Length: <span class="font-medium">${race.circuitLengthKm} km</span></p>
                    <p class="text-gray-600 mt-2">Highlights: <span class="text-sm">${race.raceHighlights}</span></p>
                    <p class="text-gray-600">Key Tactics: <span class="text-sm">${race.keyTactics}</span></p>
                    <p class="text-blue-700 font-bold mt-3">Predicted Winner: <span class="font-semibold">${predictionText}</span></p>
                `; // Added prediction line
                raceCard.id = `race-card-${race.id}`; // Add ID for potential future use

                racesListContainer.appendChild(raceCard);
            }

        } catch (error) {
            console.error('Error fetching races:', error);
            racesListContainer.innerHTML = '<p class="text-center text-red-500">Failed to load races.</p>';
        }
    }

    // Call both functions when the page loads
    fetchDrivers();
    fetchRaces();
});