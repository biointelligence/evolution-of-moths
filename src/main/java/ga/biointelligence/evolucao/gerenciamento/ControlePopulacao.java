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
public class ControlePopulacao {
	
	private int geracaoAtual;
	private List<Mariposa> populacaAtualMariposas;
	private static ControlePopulacao controle;
	private final int INTERVALO_INDIVIDUO = 246;

	private ControlePopulacao() {}

	public static synchronized ControlePopulacao getControle() {

		if (controle == null) {
			controle = new ControlePopulacao();
		}

		return controle;
	}

	public List<Mariposa> getPopulacaoAtualMariosas() {
	
		populacaAtualMariposas.sort((m1,m2) -> Integer.valueOf(m1.getRelevancia()).
				compareTo(Integer.valueOf(m2.getRelevancia())));
		
		return populacaAtualMariposas;
	}

	public void setPopulacaoMariposas(final PopulacaoMariposas populacao) {

		populacaAtualMariposas = new ArrayList<Mariposa>();
		Mariposa mariposa = null;
		int ponteiro = 0;
		int relevancia = 1;
	
		for (int a = 0; a < populacao.getIndividuos().length; a++) {

			if (a == 0 || 
					a == ponteiro ||
					a == populacao.getIndividuos().length - 1) {

				mariposa = new Mariposa();

				mariposa.setVermelho(populacao.getIndividuos()[a].getCromossomo().getGenes()[0].getValor());
				mariposa.setVerde(populacao.getIndividuos()[a].getCromossomo().getGenes()[1].getValor());
				mariposa.setAzul(populacao.getIndividuos()[a].getCromossomo().getGenes()[2].getValor());
				mariposa.setRelevancia(relevancia);
				relevancia++;
				
				populacaAtualMariposas.add(mariposa);
			}
			
			if (a == ponteiro) {
				ponteiro+= INTERVALO_INDIVIDUO;
			}
		}
	}

	public int getGeracaoAtual() {
		return geracaoAtual;
	}

	public void setGeracaoAtual(int geracaoAtual) {
		this.geracaoAtual = geracaoAtual;
	}

}
