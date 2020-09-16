
public class Avvio {
	Conto conto = new Conto(1000);
	Cliente cli1 = new Cliente(conto);
	Cliente cli2 = new Cliente(conto);
	Cliente cli3 = new Cliente(conto);
	Cliente cli4 = new Cliente(conto);
	Cliente cli5 = new Cliente(conto);
	Cliente cli6 = new Cliente(conto);
	Stipendio sti = new Stipendio(conto);
	
	public static void main(String args[]){
			Avvio avvio = new Avvio();
	}
	
	public Avvio(){
		cli1.start();
		cli2.start();
		cli3.start();
		cli4.start();
		cli5.start();
		cli6.start();
		sti.start();
	}
}
