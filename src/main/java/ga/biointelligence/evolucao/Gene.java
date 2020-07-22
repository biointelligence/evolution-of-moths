package ga.biointelligence.evolucao;


/**
*
* Classe que tem como funcao definir a estrutura de um Gene. 
* As caracteristicas de um individuo sao constituidas por um ou mais Genes.
*/
public class Gene {
	
	private int valor;
	
	public Gene(final int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
}
