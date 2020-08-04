package com.evolutionofmoths.evolution.management;

/**
 * Class which management the evolution control.
 * @author aiello
 */
public final class EvolutionControl {
	
	private Status status;
	
	public enum Status {
		ACTIVE, INACTIVE 
	}
	
	private EvolutionControl() { }
	private static EvolutionControl evolutionControl;

	public static synchronized EvolutionControl getEvolutionControl() {
		if (evolutionControl != null) {
			return evolutionControl;
		} 
		
		return new EvolutionControl();
	}
	
	public Status getStatus() {
		return status; 
	}
	
	public void setStatus(final Status status) {
		this.status = status;
	}

}


