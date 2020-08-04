package com.evolutionofmoths.socket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.evolutionofmoths.evolution.NaturalSelection;
import com.evolutionofmoths.evolution.management.EvolutionControl;

@SpringBootApplication
public class EvolutionOfMothsApplication {
	
	private static final int AMOUNT_MOTHS = 15000;
	private static final int SEARCH_SPACE = 255;
	private static final double RATE_CROSS_OVER = 0.5;
	private static final double RATE_MUTATION = 0.02;

	public static void main(String[] args) {
		SpringApplication.run(EvolutionOfMothsApplication.class, args);
		
		final EvolutionControl evolutionControl = EvolutionControl.getEvolutionControl();
		evolutionControl.setStatus(EvolutionControl.Status.ACTIVE);
		// The Evolutionary process begins with a population of 15000 Moths.
		NaturalSelection naturalSelection  = new NaturalSelection(
				AMOUNT_MOTHS, SEARCH_SPACE, RATE_CROSS_OVER, RATE_MUTATION);
		
		while (evolutionControl.getStatus()  == EvolutionControl.Status.ACTIVE) {
			naturalSelection.evolve();
		}
		
	}

}
