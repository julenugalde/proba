package eus.julenugalde.thinkinginjava.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeServerUDP {
	public static final int PORT = 56584;
	private boolean flagClose = false;
	private byte[] buf;
	private DatagramPacket rxPacket;
	private DatagramPacket txPacket;
	private DatagramSocket socket;
	
	public TimeServerUDP() throws SocketException {
		buf = new byte[1024];
		rxPacket = new DatagramPacket(buf, buf.length);
		socket = new DatagramSocket(PORT);
	}
	
	public void start() throws SocketException, IOException {
		SimpleDateFormat sdfFormat = new SimpleDateFormat(
				"yyyy-MM-dd' 'HH:mm:ss.SSSXXX", 
				DateFormatSymbols.getInstance(new Locale("es", "ES")));
		
		System.out.println("Time server started. Listening to port " + PORT + "...");
		while (!flagClose) {
			//Block until a datagram arrives
			socket.receive(rxPacket);
			String rxString = Datagram.toString(rxPacket);
			System.out.println("Packet received from " + rxPacket.getAddress() + ":" + 
					rxPacket.getPort() + " with payload '" + rxString + "'. Sending response...");
			txPacket = Datagram.toDatagram(sdfFormat.format(new Date()), 
					rxPacket.getAddress(), rxPacket.getPort());
			socket.send(txPacket);
		}
		
		socket.close();
		System.out.println("Time server stopped.");
	}
	
	public void stop() {
		flagClose = true;
		System.out.println("Stopping time server...");
	}
	
	public static void main(String[] args) {
		try {
			//Server created and started
			TimeServerUDP server = new TimeServerUDP();
			server.start();	
			//server.stop();
		} catch (SocketException sex) {
			System.err.println("SocketException@TimeServerUDP: " + sex.getLocalizedMessage());
		} catch (IOException ioex) {
			System.err.println("IOException@TimeServerUDP: " + ioex.getLocalizedMessage());
		}
	}
}
