package org.cramest.socket.IndovinaPartySecondo;

import java.util.ArrayList;

import org.cramest.socket.Server;
import org.eclipse.swt.widgets.Display;

public class IndovinaServer extends Server implements Runnable{
	
	public int numeroRandom;
	public boolean indovinato = false;
	public int indovinatoDaIndex = -1;
	public ArrayList<String> clients;
	public ArrayList<Integer> tentativi;
	private static IndovinaServerShell serverShell;
	
	public static void main(String[] args) {
		IndovinaServer server = new IndovinaServer(9999);
		//Display display = Display.getDefault();
		//serverShell = new IndovinaServerShell(display,server);
		server.Start();
	}
	
	public IndovinaServer(int port) {
		super(port);
		numeroRandom = (int)(Math.random()*99);
		System.out.println("Server - Il numero casuale e' " + numeroRandom);
	}
	
	public int riconosciClient(String ip){
		for(int i=0;i<clients.size();i++){
			if(clients.get(i).equals(ip)){
				return i;
			}
		}
		clients.add(ip);
		tentativi.add(0);
		System.out.println("Server - Aggiunto un client con ip " + ip);
		return clients.size()-1;
	}
	
	public void aggiungiTentativo(int clientIndex){
		tentativi.set(clientIndex, tentativi.get(clientIndex)+1);
		//serverShell.aggiorna();
	}
	
	public void Reset(){
		indovinatoDaIndex = -1;
		indovinato = false;
		tentativi.clear();
		//serverShell.aggiorna();
	}

	public void Start(){
		clients = new ArrayList<String>();
		tentativi = new ArrayList<Integer>();
		while(true){
			acceptSocket();
			String stringaRicevuta = getMessaggio();
			int numeroRicevuto = Integer.parseInt(stringaRicevuta);
			int indexClient = riconosciClient(currentClient.getLocalAddress().toString());
			aggiungiTentativo(indexClient);
			System.out.println("Server - "+clients.get(indexClient)+" e' al " + tentativi.get(indexClient)+" tentativo");
			if(!indovinato){
				if(numeroRicevuto == numeroRandom){
					mandaMessaggio("HAI INDOVINATO IL NUMERO, ERA " + numeroRandom);
					System.out.println("Server - Numero indovinato");
					indovinatoDaIndex = indexClient;
					indovinato = true;
				}else if(numeroRicevuto > numeroRandom){
					mandaMessaggio("Piu' piccolo");
				}else if(numeroRicevuto < numeroRandom){
					mandaMessaggio("Piu' grande");
				}
				//serverShell.aggiorna();
			}else{
				mandaMessaggio("Il numero e' gia' stato indovinato, era " + numeroRandom);
			}
			closeSocket();
		}
	}

	@Override
	public void run() {
		Start();
	}
	
}
