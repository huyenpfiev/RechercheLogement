package IHM;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javafx.scene.control.*;
import javax.swing.*;

public class Page_CreerCompte extends JFrame{
	Titre_Panneau tPanel;
	Dessus_Panneau dPanel;
	Infos_Panneau iPanel;
	Compte_Panneau cPanel;
	public Page_CreerCompte() {
		setTitle("Recherche");
		setBounds(400,0,600,800);
		setResizable(false);
		tPanel=new Titre_Panneau();
		dPanel=new Dessus_Panneau();
		iPanel=new Infos_Panneau();
		cPanel=new Compte_Panneau();
		
		JPanel panel1=new JPanel(new GridLayout(2,1,0,0));
		panel1.add(tPanel);
		panel1.add(dPanel);
		add(panel1,"North");
		
		JPanel panel2=new JPanel(new GridLayout(2,1,0,0));
		panel2.add(iPanel);
		panel2.add(cPanel);
		add(panel2,"Center");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
//	public static void main(String[] args) {
//		new Page_CreerCompte();
//
//	}
	public class Titre_Panneau extends JPanel{
		public Titre_Panneau() {
			this.setBackground(Color.GREEN);
			JLabel t=new JLabel("Un logement, bonne recherche!");
			t.setFont(new Font("Time New Roman",Font.CENTER_BASELINE,24));
			add(t);
		}
	}
	public class Dessus_Panneau extends JPanel{
		
		public Dessus_Panneau() {
			JLabel title=new JLabel("Créer votre compte",JLabel.CENTER);
			title.setFont(new Font("Time New Roman",Font.CENTER_BASELINE,20));
			title.setForeground(Color.blue);
			add(title);
		}
	}
	public class Infos_Panneau extends JPanel{
		JPanel p;
		public Infos_Panneau() {
			setLayout(new BorderLayout());
			p=new JPanel(new BorderLayout(10,10));
			p.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
			add(p,BorderLayout.CENTER);
			
			JLabel coordonnees=new JLabel("    Vos coordonnées",JLabel.LEFT);
			coordonnees.setFont(new Font("Time New Roman",Font.CENTER_BASELINE,16));
			coordonnees.setBorder(BorderFactory.createLineBorder(Color.red));
			coordonnees.setForeground(Color.red);
			add(coordonnees,BorderLayout.NORTH);
			
			JPanel namePanel=new JPanel(new GridLayout(6,1,15,15));
			String array[]= {"Civilité*","Nom*","Prénom*","Nom de la société*","Ville ou code postal*","Téléphone*"};
			for(int i=0;i<array.length;i++) {
				JLabel l=new JLabel(array[i]);
				l.setFont(new Font("Time New Roman",Font.CENTER_BASELINE,15));
				namePanel.add(l);
			}		
			p.add(namePanel,BorderLayout.WEST);
			
			JPanel inputPanel=new JPanel(new GridLayout(6,1,15,15));
			
			JComboBox sexCb=new JComboBox();
			sexCb.addItem("M.");
			sexCb.addItem("Mme");
			inputPanel.add(sexCb);
			
			JTextField nomTf=new JTextField(20);
			JTextField prenomTf=new JTextField(20);
			JTextField nomSoTf=new JTextField(20);
			JTextField villeTf=new JTextField(20);
			JTextField telTf=new JTextField(20);
			
			inputPanel.add(nomTf);
			inputPanel.add(prenomTf);
			inputPanel.add(nomSoTf);
			inputPanel.add(villeTf);
			inputPanel.add(telTf);
			
			p.add(inputPanel,BorderLayout.CENTER);
			
		}
	}
	public class Compte_Panneau extends JPanel{
		JPanel p;
		public Compte_Panneau() {
			setLayout(new BorderLayout());
			p=new JPanel(new BorderLayout(10,10));
			p.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
			add(p,BorderLayout.CENTER);
			
			JLabel mail=new JLabel("    Votre email et mot de passe",JLabel.LEFT);
			mail.setFont(new Font("Time New Roman",Font.CENTER_BASELINE,16));
			mail.setBorder(BorderFactory.createLineBorder(Color.red));
			mail.setForeground(Color.red);
			add(mail,BorderLayout.NORTH);
			
			JPanel namePanel=new JPanel(new GridLayout(5,1,15,15));
			String array[]= {"Adresse email*","Mot de passe*","Confirmer le mot de passe*"};
			for(int i=0;i<array.length;i++) {
				JLabel l=new JLabel(array[i]);
				l.setFont(new Font("Time New Roman",Font.CENTER_BASELINE,15));
				namePanel.add(l);
			}		
			p.add(namePanel,BorderLayout.WEST);
			
			JPanel inputPanel=new JPanel(new GridLayout(5,1,15,15));
			JTextField emailTf=new JTextField(20);
			JPasswordField mdp=new JPasswordField(20);
			JPasswordField mdp2=new JPasswordField(20);
			inputPanel.add(emailTf);
			inputPanel.add(mdp);
			inputPanel.add(mdp2);
			p.add(inputPanel,BorderLayout.CENTER);
			
			JButton creerBt=new JButton("Créer mon compte");
			creerBt.setFocusable(false);
			creerBt.setBackground(Color.blue);
			creerBt.setForeground(Color.white);
			creerBt.setFont(new Font("Time New Roman",Font.CENTER_BASELINE,16));
			p.add(creerBt,BorderLayout.SOUTH);
			
			
	}
}
}
