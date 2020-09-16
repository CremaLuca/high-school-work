package org.cramest.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	protected ServerSocket serverSocket;
	protected Socket currentClient;
	protected InputStreamReader isr;
	protected BufferedReader br;
	
	public Server(int port){
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			System.out.println("Server - In funzione");
		}
	}
	
	public void acceptSocket(){
		try {
			// Accetta la connessione
			currentClient = serverSocket.accept();
			 isr = new InputStreamReader(currentClient.getInputStream());
			 br = new BufferedReader(isr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeSocket(){
		if(currentClient != null){
			try {
				currentClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			currentClient = null;
			br = null;
			isr = null;
		}else{
			System.out.println("ERRORE SERVER - STAI CERCANDO DI CHIUDERE LA CONNESSIONE CON NESSUN CLIENT");
		}
	}
	
	public String getMessaggio(){
		if(currentClient != null){
			if(br != null){
				try {
					return br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				System.out.println("ERRORE SERVER - NESSUN BUFFERED READER");
			}
		}else{
			System.out.println("ERRORE SERVER - STAI CERCANDO DI RICEVERE UN MESSAGGIO SENZA NESSUN CLIENT");
		}
		return null;
	}
	
	public void mandaMessaggio(String messaggio){
		if(currentClient != null){
			PrintWriter out;
			try {
				out = new PrintWriter(currentClient.getOutputStream(), true);
				out.println(messaggio);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("ERRORE SERVER - STAI CERCANDO DI MANDARE UN MESSAGGIO SENZA NESSUN CLIENT");
		}
	}
	
}
