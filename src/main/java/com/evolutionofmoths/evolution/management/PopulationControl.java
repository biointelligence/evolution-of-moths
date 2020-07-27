package com.evolutionofmoths.evolution.management;

import java.util.ArrayList;
import java.util.List;

import com.evolutionofmoths.evolution.MothPopulation;
import com.evolutionofmoths.evolution.Moths;

/**
 * Class which manage infos of moth population.
 * @author aiello
 */
public class PopulationControl {
	
	private int currentGeneration;
	private List<Moths> currentMothPopulation;
	private static PopulationControl control;
	private final int INDIVIDUAL_INTERVAL = 246;

	private PopulationControl() { }

	public static synchronized PopulationControl getControl() {
		if (control == null) {
			control = new PopulationControl();
		}

		return control;
	}

	public List<Moths> getCurrentMothPopulation() {
		currentMothPopulation.sort((m1, m2) -> Integer.compare(m1.getRelevance(), m2.getRelevance()));
		
		return currentMothPopulation;
	}

	public void setMothsPopulation(final MothPopulation population) {
		currentMothPopulation = new ArrayList<Moths>();
		Moths moths = null;
		int pointer = 0;

		for (int a = 0; a < population.getIndividuals().length; a++) {
			if (a == 0 ||
				a == pointer ||
				a == population.getIndividuals().length - 1
			) {
				moths = new Moths();

				moths.setRed(population.getIndividuals()[a].getChromosome().getGenes()[0].getValue());
				moths.setGreen(population.getIndividuals()[a].getChromosome().getGenes()[1].getValue());
				moths.setBlue(population.getIndividuals()[a].getChromosome().getGenes()[2].getValue());
				moths.setRelevance(population.getIndividuals().length - a);

				currentMothPopulation.add(moths);
			}
			
			if (a == pointer) {
				pointer += INDIVIDUAL_INTERVAL;
			}
		}
	}

	public int getCurrentGeneration() {
		return currentGeneration;
	}

	public void setCurrentGeneration(int currentGeneration) {
		this.currentGeneration = currentGeneration;
	}

}
