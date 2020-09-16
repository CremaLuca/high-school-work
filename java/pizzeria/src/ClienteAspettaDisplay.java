import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.List;

public class ClienteAspettaDisplay {

	protected Shell shell;
	Label nomeCliente;
	Ordine ordine;

	/**
	 * Launch the application.
	 * @param args
	 */
	/*public static void main(String[] args) {
		try {
			ClienteAspettaDisplay window = new ClienteAspettaDisplay();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public ClienteAspettaDisplay(Ordine ordine){
		this.ordine = ordine;
	}

	public void close(){
		shell.close();
	}
	
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
		shell.setSize(450, 300);
		shell.setText(ordine.getClass().getName());
		
		nomeCliente = new Label(shell, SWT.HORIZONTAL | SWT.CENTER);
		nomeCliente.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		if(ordine != null){
			nomeCliente.setText(ordine.GetCliente().nome);
		}else{
			nomeCliente.setText("Ordine = null");
		}
		nomeCliente.setBounds(10, 20, 414, 37);
		
		Label lblStaiAspettando = new Label(shell, SWT.NONE);
		lblStaiAspettando.setBounds(10, 63, 110, 15);
		lblStaiAspettando.setText("Stai aspettando");
		
		List list = new List(shell, SWT.BORDER);
		list.setBounds(10, 87, 414, 165);
		if(ordine != null){
			list.setItems(ordine.GetNomiPizze());
		}else{
			String[] str = {"Ordine = null"};
			list.setItems(str);
		}
		
		
	}

}
