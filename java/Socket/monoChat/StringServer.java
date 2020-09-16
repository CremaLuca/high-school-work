package org.cramest.socket.monoChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class StringServer {
	
	static InputStreamReader ISR = new InputStreamReader(System.in);
	static BufferedReader BR = new BufferedReader(ISR);
	
	public static void main(String[] args) {
		ServerSocket ss;
		try {
			ss = new ServerSocket(9999);
			System.out.println("ServerStringhe pronto");
			while (true) {
				Socket clientSocket = ss.accept(); // Accetta la connessione
				InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				System.out.println("Client : " + br.readLine());
				// invia del testo
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				out.println(getLine());
				clientSocket.close();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	public static String getLine(){
		try {
			return BR.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
