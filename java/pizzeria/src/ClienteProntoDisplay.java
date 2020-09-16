

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;

public class ClienteProntoDisplay {

	protected Shell shell;
	private Ordine ordine;

	public ClienteProntoDisplay(Ordine ordine){
		this.ordine = ordine;
	}
	
	
	/*
	public static void main(String[] args) {
		try {
			ClienteProntoDisplay window = new ClienteProntoDisplay();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Open the window.
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
		shell.setSize(450, 320);
		shell.setText("SWT Application");
		
		List list = new List(shell, SWT.BORDER);
		list.setItems(ordine.GetNomiPizze());
		list.setBounds(10, 53, 414, 199);
		
		Label lblPizzePronte = new Label(shell, SWT.WRAP);
		lblPizzePronte.setBounds(10, 10, 414, 34);
		lblPizzePronte.setText("Il tuo ordine contenente le seguenti pizze \u00E8 stato completato, puoi ritirarle al bancone");
		
		Label lblArrivederci = new Label(shell, SWT.WRAP);
		lblArrivederci.setAlignment(SWT.CENTER);
		lblArrivederci.setBounds(10, 258, 414, 20);
		lblArrivederci.setText("Grazie e arrivederci");

	}
}
