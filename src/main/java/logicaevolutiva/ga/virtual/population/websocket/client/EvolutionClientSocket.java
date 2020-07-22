package logicaevolutiva.ga.virtual.population.websocket.client;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

//Classe responsavel envio das mensagens para o WebSocket Evolution.
@Component
public class EvolutionClientSocket {
	
	private static String URL = "ws://localhost:2000/evolution";

	//private final long SEGUNDO = 1000;
	//private final long INTERVALO = 10 * SEGUNDO;

	//@Scheduled(fixedDelay = INTERVALO)
	public void atualizarTopico() {

		WebSocketClient client = new StandardWebSocketClient();
		WebSocketStompClient stompClient = new WebSocketStompClient(client);

		stompClient.setMessageConverter(new MappingJackson2MessageConverter());

		EvolutionSessionHandler sessionHandler = new EvolutionSessionHandler();
		stompClient.connect(URL, sessionHandler);
	}
}
