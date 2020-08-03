package com.evolutionofmoths.evolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.evolutionofmoths.evolution.management.EnvironmentControl;
import com.evolutionofmoths.evolution.management.EvolutionControl;
import com.evolutionofmoths.evolution.management.PopulationControl;
import com.evolutionofmoths.evolution.util.GeneticRandomnessUtil;
import com.evolutionofmoths.evolution.util.IndividualSelectionUtil;
import com.evolutionofmoths.socket.websocket.client.EvolutionClientSocket;

/**
 * Class of definition of natural selection mechanisms
 * in order to identify the best products from a population.
 * @author aiello
 */
public class NaturalSelection {
	
	private static final Logger log = LoggerFactory.getLogger(NaturalSelection.class);
	
	// Defines the population size.
	private final int lengthPopulation;
	// Defines the population midpoint.
	private final int midpointOfThePopulation;
	// Defines the size of the chromosome by the amount of genes.
	private final int lengthChromosome;
	// Generic cross rate adopted.
	private final double rateCrossOver;
	// Mutation rate adopted within the population.
	private final double rateMutation;
	// Search space size.
	private final int lengthSpaceSearch;
	// Best solution found.
	private Individual bestChromosome;
	// In which generation the best solution was found.
	private int bestGeneration;

	private final int INTERVAL_BETWEEN_GENERATIONS = 4000;
	private final int MAXIMUM_AMOUNT_GENERATION_ENVIRONMENT = 40;
	
	/**
	 * Constructor
	 * @param lengthPopulation  - population size.
	 * @param lengthSpaceSearch - Search space size.
	 * @param rateCrossOver     - rate cross over to be applied.
	 * @param rateMutation      - mutation rate to be applied.
	 */
	public NaturalSelection(final int lengthPopulation, final int lengthSpaceSearch,
							final double rateCrossOver, final double rateMutation) {
		
		this.lengthPopulation = lengthPopulation;
		this.midpointOfThePopulation = lengthPopulation / 2;
		this.lengthChromosome = 3; // RGB
		this.rateCrossOver = rateCrossOver;
		this.rateMutation = rateMutation;
		this.lengthSpaceSearch = lengthSpaceSearch;
	}
		
	/**
	 * Performs the evolutionary process.
	 * @throws InterruptedException
	 */
	public void evolve() {
		int generation = 0;
		
		final IndividualSelectionUtil individualSelectionUtil = new IndividualSelectionUtil();
		final EvolutionClientSocket clientSocket = new EvolutionClientSocket();
		MothPopulation newGeneration = null;
		Individual individual1 = null;
		Individual individual2 = null;
		Individual[] children = null;
		
		final EvolutionControl evolutionControl = EvolutionControl.getEvolutionControl();
		evolutionControl.setStatus(EvolutionControl.Status.ACTIVE);
		
		// Initial configuration of EnvironmentControl
		final EnvironmentControl environmentControl = EnvironmentControl.getControl();

		environmentControl.setRed(GeneticRandomnessUtil.random.nextInt(255));
		environmentControl.setGreen(GeneticRandomnessUtil.random.nextInt(255));
		environmentControl.setBlue(GeneticRandomnessUtil.random.nextInt(255));

	    // Creates and populates randomly with Individuals the first generation.
		// Initial generation: 0.
		MothPopulation population = new MothPopulation(lengthPopulation, lengthChromosome,
				lengthSpaceSearch, generation, true);
		
		// Performs the first population assessment.
		population.calculatePerformsPopulationAssessment();
		
		// Configures the best solution of the first generation.
		this.bestChromosome = population.getBestIndividual();
		
		while (evolutionControl.getStatus() == EvolutionControl.Status.ACTIVE) {
			generation++;
			
			// Updates current population information.
			PopulationControl.getControl().setMothsPopulation(population);
			PopulationControl.getControl().setCurrentGeneration(generation);
			
			clientSocket.updateTopic();
			
			log.debug("Darwin LE - -----------------------------------------------------------");
			log.debug("Darwin LE - [Generation chromosome analysis: " +  generation + "] \n");

			int countingIndividuals = 0;
			
			if (log.isDebugEnabled() && this.bestChromosome.getFitness() > 0) {
				log.debug("Darwin LE - -------------------------------------------------------------");
				log.debug("Darwin LE - [Analysis of the population with the best adapted individual]");
				log.debug("Darwin LE - Fitness: " + this.bestChromosome.getFitness() );
				log.debug("Darwin LE - Generation: " + this.bestGeneration);
				log.debug("Darwin LE - Best Chromosome: " +
						this.bestChromosome.getChromosome().getGenes()[0].getValue() + "*" +
						this.bestChromosome.getChromosome().getGenes()[1].getValue() + "*" +
						this.bestChromosome.getChromosome().getGenes()[2].getValue()
						);
					log.debug("\n");
					
					log.debug("Darwin LE - -----------------------------------------------------------");
				for (int a = 0; a < population.getIndividuals().length; a++) {
					if (a == 0 || a == population.getIndividuals().length -1) {
						log.debug("Darwin LE - Fitness: " + population.getIndividuals()[a].getFitness());
						log.debug("Darwin LE - Percentual Fitness: " + population.getIndividuals()[a].getPercentFitness());
						log.debug("Darwin LE - Chromosome: " +
						    population.getIndividuals()[a].getChromosome().getGenes()[0].getValue() + "*" +
						    population.getIndividuals()[a].getChromosome().getGenes()[1].getValue() + "*" +
						    population.getIndividuals()[a].getChromosome().getGenes()[2].getValue()
						);
						log.debug("\n");
					}
				}
			}
			
			newGeneration = new MothPopulation(lengthPopulation, lengthChromosome,
					lengthSpaceSearch, generation, false);

			// It covers the quantity referring to half of the defined population.
			for (int pm = 0; pm < midpointOfThePopulation; pm++) {
				// In the current generation, it selects two more adapted individuals.
				 individual1 = individualSelectionUtil.addictedRoulette(population);
				 individual2 = individualSelectionUtil.addictedRoulette(population);
				
				// It performs the crossing of the fittest resulting in two individuals.
				try {
					children = individual1.crossOver(individual2, rateCrossOver);
				} catch (Exception e) {
					children = new Individual[2];
					children[0] = individual1;
					children[2] = individual1;
				}
				
				children[0].mutation(rateMutation);
				children[1].mutation(rateMutation);
				
				newGeneration.setIndividual(children[0], countingIndividuals);
				countingIndividuals++;
				
				newGeneration.setIndividual(children[1], countingIndividuals);
				countingIndividuals++;
			}
			
			if (generation == MAXIMUM_AMOUNT_GENERATION_ENVIRONMENT) {
				environmentControl.setRed(GeneticRandomnessUtil.random.nextInt(255));
				environmentControl.setGreen(GeneticRandomnessUtil.random.nextInt(255));
				environmentControl.setBlue(GeneticRandomnessUtil.random.nextInt(255));
				
				newGeneration = new MothPopulation(lengthPopulation, lengthChromosome,
						lengthSpaceSearch, generation, true);
				
				this.bestChromosome.setFitness(0);
				generation = 0;
			}
			
			// Evaluates the proficiency of the entire new population.
			newGeneration.calculatePerformsPopulationAssessment();
			
			// If the best individual in the new population is better
			// than the previous one, he becomes the best solution.
			if (newGeneration.getBestIndividual().getFitness() > bestChromosome.getFitness()) {
				this.bestChromosome = newGeneration.getBestIndividual();
				this.bestGeneration = generation;
			}
			
			// The new population to have the most adapted individuals of the generations.
			population = newGeneration;
			 
			try {
				Thread.sleep(INTERVAL_BETWEEN_GENERATIONS);
			} catch (InterruptedException e) {
				continue;
			}
		}
	}

}
