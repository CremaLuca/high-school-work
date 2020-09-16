import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class InserisciNoleggioGrafica {

	protected Shell shlInserisciNoleggio;
	GestoreDatabase database = new GestoreDatabase("carsharing");
	ArrayList<Socio> soci;
	ArrayList<Auto> auto;
	int idTarga;
	int idSocio;

	/**
	 * Open the window.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlInserisciNoleggio.open();
		shlInserisciNoleggio.layout();
		while (!shlInserisciNoleggio.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlInserisciNoleggio = new Shell();
		shlInserisciNoleggio.setSize(247, 256);
		shlInserisciNoleggio.setText("Inserisci Noleggio");

		Label lblTarga = new Label(shlInserisciNoleggio, SWT.NONE);
		lblTarga.setBounds(10, 24, 55, 15);
		lblTarga.setText("Targa");

		Combo comboSocio = new Combo(shlInserisciNoleggio, SWT.NONE);
		comboSocio.setBounds(82, 62, 140, 23);
		soci = database.caricaAllSoci();
		String[] codfs = new String[soci.size()];
		for(int i=0;i<soci.size();i++){
			codfs[i] = soci.get(i).getCodiceFiscale();
		}
		comboSocio.setItems(codfs);

		Label lblSocio = new Label(shlInserisciNoleggio, SWT.NONE);
		lblSocio.setBounds(10, 65, 55, 15);
		lblSocio.setText("Socio");

		Combo comboTarga = new Combo(shlInserisciNoleggio, SWT.NONE);
		comboTarga.setBounds(82, 24, 140, 23);
		auto = database.caricaAllAuto();
		String[] targhe = new String[auto.size()];
		for(int i=0;i<auto.size();i++){
			targhe[i] = auto.get(i).getTarga();
		}
		comboTarga.setItems(targhe);

		Label lblDataInizio = new Label(shlInserisciNoleggio, SWT.NONE);
		lblDataInizio.setBounds(10, 111, 61, 15);
		lblDataInizio.setText("Data Inizio");/**/

		DateTime dateBegin = new DateTime(shlInserisciNoleggio, SWT.BORDER);
		dateBegin.setBounds(82, 102, 140, 24);

		Label lblDataFine = new Label(shlInserisciNoleggio, SWT.NONE);
		lblDataFine.setBounds(10, 155, 55, 15);
		lblDataFine.setText("Data Fine");

		DateTime dateEnd = new DateTime(shlInserisciNoleggio, SWT.BORDER);
		dateEnd.setBounds(82, 144, 140, 24);

		idTarga = comboTarga.getSelectionIndex();
		idSocio=comboSocio.getSelectionIndex();

		Button btnConferma = new Button(shlInserisciNoleggio, SWT.NONE);
		btnConferma.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Socio socio = soci.get(comboSocio.getSelectionIndex());
				Auto automobile = auto.get(comboTarga.getSelectionIndex());
				String dataInizio = dateBegin.getYear()+"-"+(dateBegin.getMonth()+1)+"-"+dateBegin.getDay();
				String dataFine = dateEnd.getYear()+"-"+(dateEnd.getMonth()+1)+"-"+dateEnd.getDay();
				database.inserisciNoleggio(socio.getCodiceFiscale(),automobile.getTarga(),dataInizio,dataFine); //    <<<<<<----------------
			}
		});
		btnConferma.setBounds(10, 181, 212, 25);
		btnConferma.setText("Conferma");

	}
}
