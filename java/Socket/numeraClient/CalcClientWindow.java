package org.cramest.socket.numeraClient;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CalcClientWindow {

	protected Shell shlClient;
	private Text text;
	private Label labelNumeroClient;
	
	public ClientNumerato client;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CalcClientWindow window = new CalcClientWindow();
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
		shlClient.setSize(185, 127);
		shlClient.setText("Client");
		
		Label lblIp = new Label(shlClient, SWT.NONE);
		lblIp.setAlignment(SWT.CENTER);
		lblIp.setBounds(10, 13, 68, 15);
		lblIp.setText("IP");
		
		text = new Text(shlClient, SWT.BORDER);
		text.setText("24");
		text.setBounds(84, 10, 75, 21);
		
		Button btnNewButton = new Button(shlClient, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				client = new ClientNumerato(Integer.parseInt(text.getText()));
				labelNumeroClient.setText(client.getNumero() + "");
			}
		});
		btnNewButton.setBounds(10, 34, 149, 25);
		btnNewButton.setText("Richiedi numero");
		
		Label lblNumeroDiClient = new Label(shlClient, SWT.NONE);
		lblNumeroDiClient.setAlignment(SWT.CENTER);
		lblNumeroDiClient.setBounds(10, 65, 92, 15);
		lblNumeroDiClient.setText("Numero di client:");
		
		labelNumeroClient = new Label(shlClient, SWT.NONE);
		labelNumeroClient.setAlignment(SWT.CENTER);
		labelNumeroClient.setBounds(129, 65, 30, 15);
		labelNumeroClient.setText("?");

	}
}
