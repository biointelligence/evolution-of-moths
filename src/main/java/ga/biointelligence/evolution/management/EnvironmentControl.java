package ga.biointelligence.evolution.management;

/**
 * Structure that presents the characteristics of an EnvironmentControl
 * where moths live within a context.
 * @author aiello
 */
public final class EnvironmentControl {

	private int red;
	private int green;
	private int blue;
	
	private static EnvironmentControl control;
	
	private EnvironmentControl() { }
	
	public static synchronized EnvironmentControl getControl() {
		if (control == null) {
			load();
		}
		
		return control;
	}
	
	private static void load() {
		control = new EnvironmentControl();
	}
	
	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

}
