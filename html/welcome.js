document.addEventListener("DOMContentLoaded", function() {
    const nickInput = document.getElementById("nickInput");
    const nickForm = document.getElementById("nickForm");
    const errorElement = document.getElementById("error");
    const welcomeContainer = document.getElementById("welcomeContainer");
    const lobbyContainer = document.getElementById("lobbyContainer");

    // Setup WebSocket connection
    const connection = new WebSocket("ws://" + window.location.hostname + ":9105");

    connection.onopen = function () {
        console.log('WebSocket connection established!');
    };

    connection.onerror = function (error) {
        console.error('WebSocket Error:', error);
    };

    connection.onmessage = function (e) {
        console.log('Server:', e.data);
        const response = JSON.parse(e.data);
        handleServerResponse(response);
    };

    nickForm.addEventListener('submit', function(event) {
        event.preventDefault();
        validateNickname(nickInput.value.trim());
    });

    function validateNickname(nickname) {
        if (!nickname) {
            showError("Please enter a nickname.");
            return;
        }

        let message = {
            screen: "welcome",
            type: "validateNickname",
            nickname: nickname
        };

        // Send the message object as a string via WebSocket
        connection.send(JSON.stringify(message));
    }

    function handleServerResponse(response) {
        if (response.type === "validateNicknameResponse") {
            if (response.isValid) {
                enterLobby();
            } else {
                showError("Your nick is already taken, please reenter.");
            }
        }
    }

    function enterLobby() {
        welcomeContainer.style.display = 'none';
        lobbyContainer.style.display = 'block';
    }

    function showError(message) {
        errorElement.textContent = message;
        errorElement.style.display = 'block';
    }
});
