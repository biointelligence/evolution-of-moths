package com.evolutionofmoths.evolution;

import com.evolutionofmoths.evolution.management.EnvironmentControl;

/**
 * Class that defines the population to solve the problem
 * regarding the identification of the best products.
 * @author aiello
 */
public class MothPopulation extends Population {

	public MothPopulation(final int lengthPopulation, final int lengthChromosome, final int lengthSpaceSearch,
						  final int generation, boolean newPopulation) {

		super(lengthPopulation, lengthChromosome, lengthSpaceSearch, generation, newPopulation);

		// If it is the beginning population of the evolutionary growth
		if (newPopulation) {
			calculatePerformsPopulationAssessment();
			this.bestIndividual = this.individuals[individuals.length - 1];
		}
	}
	
	private int calculateDistance(final int valueEnvironment, final int valueGene) {
		int distance = valueEnvironment - valueGene;
		return distance < 0 ? distance*-1 : distance;
	}
	
	private double calculateScore(final int distance) {
		int baseScore = 1000;
		int score = 0;
		
		if (distance > 25) {
			// It penalizes the Gene who is at a distance greater than approximately 10% of the color value of the environment.
			score = (baseScore - distance) / 2;
		} else if (distance  == 0) {
			// Rewards the Gene that finds the Color of the environment.
			score = baseScore + 255;
		} else { 
			// Calculates the score based on the approximate value of the environment.
			score = baseScore - distance;
		}
		
		return score;
	}

	@Override
	public double evaluate(Chromosome chromosome) {
		
		// EnvironmentControl
		int valueRedEnvironment = EnvironmentControl.getControl().getRed();
		int valueGreenEnvironment = EnvironmentControl.getControl().getGreen();
		int valueBlueEnvironment = EnvironmentControl.getControl().getBlue();
		
		// Genes value
		int valueRedGene = chromosome.getGenes()[0].getValue();
		int valueGreenGene = chromosome.getGenes()[1].getValue();
		int valueBlueGene = chromosome.getGenes()[2].getValue();
		
		// Defining target distances
		int redDistance = calculateDistance(valueRedEnvironment, valueRedGene);
		int greenDistance = calculateDistance(valueGreenEnvironment, valueGreenGene);
		int blueDistance = calculateDistance(valueBlueEnvironment, valueBlueGene);

        return calculateScore(redDistance) + calculateScore(greenDistance) + calculateScore(blueDistance);
	}
	
}
