import java.util.ArrayList;

@SuppressWarnings("unused")
public class Applicazione {

	GestoreDatabase gestoreDatabase = new GestoreDatabase("carsharing");
	ArrayList<Socio> soci;
	ArrayList<Auto> auto;
	ArrayList<Noleggio> noleggi;
	
	public static void main(String[] args) {
		Applicazione app = new Applicazione();
	}
	
	public Applicazione(){
		ApriFinestra();
		soci = gestoreDatabase.caricaAllSoci();
		auto = gestoreDatabase.caricaAllAuto();
		noleggi = gestoreDatabase.caricaAllNoleggi();
	}
	
	/**
	 * Fai partire la finestra.
	 */
	private void ApriFinestra(){
		try {
			ApplicazioneGrafica window = new ApplicazioneGrafica();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
