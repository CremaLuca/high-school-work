import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	protected int port;
	protected ServerSocket serverSocket;
	protected Socket currentClient;
	protected BufferedReader input;
	protected PrintWriter output;
	
	protected Server(int port){
		this.port = port;
	}
	
	protected void Start(){
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void Accept(){
		if(serverSocket != null){
			try {
				currentClient = serverSocket.accept();
				InputStreamReader isr = new InputStreamReader(currentClient.getInputStream());
				input = new BufferedReader(isr);
				output = new PrintWriter(currentClient.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void mandaMessaggio(String messaggio){
		if(currentClient != null && output != null){
			output.println(messaggio);
		}
	}
	
	protected String riceviMessaggio(){
		if(currentClient != null && input != null){
			System.out.println("Server - Pronto a ricevere");
			try {
				InputStreamReader isr = new InputStreamReader(currentClient.getInputStream());
				input = new BufferedReader(isr);
				
				String cosa =  input.readLine();
				System.out.println("Server - Ricevuto " + cosa);
				return cosa;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	protected void Close(){
		try {
			currentClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
