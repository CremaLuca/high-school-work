
public class Pizza {
	
	private String nome;
	private double tempoCottura;
	public boolean ePronta;
	public boolean staFacendo;
	
	public Pizza(String nome,double tempoCottura){
		this.nome = nome;
		this.tempoCottura = tempoCottura;
	}
	
	public String getNome(){
		return nome;
	}
	public double getTempoCottura(){
		return tempoCottura;
	}
	
	public void PizzaPronta(){
		ePronta = true;
	}
	
}
