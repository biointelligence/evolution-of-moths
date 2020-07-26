package ga.biointelligence.evolution;

import ga.biointelligence.evolution.util.GeneticRandomnessUtil;

/**
 * Class whose function is to define the structure of an Individual.
 */
public class Individual implements Cloneable {
	
	// Individual's genetic composition
	private Chromosome chromosome;
	
    // Individual performance within the population.
	private double fitness;
	
	// Value attributed to the individual.
	private double value;
	
	// Calculates the individual's fitness percentage within the population.
	private double percentFitness;
	
	// Range of the Fitness Pizza where the Individual is.
    private double[] pieFitness = {0,0};
    
    // Search space size.
    private final int lengthSpaceSearch;

	/**
	 * Constructor
	 * @param lengthGenes       - chromosome size of the individual.
 	 * @param lengthSpaceSearch - search space size.
	 */
	public Individual(final int lengthGenes, final int lengthSpaceSearch) {
		this.chromosome = new Chromosome(lengthGenes, lengthSpaceSearch);
		this.lengthSpaceSearch = lengthGenes;
	}
	
	/**
	 * Performs the mutation process on a specific gene.
	 * @param mutationRate - rate of possible mutations attributed to that individual.
	 */
    public void mutation(final double mutationRate){
    	for (int m = 0; m < this.chromosome.getGenes().length ; m++) {
    		if (Math.random() < mutationRate) {
    			this.chromosome.getGene(m).setValue(GeneticRandomnessUtil.random.nextInt(this.lengthSpaceSearch));
    		}
    	}
    }
    
    /**
     * Configures position within the Individual's Pie Fitness.
     * @param startTrack - Start of the track at PieFitness.
     * @param endTrack   - End of track at PieFitness.
     */
    public void setPercentRangePie(final double startTrack, final double endTrack){
    	// Positional range within the Pie Chart.
        pieFitness[0] = startTrack;
        pieFitness[1] = endTrack;
    }
    
    /**
     * Performs the genetic crossing between the individual and a partner.
     * @param partner       - partner selected for the crossing.
     * @param rateCrossOver - rate at which gene recombination will apply.
     * @return Individual[] - returns two individuals resulting from the crossing.
     * @throws CloneNotSupportedException - In case of failure in cloning.
     */
    public Individual[] crossOver(final Individual partner, final double rateCrossOver) throws CloneNotSupportedException {
    	final int CHAIN = 0;
    	final int PARTNER = 1;

        // Creates a new Individual that will be the result of the
		// cross between the current individual and an informed partner.
        Individual[] children = new Individual[2];
        
        // Randomly defining the cutoff point on the chromosomes.
        // A cutoff number will be chosen between position 0 and the total position of chromosome - 1;
        int chromosomeCutPoint = GeneticRandomnessUtil.random.nextInt(this.getChromosome().getGenes().length - 1);
       
        // Cloning the genetic structure of the current individual and his partner.
        children[CHAIN] = this.clone();
        children[PARTNER] = partner.clone();
        
        // Evaluating the CrossOver rate in order to recombine or not to recombine
		// the genes of the two individuals to generate a new individual child.
        if (GeneticRandomnessUtil.random.nextDouble() < rateCrossOver){
            // It runs from the cutoff point at the end to all the genes on the chromosome.
        	for (int g = chromosomeCutPoint; g < this.getChromosome().getGenes().length ; g++){
        		// Exchange the value of the genes of the current individual's chromosome
				// for that of the partner from the cutoff point to the end.
                children[CHAIN].getChromosome().getGene(g).
						setValue(partner.getChromosome().getGene(g).getValue());
                
                //Exchange the value of the Partner's chromosome genes for the current Individual's
				// from the cutoff point to the end.
                children[PARTNER].getChromosome().getGene(g).
						setValue(this.getChromosome().getGene(g).getValue());
            }
        }

        return children;
    }

	/**
	 * Performs a copy of the Individual.
	 * @throws CloneNotSupportedException - In case of failure in cloning.
	 */
	@Override
    protected Individual clone() throws CloneNotSupportedException {
		final Individual cloneI = new Individual(this.chromosome.getGenes().length , this.lengthSpaceSearch);
        
        Gene[] genes = this.chromosome.getGenes();
        Gene[] cloneG = new Gene[genes.length];
        
        for(int g = 0; g < genes.length; g++) {
            int position = genes[g].getValue();
            cloneG[g] = new Gene(position);
        }
        
        cloneI.getChromosome().setGenes(cloneG);
        
        return cloneI;
    }

	public double getFitness() {
		return fitness;
	}

	public double[] getPieFitness() {
		return pieFitness;
	}

	public void setChromosome(final Chromosome chromosome) {
		this.chromosome = chromosome;
	}
	
	public Chromosome getChromosome() {
		return chromosome;
	}

	public void setFitness(final double fitness) {
		this.fitness = fitness;
	}

	public void setPieFitness(final double[] pieFitness) {
		this.pieFitness = pieFitness;
	}

	public double getPercentFitness() {
		return percentFitness;
	}

	public void setPercentFitness(double percentFitness) {
		this.percentFitness = percentFitness;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
