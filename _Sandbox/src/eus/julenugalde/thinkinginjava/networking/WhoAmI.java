package eus.julenugalde.thinkinginjava.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class WhoAmI {
	public static void main (String[] args) {
		System.out.print("Nombre del equipo: ");
		BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
		try {
		    String cadena = br.readLine();
		    InetAddress direccion = InetAddress.getByName(cadena);
		    System.out.println(direccion.toString());
		    System.out.println("IP: " + direccion.getHostAddress());
		    System.out.println("Canonical host name: " + direccion.getCanonicalHostName());
		    System.out.println("Is reachable: " + direccion.isReachable(5000));
		    System.out.println("Is multicast: " + direccion.isMulticastAddress());
		} catch (UnknownHostException uhex) {
			System.err.println("Error: unknown host (" + uhex.getLocalizedMessage() + ")");
		}catch (IOException ioex) {
		    System.err.println("IOException @ main: " + ioex.getMessage());
		} 

	}
}
