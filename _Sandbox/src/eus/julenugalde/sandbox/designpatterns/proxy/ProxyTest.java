package eus.julenugalde.sandbox.designpatterns.proxy;

import java.util.Scanner;

/** Ejemplo del patron de diseño proxy.
 * Extraido de {@link https://sourcemaking.com/design_patterns/proxy/java/1}
 */
public class ProxyTest {
	public static void main (String[] args) {
		String host = "localhost";
		int port = 8080;
		boolean wait = false;
		
		//El cliente se comunica con el target a través del wrapper (socketproxy)
		SocketInterface socket = new SocketProxy(host, port, wait);
		String str;
		boolean skip = true;
		while (true) {
			if (!wait && skip) {
				skip = !skip;
			} 
			else {
				str = socket.readLine();
				System.out.println("Receive - " + str);
				if (str.equals(null)) {
					break;
				}
			}
		
			System.out.print ("Send ----- ");
			str = new Scanner(System.in).nextLine();
			socket.writeLine(str);
			if (str.equals("quit")) {
				break;
			}
		}
		socket.dispose();		
	}
}
