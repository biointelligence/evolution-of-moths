package ga.biointelligence.evolucao.gerenciamento;

import java.util.ArrayList;
import java.util.List;

import ga.biointelligence.evolucao.Mariposa;
import ga.biointelligence.evolucao.PopulacaoMariposas;

/**
 * Classe que gerencia informacoes da populacao de mariposas atual.
 * 
 * @author aiello
 *
 */
public class ControlePopulacaoAtual {

	private List<Mariposa> mariposas;
	private static ControlePopulacaoAtual atual;
	private final int INTERVALO_INDIVIDUO = 246;

	private ControlePopulacaoAtual() {
	}

	public static synchronized ControlePopulacaoAtual getPopulacaoAtual() {

		if (atual == null) {
			atual = new ControlePopulacaoAtual();
		}

		return atual;
	}

	public List<Mariposa> getMariposas() {
		return mariposas;
	}

	public void setPopulacaoMariposas(final PopulacaoMariposas populacao) {

		mariposas = new ArrayList<Mariposa>();
		Mariposa mariposa = null;
		int ponteiro = 0;
	
		for (int a = 0; a < populacao.getIndividuos().length; a++) {

			if (a == 0 || 
					a == ponteiro ||
					a == populacao.getIndividuos().length - 1) {

				mariposa = new Mariposa();

				mariposa.setVermelho(populacao.getIndividuos()[a].getCromossomo().getGenes()[0].getValor());
				mariposa.setVerde(populacao.getIndividuos()[a].getCromossomo().getGenes()[1].getValor());
				mariposa.setAzul(populacao.getIndividuos()[a].getCromossomo().getGenes()[2].getValor());
				mariposas.add(mariposa);

			}
			
			if (a == ponteiro) {
				ponteiro+= INTERVALO_INDIVIDUO;
			}
		}
	}

}
