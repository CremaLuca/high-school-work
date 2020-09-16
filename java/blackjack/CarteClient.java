import java.util.ArrayList;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CarteClient extends Client{

	protected Shell shell;
	private ArrayList<Integer> carte;
	private ArrayList<Integer> carteServer;
	
	protected CarteClient(String serverIP, int port) {
		super(serverIP, port);
		ChiediCarte();
		open();
	}
	
	public void ChiediCarte(){
		Connetti();
		mandaMessaggio("carte");
		RiceviCarte();
	}
	
	public void ChiediCarta(){
		Connetti();
		mandaMessaggio("carta"); //tanto il server non controlla se e' carta, controlla solo se e' resta, possiamo anche mandargli "banana"
	}
	
	public void RiceviCarte(){
		carte = new ArrayList<Integer>();
		System.out.println("Client - Pronto a ricevere le carte");
		carte.add(Integer.parseInt(riceviMessaggio()));
		carte.add(Integer.parseInt(riceviMessaggio()));
		System.out.println("Client - Ho ricevuto il " + carte.get(0) + " e " + carte.get(1));
	}
	
	public void RiceviCarta(){
		carte.add(Integer.parseInt(riceviMessaggio()));
		System.out.println("Client - Ho ricevuto il " + carte.get(0));
	}
	
	public String RiceviStato(){
		return riceviMessaggio();
	}
	
	public void Resta(){
		mandaMessaggio("resta");
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
		shell.setText("Client");
		
		Label lblCarteAttuali = new Label(shell, SWT.NONE);
		lblCarteAttuali.setBounds(44, 10, 84, 15);
		lblCarteAttuali.setText("Carte attuali:");
		
		List list = new List(shell, SWT.BORDER);
		list.setBounds(10, 31, 167, 81);
		
		Button btnCarta = new Button(shell, SWT.NONE);
		btnCarta.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ChiediCarta();
				RiceviCarta();
				String stato = RiceviStato();
				if(stato.equals("Hai perso")){
					int somma = sommaCarte(carte);
					MessageDialog.openError(shell, "HAI PERSO", "La somma delle tue carte e' " + somma);
				}
			}
		});
		btnCarta.setBounds(195, 30, 75, 25);
		btnCarta.setText("Carta");
		
		Button btnResta = new Button(shell, SWT.NONE);
		btnResta.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Resta();
				carteServer = new ArrayList<Integer>();
				while(!riceviMessaggio().equals("Fine")){
					carteServer.add(Integer.parseInt(riceviMessaggio()));
				}
				String messaggio = riceviMessaggio();
				if(messaggio.equals("Hai vinto")){
					MessageDialog.openConfirm(shell, "HAI VINTO", "Hai vinto tu, il server ha fatto " + sommaCarte(carteServer) + ", tu invece " + sommaCarte(carte));
				}else{
					MessageDialog.openError(shell, "HAI PERSO", "Hai perso, il server ha fatto " + sommaCarte(carteServer) + ", tu invece " + sommaCarte(carte));
				}
				
			}
		});
		btnResta.setBounds(328, 30, 75, 25);
		btnResta.setText("Resta");
		
		Label lblCarteDelServer = new Label(shell, SWT.NONE);
		lblCarteDelServer.setBounds(44, 120, 84, 15);
		lblCarteDelServer.setText("Carte del server");
		
		List list_1 = new List(shell, SWT.BORDER);
		list_1.setBounds(10, 141, 167, 98);

	}
}
