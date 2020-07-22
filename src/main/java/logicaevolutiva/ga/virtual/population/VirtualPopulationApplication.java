package logicaevolutiva.ga.virtual.population;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ga.biointelligence.evolucao.SelecaoNatural;

@SpringBootApplication
public class VirtualPopulationApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualPopulationApplication.class, args);
		
		//Inicia o processo Evolutivo com uma populacao de 15000 Mariposas.
		final SelecaoNatural selecaoNatural = new SelecaoNatural(15000, 255, 0.5, 0.03);
		selecaoNatural.evoluir();
	}

}
