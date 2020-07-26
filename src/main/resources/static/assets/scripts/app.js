let stompClient = null;
const timerNextGeneration = 4;
const generationPerEnvironment = 40;
let lastEnvironment = { red: null, green: null, blue: null };
let environment = { red: 0, green: 0, blue: 0 };
let generalCount = 0;
let isConnect = true;

function connect() {
    console.log(window.location.href + 'evolution');
    const socket = new SockJS(window.location.href + "evolution");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe("/topic/evolution-of-moths", function (evolutionOfMoths) {
            const response = JSON.parse(evolutionOfMoths.body);
            removeLoader();

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
    const btn = document.getElementById("button-connect");
    if (stompClient !== null && isConnect) {
        isConnect = false;
        stompClient.disconnect();
        btn.style.backgroundColor = "#43B581";
        btn.textContent = "Connect";
    } else if (!isConnect) {
        isConnect = true;
        connect();
        btn.style.backgroundColor = "#C62828";
        btn.textContent = "Disconnect";
    }
}

function scrollToContent() {
    window.scrollTo(0, document.getElementById("content").offsetTop);
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
    var timeNextEnvironmentElement = document.getElementById("timeNextEnvironment");
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

    const LastEnvironmentContainer = document.getElementById("LastEnvironmentContainer");
    if (
        lastEnvironment.red !== null ||
        lastEnvironment.green !== null ||
        lastEnvironment.blue !== null
    ) {
        const lastEnvironmentColor = document.getElementById("lastEnvironmentColor");
        lastEnvironmentColor.style.backgroundColor = "rgb(" + lastEnvironment.red + "," + lastEnvironment.green + "," + lastEnvironment.blue + ")";
        LastEnvironmentContainer.style.display = "flex";
    } else {
        LastEnvironmentContainer.style.display = "none";
    }

    lastEnvironment = environment;
}

function setMothColor(mothsColors) {
    const moths = document.querySelectorAll("div.hexagon");

    moths.forEach(function (moth, index) {
        moth.style.backgroundColor = "rgb(" + mothsColors[index].red + "," + mothsColors[index].green + "," + mothsColors[index].blue + ")"
    });
}
