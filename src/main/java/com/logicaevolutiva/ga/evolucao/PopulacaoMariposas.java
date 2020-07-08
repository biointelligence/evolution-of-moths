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

	@Override
	public double avalia(Cromossomo cromossomo) {

		double nota = 0;
		int pontuacaoBase = 1000;
		
		int valorAmbienteVermelho = Ambiente.getAmbienteAtual().getVermelho();
		int valorAmbienteVerde = Ambiente.getAmbienteAtual().getVerde();
		int valorAmbienteAzul = Ambiente.getAmbienteAtual().getAzul();
		
		int valorGeneVermelho = cromossomo.getGenes()[0].getValor();
		int valorGeneVerde = cromossomo.getGenes()[1].getValor();
		int valorGeneAzul = cromossomo.getGenes()[2].getValor();
		
		int distanciaVermelho = (valorAmbienteVermelho - valorGeneVermelho);
		if (distanciaVermelho < 0) distanciaVermelho*=-1;
		
		int distanciaVerde = (valorAmbienteVerde - valorGeneVerde);
		if (distanciaVerde < 0) distanciaVerde*=-1;
		
		int distanciaAzul = (valorAmbienteAzul - valorGeneAzul);
		if (distanciaAzul < 0) distanciaAzul*=-1;
		
		//Vermelho
		if (distanciaVermelho > 25) {
			nota = (pontuacaoBase - distanciaVermelho) / 2;
		} else if (distanciaVermelho  == 0) {
			nota += pontuacaoBase  + 25;	
		} else {
			nota = pontuacaoBase - distanciaVermelho;
		}
		
		//Verde
		if (distanciaVerde > 25) {
			nota+= (pontuacaoBase - distanciaVerde) / 2;
		} else if (distanciaVerde  == 0) {
			nota += pontuacaoBase  + 25 ;	
		} else {
			nota += pontuacaoBase - distanciaVerde;
		}
		
		//Azul
		if (distanciaAzul > 25) {
			nota+= (pontuacaoBase - distanciaAzul) / 2;
		} else if (distanciaAzul  == 0) {
			nota += pontuacaoBase  + 25;	
		} else {
			nota += pontuacaoBase - distanciaAzul;
		}
		
		nota/=3;
		
		return nota;
	}
	
}
