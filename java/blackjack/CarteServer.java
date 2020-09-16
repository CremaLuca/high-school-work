import java.util.ArrayList;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class CarteServer extends Server implements Runnable{

	protected Shell shell;
	private ArrayList<Integer> carteClient;
	private ArrayList<Integer> carteServer;
	
	protected CarteServer(int port) {
		super(port);
	}
	
	@Override
	protected void Start(){
		super.Start();
		while(true){
			System.out.println("Server - In attesa di un client");
			Accept();
			System.out.println("Server - Arrivato un client");
			String messaggio = riceviMessaggio();
			System.out.println("Server - Ho ricevuto " + messaggio);
			if(messaggio.equals("carte")){
				MandaCarte();
			}
			if(messaggio.equals("carta")){
				MandaCarta();
			}
			if(messaggio.equals("resta")){
				Resta();
			}
			Close();
		}
		/*open();  LA GRAFICA LA METTIAMO DOPO*/
	}
	
	private void MandaCarte(){
		carteClient = new ArrayList<Integer>();
		int n1 = (int) (Math.random() * 10)+1;
		int n2 = (int) (Math.random() * 10)+1;
		//Salviamoci le due carte
		carteClient.add(n1);
		carteClient.add(n2);
		//Mandiamole al client
		mandaMessaggio(""+n1);
		mandaMessaggio(""+n2);
		System.out.println("Server - Ho restituito il " + n1 + " e " + n2);
	}
	
	private void MandaCarta(){
		int n = (int) (Math.random() * 10)+1;
		carteClient.add(n);
		mandaMessaggio(""+n); //Mandiamogli una carta
		if(sommaCarte(carteClient) > 21){
			mandaMessaggio("Hai perso");
		}else{
			mandaMessaggio("Ok");
		}
	}
	
	private void Resta(){
		while(sommaCarte(carteServer) < 16){
			int n = (int) (Math.random() * 10)+1;
			carteClient.add(n);
			mandaMessaggio(""+n);
		}
		mandaMessaggio("Fine");
		if(sommaCarte(carteServer) > 21){
			
		}
	}
	
	private int sommaCarte(ArrayList<Integer> carte){
		int somma = 0;
		for(int i=0;i<carte.size();i++){
			somma += carte.get(i);
		}
		return somma;
	}
	
	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");

	}

	@Override
	public void run() {
		Start();
	}

}
