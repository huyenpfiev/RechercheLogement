package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import IHM.Page_Resultat.Dessus_Panneau;
import IHM.Page_Resultat.Principal_Panneau;
import IHM.Page_Resultat.Titre_Panneau;

public class Page_Publication extends JFrame{
	public static final int port = 9500;
	Titre_Panneau tPanel;
	Dessus_Panneau dPanel;
	Principal_Panneau pPanel;
	int idPro;
	public Page_Publication(int idPro) {
		this.idPro=idPro;
		setTitle("Recherche");
		setBounds(400,0,600,800);
		setResizable(false);
		tPanel=new Titre_Panneau();
		dPanel=new Dessus_Panneau();
		pPanel=new Principal_Panneau();
		
		JPanel panel=new JPanel(new GridLayout(2,1,0,0));
		panel.add(tPanel);
		panel.add(dPanel);
		add(panel,BorderLayout.NORTH);
		add(pPanel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String []args) {
		//new Page_Publication(1);
	}
	
	public class Titre_Panneau extends JPanel{
		public Titre_Panneau() {
			this.setBackground(Color.GREEN);
			JLabel t=new JLabel("Un logement, bonne recherche!");
			t.setFont(new Font("Time New Roman",Font.CENTER_BASELINE,24));
			add(t);
		}
	}
	
    public class Dessus_Panneau extends JPanel implements ActionListener{	
		public Dessus_Panneau() {		
			FlowLayout layout=new FlowLayout();
			layout.setHgap(60);
			layout.setVgap(10);
			setLayout(layout);		

			ImageIcon returnIcon=new ImageIcon("back.png");
			JLabel retourner=new JLabel("Retourner");
			retourner.setFont(new Font("Time New Roman", Font.LAYOUT_LEFT_TO_RIGHT,14));
			retourner.setIcon(returnIcon);
			add(retourner,FlowLayout.LEFT);
			retourner.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					dispose();
					new Page_Personnel(idPro);
				}
			});
			
			ImageIcon compteIcon=new ImageIcon("SignUp2.png");
			JLabel compte=new JLabel("Nom");
			compte.setFont(new Font("Time New Roman", Font.CENTER_BASELINE,14));
			compte.setIcon(compteIcon);
			add(compte);
			
			ImageIcon deIcon=new ImageIcon("LogOut.png");
			JLabel deconnecter=new JLabel("Déconnexion");
			deconnecter.setFont(new Font("Time New Roman", Font.LAYOUT_LEFT_TO_RIGHT,14));
			deconnecter.setIcon(deIcon);
			add(deconnecter);
			deconnecter.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
						dispose();
						new pageconnexion();
					
				}
				
			});		
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
    
    public class Principal_Panneau extends JPanel {

    	JPanel p,namePanel,inputPanel, extraPanel;
		JButton retourner, publier;
		
		
		public Principal_Panneau() {
			JLabel title=new JLabel("Votre Publication",JLabel.CENTER);
			title.setFont(new Font("Time New Roman",Font.CENTER_BASELINE,20));
			title.setForeground(Color.blue);
			add(title,BorderLayout.NORTH);
			
			p=new JPanel(new BorderLayout(10,10));
			p.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
			add(p,BorderLayout.CENTER);
					
			namePanel=new JPanel(new GridLayout(8,1,15,15));
			String array[]= {"Titre de publication","Adresse","Ville","Code Postal","Type de logement","Prix","Surface","Description" };
			for(int i=0;i<array.length;i++) {
				JLabel l=new JLabel(array[i]);
				l.setFont(new Font("Time New Roman",Font.CENTER_BASELINE,15));
				namePanel.add(l);
			}		
			p.add(namePanel,BorderLayout.WEST);
			
			inputPanel=new JPanel(new GridLayout(8,1,15,15));
			
			String option[]= {"Chambre","Studio","Appartement T1","Maison F1","Appartement T2","Maison F2","Appartement T3","Maison F3","Appartement T4","Maison F4"};
			JComboBox cb=new JComboBox(option);
			
			JTextField titre=new JTextField(20);
			JTextField adresse=new JTextField(20);
			JTextField ville=new JTextField(20);
			JTextField codePostal=new JTextField(20);
			JTextField surface=new JTextField(20);
			JTextField prix=new JTextField(20);
			
			inputPanel.add(titre);
			inputPanel.add(adresse);
			inputPanel.add(ville);
			inputPanel.add(codePostal);
			inputPanel.add(cb);
			inputPanel.add(prix);
			inputPanel.add(surface);
			p.add(inputPanel,BorderLayout.CENTER);
			
			extraPanel=new JPanel(new GridLayout(8,1,15,15));
			String extra[]= {"","","","","","€ / mois","m²",""};
			for(int j=0;j<extra.length;j++) {
				JLabel lExtra=new JLabel(extra[j]);
				lExtra.setFont(new Font("Time New Roman",Font.LAYOUT_LEFT_TO_RIGHT,15));
				extraPanel.add(lExtra);
			}
			p.add(extraPanel,BorderLayout.EAST);

			JTextArea texte=new JTextArea(5,10);
			JScrollPane sp=new JScrollPane(texte);
			p.add(sp,BorderLayout.SOUTH);
			
			JButton creerBt=new JButton("Publier");
			creerBt.setFocusable(false);
			creerBt.setBackground(Color.blue);
			creerBt.setForeground(Color.white);
			creerBt.setFont(new Font("Time New Roman",Font.CENTER_BASELINE,16));
			add(creerBt,BorderLayout.SOUTH);
			
			ArrayList<String> pub=new ArrayList<>();
			
			
			creerBt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						pub.add(titre.getText());
						pub.add(adresse.getText());
						pub.add(ville.getText());
						pub.add(codePostal.getText());			
						pub.add(prix.getText());
						pub.add(surface.getText());
						pub.add(texte.getText());
						pub.add(Integer.toString(idPro));
						pub.add(Integer.toString(cb.getSelectedIndex()+1));
						
						int check=0;
						for(int i=0;i<7;i++) {
							if(pub.get(i).equals(""))
								check=i;
								
						}
						if(check!=0) {
							JOptionPane.showMessageDialog(pPanel, "Erreur!");
							System.out.println("check="+check);
						}else {
							savePublication(pub);
						}
						
						
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
					
				}
			});
			
		}
    	
		
		public void savePublication(ArrayList<String> publication) throws ClassNotFoundException {
			try {
				Socket socket = new Socket("localhost", port);
				
				InputStream inStream = socket.getInputStream();
				OutputStream outStream = socket.getOutputStream();

				ObjectOutputStream os = new ObjectOutputStream(outStream);
				ObjectInputStream is = new ObjectInputStream(inStream);

				os.writeObject("Publier");
				os.writeObject(publication);
				
				String s = ""+is.readObject();
	
				if(Integer.parseInt(s)==0) {
					JOptionPane.showMessageDialog(this, "Erreur!");
				}
				else
				{
					dispose();
					new Page_Personnel(idPro);
				}
				
			
			} catch (UnknownHostException e) {
		           System.err.println("Trying to connect to unknown host: " + e);
		       } catch (IOException e) {
		           System.err.println("IOException:  " + e);
		       }
			
		
		}
		
    	
    }
}

