package com.evolutionofmoths.socket.websocket.client;

import java.lang.reflect.Type;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import com.evolutionofmoths.evolution.management.EvolutionOfMoths;

/**
 * Handler class responsible for sending messages to WebSocket Evolution.
 * @author aiello
 */
public class EvolutionSessionHandler extends StompSessionHandlerAdapter {

	private static final Logger log = LoggerFactory.getLogger(EvolutionSessionHandler.class);

	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
		log.debug("[Evolution Socket] - New session started: " + session.getSessionId() + " .");

		session.subscribe("/topic/evolution-of-moths", this);
		log.debug("[Evolution Socket] - Subscription on /topic/evolution-of-moths .");

		session.send("/app/evolution", new Date());
		log.debug("[Evolution Socket] - Message sent to the WebSocket server.");
		
		session.disconnect(connectedHeaders);
	}

	@Override
	public void handleException(StompSession session, StompCommand command,
								StompHeaders headers, byte[] payload, Throwable exception) {
		log.error("[Evolution Socket] - Message sent to the WebSocket server.", exception);
	}

	@Override
	public Type getPayloadType(StompHeaders headers) {
		return EvolutionOfMoths.class;
	}

	@Override
	public void handleFrame(StompHeaders headers, Object payload) {

		//final EvolutionOfMoths evolutionOfMoths = (EvolutionOfMoths) payload;

	}

}
