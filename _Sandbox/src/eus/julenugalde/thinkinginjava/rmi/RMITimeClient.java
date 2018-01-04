package eus.julenugalde.thinkinginjava.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMITimeClient {
	final static String HOST_NAME="localhost";
	
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry(HOST_NAME);
			PerfectTimeInterface stub = 
					(PerfectTimeInterface)registry.lookup("PerfectTimeInterface");
			
			long response = stub.getPerfectTime();
			System.out.println("Response from server: " + response);
			
		} catch (Exception e) {
			System.err.println("Client exception: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
}
