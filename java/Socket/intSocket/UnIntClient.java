package org.cramest.socket.intSocket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

@SuppressWarnings("resource")
public class UnIntClient {
	public static void main(String[] args) {
		// Mi connetto al server
		try {
			Socket s = new Socket("172.16.6.24", 9080);
			s.getOutputStream().write(69);
			//legge il numero aumentato di 1 dal server
			int numero = s.getInputStream().read();
			System.out.println("Client: Ho ricevuto il numero " + numero);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}