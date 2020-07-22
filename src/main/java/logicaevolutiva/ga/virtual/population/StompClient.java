package logicaevolutiva.ga.virtual.population;

import java.util.Scanner;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;

import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import logicaevolutiva.ga.virtual.population.config.handler.StompSessionHandler;

public class StompClient {

	private static String URL = "ws://localhost:2000/evolution";

	public static void main(String[] args) {

		WebSocketClient client = new StandardWebSocketClient();
		WebSocketStompClient stompClient = new WebSocketStompClient(client);

		stompClient.setMessageConverter(new MappingJackson2MessageConverter());

		StompSessionHandler sessionHandler = new StompSessionHandler();
		stompClient.connect(URL, sessionHandler);
		
		new Scanner(System.in).nextLine(); // Don't close immediately.

	}
}
