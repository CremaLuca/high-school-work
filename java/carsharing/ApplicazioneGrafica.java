import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ApplicazioneGrafica {

	protected Shell shlCarsharing;

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlCarsharing.open();
		shlCarsharing.layout();
		while (!shlCarsharing.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlCarsharing = new Shell();
		shlCarsharing.setSize(390, 238);
		shlCarsharing.setText("Carsharing");
		
		Button btnNoleggi = new Button(shlCarsharing, SWT.NONE);
		btnNoleggi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					VisualizzaNoleggiGrafica window = new VisualizzaNoleggiGrafica();
					window.open();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNoleggi.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		btnNoleggi.setBounds(10, 96, 155, 94);
		btnNoleggi.setText("Visualizza noleggi");
		
		Button btnInserisci = new Button(shlCarsharing, SWT.NONE);
		btnInserisci.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					InserisciNoleggioGrafica window = new InserisciNoleggioGrafica();
					window.open();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnInserisci.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		btnInserisci.setBounds(209, 96, 155, 94);
		btnInserisci.setText("Inserisci");
		
		Label lblTitolo = new Label(shlCarsharing, SWT.NONE);
		lblTitolo.setFont(SWTResourceManager.getFont("Segoe UI", 15, SWT.BOLD));
		lblTitolo.setAlignment(SWT.CENTER);
		lblTitolo.setBounds(10, 10, 354, 36);
		lblTitolo.setText("CARSHARING");
		
		Label lblNewLabel = new Label(shlCarsharing, SWT.NONE);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(10, 52, 354, 15);
		lblNewLabel.setText(" by Tabela E Acerkacke");

	}
}
