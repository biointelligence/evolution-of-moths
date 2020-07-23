package ga.biointelligence.evolucao;

import ga.biointelligence.evolucao.gerenciamento.ControleAmbiente;

/**
 * Classe que define a populacao para solucao do problema referente a
 * identificacao dos melhores produtos.
 * 
 * @author aiello
 *
 */
public class PopulacaoMariposas extends Populacao {


	public PopulacaoMariposas(final int tamanhoPopulacao, final int tamanhoCromossomo, final int tamanhoEspacoBusca , final int geracao,
			boolean novaPopulacao) {
		super(tamanhoPopulacao, tamanhoCromossomo, tamanhoEspacoBusca , geracao, novaPopulacao );

		// Se for o inicio populacional da cadeia evolucionaria
		if (novaPopulacao) {
			executaCalculoAvaliacaoPopulacional();
			this.melhorIndividuo = this.individuos[individuos.length - 1];
		}
	}
	
	private int calculoDistancia(final int valorAmbiente , final int valorGene) {
		
		int distancia = valorAmbiente - valorGene;
		return distancia < 0 ? distancia*-1 : distancia;
	}
	
	private double calculaNota(final int distancia) {
		
		int pontuacaoBase = 1000;
		int nota = 0;
		
		if (distancia > 25) { 
			//Penaliza o Gene que esta com a distancia superior a aproximadamente 10% do valor da cor do ambiente.
			nota = (pontuacaoBase - distancia) / 2;
		} else if (distancia  == 0) {
			//Premia o Gene que encontra a Cor do ambiente.
			nota = pontuacaoBase + 255;	
		} else { 
			//Calcula a nota baseado na aproxima do valor do ambiente.
			nota = pontuacaoBase - distancia;
		}
		
		return nota;
	}

	@Override
	public double avalia(Cromossomo cromossomo) {

		
		//ControleAmbiente
		int valorAmbienteVermelho = ControleAmbiente.getControle().getVermelho();
		int valorAmbienteVerde = ControleAmbiente.getControle().getVerde();
		int valorAmbienteAzul = ControleAmbiente.getControle().getAzul();
		
		//Valores dos Genes
		int valorGeneVermelho = cromossomo.getGenes()[0].getValor();
		int valorGeneVerde = cromossomo.getGenes()[1].getValor();
		int valorGeneAzul = cromossomo.getGenes()[2].getValor();
		
		//Definindo as Distancias do Alvo
		int distanciaVermelho = calculoDistancia(valorAmbienteVermelho , valorGeneVermelho);
		int distanciaVerde = calculoDistancia(valorAmbienteVerde , valorGeneVerde);
		int distanciaAzul = calculoDistancia(valorAmbienteAzul , valorGeneAzul);
		
		
        return calculaNota(distanciaVermelho) + calculaNota(distanciaVerde) + calculaNota(distanciaAzul);		
	}
	
}
