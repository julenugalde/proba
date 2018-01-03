package eus.julenugalde.thinkinginjava.networking;

import java.io.IOException;

public class TestMultiClient {
	public static final int NUM_CLIENTS = 5;
	public static final String SERVER_ADDRESS = "localhost";
	
	public static void main (String[] args) {
		try {
			EchoClient client;
			Thread thread;
			for (int i=0; i<NUM_CLIENTS; i++) {
				client = new EchoClient(SERVER_ADDRESS, EchoServer.PORT);
				thread = new Thread(new ClientThread(client));
				thread.start();
			}
		} catch (IOException ioex) {
			System.err.println("IOException@TestMultiClient: " + ioex.getLocalizedMessage());
		}
	}
}

class ClientThread implements Runnable {
	private EchoClient client;
	
	public ClientThread (EchoClient client) throws IOException {
		this.client = client;
	}

	public void run() {
		try {
			client.connect();
		} catch (IOException ioex) {
			System.err.println("IOException @ ClientThread: " + ioex.getLocalizedMessage());
		}
	}
}
