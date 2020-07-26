package ga.biointelligence.evolution;

import ga.biointelligence.evolution.util.GeneticRandomnessUtil;
import ga.biointelligence.evolution.util.ChromosomeConverterUtil;

/**
 * Class that defines the structure of a Chromosome.
 */
public class Chromosome {

	// A set of genes constitutes a chromosome.
	private Gene[] genes;

	/**
	 * Constructor
	 * @param amountGenes       - amount of genes that the solution chromosome will have.
	 * @param lengthSpaceSearch - search space size.
	 */
	public Chromosome(int amountGenes , int lengthSpaceSearch) {
		genes = new Gene[amountGenes];

		// Randomly generates values for the amount of genes reported.
		for (int g = 0; g < amountGenes; g++) {
			int valueGene = GeneticRandomnessUtil.random.nextInt(lengthSpaceSearch);
			genes[g] = new Gene(valueGene);
		}
	}
	
	public Chromosome(Gene [] genes) {
		this.genes = genes;
	}
	
    /**
     * Recovers the genes from the chromosome.
     * @return  - returns the genes on the chromosome.
     */
    public Gene[] getGenes(){
        return genes;
    }
    
    
    /**
     * Configures the chromosome with a new set of genes.
     * @param genes - New set of genes.
     */
    public void setGenes(Gene [] genes){
        this.genes = genes;
    }
    
    /**
     * Recovers a specific gene.
     * @param position - position where the gene is on the chromosome.
     * @return - returns the recovered gene.
     */
    public Gene getGene(int position){
        return genes[position];
    }
	
	/**
     * Sets up the new specific gene.
     * @param gene     - new gene.
     * @param position - position to insert the new gene.
     */
    public void setGene(Gene gene, int position){
        genes[position] = gene;
    }
    
    /**
     * Retrieves the value of the chromosome in binary text format.
     * @return - returns the chromosome value.
     */
	public String getChromosomeBinaryString() {
		return ChromosomeConverterUtil.convertToBinaryString(genes);
	}
	
	/**
	 * Retrieves the value of the chromosome in decimal integer format.
	 * @return - returns the chromosome value.
	 */
	public int getChromosomeIntValue() {
		return ChromosomeConverterUtil.convertToDecimal(genes);
	}
 
}
