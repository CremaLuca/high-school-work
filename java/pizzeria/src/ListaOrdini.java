import java.util.ArrayList;

public class ListaOrdini {

	static ListaOrdini instanza;
	private ArrayList<Ordine> ordini;

	public ListaOrdini(){
		ordini = new ArrayList<Ordine>();
	}
	
	public void AggOrdine(Ordine ordine){
		ordini.add(ordine);
		if(Pizzaiolo.instanza != null){
			Pizzaiolo.instanza.aggiornaListaOrdiniDaFare();
		}
	}
	public Ordine GetOrdine(int index){
		return ordini.get(index);
	}
	public ArrayList<Ordine> GetOrdini(){
		return ordini;
	}
	public String[] GetNomeClienti(){
		String[] str = new String[ordini.size()];
		for(int i=0;i<ordini.size();i++){
			str[i] = ordini.get(i).GetCliente().nome;
		}
		return str;
	}
	public Ordine getOrdineByCliente(String nome){
		for(int i=0;i<ordini.size();i++){
			if(ordini.get(i).GetCliente().nome.equals(nome)){
				return ordini.get(i);
			}
		}
		return null;
	}
	public void ordinePronto(Ordine ordine){
		int index = findOrdineIndex(ordine);
		if(index != -1){
			if(ordini.get(index).ePronto()){
				notifyAll();
			}
		}
	}
	public int findOrdineIndex(Ordine ordine){
		for(int i=0;i<ordini.size();i++){
			if(ordini.get(i).equals(ordine)){
				return i;
			}
		}
		return -1;
	}
	public synchronized boolean GetOrdineCompletato(Cliente cliente){
		Ordine ordine = getOrdineByCliente(cliente.nome);
		while(!ordine.ePronto()){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}
