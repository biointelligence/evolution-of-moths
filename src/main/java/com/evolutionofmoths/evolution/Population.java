package com.evolutionofmoths.evolution;

/**
 * Class whose function is to define the mechanics of a Population.
 */
public abstract class Population {

	protected Individual[] individuals;
	protected int generation;

	// Best individual of each generation.
	protected Individual bestIndividual;

	/**
	 * Constructor
	 * @param lengthPopulation  - number of individuals in the population.
	 * @param lengthChromosome  - chromosome size of each individual.
	 * @param lengthSpaceSearch - search space size.
	 * @param generation        - current generation number.
	 * @param newPopulation     - indicates whether a new population should be populated.
	 */
	public Population(final int lengthPopulation, final int lengthChromosome, final int lengthSpaceSearch,
					  final int generation, final boolean newPopulation) {

		this.individuals = new Individual[lengthPopulation];
		this.generation = generation;

		if (newPopulation) {
			for (int p = 0; p < this.individuals.length; p++) {
				Individual individual = new Individual(lengthChromosome, lengthSpaceSearch);
				individuals[p] = individual;
			}
		}
	}

	/**
	 * Evaluation function.
	 * @param chromosome - chromosome to be evaluated.
	 * @return double - returns the evaluation score.
	 */
	public abstract double evaluate(final Chromosome chromosome);

	/**
	 * Execute sequentially perform population assessment calculations.
	 */
	public void calculatePerformsPopulationAssessment() {
		double totalFitness = calculateFitness();
		calculatePercentFitness(totalFitness);
		calculatePieChartFitness();

		// Find the individual best suited.
		this.bestIndividual = this.individuals[individuals.length - 1];
	}

	/**
	 * Calculates the Fitness of each Individual of the Population from
	 * the value of their Chromosome and returns the total fitness of the current population.
	 * @return double - returns the total fitness of the population.
	 */
	private double calculateFitness() {
		double totalFitness = 0D;

		for (Individual individual : individuals) {
			double fitness = evaluate(individual.getChromosome());

			totalFitness += fitness;
			individual.setFitness(fitness);
		}

		return totalFitness;
	}

	/**
	 * Calculates the percentage of Fitness that each individual
	 * represents within the Population.
	 * @param totalFitness - total fitness of the population.
	 */
	private void calculatePercentFitness(double totalFitness) {
		for (Individual individual : individuals) {
			if (totalFitness > 0)
			individual.setPercentFitness((individual.getFitness() * 100) / totalFitness);
		}
	}

	/**
	 * Position the Fitness percentage of each
	 * Individual within the corresponding range of a Pie Chart.
	 */
	private void calculatePieChartFitness() {
		order();
		double totalAccumulated = 0d;

		for (int p = 0; p < individuals.length; p++) {
			// The first individual and the least able of the population.
			if (p == 0) {
				totalAccumulated = individuals[p].getPercentFitness();
				individuals[p].setPercentRangePie(0, totalAccumulated);
			} else if (p == (individuals.length - 1)) {
				individuals[p].setPercentRangePie(totalAccumulated, 100);
			} else {
				individuals[p].setPercentRangePie(totalAccumulated, totalAccumulated + individuals[p].getPercentFitness());

				totalAccumulated += individuals[p].getPercentFitness();
			}
		}
	}

	/**
	 * It sorts from largest to smallest the vector of individuals
	 * in this population taking into account the value of Fitness.
	 */
	public void order() {
		for (int i = 0; i < individuals.length; i++) {
			for (int j = 0; j < (individuals.length - 1); j++) {
				if (individuals[j].getPercentFitness() > individuals[j + 1].getPercentFitness()) {
					Individual indTemp = individuals[j];
					individuals[j] = individuals[j + 1];
					individuals[j + 1] = indTemp;
				}
			}
		}
	}

	public Individual[] getIndividuals() {
		return individuals;
	}

	public void setIndividuals(Individual[] individuals) {
		this.individuals = individuals;
	}

	public void setIndividual(Individual individual, int position) {
		this.individuals[position] = individual;
	}

	public Individual getBestIndividual() {
		return bestIndividual;
	}

	public void setBestIndividual(Individual bestIndividual) {
		this.bestIndividual = bestIndividual;
	}

	public int getGeneration() {
		return generation;
	}
}
