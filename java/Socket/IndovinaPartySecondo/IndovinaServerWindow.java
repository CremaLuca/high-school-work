package org.cramest.socket.IndovinaPartySecondo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.List;

public class IndovinaServerWindow {

	protected Shell shell;
	private Text numeroCasuale;
	private Label lblIndovinato;
	private IndovinaServer server;
	protected List listClient;

	/**
	 * @wbp.parser.entryPoint
	 */
	public IndovinaServerWindow(IndovinaServer server){
		this.server = server;
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
	
	public void aggiorna(){
		lblIndovinato.setText(server.indovinato ? "Si" : "No");
		String[] clients = new String[server.clients.size()];
		for(int i=0;i<server.clients.size() && i<server.tentativi.size();i++){
			clients[i] = server.clients.get(i) + " - " + server.tentativi .get(i);
		}
		listClient.setItems(clients);
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(261, 300);
		shell.setText("SWT Application");
		
		Label lblNumeroCasuale = new Label(shell, SWT.NONE);
		lblNumeroCasuale.setBounds(10, 13, 96, 15);
		lblNumeroCasuale.setText("Numero casuale:");
		
		numeroCasuale = new Text(shell, SWT.BORDER);
		numeroCasuale.setEditable(false);
		numeroCasuale.setText(server.numeroRandom + "");
		numeroCasuale.setBounds(159, 10, 76, 21);
		
		Label lblEStatoIndovinato = new Label(shell, SWT.NONE);
		lblEStatoIndovinato.setBounds(10, 45, 109, 15);
		lblEStatoIndovinato.setText("E' stato indovinato");
		
		lblIndovinato = new Label(shell, SWT.NONE);
		lblIndovinato.setBounds(159, 45, 76, 15);
		lblIndovinato.setText("No");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				server.Reset();
			}
		});
		btnNewButton.setBounds(10, 66, 225, 25);
		btnNewButton.setText("Pulisci");
		
		listClient = new List(shell, SWT.BORDER);
		listClient.setBounds(10, 119, 225, 133);
		
		Label lblClients = new Label(shell, SWT.NONE);
		lblClients.setBounds(10, 98, 55, 15);
		lblClients.setText("Clients");

	}
}
