package org.cramest.socket.riordinaStringhe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class RiordinaServer {

	public static void main(String[] args) {
		ServerSocket ss;
		try {
			ss = new ServerSocket(9999);
			System.out.println("ServerRiordina pronto");
			while (true) {
				Socket clientSocket = ss.accept(); // Accetta la connessione
				InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				String[] array = new String[3];
				array[0] = br.readLine();
				array[1] = br.readLine();
				array[2] = br.readLine();
				Arrays.sort(array);
				// invia del testo
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				out.println(array[0] + " " + array[1] + " " + array[2]);
				clientSocket.close();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
}
