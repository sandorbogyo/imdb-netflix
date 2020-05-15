package filmsorozat;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.border.EmptyBorder;

public class Foablak extends JFrame {
	
	private ArrayList<Mozgokep> mozgokepek= new ArrayList<Mozgokep>();
	private ArrayList<String> kategoriak = new ArrayList<String>();

    public static void main(String[] args) {
    	Foablak f = new Foablak();
        f.setVisible(true);
    }
    
    public static void hibauzenet(String uzenet, JPanel panel) {
    	JOptionPane.showMessageDialog(panel,
			    uzenet,
			    "Hiba történt",
			    JOptionPane.ERROR_MESSAGE);
    }
    
    public static void beolvasas (String fajlnev, ArrayList<Mozgokep> mozgokepek, JPanel fopanel) {
	    try {
	    	//film és sorozat lista beolvasása
	        File myObj = new File(fajlnev);
	        Scanner myReader = new Scanner(myObj);
	        while (myReader.hasNextLine()) {
	          String data = myReader.nextLine();
	          String[] szetvagott = data.split("--");
	          //Eltárolás mozgóképként majd Arraylistbe tétel
	          Mozgokep uj = new Mozgokep(szetvagott[0],szetvagott[1],szetvagott[2],szetvagott[3],"kepek" + "\\" +szetvagott[4],szetvagott[5]);
	          mozgokepek.add(uj);
	         }
	        myReader.close();
	     } catch (FileNotFoundException e) {
	    	hibauzenet("A filmeket és a sorozatokat tároló lista nem található.", fopanel);
	     }
    }
    
    public static void logo (JPanel panel, String kepnev) {
    	try {
    		BufferedImage kep = ImageIO.read(new File(kepnev));
    		JLabel logo = new JLabel(new ImageIcon(kep));
    		logo.setBorder(new EmptyBorder(20, 0, 0, 0));
    		logo.setSize(207, 67);
    		panel.add(logo);
    	} catch (IOException e) {
	    	hibauzenet("A logó nem található.", panel);
    	}
    }
    
    
    //Ha ráklikkeltek egy képre, megnyitja az adatlapját
	public static void klikkeles(JLabel kep, Mozgokep aktualis) {
	    kep.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	Adatlap adatlp = new Adatlap(aktualis);
                adatlp.setVisible(true);
            }
	    });
	}
	
	//A kategóriák címét állítja be
    public static JLabel katcim(String cim) {
		   JLabel keszcim = new JLabel(cim);
		   keszcim.setFont(new Font("Arial", Font.PLAIN, 25));
		   keszcim.setForeground(Color.WHITE);
		   //Felülről és alulról 20-as border:
		   keszcim.setBorder(new EmptyBorder(20, 0, 20, 0));
		   return keszcim;
    }
    
    public static void osszkategoria(ArrayList<Mozgokep> mozgokepek, ArrayList<String> kategoriak) {
 	   for(int i = 0; i <mozgokepek.size(); i++) {
		    	if (kategoriak.contains((mozgokepek.get(i)).getKategoria()) == false) { //Kategória nevek Arraylistbe tevése
		    		kategoriak.add((mozgokepek.get(i)).getKategoria());
		    	}
 	   }
    }
    
    public static void katvizszintes (boolean kiemelt, ArrayList<String> kategoriak, ArrayList<Mozgokep> mozgokepek, JPanel fopanel) {
    	for(int i = 0; i <kategoriak.size(); i++) {
			   //Cím hozzáadása
    		   if (kiemelt == false) {fopanel.add(katcim(kategoriak.get(i)));}  // Ha nem kiemelt, akkor a kategória címét kiírjuk
    		   else {fopanel.add(katcim("Kiemelt"));} 
			   
			   //Panelek létrehozása
		       JPanel katPanel = new JPanel();
		       katPanel.setLayout(new BoxLayout(katPanel, BoxLayout.X_AXIS));
		       katPanel.setBackground(Color.BLACK);
		       katPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
		       JScrollPane katScroll = new JScrollPane(katPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		       katScroll.setPreferredSize(new Dimension(katScroll.getSize().width - 20, 192 + 20));
		       katScroll.setBorder(null);
		       katScroll.setBackground(Color.BLACK);
			   
			   for(int y = 0; y <mozgokepek.size(); y++) {
				   boolean talalt = false;
				   if (kiemelt == true) {
					   if (Boolean.parseBoolean((mozgokepek.get(y)).getKiemelt()) == true) { // Ha a mozgókép kiemelt, megtaláltuk
						   talalt = true;
					   }
				   }
				   else {
					   if ((mozgokepek.get(y)).getKategoria().equals(kategoriak.get(i))) { //Ha a mozgókép egyezik az aktuális kategóriával, megtaláltuk 
						   talalt = true;
					   }
				   }
				   if (talalt ==true) { //Találat esetén létrehozzuk a blokkját: kép, klikkelés, space-k, stb.
			    		Mozgokep aktualis = mozgokepek.get(y);
			        	try {
			        		BufferedImage kep = ImageIO.read(new File(aktualis.getKepnev()));
			        		JLabel uj = new JLabel(new ImageIcon(kep));
				    		klikkeles(uj, aktualis);
				    		katPanel.add(uj);
					    	katPanel.add(Box.createRigidArea(new Dimension(10,0)));
			        	} catch (IOException e) {
			    	    	hibauzenet("Egy vagy több film és sorozat képe nem található.", fopanel);
			        	}			    		
				    	talalt = false;
				   }
			   }
			   
			   fopanel.add(katScroll);  
			   
			   //Ha kiemelt akkor kilépünk a for ciklusból mert csak 1 kiemelt vízszintes blokk kell
			   if (kiemelt == true) {
				   break;
			   }
			   
		   }
    }
    

	

    public Foablak() {
        setTitle("BMDB");
        setIconImage(new ImageIcon("bmdbicon.png").getImage());
        setSize(new Dimension(1600, 800));
        setMaximumSize(new Dimension(1600, 800));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel fopanel = new JPanel();
        fopanel.setLayout(new BoxLayout(fopanel, BoxLayout.Y_AXIS));
        JScrollPane foScroll = new JScrollPane(fopanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        fopanel.setBackground(Color.BLACK);
        getContentPane().add(foScroll);
        
        beolvasas ("lista.txt", mozgokepek, fopanel);
        logo(fopanel,"bmdblogo.png");
	    osszkategoria(mozgokepek, kategoriak); 
	    katvizszintes (true,kategoriak, mozgokepek, fopanel); // Hozzáadjuk a kiemelt sorozatokat, filmeket
	    katvizszintes (false,kategoriak, mozgokepek, fopanel); // Hozzáadjuk a kategóriákat, és a hozzájuk tartozó sorozatokat, filmeket
	   
    }

}