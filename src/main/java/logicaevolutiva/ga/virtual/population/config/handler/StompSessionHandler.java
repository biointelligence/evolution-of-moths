package logicaevolutiva.ga.virtual.population.config.handler;

import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import ga.biointelligence.evolucao.EvolutionOfMoths;

public class StompSessionHandler extends StompSessionHandlerAdapter {

	private static final Logger log = LoggerFactory.getLogger(StompSessionHandler.class);

	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {

		log.info("[Evolution Socket] - Nova sessao iniciada: " + session.getSessionId());
		
		session.subscribe("/topic/evolutionofmoths", this);
		log.info("[Evolution Socket] - Subscricao em /topic/evolutionofmoths");

		session.send("/app/evolution", "DA92A37C668AC18A1756358D3BCD73A34D04A395");
		log.info("[Evolution Socket] - Mensagem enviada ao servidor WebSocket");
	}

	@Override
	public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload,
			Throwable exception) {
		log.error("[Evolution Socket] - Ocorreu uma falha na comunicacao com o Websocket", exception);
	}

	@Override
	public Type getPayloadType(StompHeaders headers) {
		return EvolutionOfMoths.class;
	}

}
