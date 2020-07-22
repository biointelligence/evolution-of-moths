package ga.biointelligence.evolucao;

import java.io.Serializable;

/**
 * Classe que tem como finalidade consolidar
 * e informacao os dados relavantes do processo evolutivo.
 * @author aiello
 *
 */
public class EvolutionOfMoths implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6710003621941705843L;
	private int geracaoAtual;
	
	public int getGeracaoAtual() {
		return geracaoAtual;
	}

	public void setGeracaoAtual(int geracaoAtual) {
		this.geracaoAtual = geracaoAtual;
	}

}
