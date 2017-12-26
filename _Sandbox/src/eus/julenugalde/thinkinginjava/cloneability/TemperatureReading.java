package eus.julenugalde.thinkinginjava.cloneability;

public class TemperatureReading implements Cloneable {
	public static double TEMP_MIN = -273.15;
	private double temperature;
	
	public TemperatureReading(double tempInCelsius) {
		if (tempInCelsius < TEMP_MIN) 
			temperature = 0;
		else
			temperature = tempInCelsius;
	}
	
	public double getTempCelsius() {
		return temperature;
	}
	
	public double getTempKelvin() {
		return TEMP_MIN + temperature;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Object result = null;
		try {
			result = (TemperatureReading)super.clone();
		}catch (CloneNotSupportedException cnsex) {
			System.err.println("Clone not supported in TemperatureReading");
		}
		return result;
	}
}
