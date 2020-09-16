
public class Auto {
	private String targa;
	private String marca;
	private String modello;
	private double costoGiornaliero;
	
	public Auto(String targa, String marca, String modello, double costoGiornaliero) {
		this.targa = targa;
		this.marca = marca;
		this.modello = modello;
		this.costoGiornaliero = costoGiornaliero;
	}
	/**
	 * 	Ricevi la targa, cioè l'ID della macchina 
	 */
	public String getTarga() {
		return targa;
	}

	public String getMarca() {
		return marca;
	}

	public String getModello() {
		return modello;
	}

	public double getCostoGiornaliero() {
		return costoGiornaliero;
	}
	
}
