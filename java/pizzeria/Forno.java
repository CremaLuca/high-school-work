

public class Forno extends Thread{

	private Pizza pizza;
	
	public Forno(Pizza pizza){
		this.pizza = pizza;
	}
	
	@Override
	public void run(){
		int tempoDaAspettare = (int) (pizza.getTempoCottura() * 1000);
		try{
			Thread.sleep(tempoDaAspettare);
		}catch(Exception e){
			System.out.println("Impossibile aspettare la cottura");
		}
		pizza.ePronta = true;
	}
	
}
