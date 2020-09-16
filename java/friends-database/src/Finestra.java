import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Finestra {

	protected Shell shlIMieiAmici;
	List listaAmici;
    int[] ids;
	ArrayList<Amico> amici = new ArrayList<Amico>();
	private Text textNome;
	private Text textCognome;
	private Text textTelefono;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Finestra window = new Finestra();
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
		 setAmici();
		shlIMieiAmici.open();
		shlIMieiAmici.layout();
		while (!shlIMieiAmici.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public void setAmici(){
		ResultSet res = null;
		try {
			 res = Connettore.getData("test", "SELECT * FROM amici");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(res != null){
			try {
				while (res.next() == true){
					amici.add(new Amico(Integer.parseInt(res.getString("ID")),res.getString("nome"),res.getString("cognome"),res.getString("telefono")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		String[] nomi = new String[amici.size()];
		ids = new int[amici.size()];
		for(int i=0;i<amici.size();i++){
			nomi[i] = amici.get(i).toString();
			ids[i] = amici.get(i).ID;
		}
		listaAmici.setItems(nomi);
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlIMieiAmici = new Shell();
		shlIMieiAmici.setSize(470, 300);
		shlIMieiAmici.setText("I miei amici");
		
		listaAmici = new List(shlIMieiAmici, SWT.BORDER);
		listaAmici.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(listaAmici.getSelectionIndex() != -1){
					textNome.setText(amici.get(listaAmici.getSelectionIndex()).nome);
					textCognome.setText(amici.get(listaAmici.getSelectionIndex()).cognome);
					textTelefono.setText(amici.get(listaAmici.getSelectionIndex()).telefono);
				}
			}
		});
		listaAmici.setBounds(10, 51, 189, 201);
		
		Label lblAmici = new Label(shlIMieiAmici, SWT.NONE);
		lblAmici.setAlignment(SWT.CENTER);
		lblAmici.setBounds(10, 30, 189, 15);
		lblAmici.setText("Amici");
		
		Label lblLeInfo = new Label(shlIMieiAmici, SWT.NONE);
		lblLeInfo.setAlignment(SWT.CENTER);
		lblLeInfo.setBounds(215, 10, 229, 15);
		lblLeInfo.setText("Le info");
		
		Label lblNome = new Label(shlIMieiAmici, SWT.NONE);
		lblNome.setAlignment(SWT.CENTER);
		lblNome.setBounds(215, 51, 229, 15);
		lblNome.setText("Nome");
		
		Label lblCognome = new Label(shlIMieiAmici, SWT.NONE);
		lblCognome.setAlignment(SWT.CENTER);
		lblCognome.setBounds(215, 99, 229, 15);
		lblCognome.setText("Cognome");
		
		Label lblTelefono = new Label(shlIMieiAmici, SWT.NONE);
		lblTelefono.setAlignment(SWT.CENTER);
		lblTelefono.setBounds(215, 147, 229, 15);
		lblTelefono.setText("Telefono");
		
		textNome = new Text(shlIMieiAmici, SWT.BORDER);
		textNome.setBounds(215, 72, 229, 21);
		
		textCognome = new Text(shlIMieiAmici, SWT.BORDER);
		textCognome.setBounds(215, 120, 229, 21);
		
		textTelefono = new Text(shlIMieiAmici, SWT.BORDER);
		textTelefono.setBounds(215, 168, 229, 21);
		
		Button btnSalva = new Button(shlIMieiAmici, SWT.NONE);
		btnSalva.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//Recupero i dati
				int ID = ids[listaAmici.getSelectionIndex()]; //Prendiamo l'id dell'elemento selezionato
				String nome = textNome.getText();
				String cognome = textCognome.getText();
				String telefono = textTelefono.getText();
				
				//Faccio la query
				try {
					Connettore.updateData("test", "UPDATE amici SET Nome = '"+nome+"',Cognome = '"+cognome+"',Telefono = '"+telefono+"' WHERE ID = '"+ID+"'");
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				
				//Aggiorno la lista dei nomi con il nuovo nome
				listaAmici.setItem(listaAmici.getSelectionIndex(), cognome);
				amici.set(listaAmici.getSelectionIndex(),new Amico(ID,nome,cognome,telefono));
			}
		});
		btnSalva.setBounds(371, 227, 73, 25);
		btnSalva.setText("Salva");
		
		Button btnCrea = new Button(shlIMieiAmici, SWT.NONE);
		btnCrea.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//Recupero i dati
				int ID = ids[listaAmici.getSelectionIndex()]; //Prendiamo l'id dell'elemento selezionato
				String nome = textNome.getText();
				String cognome = textCognome.getText();
				String telefono = textTelefono.getText();
				
				//Faccio la query
				try {
					Connettore.updateData("test", "INSERT INTO amici VALUES (NULL,'"+nome+"','"+cognome+"','"+telefono+"') ");
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				
				Amico nuovo = new Amico(ID,nome,cognome,telefono);
				
				//Aggiorno la lista dei nomi con il nuovo nome
				listaAmici.add(nuovo.cognome);
				amici.add(nuovo);
			}
		});
		btnCrea.setText("Crea nuovo");
		btnCrea.setBounds(215, 227, 73, 25);
		
		Button btnElimina = new Button(shlIMieiAmici, SWT.NONE);
		btnElimina.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//Recupero i dati
				int ID = ids[listaAmici.getSelectionIndex()]; //Prendiamo l'id dell'elemento selezionato
				
				//Faccio la query
				try {
					Connettore.updateData("test", "DELETE FROM amici WHERE ID='"+ID+"' ");
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				
				//Aggiorno la lista dei nomi con il nuovo nome
				amici.remove(listaAmici.getSelectionIndex());
				listaAmici.remove(listaAmici.getSelectionIndex());
			}
		});
		btnElimina.setText("Elimina");
		btnElimina.setBounds(294, 227, 73, 25);

	}
}
