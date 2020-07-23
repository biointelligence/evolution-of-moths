package ga.biointelligence.evolucao.gerenciamento;

import java.io.Serializable;
import java.util.List;

import ga.biointelligence.evolucao.Mariposa;

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
	
	private int ambienteVermelho;
	private int ambienteVerde;
	private int ambienteAzul;
	
	private List<Mariposa> mariposas;
	
	public int getGeracaoAtual() {
		return geracaoAtual;
	}

	public void setGeracaoAtual(int geracaoAtual) {
		this.geracaoAtual = geracaoAtual;
	}

	public List<Mariposa> getMariposas() {
		return mariposas;
	}

	public void setMariposas(List<Mariposa> mariposas) {
		this.mariposas = mariposas;
	}

	public int getAmbienteVermelho() {
		return ambienteVermelho;
	}

	public void setAmbienteVermelho(int ambienteVermelho) {
		this.ambienteVermelho = ambienteVermelho;
	}

	public int getAmbienteVerde() {
		return ambienteVerde;
	}

	public void setAmbienteVerde(int ambienteVerde) {
		this.ambienteVerde = ambienteVerde;
	}

	public int getAmbienteAzul() {
		return ambienteAzul;
	}

	public void setAmbienteAzul(int ambienteAzul) {
		this.ambienteAzul = ambienteAzul;
	}

}
