
public class Amico {

	public int ID;
	public String nome;
	public String cognome;
	public String telefono;
	
	public Amico(int ID,String nome,String cognome,String telefono){
		this.ID = ID;
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
	}
	
	public String toString(){
		return cognome;
	}
	
}
