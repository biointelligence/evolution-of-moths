package com.evolutionofmoths.evolution.util;

import com.evolutionofmoths.evolution.Gene;

/**
 * Utility class that provides chromosome conversion operations.
 */
public class ChromosomeConverterUtil {
	
	/**
	 * Retrieves the entire value from a chromosome in binary format.
	 * @param genes - genes in the integer format.
	 * @return Integer - returns the decimal value of the chromosome.
	 */
	public static int convertToDecimal(Gene[] genes) {
		return Integer.parseInt(convertToBinaryString(genes), 2);
	}

	/**
	 * Recovers the textual format of the chromosome genes.
	 * @param genes - genes in the integer format.
	 * @return String - returns the genes on the chromosome.
	 */
	public static String convertToBinaryString(Gene[] genes) {
        final StringBuilder chromosome = new StringBuilder();
                
        for (Gene gene : genes) {
            chromosome.append(String.valueOf(gene.getValue()));
        }
        
        return chromosome.toString();
    }

}
