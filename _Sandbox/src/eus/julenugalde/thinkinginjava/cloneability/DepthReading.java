package eus.julenugalde.thinkinginjava.cloneability;

public class DepthReading implements Cloneable {
	private double depth;
	
	public DepthReading(double depthInMeters) {
		depth = depthInMeters;
	}
	
	public double getDepth() {
		return depth;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Object result = null;
		try {
			result = (DepthReading)super.clone();
		} catch (CloneNotSupportedException cnsex) {
			System.err.println("Clone not supported for DepthReading");
		}
		
		return result;
	}
}
