package com.evolutionofmoths.evolution.util;

import com.evolutionofmoths.evolution.Individual;
import com.evolutionofmoths.evolution.Population;

public class IndividualSelectionUtil {
	
	/**
	 * Selection mechanism using the addicted roulette method.
	 * @param population  - population to have a selected individual.
	 * @return Individual - the selected individual returns.
	 */
	public Individual addictedRoulette(final Population population) {
	    // The point within the Pie Chart drawn from 0 to 100.
	    double drawnPoint = GeneticRandomnessUtil.random.nextDouble() * 100;
	    Individual selected = null;
	    
	    if (drawnPoint > 99) 
	    	drawnPoint = 100;
	    
	    
	    for (Individual individual : population.getIndividuals()) {
	    	
	    	double initialRange = individual.getPieFitness()[0];
	    	double finalRange = individual.getPieFitness()[1];
	    	
	        if (drawnPoint >= 
	        		initialRange &&
	            drawnPoint <= finalRange) {
	            selected = individual;
	            break;
	        }
	    }
	    
	    return selected;
    }
	

public static void main(String...strings ) {
	
	double drawnPoint = GeneticRandomnessUtil.random.nextDouble() * 100;
	
	while (drawnPoint < 99.9) {
		drawnPoint = GeneticRandomnessUtil.random.nextDouble() * 100;
		System.out.println(drawnPoint);
	}}

}


	
