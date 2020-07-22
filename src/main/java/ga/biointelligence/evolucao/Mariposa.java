package ga.biointelligence.evolucao;

import java.io.Serializable;

/**
 * Estrutura que presenta as caracteristicas de uma Mariposa 
 * dentro de um contexto.
 * @author aiello
 *
 */
public class Mariposa implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 221563010146915614L;
	private int vermelho;
	private int verde;
	private int azul;
	
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
