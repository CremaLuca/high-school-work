
public class Conto {
	double credito;
	
	public Conto(double credito){
		this.credito = credito;
	}
	
	public synchronized double Aggiungi(double credito){
		this.credito += credito;
		notifyAll();
		return this.credito;
	}
	
	public synchronized double Togli(double credito){
		while(credito > this.credito){
			try{
				wait();
			}catch(Exception e){
				
			}
		}
		this.credito -= credito;
		return this.credito;
	}
	
	public String toString(){
		return "Sul conto ci sono " + credito + "€";
	}
}
