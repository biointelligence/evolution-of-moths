package ga.biointelligence.evolucao;

/**
 * Classe que gerencia o controle da Evolucao.
 * @author aiello
 *
 */
public final class ControleEvolucao {
	
	private Status status;

	public enum Status {
		ATIVA , INATIVA
	}
	
	private ControleEvolucao() {}
	private static ControleEvolucao controleEvolucao;

	
	public static synchronized  ControleEvolucao getControleEvolucao() {
	
		if (controleEvolucao != null) {
			return controleEvolucao;
		} 
		
		return new ControleEvolucao();
	}
	
	public Status getStatus() {
		return status; 
	}
	
	public void setStatus(final Status status) {
		this.status = status;
	}

}


