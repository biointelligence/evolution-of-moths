package ga.biointelligence.virtualpopulation.websocket.client;

import java.net.InetAddress;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

//Classe responsavel envio das mensagens para o WebSocket Evolution.
public class EvolutionClientSocket {

	public void atualizarTopico() {

		final String url = "ws://" + InetAddress.getLoopbackAddress().getHostAddress() + ":2000/evolution";

		WebSocketClient client = new StandardWebSocketClient();
		WebSocketStompClient stompClient = new WebSocketStompClient(client);

		stompClient.setMessageConverter(new MappingJackson2MessageConverter());

		EvolutionSessionHandler sessionHandler = new EvolutionSessionHandler();
		stompClient.connect(url, sessionHandler);
	}
}
