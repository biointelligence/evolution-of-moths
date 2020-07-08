package com.logicaevolutiva.ga.evolucao;


public class SelecaoIndividuosUtil {
	
	/**
	 * Mecanismo de selecao utilizando o metodo de Roleta viciada.
	 * @param populacao - populacao a ter um individuo selecionado.
	 * @return Individuo - retorna o individuo sorteado.
	 */
	public Individuo roletaViciada(final Populacao populacao){
	        
	    //O ponto dentro do Pie Chart sorteado ira de 0 a 100.
	    double pontoSorteado =  AleatoriedadeGeneticaUtil.random.nextDouble() * 100;
	    Individuo selecionado = null;
	    
	    for(Individuo individuo : populacao.getIndividuos()){
	        
	        if (pontoSorteado >= individuo.getPieFitness()[0] &&
	                pontoSorteado <= individuo.getPieFitness()[1] ){
	            selecionado = individuo;
	            break;
	        }
	    }
	    
	    return selecionado;
    }

}
