import java.util.ArrayList;

public class OrdiniPronti {

	private ArrayList<Ordine> ordini;
	
	public void AggOrdine(Ordine ordine){
		ordini.add(ordine);
	}
	public synchronized Ordine GetOrdine(Cliente cliente){
		Ordine ordine = getOrdineByCliente(cliente);
		while(!ordine.ePronto()){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return ordine;
	}
	public Ordine getOrdineByCliente(Cliente cliente){
		Ordine ordine;
		for(int i=0;i<ordini.size();i++){
			if(ordini.get(i).GetCliente().equals(cliente)){
				ordine = ordini.get(i);
				return ordine;
			}
		}
		return null;
	}
}
