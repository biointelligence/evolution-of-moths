package com.evolutionofmoths.socket.websocket.client;

import java.net.InetAddress;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

// Class responsible for sending messages to WebSocket Evolution.
public class EvolutionClientSocket {

	public void updateTopic() {
		final String url = "ws://" + InetAddress.getLoopbackAddress().getHostAddress() + ":2000/evolution";

		WebSocketClient client = new StandardWebSocketClient();
		WebSocketStompClient stompClient = new WebSocketStompClient(client);

		stompClient.setMessageConverter(new MappingJackson2MessageConverter());

		EvolutionSessionHandler sessionHandler = new EvolutionSessionHandler();
		stompClient.connect(url, sessionHandler);
	}

}
