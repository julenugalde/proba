package eus.julenugalde.sandbox.designpatterns.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/** Crea un wrapper para un target remoto, "caro" o sensible */
public class SocketProxy implements SocketInterface {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	
	public SocketProxy (String host, int port, boolean wait) {
		try {
			if (wait) {
				//Encapsula la complejidad/overhead del target en el wrapper
				@SuppressWarnings("resource")
				ServerSocket server = new ServerSocket (port);
				socket = server.accept();
			}
			else {
				socket = new Socket(host, port);
			}
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
		} catch (IOException ioex) {
			System.err.println("Error: " + ioex.getLocalizedMessage());			
		}
	}
	
	@Override
	public String readLine() {
		String str = null;
		try {
			str = in.readLine();
		} catch (IOException ioex) {
			System.err.println("Error de lectura: " + ioex.getMessage());
		}
		return str;
	}

	@Override
	public void writeLine(String str) {
		//El wrapper delega en el target
		out.println(str);
	}

	@Override
	public void dispose() {
		try {
			socket.close();
		} catch (IOException ioex) {
			System.err.println("Error cerrando el socket: ");
		}
	}
}
