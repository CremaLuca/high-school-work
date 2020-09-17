import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	static ArrayList<PrintWriter> clientList = new ArrayList<PrintWriter>();
	
	public static class ServerThread extends Thread{
		
		Socket s;
		
		//Il costruttore deve ricevere il socket su cui lavorare
		public ServerThread(Socket s){
			this.s = s;
		}
		
		@Override
		public void run(){
			super.run();
			//Resta in attesa di messaggi da un client
			System.out.println("Sono pronto a ricevere messaggi");
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				//PrintWriter out = new PrintWriter(s.getOutputStream());
				while(true){
					System.out.println("Server - In attesa di un messaggio");
					String messaggio = in.readLine();
					System.out.println("Server - Ho ricevuto: " + messaggio);
					for (PrintWriter printWriter : clientList) {
						printWriter.println(messaggio);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//quando riceve un messaggio
			//manda il messaggio a tutti
		}
	}
	
	public static void main(String[] args){
		//Crea un server socket in ascolto
		try {
			ServerSocket ss = new ServerSocket(9999);
			//per ogni connessione crea un socket e un thread che lo gestisca
			while(true){
				Socket s = ss.accept();
				System.out.println("Accettato un socket");
				//Aggiunge ad un vettore il client
				PrintWriter out = new PrintWriter(s.getOutputStream());
				clientList.add(out);
				ServerThread st = new ServerThread(s);
				st.start();
				//Ritorna in ascolto/attesa
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
