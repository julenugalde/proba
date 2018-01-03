package eus.julenugalde.thinkinginjava.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class TimeServerUDPClient implements Runnable {
	private int id;
	private DatagramSocket socket;
	private DatagramPacket packet;
	private InetAddress hostAddress;
	private int serverPort;
	private byte[] buf;
	
	public TimeServerUDPClient(int id, String serverAddress, int serverPort) 
	throws SocketException, UnknownHostException, IOException {
		this.id = id;
		this.serverPort = serverPort;
		buf = new byte[1024];
		packet = new DatagramPacket(buf, buf.length);
		socket = new DatagramSocket();
		hostAddress = InetAddress.getByName(serverAddress);		
	}
	
	public void run() {
		try {
			//Send request to server
			String message = "Client " + id + " requests time";
			System.out.println("Message '" + message + "' sent to " + hostAddress);
			socket.send(Datagram.toDatagram(message, hostAddress, serverPort));
			
			//Block until response received
			socket.receive(packet);
			
			//Print server's response
			System.out.println("Client " + id + " got response from " + packet.getAddress() + 
					":" + packet.getPort() + " with payload '" + Datagram.toString(packet) + "'");
			
		} catch (SocketException sex) {
			System.err.println("SocketException@TimeServerUDPClient: "+sex.getLocalizedMessage());
		} catch (UnknownHostException uhex) {
			System.err.println("UnknownHostException@TimeServerUDPClient: " +
					uhex.getLocalizedMessage());
		} catch (IOException ioex) {
			System.err.println("IOException@TimeServerUDPClient: " + ioex.getLocalizedMessage());
		} finally {
			if (socket != null)
				socket.close();
		}
	}
}
