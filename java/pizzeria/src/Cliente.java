
public class Cliente {

	private ClienteAspettaDisplay shell;
	public String nome;
	
	public Cliente(String nome){
		this.nome = nome;
	}
	
	public void Aspetta(Ordine ordine){
		shell = new ClienteAspettaDisplay(ordine);
		shell.open();
		ListaOrdini.instanza.GetOrdineCompletato(this);
		System.out.println("ordine completato");
		shell.close();
		ClienteProntoDisplay shell2 = new ClienteProntoDisplay(ordine);
		shell2.open();
	}
	
}
