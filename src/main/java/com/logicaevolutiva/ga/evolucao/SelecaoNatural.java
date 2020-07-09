package com.logicaevolutiva.ga.evolucao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe de definicao dos mecanismos de Selecao Natural
 * com a finalidade de identificar os melhores produtos a partir de uma 
 * populacao.
 * @author aiello
 *
 */
public class SelecaoNatural {
	
private static final Logger log = LoggerFactory.getLogger(SelecaoNatural.class);
	
	//Define o tamanho populacional.
	private int tamanhoPopulacao;
	//Define o ponto medio populacional
	private int pontoMedioPopulacao;
	//Define o tamanho do cromossomo atraves da quantidade de genes.
	private int tamanhoCromossomo;
	//Taxa de cruzado generico adotada.
	private double taxaCrossOver;
	//Taxa de mutacao adotada dentro da populacao.
	private double taxaMutacao;
	//Tamanho do espaco de busca.
	private int tamanhoEspacoBusca;
	//Melhor solucao encontrada.
	private Individuo melhorCromossomo;
	//Em que geracao a melhor solucao foi encontrada.
	private int melhorGeracao;
	
	public static void main (String ...strings) {
		
		SelecaoNatural selecaoNatural = new SelecaoNatural(15000,
				255, 0.5, 0.03);
		
		selecaoNatural.evoluir();
		
	}

	/**
	 * Construtor
	 * @param tamanhoPopulacao - tamanho da populacao.
	 * @param tamanhoEspacoBusca - tamanho do espaco de busca.
	 * @param taxaCrossOver - taxa do crossOver a ser aplicada.
	 * @param taxaMutacao - taxa de mutacao a ser aplicada.
	 */
	public SelecaoNatural(final int tamanhoPopulacao, final int tamanhoEspacoBusca,
			final double taxaCrossOver, final double taxaMutacao) {
		
		this.tamanhoPopulacao = tamanhoPopulacao;
		this.pontoMedioPopulacao = tamanhoPopulacao / 2;
		this.tamanhoCromossomo = 3; //RGB
		this.taxaCrossOver = taxaCrossOver;
		this.taxaMutacao = taxaMutacao;
		this.tamanhoEspacoBusca = tamanhoEspacoBusca;

	}	
		
	/**
	 * Executa o processo evolutivo
	 */
	public void evoluir() {
		
		int geracao = 0;
		
		final SelecaoIndividuosUtil selecaoIndividuosUtil = new SelecaoIndividuosUtil();
		
		final ControleEvolucao controleEvolucao = ControleEvolucao.getControleEvolucao();
		controleEvolucao.setStatus(ControleEvolucao.Status.ATIVA);
		
		//Configuracao inicial do Ambiente
		final Ambiente ambiente = Ambiente.getAmbienteAtual();
		
		ambiente.setAzul(130);
		ambiente.setVerde(130);
		ambiente.setVermelho(130);
		
	    //Cria e popula aletoriamente com Individuos a primeira geracao.
		//Geracao inicial: 0 .
		PopulacaoMariposas populacao = new PopulacaoMariposas(tamanhoPopulacao, tamanhoCromossomo, tamanhoEspacoBusca , 
				geracao, true);
		
		//Executa a primeira avaliacao da populacao.
		populacao.executaCalculoAvaliacaoPopulacional();
		
		//Configura a melhor solucao da primeira geracao.
		this.melhorCromossomo = populacao.getMelhorIndividuo();
		
		while (controleEvolucao.getStatus() == ControleEvolucao.Status.ATIVA) {
			
			geracao++;
			
			log.debug("Darwin LE - -----------------------------------------------------------");
			log.debug("Darwin LE - [Analise de Cromossomo da geracao : " +  geracao + "] \n");

			
			int contagemIndividuos = 0;
			
			if (log.isDebugEnabled() && this.melhorCromossomo.getFitness() > 0) {
				
				log.debug("Darwin LE - -----------------------------------------------------------");
				log.debug("Darwin LE - [Analise da populacao com o individuo melhor adaptado]");
				log.debug("Darwin LE - Fitness: " + this.melhorCromossomo.getFitness() );
				log.debug("Darwin LE - Geracao: " + this.melhorGeracao );
				log.debug("Darwin LE - Melhor Cromossomo: " + 
						this.melhorCromossomo.getCromossomo().getGenes()[0].getValor() + "*" + 
						this.melhorCromossomo.getCromossomo().getGenes()[1].getValor() + "*" + 
						this.melhorCromossomo.getCromossomo().getGenes()[2].getValor() 
							);
					log.debug("\n");
					
					log.debug("Darwin LE - -----------------------------------------------------------");

				
				for (int a = 0 ; a < populacao.getIndividuos().length; a++) {
					
					if (a == 0 || a == populacao.getIndividuos().length -1) {
						log.debug("Darwin LE - Fitness: " + populacao.getIndividuos()[a].getFitness());
						log.debug("Darwin LE - Percentual Fitness: " + populacao.getIndividuos()[a].getPercentFitness());
						log.debug("Darwin LE - Cromossomo: " + 
						     populacao.getIndividuos()[a].getCromossomo().getGenes()[0].getValor() + "*" + 
						     populacao.getIndividuos()[a].getCromossomo().getGenes()[1].getValor() + "*" + 
						     populacao.getIndividuos()[a].getCromossomo().getGenes()[2].getValor() 
								);
						log.debug("\n");
						
					}
				}
			}
			
			
			PopulacaoMariposas novaGeracao = new PopulacaoMariposas(tamanhoPopulacao, tamanhoCromossomo, tamanhoEspacoBusca,
					geracao, false);

			//Percorre a quantidade referente a metade da populacao definida.
			for (int pm = 0 ; pm < pontoMedioPopulacao ; pm++) { 
				
				//Seleciona na geracao atual dois individuos mais adaptados.
				Individuo individuo1 = selecaoIndividuosUtil.roletaViciada(populacao);
				Individuo individuo2 = selecaoIndividuosUtil.roletaViciada(populacao);
				
				//Executa o cruzamento dos mais aptos resultando em dois indivuduos.
				Individuo[] filhos;
				try {
					filhos = individuo1.crossOver(individuo2, taxaCrossOver);
				} catch (Exception e) {
					filhos = new Individuo[2];
					filhos[0] = individuo1;
					filhos[2] = individuo1;
				}
				
				filhos[0].mutacao(taxaMutacao);
				filhos[1].mutacao(taxaMutacao);
				
				novaGeracao.setIndividuos(filhos[0] , contagemIndividuos);
				contagemIndividuos++;
				
				novaGeracao.setIndividuos(filhos[1], contagemIndividuos);
				contagemIndividuos++;
				
			}
			
			if (geracao == 20) {
				
				ambiente.setAzul(AleatoriedadeGeneticaUtil.random.nextInt(255));
				ambiente.setVerde(AleatoriedadeGeneticaUtil.random.nextInt(255));
				ambiente.setVermelho(AleatoriedadeGeneticaUtil.random.nextInt(255));
				
				
				
				System.out.println(ambiente.getVermelho());
				System.out.println(ambiente.getVerde());
				System.out.println(ambiente.getAzul());
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				novaGeracao = new PopulacaoMariposas(tamanhoPopulacao, tamanhoCromossomo, tamanhoEspacoBusca,
						geracao, true);
				this.melhorCromossomo.setFitness(0);
			}
			
			//Avalia a proeficiencia de toda a nova populacao.
			novaGeracao.executaCalculoAvaliacaoPopulacional();
			
			//Se o melhor individuo da nova populacao for melhor do que o da anterior
			//ele passa a ser a melhor solucao.
			if (novaGeracao.getMelhorIndividuo().getFitness() > melhorCromossomo.getFitness()) {
				this.melhorCromossomo = novaGeracao.getMelhorIndividuo();
				this.melhorGeracao = geracao;
			}
			
			//A nova populacao a ter os individuos mais aptos das geracoes.
			 populacao = novaGeracao;
		}
		
	}
	
	
}
