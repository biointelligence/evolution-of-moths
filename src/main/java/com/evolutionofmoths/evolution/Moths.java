package com.evolutionofmoths.evolution;

import java.io.Serializable;

/**
 * Structure which represents characteristics
 * from moth inside of a context
 * @author aiello
 */
public class Moths implements Serializable {

	private static final long serialVersionUID = 221563010146915614L;
	private int red;
	private int green;
	private int blue;
	private int relevance;
	
	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	public int getRelevance() {
		return relevance;
	}

	public void setRelevance(int relevance) {
		this.relevance = relevance;
	}

}
