package eus.julenugalde.sandbox.designpatterns.abstractfactory;

/** CPU de la arquitectura Enginola. Se identifica por número de serie y version */
public class EnginolaCPU extends CPU {
	private int serialCode;
	private int version;
	
	public EnginolaCPU (int serialCode, int version) {
		this.serialCode = serialCode;
		this.version = version;
	}

	public EnginolaCPU (int serialCode) {
		this(serialCode, 0);
	}
	
	@Override
	public String getArchitectureName() {
		return "Enginola";
	}

	@Override
	public String toString() {
		return "Enginola CPU with serial code " + serialCode + " (version " + version + ")";
	}
}
