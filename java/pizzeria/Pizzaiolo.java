import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class Pizzaiolo implements PizzaHandler{

	public static Pizzaiolo instanza;
	protected Shell shell;
	List listaDaFare;
	List listaPizzeOrdine;
	List listaCompletamento;
	List listaCompletati;
	Ordine ordineSelezionato;
	ArrayList<Pizza> pizzeCompletamento;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Pizzaiolo window = new Pizzaiolo();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		instanza = this;
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		if(ListaOrdini.instanza == null){
			ListaOrdini.instanza = new ListaOrdini();
		}
		pizzeCompletamento = new ArrayList<Pizza>(); 
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public void aggiornaListaOrdiniDaFare(){
		listaDaFare.setItems(ListaOrdini.instanza.GetNomeClienti());
	}

	@Override
	public void OnPizzaPronta(Pizza p) {
		listaCompletati.add(p.getNome());
		listaCompletamento.remove(p.getNome());
	}
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(496, 491);
		shell.setText("Pizzaiolo");
		
		Label lblPizzaiolo = new Label(shell, SWT.CENTER);
		lblPizzaiolo.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblPizzaiolo.setBounds(10, 10, 460, 40);
		lblPizzaiolo.setText("Pizzaiolo");
		
		listaDaFare = new List(shell, SWT.BORDER);
		listaDaFare.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				if(listaDaFare.getSelectionIndex() >= 0){
					ordineSelezionato = ListaOrdini.instanza.getOrdineByCliente(listaDaFare.getItem(listaDaFare.getSelectionIndex()));
					listaPizzeOrdine.setItems(ordineSelezionato.GetNomiPizzeDaFare());
				}
			}
		});
		aggiornaListaOrdiniDaFare();
		listaDaFare.setBounds(10, 174, 216, 103);
		
		listaPizzeOrdine = new List(shell, SWT.BORDER);
		listaPizzeOrdine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				//Pizza daFare = listaDaFare.getItem(listaDaFare.getSelectionIndex());
				if(pizzeCompletamento.size() < 3){
					Pizza pizza = ordineSelezionato.GetPizze()[listaPizzeOrdine.getSelectionIndex()];
					String nomePizza = listaPizzeOrdine.getItem(listaPizzeOrdine.getSelectionIndex());
					listaCompletamento.add(nomePizza);
					pizzeCompletamento.add(pizza);
					pizza.staFacendo = true;
					listaPizzeOrdine.remove(listaPizzeOrdine.getSelectionIndex());
					Forno f = new Forno(pizza);
					f.start();
					try {
						f.join();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}finally{
						//QUANDO IL THREAD HA FINITO
						listaCompletamento.remove(nomePizza);
						listaCompletati.add(pizza.getNome());
						pizzeCompletamento.remove(pizza);
					}
					
					
				}
			}
		});
		listaPizzeOrdine.setBounds(254, 174, 216, 103);
		
		listaCompletamento = new List(shell, SWT.BORDER);
		listaCompletamento.setBounds(10, 304, 216, 103);
		
		listaCompletati = new List(shell, SWT.BORDER);
		listaCompletati.setBounds(254, 304, 216, 103);
		
		Label lblOrdiniDaFare = new Label(shell, SWT.NONE);
		lblOrdiniDaFare.setBounds(10, 153, 144, 15);
		lblOrdiniDaFare.setText("Ordini da fare");
		
		Label lblOrdiniInCompletament = new Label(shell, SWT.NONE);
		lblOrdiniInCompletament.setBounds(10, 283, 216, 15);
		lblOrdiniInCompletament.setText("Pizze in completamento");
		
		Label lblOrdiniCompletati = new Label(shell, SWT.NONE);
		lblOrdiniCompletati.setBounds(254, 283, 216, 15);
		lblOrdiniCompletati.setText("Pizze completate");
		
		
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("Pizze completate");
		label.setBounds(254, 153, 216, 15);

	}


}
