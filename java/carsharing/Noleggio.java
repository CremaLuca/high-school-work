import java.sql.Date;

public class Noleggio {

	private int codiceNoleggio;
	private String targaAuto;
	private String socio;
	private Date inizio;
	private Date fine;
	private boolean autoRestituita;
	
	public Noleggio(int codiceNoleggio, String targaAuto, String socio, Date inizio, Date fine, boolean autoRestituita) {
		this.codiceNoleggio = codiceNoleggio;
		this.targaAuto = targaAuto;
		this.socio = socio;
		this.inizio = inizio;
		this.fine = fine;
		this.autoRestituita = autoRestituita;
	}

	public int getCodiceNoleggio() {
		return codiceNoleggio;
	}

	public String getTargaAuto() {
		return targaAuto;
	}

	public String getSocio() {
		return socio;
	}

	public Date getInizio() {
		return inizio;
	}

	public Date getFine() {
		return fine;
	}

	public boolean isAutoRestituita() {
		return autoRestituita;
	}

	public void setAutoRestituita(boolean autoRestituita) {
		this.autoRestituita = autoRestituita;
	}
	
}
