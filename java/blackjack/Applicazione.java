
public class Applicazione {

	public static void main(String[] args) {
		CarteServer server = new CarteServer(7777);
		Thread serverThread = new Thread(server);
		serverThread.start();
		CarteClient client = new CarteClient("localhost",7777);
	}
	
}
