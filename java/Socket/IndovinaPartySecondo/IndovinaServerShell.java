package org.cramest.socket.IndovinaPartySecondo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class IndovinaServerShell extends Shell {

	private Text numeroCasuale;
	private Label lblIndovinato;
	private IndovinaServer server;
	protected List listClient;

	/**
	 * Create the shell.
	 * @param display
	 */
	public IndovinaServerShell(Display display,IndovinaServer server) {
		super(display, SWT.SHELL_TRIM);
		setSize(266, 300);
		this.server = server;
		createContents();
		open();
		//layout();
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
	 * Create contents of the shell.
	 */
	protected void createContents() {
		
		Label lblNumeroCasuale = new Label(this, SWT.NONE);
		lblNumeroCasuale.setBounds(10, 13, 96, 15);
		lblNumeroCasuale.setText("Numero casuale:");
		
		numeroCasuale = new Text(this, SWT.BORDER);
		numeroCasuale.setEditable(false);
		numeroCasuale.setText(server.numeroRandom + "");
		numeroCasuale.setBounds(159, 10, 76, 21);
		
		Label lblEStatoIndovinato = new Label(this, SWT.NONE);
		lblEStatoIndovinato.setBounds(10, 45, 109, 15);
		lblEStatoIndovinato.setText("E' stato indovinato");
		
		lblIndovinato = new Label(this, SWT.NONE);
		lblIndovinato.setBounds(159, 45, 76, 15);
		lblIndovinato.setText("No");
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				server.Reset();
			}
		});
		btnNewButton.setBounds(10, 66, 225, 25);
		btnNewButton.setText("Pulisci");
		
		listClient = new List(this, SWT.BORDER);
		listClient.setBounds(10, 119, 225, 133);
		
		Label lblClients = new Label(this, SWT.NONE);
		lblClients.setBounds(10, 98, 55, 15);
		lblClients.setText("Clients");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
