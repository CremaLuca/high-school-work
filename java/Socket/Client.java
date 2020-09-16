package org.cramest.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	protected Socket serverSocket;
	protected int IP;
	protected int port;
	
	protected PrintWriter printWriter;
	protected InputStreamReader isr;
	protected BufferedReader in;
	
	public Client(int IP,int port){
		this.IP = IP;
		this.port = port;
	}
	
	public void ConnettiServer(){
		try {
			serverSocket = new Socket("172.16.6."+IP, port);
			printWriter = new PrintWriter(serverSocket.getOutputStream(), true);
			isr = new InputStreamReader(serverSocket.getInputStream());
			in = new BufferedReader(isr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String RiceviMessaggio(){
		if(serverSocket != null){
			
		}else{
			System.out.println("ERRORE CLIENT - STAI RICEVENTO UN MESSAGGIO SENZA ESSERE COLLEGATO AD UN SERVER");
		}
		return null;
	}
	
	public void MandaMessaggio(String messaggio){
		if(serverSocket != null){
			printWriter.println(messaggio);
		}else{
			System.out.println("ERRORE CLIENT - STAI MANDANDO UN MESSAGGIO SENZA ESSERE COLLEGATO AD UN SERVER");
		}
	}
	
}
