import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	protected Socket serverSocket;
	protected String serverIP;
	protected int port;
	private BufferedReader input;
	protected PrintWriter output;
	
	protected Client(String serverIP,int port){
		this.serverIP = serverIP;
		this.port = port;
	}
	
	protected void Connetti(){
		try {
			serverSocket = new Socket(serverIP,port);
			
			output = new PrintWriter(serverSocket.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void mandaMessaggio(String messaggio){
		if(serverSocket != null && output != null){
			output.println(messaggio);
			System.out.println("Client - Mandato " + messaggio);
		}
	}
	
	protected String riceviMessaggio(){
		if(serverSocket != null){
			try {
				InputStreamReader isr = new InputStreamReader(serverSocket.getInputStream());
				input = new BufferedReader(isr);
				return input.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
