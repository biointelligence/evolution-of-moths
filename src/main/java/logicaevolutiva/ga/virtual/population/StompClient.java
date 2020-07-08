package logicaevolutiva.ga.virtual.population;

import java.util.Scanner;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import logicaevolutiva.ga.virtual.population.config.handler.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

public class StompClient {

    private static String URL = "ws://localhost:2000/gs-guide-websocket";

    public static void main(String[] args) {
    	
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);

        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSessionHandler sessionHandler = new StompSessionHandler();
        stompClient.connect(URL, sessionHandler);
        
        try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}
