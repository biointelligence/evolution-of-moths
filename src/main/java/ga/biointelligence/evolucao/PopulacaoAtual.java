package ga.biointelligence.evolucao;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que gerencia que gerencia informacoes da populacao de mariposas atual.
 * 
 * @author aiello
 *
 */
public class PopulacaoAtual {

	private List<Mariposa> mariposas;
	private static PopulacaoAtual atual;

	private PopulacaoAtual() {
	}

	public static synchronized PopulacaoAtual getPopulacaoAtual() {

		if (atual == null) {
			return new PopulacaoAtual();
		}

		return atual;
	}

	public List<Mariposa> getMariposas() {
		return mariposas;
	}

	public void setMariposas(final PopulacaoMariposas populacao) {

		mariposas = new ArrayList<Mariposa>();
		Mariposa mariposa = null;

		for (int a = 0; a < populacao.getIndividuos().length; a++) {

			if (a == 0 || a == populacao.getIndividuos().length - 1) {

				mariposa = new Mariposa();

				mariposa.setVermelho(populacao.getIndividuos()[a].getCromossomo().getGenes()[0].getValor());
				mariposa.setVerde(populacao.getIndividuos()[a].getCromossomo().getGenes()[1].getValor());
				mariposa.setAzul(populacao.getIndividuos()[a].getCromossomo().getGenes()[2].getValor());

			}
		}
	}

}
