package Slots;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyListener;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class SlotMachine implements KeyListener {
	Display display;
	protected Shell shlSlotMachine;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text txtTitle;
	private Text txtCrediti;
	private Text txtBet;
	private Text txtVincita;
	private Label lblGirandola1;
	private Label lblGirandola2;
	private Label lblGirandola3;
	private Label[] girandole = new Label[3];
	private static int[] numeroImmagini = new int[3];
	private int crediti = 99;
	private int bet = 1;
	private int pvincita = 2;
	private static String[] pathImmagini = { "/Immagini/arancia.png", "/Immagini/limone.png", "/Immagini/bar.png",
			"/Immagini/bigwin.png", "/Immagini/ciliegie.png",

			// "/Immagini/banane.png",
			// "/Immagini/melone.png",
			// "/Immagini/prugna.png",
			"/Immagini/sette.png" };
	private int threadCheHannoFinito = 0;
	private boolean haFinito = true;
	private Clip levaClip;
	private AudioInputStream audioIn;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			SlotMachine window = new SlotMachine();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Costruttore vuoto
	 */
	protected SlotMachine() {

	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shlSlotMachine.open();
		shlSlotMachine.layout();
		while (!shlSlotMachine.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public class ThreadGirandola extends Thread {

		private int nGirandola;
		private int nImmagine;

		public ThreadGirandola(int nGirandola, int nImmagine) {
			this.nGirandola = nGirandola;
			this.nImmagine = nImmagine;
		}

		public void run() {
			if (nImmagine == -1) {
				nImmagine = random();
			}
			cambiaImmagine(nGirandola, nImmagine);
			// saviamoci che questa girandola ha questa immagine
			numeroImmagini[nGirandola - 1] = nImmagine;
			finisci();
		}
	}

	/**
	 * @param girandola
	 *            Di quale ruota cambiare l'immagine
	 * @param indexImmagine
	 *            Qual'è la posizione dell'immagine da prendere sulla lista
	 *            delle immagini
	 */
	private void cambiaImmagine(int girandola, int indexImmagine) {
		girandola -= 1; // Così possiamo usare girandola 1-2-3 e non 0-1-2

		// i<Numero di rotazioni che deve fare (a caso tipo)
		int giraFinoA = (int) (Math.random() * 20 + 20);
		for (int i = 0; i < giraFinoA; i++) {
			// random così sembra uno shuffle casuale
			// System.out.println("Ciao da girandola numero " + girandola);
			int randSleep = (int) ((Math.random() * 10) + 50);
			try {
				Thread.sleep(randSleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int immagineRandom = random();
			cambiaImmagineDaThread(girandola, immagineRandom);
		}
		cambiaImmagineDaThread(girandola, indexImmagine);
	}

	private void cambiaImmagineDaThread(int numeroGirandola, int numero) {
		display.asyncExec(new Runnable() {
			@Override
			public void run() {
				girandole[numeroGirandola]
						.setImage(SWTResourceManager.getImage(SlotMachine.class, pathImmagini[numero]));
			}
		});
	}

	public void playSound() {
		try {
			File file = new File("src/Suoni/Leva.wav");
			audioIn = AudioSystem.getAudioInputStream(file);
			levaClip = AudioSystem.getClip();
			levaClip.open(audioIn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		levaClip.start();
	}

	public void gira() {
		if (haFinito) {
			if (bet > 0) {
				playSound();
				threadCheHannoFinito = 0;
				haFinito = false;

				int immagineComune = -1;
				if (Math.random() * 100 > 90) {
					System.out.println("Adesso ti faccio vincere");
					immagineComune = random();
				}
				ThreadGirandola t1 = new ThreadGirandola(1, immagineComune);
				ThreadGirandola t2 = new ThreadGirandola(2, immagineComune);
				ThreadGirandola t3 = new ThreadGirandola(3, immagineComune);
				t1.start();
				t2.start();
				t3.start();
			} else {
				System.out.println("Devi scommettere qualcosa pero'");
			}
		} else {
			System.out.println("Non ha neancora finito");
		}
	}

	/**
	 * Chiamato dai thread per indicare che le girandole hanno finito di girare
	 */
	public void finisci() {
		threadCheHannoFinito++;
		if (threadCheHannoFinito >= 3) {
			haFinito = true;
			finito();
		}
	}

	private void stopPlay() {
		if (levaClip != null) {
			levaClip.stop();
			levaClip.close();
			levaClip = null;
		}
	}

	/**
	 * Quando le girandole hanno finito di girare
	 */
	public void finito() {
		// System.out.println("Finito");
		stopPlay();
		controlloF();
	}

	/**
	 * Viene chiamato da un thread quindi ha bisogno di usare il asyncExec
	 */
	public void controlloF() {

		if (crediti > 0) {
			bet = 1;
			crediti -= 1;
		} else {
			bet = 0;
		}

		if (haVinto()) {
			switch (numeroImmagini[0]) {
			case 2: // BAR
				crediti += pvincita * 2;
				MessageDialogDaThread("VINCITA", "BAR! BAR! BAR!");
				break;
			case 5: // SETTE
				crediti += pvincita * 4;
				MessageDialogDaThread("VINCITA", "7! 7! 7!");
				break;
			case 3: // BIGWIN
				crediti += pvincita * 8;
				MessageDialogDaThread("VINCITA", "BIG WIN! BIG WIN! BIG WIN!");
				break;
			default:
				crediti += pvincita;
				break;
			}
		}
		pvincita = bet * 2;

		display.asyncExec(new Runnable() {
			@Override
			public void run() {

				txtBet.setText("" + bet);

				txtCrediti.setText("" + crediti);

				txtVincita.setText("" + pvincita);
			}
		});
	}

	public void MessageDialogDaThread(String titolo, String testo) {
		display.asyncExec(new Runnable() {
			@Override
			public void run() {
				MessageDialog.openConfirm(shlSlotMachine, titolo, testo);
			}
		});
	}

	private boolean haVinto() {
		if (numeroImmagini[0] == numeroImmagini[1] && numeroImmagini[0] == numeroImmagini[2]) {
			return true;
		} else {
			// System.out.println("Immagine1 = " + numeroImmagini[0] + ", 2 =" +
			// numeroImmagini[1] + ", 3 = " + numeroImmagini[2]);
			return false;
		}
	}

	/**
	 * 
	 * @return Un numero random che rappresenta una immagine (0-numeroImmagini)
	 */
	public static int random() {
		return (int) (Math.random() * pathImmagini.length);
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlSlotMachine = new Shell();
		shlSlotMachine.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		shlSlotMachine.setSize(500, 500);
		shlSlotMachine.setText("Slot Machine");
		shlSlotMachine.addKeyListener(this);
		shlSlotMachine.setText("Progetto di Crema Fedato e Kawkab");

		Composite compositeTop = new Composite(shlSlotMachine, SWT.NONE);
		compositeTop.setBounds(10, 10, 464, 58);

		txtTitle = new Text(compositeTop, SWT.SINGLE | SWT.READ_ONLY | SWT.BORDER | SWT.CENTER);

		txtTitle.setFont(SWTResourceManager.getFont("Segoe UI Light", 28, SWT.NORMAL));
		txtTitle.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtTitle.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		txtTitle.setText("\n\nSlot Machine");
		txtTitle.setBounds(0, 0, 464, 58);
		txtTitle.addKeyListener(this);

		Composite compositeBottoni = new Composite(shlSlotMachine, SWT.NONE);
		compositeBottoni.setBackground(SWTResourceManager.getColor(SWT.COLOR_LINK_FOREGROUND));
		compositeBottoni.setBounds(10, 350, 464, 100);

		Button btnReset = new Button(compositeBottoni, SWT.NONE);
		btnReset.setLocation(10, 10);
		btnReset.setSize(65, 75);
		btnReset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				crediti = 99;
				bet = 1;
				pvincita = bet * 2;

				txtBet.setText("" + bet);
				txtCrediti.setText("" + crediti);
				txtVincita.setText("" + pvincita);
			}
		});
		btnReset.setText("Reset");
		btnReset.addKeyListener(this);

		Button btnRitira = new Button(compositeBottoni, SWT.CENTER);
		btnRitira.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				bet = Integer.parseInt(txtBet.getText());
				crediti = Integer.parseInt(txtCrediti.getText());
				pvincita = (bet + crediti);
				bet = 0;
				crediti = 0;
				txtBet.setText("0");
				txtCrediti.setText("0");
				txtVincita.setText("0");
				if (pvincita > 0) {
					MessageDialog.openInformation(shlSlotMachine, "RITIRATO",
							"Ti sei ritirato vincendo: " + pvincita + " crediti!");
				} else {
					MessageDialog.openInformation(shlSlotMachine, "PERDENTE",
							"Inutile che ti ritiri tanto non vinci niente");
				}
			}
		});
		btnRitira.setLocation(90, 10);
		btnRitira.setSize(75, 75);
		btnRitira.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnRitira.setText("Ritira");
		btnRitira.addKeyListener(this);

		Button btnScommettiUno = new Button(compositeBottoni, SWT.CENTER);
		btnScommettiUno.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int t;
				t = Integer.parseInt(txtCrediti.getText());
				if (t > 0) {
					bet = Integer.parseInt(txtBet.getText());
					bet = bet + 1;

					crediti = Integer.parseInt(txtCrediti.getText());
					crediti = crediti - 1;
					pvincita = bet * 2;

					txtBet.setText("" + bet);
					txtCrediti.setText("" + crediti);
					txtVincita.setText("" + pvincita);
				} else {
					MessageDialog.openError(shlSlotMachine, "SCOMMESSA NON VALIDA", "Non hai crediti per scommettere!");
				}
			}
		});
		btnScommettiUno.setLocation(180, 10);
		btnScommettiUno.setSize(75, 75);
		btnScommettiUno.setText("Bet +1");
		btnScommettiUno.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnScommettiUno.addKeyListener(this);

		Button btnScommettiMax = new Button(compositeBottoni, SWT.CENTER);
		btnScommettiMax.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				bet += crediti;
				crediti = 0;
				pvincita = bet * 2;

				txtBet.setText("" + bet);
				txtCrediti.setText("" + crediti);
				txtVincita.setText("" + pvincita);
			}
		});
		btnScommettiMax.setLocation(270, 10);
		btnScommettiMax.setSize(75, 75);
		btnScommettiMax.setText("Bet Max");
		btnScommettiMax.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnScommettiMax.addKeyListener(this);

		Button btnGira = new Button(compositeBottoni, SWT.CENTER);
		btnGira.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				gira();
			}
		});
		btnGira.setLocation(389, 10);
		btnGira.setSize(65, 75);
		btnGira.setText("Gira");
		btnGira.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnGira.addKeyListener(this);

		lblGirandola1 = new Label(shlSlotMachine, SWT.BORDER | SWT.WRAP | SWT.SHADOW_NONE | SWT.CENTER);
		lblGirandola1.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		lblGirandola1.setImage(SWTResourceManager.getImage(SlotMachine.class, "/Immagini/arancia.png"));
		lblGirandola1.setBounds(10, 75, 150, 150);
		formToolkit.adapt(lblGirandola1, true, true);
		girandole[0] = lblGirandola1;

		lblGirandola2 = new Label(shlSlotMachine, SWT.BORDER | SWT.WRAP | SWT.SHADOW_NONE | SWT.CENTER);
		lblGirandola2.setImage(SWTResourceManager.getImage(SlotMachine.class, "/Immagini/sette.png"));
		lblGirandola2.setBounds(168, 75, 150, 150);
		formToolkit.adapt(lblGirandola2, true, true);
		girandole[1] = lblGirandola2;

		lblGirandola3 = new Label(shlSlotMachine, SWT.BORDER | SWT.WRAP | SWT.SHADOW_NONE | SWT.CENTER);
		lblGirandola3.setImage(SWTResourceManager.getImage(SlotMachine.class, "/Immagini/bar.png"));
		lblGirandola3.setBounds(324, 75, 150, 150);
		formToolkit.adapt(lblGirandola3, true, true);
		girandole[2] = lblGirandola3;

		txtCrediti = new Text(shlSlotMachine, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		txtCrediti.setText("" + crediti);
		txtCrediti.setBounds(10, 281, 150, 21);
		formToolkit.adapt(txtCrediti, true, true);
		txtCrediti.addKeyListener(this);

		Label lblCrediti = new Label(shlSlotMachine, SWT.NONE);
		lblCrediti.setBackground(SWTResourceManager.getColor(240, 240, 240));
		lblCrediti.setBounds(10, 260, 150, 15);
		formToolkit.adapt(lblCrediti, true, true);
		lblCrediti.setText("Crediti");

		txtBet = new Text(shlSlotMachine, SWT.BORDER);
		txtBet.setEditable(false);
		txtBet.setText("" + bet);
		txtBet.setBounds(210, 281, 76, 21);
		formToolkit.adapt(txtBet, true, true);
		txtBet.addKeyListener(this);

		Label lblBet = new Label(shlSlotMachine, SWT.NONE);
		lblBet.setBounds(210, 260, 76, 15);
		formToolkit.adapt(lblBet, true, true);
		lblBet.setText("Bet");

		txtVincita = new Text(shlSlotMachine, SWT.BORDER);
		txtVincita.setEditable(false);
		txtVincita.setText("" + pvincita);
		txtVincita.setBounds(324, 281, 150, 21);
		formToolkit.adapt(txtVincita, true, true);
		txtVincita.addKeyListener(this);

		Label lblPossibileVincita = new Label(shlSlotMachine, SWT.NONE);
		lblPossibileVincita.setBounds(324, 260, 150, 15);
		formToolkit.adapt(lblPossibileVincita, true, true);
		lblPossibileVincita.setText("Possibile vincita");

	}

	@Override
	public void keyPressed(org.eclipse.swt.events.KeyEvent arg0) {

	}

	@Override
	public void keyReleased(org.eclipse.swt.events.KeyEvent arg0) {
		if (haFinito) {
			// SU
			if (arg0.character == 'w') {
				if (crediti > 0) {
					bet += 1;
					crediti -= 1;
					pvincita = bet * 2;
				} else {
					System.out.println(crediti);
				}
			}
			// GIU
			if (arg0.character == 's') {
				if (bet > 1) {
					bet -= 1;
					crediti += 1;
					pvincita = bet * 2;
				} else {
					System.out.println("Non hai abbastanza bet");
				}
			}
			if (arg0.character == 'g') {
				gira();
			}
		}

		txtBet.setText("" + bet);
		txtCrediti.setText("" + crediti);
		txtVincita.setText("" + pvincita);
	}
}
