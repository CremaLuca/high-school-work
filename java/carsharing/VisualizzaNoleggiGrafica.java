import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLClientInfoException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import com.mysql.jdbc.DatabaseMetaData;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;

public class VisualizzaNoleggiGrafica {

	protected Shell shlElencoNoleggi;
	GestoreDatabase database = new GestoreDatabase("carsharing");
	ArrayList<Socio> soci;
	ArrayList<Noleggio> noleggi;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			VisualizzaNoleggiGrafica window = new VisualizzaNoleggiGrafica();
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
		shlElencoNoleggi.open();
		shlElencoNoleggi.layout();
		while (!shlElencoNoleggi.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlElencoNoleggi = new Shell();
		shlElencoNoleggi.setSize(450, 313);
		shlElencoNoleggi.setText("Elenco Noleggi");

		Combo comboSocio = new Combo(shlElencoNoleggi, SWT.NONE);
		comboSocio.setBounds(48, 20, 127, 23);
		soci = database.caricaAllSoci();
		String[] CFs = new String[soci.size()];
		for (int i = 0; i < soci.size(); i++) {
			CFs[i] = soci.get(i).getCodiceFiscale();
		}
		comboSocio.setItems(CFs);
		Label lblSocio = new Label(shlElencoNoleggi, SWT.NONE);
		lblSocio.setBounds(10, 23, 36, 15);
		lblSocio.setText("Socio");

		Label lblInizio = new Label(shlElencoNoleggi, SWT.NONE);
		lblInizio.setBounds(262, 23, 28, 15);
		lblInizio.setText("Inizio");

		List listNoleggi = new List(shlElencoNoleggi, SWT.BORDER);
		listNoleggi.setBounds(10, 120, 414, 147);

		DateTime dateInizio = new DateTime(shlElencoNoleggi, SWT.BORDER);
		dateInizio.setBounds(296, 20, 128, 24);

		Button btnConferma = new Button(shlElencoNoleggi, SWT.NONE);
		btnConferma.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (comboSocio.getSelectionIndex() != -1) {
					noleggi = database.caricaNoleggio(soci.get(comboSocio.getSelectionIndex()).getCodiceFiscale());
					if (noleggi != null) {
						for (int i = 0; i < noleggi.size(); i++) {
							listNoleggi.add(noleggi.get(i).getInizio().toString());
						}
					} else {
						JOptionPane.showMessageDialog(null, "Non ci sono noleggi per questa persona.", "Errore",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnConferma.setBounds(10, 89, 414, 25);
		btnConferma.setText("Conferma");

		Label lblFine = new Label(shlElencoNoleggi, SWT.NONE);
		lblFine.setBounds(262, 55, 28, 15);
		lblFine.setText("Fine");

		DateTime dateFine = new DateTime(shlElencoNoleggi, SWT.BORDER);
		dateFine.setBounds(295, 50, 129, 24);
	}
}
