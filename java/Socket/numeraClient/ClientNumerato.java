package org.cramest.socket.numeraClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientNumerato {

	private int IP;
	
	public ClientNumerato(int IP){
		this.IP = IP;
	}
	
	public int getNumero(){
		try {
			Socket s = new Socket("172.16.6."+IP, 9999);
			// ricevo del testo
			InputStreamReader isr = new InputStreamReader(s.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			String ricevuto = in.readLine();
			System.out.println("Server : Sei il client numero " + ricevuto);
			s.close();
			return Integer.parseInt(ricevuto);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
}
