
public class ListaPizze {
	public static String[] listaPizze = {"Margherita","Capricciosa","Salamino"};
	public static int[] tempiCottura = {3,6,5};
	
	public static int getTempoCottura(String pizza){
		for(int i=0;i<listaPizze.length;i++){
			if(listaPizze[i].equals(pizza)){
				return tempiCottura[i];
			}
		}
		return 0;
	}
}
