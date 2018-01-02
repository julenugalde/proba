package eus.julenugalde.thinkinginjava.networking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class EchoClient {
	public static void main(String[] args) {
		try {
			InetAddress address = InetAddress.getByName("localhost");
			System.out.println("Client address: " + address);
			Socket socket = new Socket(address, EchoServer.PORT);
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter pr = new PrintWriter(
						new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),
						true);
				System.out.println(
						"Send text to the echo server. Quit with '" + EchoServer.QUIT_STRING + "'");
				BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
				String str = "";
				while (!str.equals(EchoServer.QUIT_STRING)) {
					System.out.print("echoServer@" + socket.getRemoteSocketAddress() + " >> ");
					str = keyboard.readLine();
					//System.out.println("Line '"  + str + "' will be sent");
					pr.println(str);
					if (str.equals(EchoServer.QUIT_STRING)) {
						System.out.println("Ending connection...");
					}
					else {
						str = br.readLine();
						System.out.println("Response from echo server: '" + str + "'");
					}					
				}
			} finally {
				socket.close();
			}			
		} catch (IOException e) {
			System.err.println("IOException @ EchoClient.main: " + e.getLocalizedMessage());
		}
	}
}
