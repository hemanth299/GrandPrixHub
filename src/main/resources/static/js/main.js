document.addEventListener('DOMContentLoaded', () => {
    const driversListContainer = document.getElementById('drivers-list');
    const racesListContainer = document.getElementById('races-list');
    const chatMessagesContainer = document.getElementById('chat-messages'); // New: Chat messages div
    const usernameInput = document.getElementById('username-input'); // New: Username input
    const messageInput = document.getElementById('message-input'); // New: Message input
    const sendMessageBtn = document.getElementById('send-message-btn'); // New: Send button

    // Function to fetch and display drivers (existing)
    async function fetchDrivers() {
        // ... (keep the existing fetchDrivers function as is) ...
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

    // Function to fetch and display races (existing)
    async function fetchRaces() {
        // ... (keep the existing fetchRaces function as is) ...
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
                `;
                raceCard.id = `race-card-${race.id}`;

                racesListContainer.appendChild(raceCard);
            }

        } catch (error) {
            console.error('Error fetching races:', error);
            racesListContainer.innerHTML = '<p class="text-center text-red-500">Failed to load races.</p>';
        }
    }

    // --- New function to fetch and display chat messages ---
    async function fetchChatMessages() {
        try {
            const response = await fetch('/api/chat');
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const messages = await response.json();

            chatMessagesContainer.innerHTML = ''; // Clear existing content

            if (messages.length === 0) {
                chatMessagesContainer.innerHTML = '<p class="text-center text-gray-500">No messages yet. Be the first!</p>';
                return;
            }

            messages.forEach(msg => {
                const messageElement = document.createElement('div');
                messageElement.className = 'mb-2 p-2 rounded';
                if (msg.username === usernameInput.value && usernameInput.value !== '') { // Highlight user's own messages
                    messageElement.classList.add('bg-blue-100', 'text-right');
                } else {
                    messageElement.classList.add('bg-gray-50');
                }

                const timestamp = new Date(msg.timestamp).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
                messageElement.innerHTML = `
                    <span class="font-bold text-blue-800">${msg.username}:</span>
                    <span>${msg.message}</span>
                    <span class="text-xs text-gray-500 ml-2">${timestamp}</span>
                `;
                chatMessagesContainer.appendChild(messageElement);
            });
            // Scroll to the bottom of the chat
            chatMessagesContainer.scrollTop = chatMessagesContainer.scrollHeight;

        } catch (error) {
            console.error('Error fetching chat messages:', error);
            chatMessagesContainer.innerHTML = '<p class="text-center text-red-500">Failed to load chat messages.</p>';
        }
    }

    // --- New function to send a chat message ---
    async function sendMessage() {
        const username = usernameInput.value.trim();
        const message = messageInput.value.trim();

        if (username === '' || message === '') {
            alert('Please enter your name and a message.');
            return;
        }

        try {
            const response = await fetch('/api/chat', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ username, message }) // Send data as JSON
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            messageInput.value = ''; // Clear message input after sending
            fetchChatMessages(); // Refresh chat messages to show the new one

        } catch (error) {
            console.error('Error sending message:', error);
            alert('Failed to send message.');
        }
    }

    // --- Event Listeners for Chat ---
    sendMessageBtn.addEventListener('click', sendMessage);
    messageInput.addEventListener('keypress', (event) => {
        if (event.key === 'Enter') {
            sendMessage();
        }
    });
    // --- End Event Listeners ---


    // Call all functions when the page loads
    fetchDrivers();
    fetchRaces();
    fetchChatMessages(); // New: Call the chat function
});