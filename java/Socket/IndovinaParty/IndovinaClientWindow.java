package org.cramest.socket.IndovinaParty;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class IndovinaClientWindow {

	protected Shell shlClient;
	private Text textIP;
	private Text textNumero;
	private Label lblRisultato;
	
	private IndovinaClient client;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			IndovinaClientWindow window = new IndovinaClientWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlClient.open();
		shlClient.layout();
		while (!shlClient.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlClient = new Shell();
		shlClient.setSize(173, 190);
		shlClient.setText("Client");
		
		Label lblIp = new Label(shlClient, SWT.NONE);
		lblIp.setBounds(10, 13, 55, 15);
		lblIp.setText("IP");
		
		textIP = new Text(shlClient, SWT.BORDER);
		textIP.setText("24");
		textIP.setBounds(71, 10, 76, 21);
		
		textNumero = new Text(shlClient, SWT.BORDER);
		textNumero.setText("0");
		textNumero.setBounds(71, 37, 76, 21);
		
		Label lblNumero = new Label(shlClient, SWT.NONE);
		lblNumero.setBounds(10, 40, 55, 15);
		lblNumero.setText("Numero");
		
		Button btnNewButton = new Button(shlClient, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				client = new IndovinaClient(Integer.parseInt(textIP.getText()));
				String testoRicevuto = client.mandaNumero(textNumero.getText());
				lblRisultato.setText(testoRicevuto);
			}
		});
		btnNewButton.setBounds(10, 64, 137, 25);
		btnNewButton.setText("Prova");
		
		lblRisultato = new Label(shlClient, SWT.NONE);
		lblRisultato.setAlignment(SWT.CENTER);
		lblRisultato.setBounds(10, 95, 137, 47);

	}
}
