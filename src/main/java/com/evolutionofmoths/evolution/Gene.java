package com.evolutionofmoths.evolution;

/**
 * Class whose function is to define the structure of a Gene.
 * The characteristics of an individual are made up of one or more genes.
*/
public class Gene {
	
	private int value;
	
	public Gene(final int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
