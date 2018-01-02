package eus.julenugalde.thinkinginjava.networking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoTask implements Runnable {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	
	public EchoTask(Socket socket) throws IOException {
		this.socket = socket;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(
				new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),
				true);
		new Thread(this).start();
		System.out.println("Echo task created with port=" + socket.getPort() + 
				", local_port= " + socket.getLocalPort());
	}
	
	public void run() {
		try {
			String str = "";
			String titulo = 
					"[" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "]";
			do {
				str = in.readLine();
				System.out.println(titulo + " Echoing '"+ str + "'");
				out.println(str);
			}
			while (!str.equals(EchoServer.QUIT_STRING));
			System.out.println("Connection " + titulo + " terminated");
		} catch (IOException ioex) {
			System.err.println("IOException@EchoTask: " + ioex.getLocalizedMessage());
		} finally {
			try {
				socket.close();
			} catch (IOException ioex) {
				System.err.println("Error closing the socket: " + ioex.getLocalizedMessage());
			}
		}
	}
}
