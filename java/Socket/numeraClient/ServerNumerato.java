package org.cramest.socket.numeraClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerNumerato {
	
	public static int nClient;
	
	public static void main(String[] args) {
		ServerSocket ss;
		try {
			ss = new ServerSocket(9999);
			System.out.println("Server numeratore pronto");
			while (true) {
				Socket clientSocket = ss.accept(); // Accetta la connessione
				// invia del testo
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				out.println(nClient++);
				clientSocket.close();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
