package eus.julenugalde.thinkinginjava.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static final int PORT = 5153;
	public static final String QUIT_STRING = "quit";
	
	public static void main (String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("Server started: " + serverSocket);
			
			while (true) {
				Socket socket = serverSocket.accept();
				try {
					System.out.println("Connection accepted: " + socket);
					new EchoTask(socket);					
				} catch (IOException ioex) {
					System.err.println("IOException@EchoServer: " + ioex.getLocalizedMessage());
					socket.close();
				}
			}			
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				System.err.println("Exception closing server: " + e.getLocalizedMessage());
			}
		}
	}
}
