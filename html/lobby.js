document.addEventListener("DOMContentLoaded", function() {
    const socket = new WebSocket("ws://" + window.location.hostname + ":9105");
    const gameSelect = document.getElementById("gameSelect");
    const modeSelect = document.getElementById("modeSelect");
    const confirmButton = document.getElementById("confirmButton");
    const startButton = document.getElementById("startButton");
    const leaveButton = document.getElementById("leaveButton");
    const refreshButton = document.getElementById("refreshButton");
    const gameTable = document.getElementById("gameListSection");

    socket.onopen = function() {
        console.log("WebSocket connection opened.");
        requestGameList();
    };

    socket.onmessage = function(event) {
        const data = JSON.parse(event.data);
        if (data.type === "gameListUpdate") {
            updateGameList(data.games);
        }
    };

    socket.onerror = function(error) {
        console.error("WebSocket error:", error);
    };

    socket.onclose = function() {
        console.log("WebSocket connection closed.");
    };

    confirmButton.addEventListener('click', function() {
        const gameIndex = gameSelect.selectedIndex;
        const modeIndex = modeSelect.selectedIndex;
        if (gameIndex >= 0 && modeIndex >= 0) {
            joinGame(gameSelect.value, modeSelect.options[modeSelect.selectedIndex].text);
        } else {
            alert('Please select both a game and a mode.');
        }
    });

    refreshButton.addEventListener('click', function() {
        requestGameList();
    });

    function requestGameList() {
        socket.send(JSON.stringify({ type: "requestGameList" }));
    }

    function joinGame(gameId, mode) {
        const message = {
            type: "joinGame",
            gameId: gameId,
            mode: mode
        };
        socket.send(JSON.stringify(message));
    }

    function updateGameList(games) {
        while (gameTable.rows.length > 1) {
            gameTable.deleteRow(1);
        }

        // Add new rows for each game
        games.forEach(function(game) {
            let row = gameTable.insertRow();
            let nameCell = row.insertCell(0);
            let joinCell = row.insertCell(1);
            let playersCell = row.insertCell(2);

            nameCell.textContent = game.name;
            joinCell.innerHTML = `<button onclick='joinGame("${game.id}", "${modeSelect.value}")'>Join</button>`;
            playersCell.textContent = game.players.join(", ");
        });
    }
});
