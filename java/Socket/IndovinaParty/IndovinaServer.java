package org.cramest.socket.IndovinaParty;

import org.cramest.socket.Server;

public class IndovinaServer extends Server{
	
	private int numeroRandom;
	private boolean indovinato = false;
	
	public static void main(String[] args) {
		IndovinaServer server = new IndovinaServer(9999);
		server.Start();
	}
	
	public IndovinaServer(int port) {
		super(port);
		numeroRandom = (int)(Math.random()*99);
		System.out.println("Server - Il numero casuale e' " + numeroRandom);
	}

	public void Start(){
		while(true){
			acceptSocket();
			String stringaRicevuta = getMessaggio();
			int numeroRicevuto = Integer.parseInt(stringaRicevuta);
			if(!indovinato){
				if(numeroRicevuto == numeroRandom){
					mandaMessaggio("HAI INDOVINATO IL NUMERO, ERA " + numeroRandom);
					System.out.println("Server - Numero indovinato");
					indovinato = true;
				}else if(numeroRicevuto > numeroRandom){
					mandaMessaggio("Piu' piccolo");
				}else if(numeroRicevuto < numeroRandom){
					mandaMessaggio("Piu' grande");
				}
			}else{
				mandaMessaggio("Il numero e' gia' stato indovinato, era " + numeroRandom);
			}
			closeSocket();
		}
	}
	
}
