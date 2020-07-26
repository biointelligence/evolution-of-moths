package ga.biointelligence.virtualpopulation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ga.biointelligence.evolution.NaturalSelection;

@SpringBootApplication
public class VirtualPopulationApplication {
	
	private static final int AMOUNT_MOTHS = 15000;
	private static final int SEARCH_SPACE = 255;
	private static final double RATE_CROSS_OVER = 0.5;
	private static final double RATE_MUTATION = 0.03;

	public static void main(String[] args) {
		SpringApplication.run(VirtualPopulationApplication.class, args);
		
		// The Evolutionary process begins with a population of 15000 Moths.
		final NaturalSelection naturalSelection = new NaturalSelection(
				AMOUNT_MOTHS, SEARCH_SPACE, RATE_CROSS_OVER, RATE_MUTATION
		);
		naturalSelection.evolve();
	}

}
