package logicaevolutiva.ga.virtual.population;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ga.biointelligence.evolucao.SelecaoNatural;

@SpringBootApplication
public class VirtualPopulationApplication {
	
	private static final int QTD_MARIPOSAS = 15000;
	private static final int ESPACO_DE_BUSCA = 255;
	private static final double TAXA_CROSS_OVER = 0.5;
	private static final double TAXA_MUTACAO = 0.03;

	public static void main(String[] args) {
		SpringApplication.run(VirtualPopulationApplication.class, args);
		
		//Inicia o processo Evolutivo com uma populacao de 15000 Mariposas.
		final SelecaoNatural selecaoNatural = new SelecaoNatural(QTD_MARIPOSAS, ESPACO_DE_BUSCA, 
				TAXA_CROSS_OVER, TAXA_MUTACAO);
		selecaoNatural.evoluir();
	}

}
