package filmtest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import filmsorozat.Foablak;
import filmsorozat.Mozgokep;

public class filmsorozatTest {
	
	Mozgokep m;
	JPanel jp;
	ArrayList<Mozgokep> tarolt;
	String fajlnev;
	

	@Before
	public void setUp(){
		m = new Mozgokep("13 okom volt","2017","..Titkok. Hazugságok. Bosszú. A Liberty Gimnáziumban mindenkinek van rejtegetnivalója... de hamarosan lehull a lepel az igazságról.","dráma","13okomvolt.jpg","false");
        jp = new JPanel();
        fajlnev = "lista.txt";
        tarolt = new ArrayList<Mozgokep>();
	}

	@Test
	public void egyszerupeldanyositas() {
		assertEquals("cim eltárolás", "13 okom volt", m.getCim());
		assertEquals("évszám eltárolás", "2017", m.getKiadaseve());
		assertEquals("leírás eltárolás", "..Titkok. Hazugságok. Bosszú. A Liberty Gimnáziumban mindenkinek van rejtegetnivalója... de hamarosan lehull a lepel az igazságról.", m.getLeiras());
		assertEquals("kategória eltárolás", "dráma", m.getKategoria());
		assertEquals("kép név eltárolás", "13okomvolt.jpg", m.getKepnev());
		assertEquals("kiemelt eltárolás","false",m.getKiemelt());
	}
	
	//Beolvasás
	@Test
	public void helyesadatok() {
		Foablak.beolvasas (fajlnev, tarolt, jp);
		for (int i = 0; i <tarolt.size(); i++) {
			assertTrue("Helyes cím", (tarolt.get(i)).getCim().length()>1);
			assertTrue("Helyes évszám", (tarolt.get(i)).getKiadaseve().length()>3);
			assertTrue("Helyes leírás", (tarolt.get(i)).getLeiras().length()>0);
			assertTrue("Helyes kategória", (tarolt.get(i)).getKategoria().length()>3);
			assertTrue("Helyes képnév", (tarolt.get(i)).getKepnev().length()>4);
			assertTrue("Helyes kiemelt", Boolean.parseBoolean(tarolt.get(i).getKiemelt()) == true || Boolean.parseBoolean(tarolt.get(i).getKiemelt()) ==false);
		}

	}

}
