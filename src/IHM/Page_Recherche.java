package IHM;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import BD.Logement;

public class Page_Recherche extends JFrame{
	Titre_Panneau tPanel;
	Principal_Panneau pPanel;
	public Page_Recherche() {
		setTitle("Recherche");
		setBounds(50,0,600,600);
		setResizable(false);
		tPanel=new Titre_Panneau();
		pPanel=new Principal_Panneau();
		add(tPanel,"North");
		add(pPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new Page_Recherche();

	}
	public class Titre_Panneau extends JPanel{
		public Titre_Panneau() {
			this.setBackground(Color.GREEN);
			JLabel t=new JLabel("Un logement, bonne recherche!");
			t.setFont(new Font("Time New Roman",Font.CENTER_BASELINE,24));
			add(t);
		
		}
	}
	public class Principal_Panneau extends JPanel implements ActionListener  {
		JPanel p,namePanel,inputPanel,unitePanel;
		JButton rechercher;
		JTextField villeOuCP,prixMinTf,prixMaxTf,surfaceMinTf,surfaceMaxTf;
		JComboBox cb;
		public Principal_Panneau() {
			p=new JPanel(new BorderLayout(10,10));
			p.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
			add(p,BorderLayout.CENTER);
			
			namePanel=new JPanel(new GridLayout(6,1,15,15));
			JLabel nomVille=new JLabel("Ville ou Code postal :");
			nomVille.setFont(new Font("Time New Roman", Font.CENTER_BASELINE,15));
			JLabel type=new JLabel("Type de logement :");
			type.setFont(new Font("Time New Roman", Font.CENTER_BASELINE,15));
			JLabel prixMin=new JLabel("Prix min :");
			prixMin.setFont(new Font("Time New Roman", Font.CENTER_BASELINE,15));
			JLabel prixMax=new JLabel("Prix max :");
			prixMax.setFont(new Font("Time New Roman", Font.CENTER_BASELINE,15));
			JLabel surfaceMin=new JLabel("Surface min :");
			surfaceMin.setFont(new Font("Time New Roman", Font.CENTER_BASELINE,15));
			JLabel surfaceMax=new JLabel("Surface max :");
			surfaceMax.setFont(new Font("Time New Roman", Font.CENTER_BASELINE,15));
			namePanel.add(nomVille);
			namePanel.add(type);
			namePanel.add(prixMin);
			namePanel.add(prixMax);
			namePanel.add(surfaceMin);
			namePanel.add(surfaceMax);
			p.add(namePanel,BorderLayout.WEST);
			
			inputPanel=new JPanel(new GridLayout(6,1,15,15));
			villeOuCP=new JTextField(20);
			inputPanel.add(villeOuCP);
			String option[]= {"Tous les types","Chambre","Studio","Appartement T1","Maison F1","Appartement T2","Maison F2","Appartement T3","Maison F3","Appartement T4","Maison F4"};
			cb=new JComboBox(option);
			inputPanel.add(cb);
			prixMinTf=new JTextField(10);
			prixMaxTf=new JTextField(10);
			surfaceMinTf=new JTextField(10);
			surfaceMaxTf=new JTextField(10);
			inputPanel.add(prixMinTf);
			inputPanel.add(prixMaxTf);
			inputPanel.add(surfaceMinTf);
			inputPanel.add(surfaceMaxTf);
			p.add(inputPanel,BorderLayout.CENTER);
			
			unitePanel=new JPanel(new GridLayout(6,1,15,15));
			String[] unite= {"","","€","€","m²","m²"};
			for(int i=0;i<6;i++) {
				unitePanel.add(new JLabel(unite[i]));
			}
			p.add(unitePanel,BorderLayout.EAST);
			
			rechercher=new JButton("Rechercher");
			rechercher.setForeground(Color.white);
			rechercher.setBackground(Color.decode("#dc4c18"));
			rechercher.setFont(new Font("Time New Roman", Font.CENTER_BASELINE,20));
			rechercher.setFocusable(false);
			rechercher.addActionListener(this);
			add(rechercher,BorderLayout.SOUTH);
			
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Rechercher")) {
//				dispose();
				String[] tableau= {prixMinTf.getText(),prixMaxTf.getText(),surfaceMinTf.getText(),surfaceMaxTf.getText()};
				for(int i=0;i<4;i++) {
					if(!isNumber(tableau[i]))
					{
						JOptionPane.showMessageDialog(pPanel, "Erreur!");
						break;
					}
					if(i==3) {
						new Page_Resultat(villeOuCP.getText(),cb.getSelectedIndex(),prixMinTf.getText(),prixMaxTf.getText(),surfaceMinTf.getText(),surfaceMaxTf.getText());
					}
				}
				
			}
		}
		public boolean isNumber(String s) {
			if(s.length()==0) return true;
			for (int i = 0; i < s.length(); i++) {
	            if (Character.isLetter(s.charAt(i))) {	                
	                break;            
	            }
	            if (i + 1 == s.length()) {
	                return true;
	            }
	            
			}
			return false;
		
		}

	}
}
