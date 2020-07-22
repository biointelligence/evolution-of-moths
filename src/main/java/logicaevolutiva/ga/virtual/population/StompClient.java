package logicaevolutiva.ga.virtual.population;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import logicaevolutiva.ga.virtual.population.config.handler.StompSessionHandler;

public class StompClient {

    private static String URL = "ws://localhost:2000/evolution-websocket";

    public static void main(String[] args) {
    	
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);

        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        
        for (int a = 0; a < 2000 ; a++) {
        	 StompSessionHandler sessionHandler = new StompSessionHandler();
             stompClient.connect(URL, sessionHandler);
        }

        
        try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}
