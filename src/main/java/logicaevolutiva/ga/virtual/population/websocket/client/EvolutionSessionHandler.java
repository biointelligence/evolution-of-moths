package logicaevolutiva.ga.virtual.population.websocket.client;

import java.lang.reflect.Type;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import ga.biointelligence.evolucao.gerenciamento.EvolutionOfMoths;

/**
 * Classe Handler reponsavel em enviar mensagens ao WebSocket Evolution.
 * @author aiello
 *
 */
public class EvolutionSessionHandler extends StompSessionHandlerAdapter {

	private static final Logger log = LoggerFactory.getLogger(EvolutionSessionHandler.class);

	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {

		log.info("[Evolution Socket] - Nova sessao iniciada: " + session.getSessionId() + " .");
		
		session.subscribe("/topic/evolutionofmoths", this);
		log.info("[Evolution Socket] - Subscricao em /topic/evolutionofmoths .");

		session.send("/app/evolution", new Date());
		log.info("[Evolution Socket] - Mensagem enviada ao servidor WebSocket.");
	}

	@Override
	public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload,
			Throwable exception) {
		log.error("[Evolution Socket] - Ocorreu uma falha na comunicacao com o Websocket.", exception);
	}

	@Override
	public Type getPayloadType(StompHeaders headers) {
		return EvolutionOfMoths.class;
	}

	@Override
	public void handleFrame(StompHeaders headers, Object payload) {
		EvolutionOfMoths evolutionOfMoths = (EvolutionOfMoths) payload;
		
		log.info("---------------------- Gerecao " + evolutionOfMoths.getGeracaoAtual() + "-------------------");
		
		evolutionOfMoths.getMariposas().forEach(m -> {
			
			log.info(String.valueOf(" Vermelho " + m.getVermelho()) +
					 String.valueOf(" Verde "    + m.getVerde())  +
			         String.valueOf(" Azul "     + m.getAzul()));
		});
		
	}

}
