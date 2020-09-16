import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ClienteDisplay {

	protected Shell shlCliente;
	private Text text;
	List list_1;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			if(ListaOrdini.instanza == null){
				ListaOrdini.instanza = new ListaOrdini();
			}
			ClienteDisplay window = new ClienteDisplay();
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
		shlCliente.open();
		shlCliente.layout();
		Pizzaiolo window2 = new Pizzaiolo();
		window2.open();
		while (!shlCliente.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlCliente = new Shell();
		shlCliente.setSize(499, 535);
		shlCliente.setText("Cliente");
		
		Label lblNome = new Label(shlCliente, SWT.CENTER);
		lblNome.setAlignment(SWT.CENTER);
		lblNome.setBounds(9, 13, 139, 15);
		lblNome.setText("Nome:");
		
		text = new Text(shlCliente, SWT.BORDER);
		text.setBounds(154, 10, 319, 21);
		
		List list = new List(shlCliente, SWT.BORDER);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				list_1.add(list.getItem(list.getSelectionIndex()));
			}
		});
		list.setItems(ListaPizze.listaPizze);
		list.setBounds(9, 72, 205, 367);
		
		list_1 = new List(shlCliente, SWT.BORDER);
		list_1.setBounds(268, 72, 205, 367);
		
		Label lblPizzeOrdinabili = new Label(shlCliente, SWT.NONE);
		lblPizzeOrdinabili.setBounds(9, 51, 205, 15);
		lblPizzeOrdinabili.setText("Pizze ordinabili");
		
		Label lblPizzeOrdinate = new Label(shlCliente, SWT.NONE);
		lblPizzeOrdinate.setBounds(268, 51, 205, 15);
		lblPizzeOrdinate.setText("Pizze ordinate");
		
		Button btnOrdina = new Button(shlCliente, SWT.NONE);
		btnOrdina.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Pizza[] pizze = new Pizza[list_1.getItemCount()];
				for(int i=0;i<list_1.getItemCount();i++){
					String nome = list_1.getItem(i);
					pizze[i] = new Pizza(nome,ListaPizze.getTempoCottura(nome));
				}
				Cliente cliente = new Cliente(text.getText());
				Ordine ordine = new Ordine(cliente,pizze);
				ListaOrdini.instanza.AggOrdine(ordine);
				shlCliente.close();
				cliente.Aspetta(ordine);
			}
		});
		btnOrdina.setBounds(9, 445, 464, 42);
		btnOrdina.setText("ORDINA");
		
	}
}
