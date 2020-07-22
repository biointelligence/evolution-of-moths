package ga.biointelligence.evolucao;

/**
 * Classe que tem como funcao definir a mecanica de uma Populacao.
 *
 */
public abstract class Populacao {

	protected Individuo[] individuos;
	protected int geracao;

	// Melhor individuo da cada geracao.
	protected Individuo melhorIndividuo;

	/**
	 * Construtor
	 * 
	 * @param tamanhoPopulacao  - quantidade de individuos da populacao.
	 * @param tamanhoCromossomo - tamanho do cromomosso de cada individuo.
	 * @param tamanhoEspacoBusca - tamanho do espaco de busca.
	 * @param geracao           - numero da geracao corrente.
	 * @param novaPopulacao     - indica se uma nova populacao deve ser populada.
	 */
	public Populacao(final int tamanhoPopulacao, final int tamanhoCromossomo, final int tamanhoEspacoBusca, final int geracao,
			final boolean novaPopulacao) {

		this.individuos = new Individuo[tamanhoPopulacao];
		this.geracao = geracao;

		if (novaPopulacao) {

			for (int p = 0; p < this.individuos.length; p++) {

				Individuo individuo = new Individuo(tamanhoCromossomo , tamanhoEspacoBusca);
				individuos[p] = individuo;
			}
		}
	}

	/**
	 * Funcao de avaliacao.
	 * 
	 * @param valor - valor a ser avaliado .
	 * @return double - retorna a nota da avaliacao.
	 */
	public abstract double avalia(final Cromossomo cromossomo);

	/**
	 * /Excecuta sequencialmente os calculos de avaliacao populacional.
	 */
	public void executaCalculoAvaliacaoPopulacional() {

		double totalFitness = calculaFitness();
		calculaPercentFitness(totalFitness);
		calculaPieChartFitness();

		// Encontra o individuo melhor adaptado.
		this.melhorIndividuo = this.individuos[individuos.length - 1];
	}

	/**
	 * Calcula o Fitness de cada Individuo da Populacao a partir do valor o seu
	 * Cromossomo e retorna o total fitness da populacao corrente.
	 * 
	 * @return double - retorna o total fitness da populacao.
	 */
	private double calculaFitness() {

		double totalFitness = 0D;

		for (Individuo individuo : individuos) {

			double fitness = avalia(individuo.getCromossomo());

			totalFitness += fitness;
			individuo.setFitness(fitness);
		}

		return totalFitness;
	}

	/**
	 * Calcula o percentual do Fitness que cada Individuo representa dentro da
	 * Populacao.
	 * 
	 * @param totalFitness - total fitness da populacao.
	 */
	private void calculaPercentFitness(double totalFitness) {

		for (Individuo individuo : individuos) {
			individuo.setPercentFitness((individuo.getFitness() * 100) / totalFitness);
		}
	}

	/**
	 * Posiciona o percentual do Fitness de cada Individuo dentro da faixa
	 * correspondente de um Pie Chart.
	 */
	private void calculaPieChartFitness() {

		ordena();
		double totalAcumulado = 0d;

		for (int p = 0; p < individuos.length; p++) {

			// O primeiro Individuo e o menos apto da Populacao.
			if (p == 0) {
				totalAcumulado = individuos[p].getPercentFitness();
				individuos[p].setFaixaPercentualPie(0, totalAcumulado);
			} else if (p == (individuos.length - 1)) {
				individuos[p].setFaixaPercentualPie(totalAcumulado, 100);
			} else {
				individuos[p].setFaixaPercentualPie(totalAcumulado, totalAcumulado + individuos[p].getPercentFitness());

				totalAcumulado += individuos[p].getPercentFitness();
			}
		}
	}

	/**
	 * Ordena do maior para o menor o vetor de Individuos desta populacao levando em
	 * consideracao o valor do Fitness.
	 */
	public void ordena() {
		for (int i = 0; i < individuos.length; i++) {
			for (int j = 0; j < (individuos.length - 1); j++) {

				if (individuos[j].getPercentFitness() > individuos[j + 1].getPercentFitness()) {
					Individuo indTemp = individuos[j];
					individuos[j] = individuos[j + 1];
					individuos[j + 1] = indTemp;
				}
			}

		}

	}

	public Individuo[] getIndividuos() {
		return individuos;
	}

	public void setIndividuos(Individuo[] individuos) {
		this.individuos = individuos;
	}

	public void setIndividuos(Individuo individuo, int posicao) {
		this.individuos[posicao] = individuo;
	}

	public Individuo getMelhorIndividuo() {
		return melhorIndividuo;
	}

	public void setMelhorIndividuo(Individuo melhorIndividuo) {
		this.melhorIndividuo = melhorIndividuo;
	}

	public int getGeracao() {
		return geracao;
	}

}
