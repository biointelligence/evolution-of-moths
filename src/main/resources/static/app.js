var stompClient = null;

function connect() {
	console.log('chegou');
    var socket = new SockJS('localhost:2000/evolution');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/evolutionofmoths', function (evolutionOfMoths) {
            show(JSON.parse(evolutionOfMoths.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function show(message) {
    $("#evolutionofmoths").append("<tr><td>Processando geração: " + message.geracaoAtual + "</td></tr>");
}

