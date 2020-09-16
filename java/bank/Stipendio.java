
public class Stipendio extends Thread{

	private Conto conto;
	
	public Stipendio(Conto conto){
		this.conto = conto;
	}
	
	public void run(){
		while(true){
			conto.Aggiungi(1000);
			System.out.println("STIPENDIO ARRIVATO : 10000 EURO AGGIUNTI");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	
}
