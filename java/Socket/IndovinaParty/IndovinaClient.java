package org.cramest.socket.IndovinaParty;
import org.cramest.socket.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class IndovinaClient extends Client{
	
	public IndovinaClient(int IP){
		super(IP,9999);
	}
	
	public String mandaNumero(String numero){
		try {
			Socket s = new Socket("172.16.6."+IP, 9999);
			//Mando il numero
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			out.println(numero);
			// ricevo il testo
			InputStreamReader isr = new InputStreamReader(s.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			String ricevuto = in.readLine();
			s.close();
			return ricevuto;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
