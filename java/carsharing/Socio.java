
public class Socio {

	private String codiceFiscale;
	private String cognome;
	private String nome;
	private String indirizzo;
	private String telefono;
	
	public Socio(String codiceFiscale, String cognome, String nome, String indirizzo, String telefono) {
		this.codiceFiscale = codiceFiscale;
		this.cognome = cognome;
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.telefono = telefono;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public String getCognome() {
		return cognome;
	}

	public String getNome() {
		return nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public String getTelefono() {
		return telefono;
	}
	
}
