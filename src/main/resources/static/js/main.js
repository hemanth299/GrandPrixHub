document.addEventListener('DOMContentLoaded', () => {
    const driversListContainer = document.getElementById('drivers-list');

    // Function to fetch and display drivers
    async function fetchDrivers() {
        try {
            const response = await fetch('/api/drivers'); // Make a GET request to our Spring Boot API
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const drivers = await response.json(); // Parse the JSON response

            if (drivers.length === 0) {
                driversListContainer.innerHTML = '<p class="text-center text-gray-500">No drivers found.</p>';
                return;
            }

            // Clear previous content
            driversListContainer.innerHTML = '';

            // Iterate over drivers and create HTML elements for each
            drivers.forEach(driver => {
                const driverCard = document.createElement('div');
                driverCard.className = 'bg-white p-6 rounded-lg shadow-lg hover:shadow-xl transition-shadow duration-300'; // Tailwind classes for styling

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

    // Call the function to fetch drivers when the page loads
    fetchDrivers();
});