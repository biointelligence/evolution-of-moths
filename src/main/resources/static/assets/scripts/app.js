const timerNextGeneration = 4;
const generationPerEnvironment = 40;
let stompClient = null;
let environment = { red: 0, green: 0, blue: 0 };
let generalCount = 0;
let isConnect = true;

function connect(reconnect) {
    console.log(window.location.href + 'evolution');
    var hostName = new URL(window.location.href).hostname;
    const port = new URL(window.location.href).port;

    if (port !== "") {
      hostName = hostName + ":" + port;
    }

    const socket = new SockJS("http://" + hostName + "/evolution");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe("/topic/evolution-of-moths", function (evolutionOfMoths) {
            const response = JSON.parse(evolutionOfMoths.body);
            console.log(response);
            removeLoader();

            setTimeNextGeneration();
            setCurrentEnvironment(response.currentGeneration);

            if (
                environment.red !== response.redEnvironment ||
                environment.green !== response.greenEnvironment ||
                environment.blue !== response.blueEnvironment ||
                reconnect
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
    const buttons = document.querySelectorAll("span.button-connect");
    if (stompClient !== null && isConnect) {
        isConnect = false;
        stompClient.disconnect();
        buttons.forEach(function (btn) {
            btn.style.backgroundColor = "#43B581";
            btn.textContent = "Connect";
        });
    } else if (!isConnect) {
        isConnect = true;
        connect(true);
        buttons.forEach(function (btn) {
            btn.style.backgroundColor = "#C62828";
            btn.textContent = "Disconnect";
        });
    }
}

function removeLoader() {
    const loader = document.getElementById("loader");
    loader.style.opacity = "0";
    setTimeout(function () {
        loader.style.display = "none";
    }, 200)

    document.getElementById("main").classList.add("transition-main");
}

function setCurrentEnvironment(currentEnvironment) {
    document.getElementById("currentEnvironment").textContent = currentEnvironment;
}

function setTimeNextGeneration() {
    let count = timerNextGeneration; // + 1
    const timeNextGenerationElement = document.getElementById("timeNextGeneration");
    timeNextGenerationElement.textContent = count + "s";

    const timer = setInterval(function () {
        count--;
        timeNextGenerationElement.textContent = count + "s";

        if (count === 0) {
            clearInterval(timer);
        }
    }, 1000);
}

function setTimeNextEnvironment() {
    const timeNextEnvironmentElement = document.getElementById("timeNextEnvironment");
    timeNextEnvironmentElement.textContent = generalCount + "s";

    const timer = setInterval(function () {
        generalCount--;
        timeNextEnvironmentElement.textContent = generalCount + "s";

        if (generalCount % timerNextGeneration === 0) {
            clearInterval(timer);
        }
    }, 1000);
}

function setEnvironmentColor() {
    const grid = document.getElementById("grid");
    grid.style.backgroundColor = "rgb(" + environment.red + "," + environment.green + "," + environment.blue + ")";

    const currentEnvironmentColor = document.getElementById("currentEnvironmentColor");
    currentEnvironmentColor.style.backgroundColor = "rgb(" + environment.red + "," + environment.green + "," + environment.blue + ")";
}

function setMothColor(mothsColors) {
    const moths = document.querySelectorAll("div.hexagon");

    moths.forEach(function (moth, index) {
        moth.style.backgroundColor = "rgb(" + mothsColors[index].red + "," + mothsColors[index].green + "," + mothsColors[index].blue + ")";
    });
}
