import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.widgets.DateTime;

public class GestoreDatabase {

	String database;

	public GestoreDatabase(String database) {
		this.database = database;
	}

	public ArrayList<Socio> caricaAllSoci() {
		ResultSet res = null;
		ArrayList<Socio> soci = new ArrayList<Socio>();
		try {
			res = Connettore.getData(database, "SELECT * FROM soci");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (res != null) {
					while (res.next() == true) {
						soci.add(new Socio(res.getString("CF"), res.getString("Cognome"), res.getString("Nome"),
								res.getString("Indirizzo"), res.getString("Telefono")));
					}
					return soci;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ArrayList<Auto> caricaAllAuto() {
		ResultSet res = null;
		ArrayList<Auto> auto = new ArrayList<Auto>();
		try {
			res = Connettore.getData(database, "SELECT * FROM auto");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (res != null) {
					while (res.next() == true) {
						auto.add(new Auto(res.getString("Targa"), res.getString("Marca"), res.getString("Modello"),
								res.getDouble("Costo_Giornaliero")));
					}
					return auto;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ArrayList<Noleggio> caricaAllNoleggi() {
		ResultSet res = null;
		ArrayList<Noleggio> noleggi = new ArrayList<Noleggio>();
		try {
			res = Connettore.getData(database, "SELECT * FROM noleggio");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (res != null) {
					while (res.next() == true) {
						noleggi.add(new Noleggio(res.getInt("ID"), res.getString("IDAuto"), res.getString("IDSocio"),
								res.getDate("Inizio"), res.getDate("Fine"), res.getBoolean("eRestituita")));
					}
					return noleggi;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public ArrayList <Noleggio> caricaNoleggio(String CF){
		return null;
	}

	public String[] caricaAllTarghe() {
		ResultSet res = null;
		ArrayList<String> targhe = null;
		try {
			res = Connettore.getData(database, "SELECT * FROM auto");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (res != null) {
					targhe = new ArrayList<String>();
					while (res.next() == true) {
						targhe.add(res.getString("Targa"));
					}
					String[] targheArray = new String[targhe.size()];
					targhe.toArray(targheArray);
					return targheArray;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
<<<<<<< HEAD
	public void inserisciNoleggio(String CF, String IDAuto,String dataInizio, String dataFine){
		try {
			Connettore.updateData(database, "INSERT INTO Noleggi('IDSocio','IDAuto','Inizio','Fine') VALUES('"+CF+"','"+IDAuto+"','"+dataInizio.toString()+"','"+dataFine.toString()+"')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
=======
	public void inserisciNoleggio(){
		/*
		 * TO MARE PUTANA DIO CAN TE DO UN SCIAFON CHE TE IMPITURO SU PAL MURO
		 * 15+18 QUANTO FA? COGLIONE!
		 * 
		 */
>>>>>>> branch 'master' of https://github.com/CremaLuca/Carsharing.git
	}

	public String[] caricaAllCognome() {
		ResultSet res = null;
		ArrayList<String> Cognome = null;
		try {
			res = Connettore.getData(database, "SELECT * FROM soci");
			System.out.println(res);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (res != null) {
					Cognome = new ArrayList<String>();
					while (res.next() == true) {
						Cognome.add(res.getString("Cognome"));
						System.out.println("Cognome: " + Cognome);
					}
					String[] CognomeArray = new String[Cognome.size()];
					Cognome.toArray(CognomeArray);
					return CognomeArray;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public String[] caricaAllNoleggiStr() {
		ResultSet res = null;
		ArrayList<String> noleggi = null;
		try {
			res = Connettore.getData(database, "SELECT * FROM noleggio");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (res != null) {
					noleggi = new ArrayList<String>();
					while (res.next() == true) {
						noleggi.add(res.getString("ID"));
						noleggi.add(res.getString("IDAuto"));
						noleggi.add(res.getString("IDSocio"));
						noleggi.add(res.getString("Inizio"));
						noleggi.add(res.getString("Fine"));
						noleggi.add(res.getString("eRestituita"));
					}
					String[] noleggiArray = new String[noleggi.size()];
					noleggi.toArray(noleggiArray);
					return noleggiArray;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
