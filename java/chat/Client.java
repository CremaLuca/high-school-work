import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Client {

	protected Shell shlChat;
	private Text txtInvia;
	private Text text;
	private Button btnConnetti;
	Socket s;
	PrintWriter out;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Client window = new Client();
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
		shlChat.open();
		shlChat.layout();
		while (!shlChat.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlChat = new Shell();
		shlChat.setSize(450, 300);
		shlChat.setText("Chat");
		
		txtInvia = new Text(shlChat, SWT.BORDER);
		txtInvia.setBounds(10, 227, 329, 25);
		
		Button btnInvia = new Button(shlChat, SWT.NONE);
		btnInvia.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String messagge = txtInvia.getText();
				System.out.println("Mando " + messagge);
				out.println(messagge);
			}
		});
		btnInvia.setBounds(349, 227, 75, 25);
		btnInvia.setText("Invia");
		
		text = new Text(shlChat, SWT.BORDER);
		text.setBounds(10, 39, 414, 179);
		
		btnConnetti = new Button(shlChat, SWT.NONE);
		btnConnetti.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					s = new Socket("172.16.6.18",9999);
					ClientReciever cr = new ClientReciever(s,Client.this);
					out = new PrintWriter(s.getOutputStream());
					System.out.println("Connesso");
					cr.start();
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnConnetti.setBounds(10, 10, 414, 25);
		btnConnetti.setText("Connetti");

	}
	
	public void addMessage(String message){
		Display.getDefault().asyncExec(new Runnable(){
			@Override
			public void run() {
				text.append(message+"\n");
			}});
		
	}
}
