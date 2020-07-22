package logicaevolutiva.ga.virtual.population.config.handler;

import java.lang.reflect.Type;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import logicaevolutiva.ga.virtual.population.controller.HelloMessage;

public class StompSessionHandler extends StompSessionHandlerAdapter {

	private static final Logger log = LoggerFactory.getLogger(StompSessionHandler.class);

	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {

		log.info("[Evolution Socket] - Nova sessao iniciada: " + session.getSessionId());

		session.subscribe("/topic/evolution", this);
		log.info("[Evolution Socket] - Subscricao em /topic/evolution");

		session.send("/app/evolutionofmoths", evolutionOfMoths());
		log.info("[Evolution Socket] - Mensagem enviada ao servidor WebSocket");
	}

	@Override
	public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload,
			Throwable exception) {
		log.error("[Evolution Socket] - Ocorreu uma falha no Web Socket!", exception);
	}

	@Override
	public Type getPayloadType(StompHeaders headers) {
		return Message.class;
	}

	/**
	 * A sample message instance.
	 * 
	 * @return instance of <code>Message</code>
	 */
	private HelloMessage getSampleMessage() {
		HelloMessage msg = new HelloMessage();
		msg.setName("Aiello " + (new Date()).getTime());
		return msg;
	}

	private String  evolutionOfMoths() {

		System.out.println("Mariposas voando");
		
		return "Mariposas";
	}

}
