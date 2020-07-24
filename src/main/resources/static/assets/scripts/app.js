var stompClient = null;
var timerNextGeneration = 4;
var generationPerEnvironment = 40;
var lastEnvironment = { red: null, green: null, blue: null };
var environment = { red: 0, green: 0, blue: 0 };
var generalCount = 0;

function connect() {
    var socket = new SockJS('localhost:2000/evolution');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/evolutionofmoths', function (evolutionOfMoths) {
            var response = JSON.parse(evolutionOfMoths.body);
            console.log(response);
            console.log(response.geracaoAtual);
            console.log(response.ambienteVermelho);
            console.log(response.ambienteVerde);
            console.log(response.ambienteAzul);
            console.log(response.mariposas);

            setTimeNextGeneration();
            setCurrentEnvironment(response.geracaoAtual);

            if (
                environment.red !== response.ambienteVermelho ||
                environment.green !== response.ambienteVerde ||
                environment.blue !== response.ambienteAzul
            ) {
                environment = {
                    red: response.ambienteVermelho,
                    green: response.ambienteVerde,
                    blue: response.ambienteAzul
                };
                generalCount = (timerNextGeneration * generationPerEnvironment);
                if (response.geracaoAtual !== 1) {
                    generalCount -= timerNextGeneration * (response.geracaoAtual - 1);
                }
                setEnvironmentColor();
            }

            setTimeNextEnvironment();
            setMothColor(response.mariposas);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function setCurrentEnvironment(currentEnvironment) {
    document.getElementById("currentEnvironment").textContent = currentEnvironment;
}

function setTimeNextGeneration() {
    var count = timerNextGeneration; // + 1
    var timeNextGenerationElement = document.getElementById("timeNextGeneration");
    timeNextGenerationElement.textContent = count + "s";

    var timer = setInterval(function() {
        count--;
        timeNextGenerationElement.textContent = count + "s";

        if (count === 0) {
            clearInterval(timer);
        }
    }, 1000);
}

function setTimeNextEnvironment() {
    var timeNextEnvironmentElement = document.getElementById("timeNextEnvironment");
    timeNextEnvironmentElement.textContent = generalCount + "s";

    var timer = setInterval(function() {
        generalCount--;
        timeNextEnvironmentElement.textContent = generalCount + "s";

        if (generalCount % timerNextGeneration === 0) {
            clearInterval(timer);
        }
    }, 1000);
}

function setEnvironmentColor() {
    var grid = document.getElementById("grid");
    grid.style.backgroundColor = "rgb(" + environment.red + "," + environment.green + "," + environment.blue + ")";

    var currentEnvironmentColor = document.getElementById("currentEnvironmentColor");
    currentEnvironmentColor.style.backgroundColor = "rgb(" + environment.red + "," + environment.green + "," + environment.blue + ")";

    var LastEnvironmentContainer = document.getElementById("LastEnvironmentContainer");
    if (
        lastEnvironment.red !== null ||
        lastEnvironment.green !== null ||
        lastEnvironment.blue !== null
    ) {
        var lastEnvironmentColor = document.getElementById("lastEnvironmentColor");
        lastEnvironmentColor.style.backgroundColor = "rgb(" + lastEnvironment.red + "," + lastEnvironment.green + "," + lastEnvironment.blue + ")";
        LastEnvironmentContainer.style.display = "flex";
    } else {
        LastEnvironmentContainer.style.display = "none";
    }

    lastEnvironment = environment;
}

function setMothColor(mothsColors) {
    var moths = document.querySelectorAll("div.hexagon");

    moths.forEach(function (moth, index) {
        moth.style.backgroundColor = "rgb(" + mothsColors[index].vermelho + "," + mothsColors[index].verde + "," + mothsColors[index].azul + ")"
    });
}
