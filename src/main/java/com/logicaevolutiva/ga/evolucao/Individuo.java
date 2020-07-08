package com.logicaevolutiva.ga.evolucao;


/**
 * 
 * Classe que tem como funcao definir a estrutura de um Individuo.  
 *
 */
public class Individuo implements Cloneable{
	
	//Composicao genetica do Individuo
	private Cromossomo cromossomo;
	
    //Desempenho do Individuo dentro da populacao.
	private double fitness;
	
	//Valor atribuido ao individuo.
	private double valor;
	
	//Calcula o percentual fitness do Individuo dentro da populacao.
	private double percentFitness;
	
	//Faixa da Pizza de Fitness em 
    //que o Individuo se encontra.
    private double[] pieFitness = {0,0};
    
    //Tamanho do espaco de busca.
    private int tamanhoEspacoBusca;

	/**
	 * Construtor
	 * @param quantidadeGenes - tamanho do cromossomo do Individuo.
 	 * @param tamanhoEspacoBusca - tamanho do espaco de busca.
	 */
	public Individuo(final int tamanhoCromossomo, final int tamanhoEspacoBusca) {
		this.cromossomo = new Cromossomo(tamanhoCromossomo, tamanhoEspacoBusca);
		this.tamanhoEspacoBusca = tamanhoCromossomo;
	}
	
	/**
	 * Executa o processo de mutacao em um gene especifico.
	 * @param taxaMutacao - taxa de possiveis mutacoes atribuidas a esse individuo.
	 */
    public void mutacao(final double taxaMutacao){
    	
    	for (int m = 0; m < this.cromossomo.getGenes().length ; m++) {
    		
    		if (Math.random() < taxaMutacao) {
    			this.cromossomo.getGene(m).setValor(AleatoriedadeGeneticaUtil.random.nextInt(this.tamanhoEspacoBusca));   
    		}
    	}
    
    }
    
    /**
     * Configura posicao dentro da Pie Fitness do Individuo.
     * @param faixaInicial - Inicio da faixa na PieFitness. 
     * @param faixaFinal - Fim da faixa na PieFitness.
     */
    public void setFaixaPercentualPie(final double faixaInicial, final double faixaFinal){
     
    	//Intervalo posicional dentro da Pie Chart.
        pieFitness[0] = faixaInicial;
        pieFitness[1] = faixaFinal;
    }
    
    /**
     * Executa o cruzamento genetico entre o individuo e um parceiro.
     * @param parceiro - parceiro selecionado para o cruzamento.
     * @param taxaCrossOver - taxa em que se aplicara a recombinacao dos genes.
     * @return Individuo [] - retorna dois individuos resultantes do cruzamento.
     * @throws CloneNotSupportedException  - Em caso de falha na clonagem.
     */
    public Individuo [] crossOver(final Individuo parceiro ,final double taxaCrossOver) throws CloneNotSupportedException{
    	
    	final int CORRENTE = 0;
    	final int PARCEIRO = 1;

        //Cria um novo Individuo que sera o resultado 
        //do cruzamento entre o individuo corrente e 
        //um parceiro informado.
        Individuo [] filhos = new Individuo[2];
        
        //Definindo aleatoriamente o ponto de corte no cromossomos.
        //Sera escolhido um numero de corte entre a posicao 0 a posicao total do cromossomo - 1;
        int pontoCorteCromossomo = AleatoriedadeGeneticaUtil.random.nextInt(this.getCromossomo().getGenes().length - 1);
       
        //Clonando a estrutura genetica do
        //individuo corrente e de seu parceiro.
        filhos[CORRENTE] = this.clone();
        filhos[PARCEIRO] = parceiro.clone();
        
        //Avaliando a taxa de CrossOver com o objetivo de
        //recombinar ou nao os genes dos dois individuos
        //para gerar um novo individuo Filho.
        if (AleatoriedadeGeneticaUtil.random.nextDouble() < taxaCrossOver){

            //Percorre a partir do ponto de corte ao final todos os genes do cromossomo.
        	for (int g = pontoCorteCromossomo ; g < this.getCromossomo().getGenes().length ; g++ ){
                
        		//Troca o valor dos genes do cromossomo do Individuo atual  pelo o do parceiro a partir
        		//do ponto de corte ate o final.
                filhos[CORRENTE].getCromossomo().getGene(g).
                	setValor(parceiro.getCromossomo().getGene(g).getValor());
                
               //Troca o valor dos genes do cromossomo do Parceiro pelo o do Individuo atual a partir
        		//do ponto de corte ate o final. 
                filhos[PARCEIRO].getCromossomo().getGene(g).
                setValor(this.getCromossomo().getGene(g).getValor());
            }
        }

        return filhos;
    }

	
	/**
	 * Executa uma copia do Individuo.
	 */
	@Override
    protected Individuo clone() throws CloneNotSupportedException {
       
        final Individuo cloneI = new Individuo(this.cromossomo.getGenes().length , this.tamanhoEspacoBusca);
        
        Gene [] genes = this.cromossomo.getGenes();
        Gene [] cloneG = new Gene[genes.length];
        
        for(int g = 0 ; g < genes.length ; g++){
        	
            int posicao = genes[g].getValor();
            cloneG[g] = new Gene(posicao);
            
        }
        
        cloneI.getCromossomo().setGenes(cloneG);
        
        return cloneI;
    }
		

	public double getFitness() {
		return fitness;
	}

	public double[] getPieFitness() {
		return pieFitness;
	}

	public void setCromossomo(final Cromossomo cromossomo) {
		this.cromossomo = cromossomo;
	}
	
	public Cromossomo getCromossomo() {
		return cromossomo;
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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
    
}
