package com.logicaevolutiva.ga.evolucao;


/**
 * 
 * Classe que tem como funcao definir a estrutura de um Cromossomo.
 *
 */
public class Cromossomo {

	// Um conjunto de Genes constitue um Cromossomo.
	private Gene[] genes;

	/**
	 * Construtor
	 * 
	 * @param quantidadeGenes - quantidade de genes que o cromossomo solucao ira possuir.
	 * @param tamanhoEspacoBusca - tamanho do espaco de busca.
	 */
	public Cromossomo(int quantidadeGenes , int tamanhoEspacoBusca) {

		genes = new Gene[quantidadeGenes];

		// Gera aletoriamente valores para quantidade
		// de genes informada
		for (int g = 0; g < quantidadeGenes; g++) {

			int valorGene = AleatoriedadeGeneticaUtil.random.nextInt(tamanhoEspacoBusca);
			genes[g] = new Gene(valorGene);
		}

	}
	
	public Cromossomo(Gene [] genes) {
		this.genes = genes;
	}
	
    /**
     * Recupera o genes do cromossomo.
     * @return  - retorna os genes do cromossomo.
     */
    public Gene[] getGenes(){
        return genes;
    }
    
    
    /**
     * Configura o cromossomo com um novo
     * conjunto de genes.
     * @param genes - Novo conjunto de genes.
     */
    public void setGenes(Gene [] genes){
        this.genes = genes;
    }
    
    /**
     * Recupera um gene especifico.
     * @param posicao - posicao onde esse gene se encontra no cromossomo.
     * @return - retorna o gene recuperado.
     */
    public Gene getGene(int posicao){
        return  genes[posicao];
    }
	
	/**
     * Configura o novo gene especifico.
     * @param gene - novo gene .
     * @param posicao  - posicao para inserir o novo gene.
     */
    public void setGene(Gene gene , int posicao){
        genes[posicao] = gene;
    }
    
    /**
     * Recupera o valor do cromossomo no formato binario em texto.
     * @return - retorna o valor do cromossomo.
     */
	public String getCromossomoTextoBinario() {
		return ConversorCromossomoUtil.converteParaTextoBinario(genes);

	}
	/**
	 * Recupera o valor do cromossomo no format inteiro decimal.
	 * @return - retorna o valor do cromossomo.
	 */
	public int getCromossomoValorInteiro() {
		return ConversorCromossomoUtil.converteParaDecimal(genes);
	}
 
}
