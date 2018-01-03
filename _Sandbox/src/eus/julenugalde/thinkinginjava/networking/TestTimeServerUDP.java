package eus.julenugalde.thinkinginjava.networking;

import java.io.IOException;
import java.net.SocketException;

public class TestTimeServerUDP {
	public static void main(String[] args) {
		try {
			TimeServerUDPClient c;
			for (int i=0; i<10; i++) {
				c = new TimeServerUDPClient(i, "localhost", TimeServerUDP.PORT);
				new Thread(c).start();
				Thread.sleep((int)(2000*Math.random()));
			}
		} catch (SocketException sex) {
			System.err.println("SocketException@TimeServerUDP: " + sex.getLocalizedMessage());
		} catch (IOException ioex) {
			System.err.println("IOException@TimeServerUDP: " + ioex.getLocalizedMessage());
		} catch (InterruptedException iex) {
			System.err.println("InterruptedException@TimeServerUDP: " + iex.getLocalizedMessage());
		}
	}
}
