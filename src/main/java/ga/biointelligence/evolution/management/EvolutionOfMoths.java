package ga.biointelligence.evolution.management;

import java.io.Serializable;
import java.util.List;

import ga.biointelligence.evolution.Moths;

/**
 * Class that aims to consolidate and inform the relevant
 * data of the evolutionary process.
 * @author aiello
 */
public class EvolutionOfMoths implements Serializable {

	private static final long serialVersionUID = -6710003621941705843L;
	private int currentGeneration;
	
	private int redEnvironment;
	private int greenEnvironment;
	private int BlueEnvironment;
	
	private List<Moths> moths;
	
	public int getCurrentGeneration() {
		return currentGeneration;
	}

	public void setCurrentGeneration(int currentGeneration) {
		this.currentGeneration = currentGeneration;
	}

	public List<Moths> getMoths() {
		return moths;
	}

	public void setMoths(List<Moths> moths) {
		this.moths = moths;
	}

	public int getRedEnvironment() {
		return redEnvironment;
	}

	public void setRedEnvironment(int redEnvironment) {
		this.redEnvironment = redEnvironment;
	}

	public int getGreenEnvironment() {
		return greenEnvironment;
	}

	public void setGreenEnvironment(int greenEnvironment) {
		this.greenEnvironment = greenEnvironment;
	}

	public int getBlueEnvironment() {
		return BlueEnvironment;
	}

	public void setBlueEnvironment(int blueEnvironment) {
		this.BlueEnvironment = blueEnvironment;
	}

}
