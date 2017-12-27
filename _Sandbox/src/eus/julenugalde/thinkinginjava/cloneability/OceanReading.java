package eus.julenugalde.thinkinginjava.cloneability;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class OceanReading implements Cloneable {
	private long time;
	private TemperatureReading tempReading;
	private DepthReading depthReading;
	
	public OceanReading (double tempInCelsius, double depthInMeters) {
		time = System.currentTimeMillis();
		tempReading = new TemperatureReading(tempInCelsius);
		depthReading = new DepthReading(depthInMeters);
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdfFormato = new SimpleDateFormat("Y-MM-dd' 'HH:mm:ss Z", 
				DateFormatSymbols.getInstance(Locale.getDefault()));
		return sdfFormato.format(time) + " --> " + tempReading.getTempCelsius() + "ºC, " + 
				depthReading.getDepth() + " m.";
	}
	
	@Override
	protected Object clone() {
		Object result = null;
		try {
			result = (OceanReading)super.clone();
		} catch (CloneNotSupportedException e) {
			System.err.println("OceanReading doesn't support cloning");
		}
		return result;
	}
}
