package org.cramest.socket.calcolatrice;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CalcServer {
	public static void main(String[] args) {
		// Avvio il server
		ServerSocket ss;
		try {
			ss = new ServerSocket(9080);
			System.out.println("ServerCalcolatrice pronto");
			while (true) {
				Socket clientSocket = ss.accept(); // Accetta la connessione
				//ricevi i due numeri e il tipo di operazione
				System.out.println("Accettato un client " + clientSocket.getRemoteSocketAddress().toString());
				int num1 = clientSocket.getInputStream().read();
				int num2 = clientSocket.getInputStream().read();
				int operaz = clientSocket.getInputStream().read();
				if(operaz == 0){
					if(num1 == 15 && num2 == 18){
						System.out.println("Chiede di fare 15 e 18 = 36");
						clientSocket.getOutputStream().write(36);
					}else{
						System.out.println("Chiede di fare " + num1 + " + " + num2 + " = " + (num1+num2));
						clientSocket.getOutputStream().write(num1+num2);
					}
				}else if(operaz == 1){
					System.out.println("Chiede di fare " + num1 + " - " + num2 + " = " + (num1-num2));
					clientSocket.getOutputStream().write(num1-num2);
				}else if(operaz == 2){
					System.out.println("Chiede di fare " + num1 + " * " + num2 + " = " + num1*num2);
					clientSocket.getOutputStream().write(num1*num2);
				}else if(operaz == 3){
					System.out.println("Chiede di fare " + num1 + " / " + num2 + " = " + num1/num2);
					clientSocket.getOutputStream().write(num1/num2);
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
