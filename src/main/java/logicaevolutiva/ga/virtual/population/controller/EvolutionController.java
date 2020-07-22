package logicaevolutiva.ga.virtual.population.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

import ga.biointelligence.evolucao.Mariposa;
import ga.biointelligence.evolucao.gerenciamento.ControlePopulacaoAtual;
import ga.biointelligence.evolucao.gerenciamento.EvolutionOfMoths;
import logicaevolutiva.ga.virtual.population.config.WebSocketConfig;

@Controller
@EnableScheduling
public class EvolutionController {

	private static final Logger log = LoggerFactory.getLogger(EvolutionController.class);

	// Escutando as mensagens do topico evolutionmoths.
	@MessageMapping("/evolution")
	@SendTo("/topic/evolutionofmoths")
	public EvolutionOfMoths getMariposa(final Date dataMensagem) throws Exception {

		final EvolutionOfMoths evolutionOfMoths = new EvolutionOfMoths();

		final List<Mariposa> mariposas = ControlePopulacaoAtual.getPopulacaoAtual().getMariposas();
		evolutionOfMoths.setMariposas(mariposas);
		evolutionOfMoths.setGeracaoAtual(2000);


		return evolutionOfMoths;
	}

}
