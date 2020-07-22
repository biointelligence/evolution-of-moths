package ga.biointelligence.evolucao.util;

import ga.biointelligence.evolucao.Gene;

/**
 * Classe utilitaria que prove operacoes de conversao 
 * para cromossomos
 *
 */
public class ConversorCromossomoUtil {
	
	/**
	 * Recupera o valor inteiro a partir de um cromossomo no formato binario.
	 * @param genes - genes no formato inteiro.
	 * @return - retorna o valor decimal do cromossomo.
	 */
	public static int converteParaDecimal(Gene[] genes) {
		// Retorna o valor decimal referente ao cromossomo.
		return Integer.parseInt(converteParaTextoBinario(genes), 2);
	}

	/**
	 * Recupera o formato textual os genes do cromossomo.
	 * @param genes - genes no formato inteiro.
	 * @return String - retorna o genes do cromossomo.
	 */
	public static String converteParaTextoBinario(Gene[] genes){  
        
        final StringBuilder cromossomo = new StringBuilder();
                
        for (Gene gene : genes) {
            cromossomo.append(String.valueOf(gene.getValor()));
        }
        
        return cromossomo.toString();      
    }
		

}
