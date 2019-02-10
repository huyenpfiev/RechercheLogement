package IHM;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import Serveur.Serveur;

public class pageconnexion extends JFrame {
	public static final int port = 9500;
	Titre_Panneau tPanel;
	Principal_Panneau pPanel;
	Bas_Panneau bPanel;
	JButton seConnecter;
	JButton creerUnCompte;
	public pageconnexion() {
		setTitle("Connexion");
		setBounds(400,0,600,400);
		setResizable(false);
		tPanel = new Titre_Panneau();
		pPanel = new Principal_Panneau();
		bPanel = new Bas_Panneau();
		add(tPanel,"North");
		add(pPanel,"Center");
		add(bPanel,"South");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		new pageconnexion();
		
	}
	public class Titre_Panneau extends JPanel {
		public Titre_Panneau() {
			this.setBackground(Color.GREEN);
			JLabel t=new JLabel("Un logement, bonne recherche!");
			t.setFont(new Font("Time New Roman",Font.CENTER_BASELINE,24));
			add(t);		
		}
	}
	public class Principal_Panneau extends JPanel implements ActionListener {

		JPanel p;
		JTextField emailTf,passWordTf;
		public Principal_Panneau() {
			p = new JPanel(new BorderLayout(10,10));
			p.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
			add(p, BorderLayout.CENTER);
			JLabel lbCon = new JLabel("Connexion", JLabel.CENTER);
			lbCon.setFont(new Font("Time New Roman", Font.CENTER_BASELINE,22));
			lbCon.setForeground(Color.BLUE);
			p.add(lbCon,BorderLayout.NORTH); 
			
			JPanel namePanel = new JPanel(new GridLayout(2,1,15,15)); 
			JLabel lbMail=new JLabel("Adresse email");
			JLabel lbPW=new JLabel("Mot de passe");
			lbMail.setFont(new Font("Time New Roman", Font.CENTER_BASELINE,15));
			lbPW.setFont(new Font("Time New Roman", Font.CENTER_BASELINE,15));
			namePanel.add(lbMail); 
			namePanel.add(lbPW);
			p.add(namePanel,BorderLayout.WEST);
			
			JPanel inputPanel=new JPanel(new GridLayout(2,1,15,15));
			emailTf=new JTextField(20);
			passWordTf=new JPasswordField(20);
			inputPanel.add(emailTf);
			inputPanel.add(passWordTf);
			p.add(inputPanel,BorderLayout.CENTER);
			
//			JPanel statusPanel=new JPanel(new GridLayout(2,1,15,15));
//			statusPanel.add(new JLabel(""));
//			statusPanel.add(new JLabel(""));
//			p.add(statusPanel,BorderLayout.EAST);
			
			seConnecter = new JButton("Se connecter");
			seConnecter.setFont(new Font("Time New Roman", Font.CENTER_BASELINE, 16));
			seConnecter.setForeground(Color.WHITE);
			seConnecter.setBackground(Color.BLUE);
			seConnecter.setEnabled(false);
			seConnecter.addActionListener(this);
			emailTf.getDocument().addDocumentListener(new DocumentListener() {

				@Override
				public void changedUpdate(DocumentEvent arg0) {
					
				}

				@Override
				public void insertUpdate(DocumentEvent arg0) {
					if(passWordTf.getText().equals("")) {
						seConnecter.setEnabled(false);
					}
					else
						seConnecter.setEnabled(true);
					
				}

				@Override
				public void removeUpdate(DocumentEvent arg0) {
					if(emailTf.getText().equals("")) {
						seConnecter.setEnabled(false);
					}
					
				}
				
			});
			passWordTf.getDocument().addDocumentListener(new DocumentListener() {

				@Override
				public void changedUpdate(DocumentEvent e) {
					
				}

				@Override
				public void insertUpdate(DocumentEvent e) {
					if(emailTf.getText().equals("")) {
						seConnecter.setEnabled(false);
					}
					else
					{
						seConnecter.setEnabled(true);
					}
					
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					if(passWordTf.getText().equals("")) {
						seConnecter.setEnabled(false);
					}
					
				}
				
			});
			seConnecter.setFocusable(false);
			p.add(seConnecter,BorderLayout.SOUTH);
			p.setOpaque(false);
    	}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Se connecter")) {
				try {
					checkSignIn(emailTf.getText(),passWordTf.getText());
					
				} catch (ClassNotFoundException e1) {			
					e1.printStackTrace();
				}
				
			}
			
		}
//================================================
		public void checkSignIn(String email,String passWord) throws ClassNotFoundException {
			try {
				Socket socket = new Socket("localhost", port);
				
				InputStream inStream = socket.getInputStream();
				OutputStream outStream = socket.getOutputStream();

				ObjectOutputStream os = new ObjectOutputStream(outStream);
				ObjectInputStream is = new ObjectInputStream(inStream);

				os.writeObject("Se Connecter");
				Vector<String> compte = new Vector();
				compte.add(email);
				compte.add(passWord);

				os.writeObject(compte);
				String s = ""+is.readObject();
				int idPro=Integer.parseInt(s);
				System.out.println(idPro);
				if(idPro==0) {
					JOptionPane.showMessageDialog(this, "Invalide");
				}
				else {
					JOptionPane.showMessageDialog(this, "OK");
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
	
	public  class Bas_Panneau extends JPanel implements ActionListener{
        public Bas_Panneau() {
           this.setBackground(Color.WHITE);
           JPanel p = new JPanel(new GridLayout(2,1,8,8));
           add(p, BorderLayout.CENTER);
			JLabel label = new JLabel("Vous n'avez pas de compte?",JLabel.CENTER);
			label.setFont(new Font("Time New Roman",Font.CENTER_BASELINE,14));
			label.setForeground(Color.BLACK);
			p.add(label);
			creerUnCompte = new JButton("Créer un compte");
			creerUnCompte.setFont(new Font("Time New Roman",Font.CENTER_BASELINE,14));
			creerUnCompte.setForeground(Color.BLUE);
			creerUnCompte.addActionListener(this);
			creerUnCompte.setFocusable(false);
			p.add(creerUnCompte);
            p.setOpaque(false);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Créer un compte")) {
			dispose();
			new Page_CreerCompte();
		}
		
	}
}
	//================================================================================================
	
}
