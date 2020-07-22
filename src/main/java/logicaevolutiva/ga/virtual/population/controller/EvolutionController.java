package logicaevolutiva.ga.virtual.population.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import ga.biointelligence.evolucao.EvolutionOfMoths;
import ga.biointelligence.evolucao.Mariposa;

@Controller
@EnableScheduling 
public class EvolutionController {
	
	private final String clientHash = "DA92A37C668AC18A1756358D3BCD73A34D04A395";

	@MessageMapping("/evolution")
	@SendTo("/topic/evolutionofmoths")
	public EvolutionOfMoths getMariposa(final String clientHash) throws Exception {
		
		EvolutionOfMoths evolutionOfMoths = null;
		
		if (this.clientHash.equals(clientHash)) {
			evolutionOfMoths = new EvolutionOfMoths();
			evolutionOfMoths.setGeracaoAtual(2000);
			System.out.println("QUANTIDADE " + evolutionOfMoths.getGeracaoAtual());
		}
		
		return evolutionOfMoths;
	}
	
	
}
