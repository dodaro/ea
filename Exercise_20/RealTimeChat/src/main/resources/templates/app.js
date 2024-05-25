var stompClient = null;

window.onload = function() {
    connect();
};

function connect() {
    var socket = new SockJS('http://localhost:8080/kittify-socket'); // Adjust URL to your WebSocket endpoint
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', function(messageOutput) {
            showMessageOutput(JSON.parse(messageOutput.body).message);
        });
    });
}

function sendMessage() {
    var message = document.getElementById('messageInput').value;
    stompClient.send("/app/chat", {}, JSON.stringify({'message': message}));
}

function showMessageOutput(message) {
    var messagesDiv = document.getElementById('messages');
    var messageParagraph = document.createElement('p');
    messageParagraph.style.wordWrap = 'break-word';
    messageParagraph.appendChild(document.createTextNode(message));
    messagesDiv.appendChild(messageParagraph);
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}