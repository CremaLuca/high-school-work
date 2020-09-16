import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import utils.CaricaFile;
import utils.SalvaFile;

import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class Main {

	protected Shell shell;
	protected static List listAtleti;
	protected static List listRisultati;

	private static ArrayList<Atleta> atleti;
	private static ArrayList<Misurazione[]> misurazioni;

	private static SalvaFile sfAtleti;
	private static SalvaFile sfMisuraz;
	private static CaricaFile cfAtleti;
	private static CaricaFile cfMisuraz;
	
	private static Label lblAltetaSelez;
	private Text textNomeAtl;
	private Text textTempo;
	
	private int indexAtlSelez;

	public static void main(String[] args) {
		// inizializzazioni
		atleti = new ArrayList<Atleta>();
		misurazioni = new ArrayList<Misurazione[]>();
		// iniz. carica e salva
		sfAtleti = new SalvaFile(System.getProperty("user.dir") + "\\iscritti.txt");
		sfMisuraz = new SalvaFile(System.getProperty("user.dir") + "\\salti.txt");
		cfAtleti = new CaricaFile(System.getProperty("user.dir") + "\\iscritti.txt");
		cfMisuraz = new CaricaFile(System.getProperty("user.dir") + "\\salti.txt");
		// fasi iniziali
		caricaAtleti();
		caricaRisultati();
		// apri il programma
		try {
			Main window = new Main();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void aggiornaListaAtleti() {
		if (listAtleti != null) {
			String[] nomi = new String[atleti.size()];
			for (int i = 0; i < atleti.size(); i++) {
				nomi[i] = atleti.get(i).getNome();
			}
			listAtleti.setItems(nomi);
		}
	}

	private static int nuovoSlot(String nomeAtleta) {
		atleti.add(new Atleta(nomeAtleta));
		misurazioni.add(new Misurazione[3]);
		aggiornaListaAtleti();
		return misurazioni.size() - 1;
	}

	private static void salvaNuovoAtleta(Atleta atl) {
		sfAtleti.salva(atl.getNome());
	}

	private static void salvaNuovaMisurazione(Misurazione misura) {
		sfMisuraz.salva(misura.getAtleta().getNome() + "_" + misura.getDistanza());
	}

	private static void cancellaAtleta(int index) {
		if (index > -1 && index < atleti.size()) {
			atleti.remove(index);
			sfAtleti.CancellaFile();
			for (int i = 0; i < atleti.size(); i++) {
				salvaNuovoAtleta(atleti.get(i));
			}
		}
	}

	private static void cancellaMisurazione(int index) {
		if (index > -1 && index < misurazioni.size()) {
			misurazioni.remove(index);
			sfMisuraz.CancellaFile();
			for (int i = 0; i < misurazioni.size(); i++) {
				for (int e = 0; e < 3; e++) {
					salvaNuovaMisurazione(misurazioni.get(i)[e]);
				}
			}
		}
	}

	private static int trovaIndexAtleta(Atleta atl) {
		for (int i = 0; i < atleti.size(); i++) {
			if (atleti.get(i).equals(atl)) {
				return i;
			}
		}
		return -1;
	}

	private static void caricaAtleti() {
		ArrayList<String> lineeAtleti = cfAtleti.getFileRighe();
		atleti.clear();
		for (int i = 0; i < lineeAtleti.size(); i++) {
			String linea = lineeAtleti.get(i);
			String[] parti = linea.split("_");
			Atleta atl = new Atleta(parti[0]);
			atleti.add(atl);
		}
	}

	private static void caricaRisultati() {
		ArrayList<String> lineeRis = cfMisuraz.getFileRighe();
		misurazioni.clear();

		for (int i = 0; i < lineeRis.size(); i++) {

			Misurazione mis[] = new Misurazione[3];

			String nome = null;
			
			for (int e = 0; e < 3; e++, i++) {

				String linea = lineeRis.get(i);
				String parti[] = linea.split("_");
				if(nome == null){
					nome = parti[0];
				}else{
					if(!nome.equals(parti[0])){
						break;
					}
				}
				mis[e] = new Misurazione(new Atleta(parti[0]), Double.parseDouble(parti[1]));

			}
			//se l'ultimo esiste
			if(mis[2] != null){
				System.out.println("Aggiunto qualcosa");
				misurazioni.add(mis);
			}
		}
	}

	private static void caricaRisultati(Atleta atl) {

		ArrayList<String> lineeRis = cfAtleti.getFileRighe();
		cancellaMisurazione(trovaIndexAtleta(atl));

		for (int i = 0; i < lineeRis.size(); i++) {

			Misurazione mis[] = new Misurazione[3];

			for (int e = 0; e < 3; e++, i++) {

				String linea = lineeRis.get(i);
				String parti[] = linea.split("_");
				if (parti[0].equals(atl.getNome())) {
					mis[e] = new Misurazione(new Atleta(parti[0]), Double.parseDouble(parti[1]));
				} else {
					break;
				}
			}
			System.out.println("Aggiunto qualcosa");
			misurazioni.add(mis);
		}
	}

	private void setRisultatiAtlLista(int indexAtl) {
		if (listRisultati != null) {
			//se esiste un record
			if(misurazioni.get(indexAtl)[0] != null){
				String[] riusultatiAtleta = new String[3];
				for (int i = 0; i < 3; i++) {
					if(misurazioni.get(indexAtl)[i] != null){
						double ris = misurazioni.get(indexAtl)[i].getDistanza();
						riusultatiAtleta[i] = String.valueOf(ris);
					}else{
						riusultatiAtleta[i] = "Non ancora reg.";
					}
				}
				listRisultati.setItems(riusultatiAtleta);
			}else{
				String[] non = new String[]{
						"Non ha neancora risultati"
				};
				listRisultati.setItems(non);
			}
		}
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
		shell.setSize(543, 440);
		shell.setText("SWT Application");
		shell.setLayout(null);

		Label lblRio = new Label(shell, SWT.NONE);
		lblRio.setAlignment(SWT.CENTER);
		lblRio.setBounds(215, 10, 106, 30);
		lblRio.setFont(SWTResourceManager.getFont("Shruti", 18, SWT.BOLD));
		lblRio.setText("RIO 2016");

		listAtleti = new List(shell, SWT.BORDER);
		listAtleti.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (listAtleti.getSelectionIndex() != -1) {
					setRisultatiAtlLista(listAtleti.getSelectionIndex());
					lblAltetaSelez.setText(atleti.get(listAtleti.getSelectionIndex()).getNome());
				} else {
					System.out.println("Non hai selezionato niente");
				}
			}
		});
		listAtleti.setBounds(373, 78, 144, 202);
		aggiornaListaAtleti();
		
		Label lblAtleti = new Label(shell, SWT.NONE);
		lblAtleti.setAlignment(SWT.CENTER);
		lblAtleti.setBounds(373, 57, 144, 15);
		lblAtleti.setText("Atleti");

		listRisultati = new List(shell, SWT.BORDER);
		listRisultati.setBounds(373, 326, 144, 68);
		
		
		Label lblRisultati = new Label(shell, SWT.NONE);
		lblRisultati.setAlignment(SWT.CENTER);
		lblRisultati.setBounds(373, 305, 144, 15);
		lblRisultati.setText("Risultati Atleta sel.");
		
		Composite compoAggAtl = new Composite(shell, SWT.NONE);
		compoAggAtl.setBounds(8, 78, 146, 106);
		
		Button btnAggiungiAtl = new Button(compoAggAtl, SWT.NONE);
		btnAggiungiAtl.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = nuovoSlot(textNomeAtl.getText());
				System.out.println("Aggiunto nuovo atleta con codice " + index);
				salvaNuovoAtleta(atleti.get(index));
			}
		});
		btnAggiungiAtl.setToolTipText("Aggiungi");
		btnAggiungiAtl.setLocation(0, 81);
		btnAggiungiAtl.setSize(146, 25);
		btnAggiungiAtl.setText("Aggiungi");
		
		textNomeAtl = new Text(compoAggAtl, SWT.BORDER);
		textNomeAtl.setToolTipText("Nome dell'atleta");
		textNomeAtl.setLocation(70, 39);
		textNomeAtl.setSize(76, 21);
		
		Label lblNuovoAtleta = new Label(compoAggAtl, SWT.NONE);
		lblNuovoAtleta.setSize(144, 15);
		lblNuovoAtleta.setAlignment(SWT.CENTER);
		lblNuovoAtleta.setText("Nuovo Atleta");
		
		Label lblNome = new Label(compoAggAtl, SWT.NONE);
		lblNome.setAlignment(SWT.CENTER);
		lblNome.setBounds(0, 43, 69, 15);
		lblNome.setText("Nome");
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(175, 78, 146, 106);
		
		Label lblNuovaMisurazione = new Label(composite, SWT.NONE);
		lblNuovaMisurazione.setAlignment(SWT.CENTER);
		lblNuovaMisurazione.setBounds(0, 0, 146, 15);
		lblNuovaMisurazione.setText("Nuova misurazione");
		
		lblAltetaSelez = new Label(composite, SWT.NONE);
		lblAltetaSelez.setAlignment(SWT.CENTER);
		lblAltetaSelez.setBounds(0, 21, 146, 15);
		lblAltetaSelez.setText("Nessun alteta selez.");
		
		textTempo = new Text(composite, SWT.BORDER);
		textTempo.setBounds(70, 52, 76, 21);
		
		Label lblTempo = new Label(composite, SWT.NONE);
		lblTempo.setBounds(0, 58, 55, 15);
		lblTempo.setText("Tempo");
		
		Button btnRegistra = new Button(composite, SWT.NONE);
		btnRegistra.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(listAtleti.getSelectionIndex() != -1){
					int dove = -1;
					double tempo = Double.parseDouble(textTempo.getText());
					//troviamo se ha buchi liberi
					for(int i=0;i<3;i++){
						if(misurazioni.get(listAtleti.getSelectionIndex())[i] == null){
							dove = i;
							break;
						}
					}
					if(dove != -1){
						misurazioni.get(listAtleti.getSelectionIndex())[dove] = new Misurazione(atleti.get(listAtleti.getSelectionIndex()),tempo);
						if(dove == 2){
							if(listAtleti.getItemCount() <= listAtleti.getSelectionIndex() + 1){
								listAtleti.select(listAtleti.getSelectionIndex() + 1);
							}
						}
					}else{
						System.out.println("Non puoi piu' registrare questo atleta");
					}
				}
			}
		});
		btnRegistra.setBounds(0, 81, 146, 25);
		btnRegistra.setText("Registra");

	}
}
