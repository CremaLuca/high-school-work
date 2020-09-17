import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReciever extends Thread{
	Socket s;
	Client c;
	
	public ClientReciever(Socket s,Client c){
		this.s = s;
		this.c = c;
	}
	
	public void run(){
		super.run();
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			while(true){
				System.out.println("Pronto a ricevere messaggi");
				String message = br.readLine();
				System.out.println("Ricevuto " + message);
				c.addMessage(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
