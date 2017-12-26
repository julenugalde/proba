package eus.julenugalde.thinkinginjava.cloneability;

public class TestCloneability {

	public static void main(String[] args) {
		OceanReading originalReading = new OceanReading(2.23, 25.57);
		System.out.println(originalReading.toString());
		OceanReading copiedReading = (OceanReading)originalReading.clone();
		System.out.println(copiedReading.toString());
		
	}

}
