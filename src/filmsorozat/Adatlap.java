package filmsorozat;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.border.EmptyBorder;

public class Adatlap extends JFrame {
    
    public static void Kep (String kepnev, JPanel panel){
    	try {
    		BufferedImage kp = ImageIO.read(new File(kepnev));
    		JLabel kep = new JLabel(new ImageIcon(kp));
    		kep.setBorder(new EmptyBorder(0, 0, 10, 0));
    		panel.add(kep);
    	} catch (IOException e) { // Ha valaki valamiért a program megnyitása után törölné a képet
	    	Foablak.hibauzenet("A film vagy sorozat képe nem található.", panel);
    	}
    }
    
    public static void Szoveg (String tartalom, JPanel panel, int fontmeret) {
		JLabel elem = new JLabel("<html><p style=\"width:500px\">"+tartalom+"</p></html>"); //Ne lógjon ki az ablakból
		elem.setBorder(new EmptyBorder(0, 0, 10, 0));
		elem.setFont(new Font("Arial", Font.PLAIN, fontmeret));
		elem.setForeground(Color.WHITE);
        panel.add(elem);
    }

    public Adatlap(Mozgokep m) {
        setIconImage(new ImageIcon("bmdbicon.png").getImage());
        setSize(new Dimension(700, 600));
        setLocationRelativeTo(null);
        setTitle(m.getCim());
        setMaximumSize(new Dimension(700, 600));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel mainpanel = new JPanel();
        mainpanel.setBorder(new EmptyBorder(20, 20, 0, 0));
        mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.Y_AXIS));
		mainpanel.setBackground(Color.BLACK);
        JScrollPane mainScroll = new JScrollPane(mainpanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(mainScroll);
        

    	Kep (m.getKepnev(), mainpanel);
        Szoveg (m.getCim(), mainpanel, 34);
        Szoveg (m.getKiadaseve(), mainpanel, 18);
        Szoveg (m.getLeiras(), mainpanel, 22);
        Szoveg (m.getKategoria(), mainpanel, 18);

 
        }
    }

