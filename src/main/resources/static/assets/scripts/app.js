var stompClient = null;
var timerNextGeneration = 4;
var generationPerEnvironment = 40;
var lastEnvironment = { red: null, green: null, blue: null };
var environment = { red: 0, green: 0, blue: 0 };
var generalCount = 0;

function connect() {
    console.log(document.URL);
    console.log(window.location.href);
    var socket = new SockJS(window.location.href + 'evolution');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/evolution-of-moths', function (evolutionOfMoths) {
            var response = JSON.parse(evolutionOfMoths.body);
            /*console.log(response);
            console.log(response.currentGeneration);
            console.log(response.redEnvironment);
            console.log(response.greenEnvironment);
            console.log(response.blueEnvironment);
            console.log(response.moths);*/

            setTimeNextGeneration();
            setCurrentEnvironment(response.currentGeneration);

            if (
                environment.red !== response.redEnvironment ||
                environment.green !== response.greenEnvironment ||
                environment.blue !== response.blueEnvironment
            ) {
                environment = {
                    red: response.redEnvironment,
                    green: response.greenEnvironment,
                    blue: response.blueEnvironment
                };
                generalCount = (timerNextGeneration * generationPerEnvironment);
                if (response.currentGeneration !== 1) {
                    generalCount -= timerNextGeneration * (response.currentGeneration - 1);
                }
                setEnvironmentColor();
            }

            setTimeNextEnvironment();
            setMothColor(response.moths);
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
        moth.style.backgroundColor = "rgb(" + mothsColors[index].red + "," + mothsColors[index].green + "," + mothsColors[index].blue + ")"
    });
}
