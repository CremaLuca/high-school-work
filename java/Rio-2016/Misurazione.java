
public class Misurazione {
	private Atleta atleta;
	private double distanza;
	
	public Misurazione(Atleta atleta, double distanza){
		this.atleta = atleta;
		this.distanza = distanza;
	}
	
	public Atleta getAtleta(){
		return atleta;
	}
	
	public double getDistanza(){
		return distanza;
	}
}
