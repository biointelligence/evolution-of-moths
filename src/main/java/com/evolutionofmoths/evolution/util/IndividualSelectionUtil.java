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
	    
	    for (Individual individual : population.getIndividuals()) {
	        if (drawnPoint >= individual.getPieFitness()[0] &&
	            drawnPoint <= individual.getPieFitness()[1]) {
	            selected = individual;
	            break;
	        }
	    }
	    
	    return selected;
    }

}
