package IHM;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
public class Accueil_Ecran extends JFrame {
	
	private Titre_Panneau tPanel;
	private Principal_Panneau pPanel;
	JButton rechercher,publier;
	
	public Accueil_Ecran(String titre, int a,int b,int l, int h)  {
		setTitle(titre);
		setBounds(a,b,l,h);
		setResizable(false);
		tPanel=new Titre_Panneau();
		pPanel=new Principal_Panneau();
		add(tPanel,"North");
		add(pPanel);
	}
	public static void main(String[] args)  {
		Accueil_Ecran ae=new Accueil_Ecran("Accueil",400,0,600,400);
		ae.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ae.setVisible(true);

	}
	public class Titre_Panneau extends JPanel{
		public Titre_Panneau() {
			this.setBackground(Color.GREEN);
			JLabel t=new JLabel("Un logement, bonne recherche!");
			t.setFont(new Font("Time New Roman",Font.CENTER_BASELINE,24));
			add(t);
		
		}
	}
	public class Principal_Panneau extends JPanel implements ActionListener{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

	        String fichier = "accueil.jpg";
	        BufferedImage back_ground;
			try {
				back_ground = ImageIO.read(new File(fichier));
		        g.drawImage(back_ground, 0, 0, getWidth(), getHeight(),null);  
			} catch (IOException e) {
				
				e.printStackTrace();
			}
	    }
		public Principal_Panneau()  {
			
			JLabel label=new JLabel("Vous voulez:",JLabel.CENTER);
			label.setFont(new Font("Time New Roman",Font.CENTER_BASELINE,18));
			label.setForeground(Color.BLUE);
			
			JPanel pBu=new JPanel(new GridLayout(3, 1, 30, 30));
			pBu.setOpaque(false);
			add(pBu,BorderLayout.CENTER);
			
			pBu.add(label);
			
			rechercher=new JButton("Rechercher un logement");
			rechercher.addActionListener(this);
			rechercher.setBackground(Color.decode("#dc4c18"));
			rechercher.setFont(new Font("Time New Roman",Font.CENTER_BASELINE,16));
			rechercher.setForeground(Color.WHITE);
			rechercher.setFocusable(false);
			pBu.add(rechercher);
			
			publier=new JButton("Publier une annonce");
			publier.addActionListener(this);
			publier.setBackground(Color.blue);
			publier.setFont(new Font("Time New Roman",Font.CENTER_BASELINE,16));
			publier.setForeground(Color.WHITE);
			publier.setFocusable(false);
			pBu.add(publier);
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Publier une annonce")) {
				dispose();
				new pageconnexion();
			
			}
			if(e.getActionCommand().equals("Rechercher un logement")) {
				dispose();
				new Page_Recherche();
			
			}
			
		}
	}
	

}
