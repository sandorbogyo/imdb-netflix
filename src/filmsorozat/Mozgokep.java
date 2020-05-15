package filmsorozat;

public class Mozgokep {
	private String kiadaseve;
	private String cim;
	private String leiras;
	private String kategoria; // Scifi, horror, stb.
	private String kiemelt; //megjelenik első sorban
	private String kepnev; //valami.jpg
	
	public Mozgokep (String cm, String ke, String li, String kg, String kn, String kt) {
		cim = cm;
		kiadaseve = ke;
		leiras = li;
		kategoria = kg;
		kepnev = kn;
		kiemelt = kt;
	}
	
	public String getKiadaseve() {return kiadaseve;}
	public String getCim() {return cim;}
	public String getLeiras() {return leiras;}
	public String getKategoria() {return kategoria;}
	public String getKepnev() {return kepnev;}
	public String getKiemelt() {return kiemelt;}

}
