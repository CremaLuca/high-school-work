package org.cramest.socket.calcolatrice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class CalcClient {
	
	static InputStreamReader ISR = new InputStreamReader(System.in);
	static BufferedReader BR = new BufferedReader(ISR);
	
	public static void main(String[] args) {
		// Mi connetto al server
		
		System.out.println("A che computer del lab 6 devo connettermi?");
		String IP = getLine();
		try {
			Socket s = new Socket("172.16.6."+IP, 9080);
			System.out.println("Primo numero dell'operazione");
			int num1 = Integer.parseInt(getLine());
			s.getOutputStream().write(num1);
			System.out.println("Secondo numero dell'operazione");
			int num2 = Integer.parseInt(getLine());
			s.getOutputStream().write(num2);
			System.out.println("Operazione da fare: 0-sum 1-sot 2-mol 3-div");
			int num3 = Integer.parseInt(getLine());
			s.getOutputStream().write(num3);
			
			//legge il risultato dal server
			int numero = s.getInputStream().read();
			System.out.println("Client: L'operazione fa " + numero);
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
