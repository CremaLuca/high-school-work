
public class Ordine {

	private Cliente cliente;
	private Pizza[] pizze;
	
	public Ordine(Cliente cliente,Pizza[] pizze) {
		this.cliente = cliente;
		this.pizze = pizze;
	}
	public Cliente GetCliente() {
		return cliente;
	}

	public Pizza[] GetPizze() {
		return pizze;
	}
	public boolean ePronto(){
		int pizzePronte = 0;
		for(int i=0;i<pizze.length;i++){
			if(pizze[i].ePronta){
				pizzePronte++;
			}
		}
		if(pizzePronte == pizze.length)
			return true;
		return false;
	}
	public String[] GetNomiPizze(){
		String[] str = new String[pizze.length];
		for(int i=0;i<pizze.length;i++){
			str[i] = pizze[i].getNome();
		}
		return str;
	}
	public String[] GetNomiPizzeDaFare(){
		String[] str = new String[pizze.length];
		for(int i=0;i<pizze.length;i++){
			if(!pizze[i].ePronta && !pizze[i].staFacendo){
				str[i] = pizze[i].getNome();
			}
		}
		return str;
	}
	public void CompletaPizza(String nomePizza){
		
	}
	
}
