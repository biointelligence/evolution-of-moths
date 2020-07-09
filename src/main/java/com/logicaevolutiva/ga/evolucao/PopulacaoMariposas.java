package com.logicaevolutiva.ga.evolucao;

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
			nota = (pontuacaoBase - distancia) / 2;
		} else if (distancia  == 0) {
			nota = pontuacaoBase + 255;	
		} else {
			nota = pontuacaoBase - distancia;
		}
		
		return nota;
	}

	@Override
	public double avalia(Cromossomo cromossomo) {

		
		//Ambiente
		int valorAmbienteVermelho = Ambiente.getAmbienteAtual().getVermelho();
		int valorAmbienteVerde = Ambiente.getAmbienteAtual().getVerde();
		int valorAmbienteAzul = Ambiente.getAmbienteAtual().getAzul();
		
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
