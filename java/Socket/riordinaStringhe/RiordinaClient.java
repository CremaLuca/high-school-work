package org.cramest.socket.riordinaStringhe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class RiordinaClient {

	static InputStreamReader ISR = new InputStreamReader(System.in);
	static BufferedReader BR = new BufferedReader(ISR);
	
	public static void main(String[] args) {
		// Mi connetto al server
		
		System.out.println("A che computer del lab 6 devo connettermi?");
		String IP = getLine();
		try {
			Socket s = new Socket("172.16.6."+IP, 9999);
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			System.out.println("Inserisci tre parole da ordinare");
			out.println(getLine());
			out.println(getLine());
			out.println(getLine());

			// riceva del testo
			InputStreamReader isr = new InputStreamReader(s.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			System.out.println("Server : " + in.readLine());
			
			s.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
