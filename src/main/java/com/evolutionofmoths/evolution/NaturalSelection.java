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
 * Class of definition of natural selection mechanisms in order to identify the
 * best products from a population.
 * 
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
	private final int INTERVAL_BETWEEN_GENERATIONS = 4000;
	private final int MAXIMUM_AMOUNT_GENERATION_ENVIRONMENT = 40;

	/**
	 * Constructor
	 * 
	 * @param lengthPopulation  - population size.
	 * @param lengthSpaceSearch - Search space size.
	 * @param rateCrossOver     - rate cross over to be applied.
	 * @param rateMutation      - mutation rate to be applied.
	 */
	public NaturalSelection(final int lengthPopulation, final int lengthSpaceSearch, final double rateCrossOver,
			final double rateMutation) {

		this.lengthPopulation = lengthPopulation;
		this.midpointOfThePopulation = lengthPopulation / 2;
		this.lengthChromosome = 3; // RGB
		this.rateCrossOver = rateCrossOver;
		this.rateMutation = rateMutation;
		this.lengthSpaceSearch = lengthSpaceSearch;
	}

	/**
	 * Performs the evolutionary process.
	 */
	public EvolutionControl.Status evolve() {
		int generation = 0;

		final IndividualSelectionUtil individualSelectionUtil = new IndividualSelectionUtil();
		MothPopulation newGeneration = null;
		Individual individual1 = null;
		Individual individual2 = null;
		Individual[] children = null;

		// Initial configuration of EnvironmentControl
		final EnvironmentControl environmentControl = EnvironmentControl.getControl();

		environmentControl.setRed(GeneticRandomnessUtil.random.nextInt(255));
		environmentControl.setGreen(GeneticRandomnessUtil.random.nextInt(255));
		environmentControl.setBlue(GeneticRandomnessUtil.random.nextInt(255));

		// Creates and populates randomly with Individuals the first generation.
		// Initial generation: 0.
		MothPopulation population = new MothPopulation(lengthPopulation, lengthChromosome, lengthSpaceSearch,
				generation, true);

		// Performs the first population assessment.
		population.calculatePerformsPopulationAssessment();

		// Configures the best solution of the first generation.
		this.bestChromosome = population.getBestIndividual();
		
		EvolutionClientSocket clientSocket = new EvolutionClientSocket();

		while (generation < MAXIMUM_AMOUNT_GENERATION_ENVIRONMENT) {
			generation++;

			// Updates current population information.
			PopulationControl.getControl().setMothsPopulation(population);
			PopulationControl.getControl().setCurrentGeneration(generation);
			
			clientSocket.updateTopic();

			int countingIndividuals = 0;

			newGeneration = new MothPopulation(lengthPopulation, lengthChromosome, lengthSpaceSearch, generation,
					false);

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
					children[1] = individual2;
				}

				children[0].mutation(rateMutation);
				children[1].mutation(rateMutation);

				newGeneration.setIndividual(children[0], countingIndividuals);
				countingIndividuals++;

				newGeneration.setIndividual(children[1], countingIndividuals);
				countingIndividuals++;
			}

			// }

			// Evaluates the proficiency of the entire new population.
			newGeneration.calculatePerformsPopulationAssessment();

			// If the best individual in the new population is better
			// than the previous one, he becomes the best solution.
			if (newGeneration.getBestIndividual().getFitness() > bestChromosome.getFitness()) {
				this.bestChromosome = newGeneration.getBestIndividual();
			}

			// The new population to have the most adapted individuals of the generations.
			population = newGeneration;

			/*try {
				Thread.sleep(INTERVAL_BETWEEN_GENERATIONS);
			} catch (InterruptedException e) {
				continue;
			}
*/
		}

		return EvolutionControl.Status.ACTIVE;
	}

}
