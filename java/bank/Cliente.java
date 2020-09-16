
public class Cliente extends Thread{

	public Conto conto;
	
	public Cliente(Conto conto){
		this.conto = conto;
	}
	
	@Override
	public void run() {
		for(int i=0;i<1000;i++){
			
			//int azione = (int) (Math.random()*2);
			int azione = 0;
			double soldi = Math.random()*100;
			soldi = Math.round(soldi);
			double credito = 0;
			switch(azione){
			case 1:
				credito = conto.Aggiungi(soldi);
				System.out.println("Ho aggiunto:" + soldi);
				break;
			case 0:
				credito = conto.Togli(soldi);
				System.out.println("Ho prelevato:" + soldi);
				break;
			}
			System.out.println("Estratto conto:" + credito);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
