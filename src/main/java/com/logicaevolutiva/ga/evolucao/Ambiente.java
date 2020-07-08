package com.logicaevolutiva.ga.evolucao;

import java.io.Serializable;


/**
 * Estrutura que presenta as caracteristicas de um Ambiente onde vivem as Mariposas 
 * dentro de um contexto.
 * @author aiello
 *
 */
public final class Ambiente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1619329571370661501L;
	private int vermelho;
	private int verde;
	private int azul;
	
	private static Ambiente ambiente;
	
	private Ambiente() {}
	
	public static synchronized Ambiente getAmbienteAtual() {
		
		if (ambiente == null) {
			load();
		}
		
		return  ambiente;
	}
	
	private static void load() {
		ambiente = new Ambiente();
	}
	
	public int getVermelho() {
		return vermelho;
	}
	public void setVermelho(int vermelho) {
		this.vermelho = vermelho;
	}
	public int getVerde() {
		return verde;
	}
	public void setVerde(int verde) {
		this.verde = verde;
	}
	public int getAzul() {
		return azul;
	}
	public void setAzul(int azul) {
		this.azul = azul;
	}

}
