package ga.biointelligence.virtualpopulation.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

import ga.biointelligence.evolucao.Mariposa;
import ga.biointelligence.evolucao.gerenciamento.ControleAmbiente;
import ga.biointelligence.evolucao.gerenciamento.ControlePopulacao;
import ga.biointelligence.evolucao.gerenciamento.EvolutionOfMoths;

@Controller
@EnableScheduling
public class EvolutionController {

	private static final Logger log = LoggerFactory.getLogger(EvolutionController.class);

	// Escutando as mensagens do topico evolutionmoths.
	@MessageMapping("/evolution")
	@SendTo("/topic/evolutionofmoths")
	@CrossOrigin(origins = "*")
	public EvolutionOfMoths getMariposa(final Date dataMensagem) throws Exception {

		final EvolutionOfMoths evolutionOfMoths = new EvolutionOfMoths();

		final List<Mariposa> mariposas = ControlePopulacao.getControle().getPopulacaoAtualMariosas();
		evolutionOfMoths.setMariposas(mariposas);
		evolutionOfMoths.setGeracaoAtual(ControlePopulacao.getControle().getGeracaoAtual());
		
		evolutionOfMoths.setAmbienteVermelho(ControleAmbiente.getControle().getVermelho());
		evolutionOfMoths.setAmbienteVerde(ControleAmbiente.getControle().getVerde());
		evolutionOfMoths.setAmbienteAzul(ControleAmbiente.getControle().getAzul());


		return evolutionOfMoths;
	}

}
