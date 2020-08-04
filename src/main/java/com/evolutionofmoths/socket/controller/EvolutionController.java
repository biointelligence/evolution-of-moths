package com.evolutionofmoths.socket.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.evolutionofmoths.evolution.Moths;
import com.evolutionofmoths.evolution.management.EnvironmentControl;
import com.evolutionofmoths.evolution.management.EvolutionOfMoths;
import com.evolutionofmoths.evolution.management.PopulationControl;

@Controller
@EnableScheduling
@Scope("websocket")
public class EvolutionController {

	private static final Logger log = LoggerFactory.getLogger(EvolutionController.class);

	// Listening to the messages of the topic evolution of months.
	@MessageMapping("/evolution")
	@SendTo("/topic/evolution-of-moths")
	@CrossOrigin(origins = "*")
	public EvolutionOfMoths getMoths(final Date dataMessage) throws Exception {
		final EvolutionOfMoths evolutionOfMoths = new EvolutionOfMoths();

		final List<Moths> moths = PopulationControl.getControl().getCurrentMothPopulation();
		evolutionOfMoths.setMoths(moths);
		evolutionOfMoths.setCurrentGeneration(PopulationControl.getControl().getCurrentGeneration());
		
		evolutionOfMoths.setRedEnvironment(EnvironmentControl.getControl().getRed());
		evolutionOfMoths.setGreenEnvironment(EnvironmentControl.getControl().getGreen());
		evolutionOfMoths.setBlueEnvironment(EnvironmentControl.getControl().getBlue());

		return evolutionOfMoths;
	}

}
