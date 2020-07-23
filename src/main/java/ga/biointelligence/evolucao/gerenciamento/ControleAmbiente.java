package ga.biointelligence.evolucao.gerenciamento;

/**
 * Estrutura que presenta as caracteristicas de um ControleAmbiente onde vivem as Mariposas 
 * dentro de um contexto.
 * @author aiello
 *
 */
public final class ControleAmbiente  {
	
	/**
	 * 
	 */
	private int vermelho;
	private int verde;
	private int azul;
	
	private static ControleAmbiente controle;
	
	private ControleAmbiente() {}
	
	public static synchronized ControleAmbiente getControle() {
		
		if (controle == null) {
			load();
		}
		
		return  controle;
	}
	
	private static void load() {
		controle = new ControleAmbiente();
	}
	
	public int getVermelho() {
		return vermelho;
	}
	public void setVermelho(int vermelho) {
		this.vermelho = vermelho;
	}
	public int getVerde() {
		return verde;
	}
	public void setVerde(int verde) {
		this.verde = verde;
	}
	public int getAzul() {
		return azul;
	}
	public void setAzul(int azul) {
		this.azul = azul;
	}

}
