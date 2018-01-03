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
	private Socket socket;
	private BufferedReader br;
	private PrintWriter pr;
	
	public EchoClient (String serverName, int serverPort) throws IOException {
		InetAddress address = InetAddress.getByName(serverName);
		socket = new Socket(address, serverPort);	
	}
	
	public void connect() throws IOException {
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pr = new PrintWriter(
					new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),
					true);
			System.out.println("Client connected to " + socket.getRemoteSocketAddress() + ". " +
					"Ready to send text to the echo server. Quit and disconnect typing '" + 
					EchoServer.QUIT_STRING + "'.");
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
			String str = "";
			while (!str.equals(EchoServer.QUIT_STRING)) {
				System.out.print("echoServer@" + socket.getRemoteSocketAddress() + " >> ");
				str = keyboard.readLine();
				//System.out.println("Line '"  + str + "' will be sent");
				pr.println(str);
				if (str.equals(EchoServer.QUIT_STRING)) {
					System.out.println("'" + EchoServer.QUIT_STRING + 
							" command sent. Ending connection...");
				}
				else {
					str = br.readLine();
					System.out.println("'" + str + "'");
				}					
			}
		} finally {
			socket.close();
		}
	}
	
	public static void main(String[] args) {
		try {
			EchoClient client = new EchoClient("localhost", EchoServer.PORT);
			client.connect();
						
		} catch (IOException e) {
			System.err.println("IOException @ EchoClient.main: " + e.getLocalizedMessage());
		}
	}
}
