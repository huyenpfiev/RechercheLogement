package IHM;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import Serveur.Serveur;
import BD.Logement;



public class Page_Personnel extends JFrame{
	public static final int port = 9500;
	Titre_Panneau tPanel;
	Dessus_Panneau dPanel;
	Principal_Panneau pPanel;
	int idPro;
	public Page_Personnel(int idPro ) {
		
		this.idPro=idPro;
		setTitle("Page personnel");
		setBounds(400,0,600,600);
		setResizable(false);
		tPanel=new Titre_Panneau();
		dPanel=new Dessus_Panneau();
		pPanel=new Principal_Panneau();
		JPanel panel=new JPanel(new GridLayout(2,1,0,0));
		panel.add(tPanel);
		panel.add(dPanel);
		add(panel,"North");
		add(pPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public static void main(String []args) {
		//new Page_Personnel(1);
	}
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
			FlowLayout layout=new FlowLayout();
			layout.setHgap(60);
			layout.setVgap(10);
			setLayout(layout);		

			
			JLabel publier=new JLabel("<HTML><U>Publier une annonce</U></HTML>");
			publier.setFont(new Font("Time New Roman", Font.BOLD,14));
			add(publier,FlowLayout.LEFT);
			publier.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					dispose();
					new Page_Publication(idPro);
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
	}
	public class Principal_Panneau extends JPanel {
		JTable table;
		ArrayList<Logement> result;
		public Principal_Panneau() {
			afficher();
			TableModel tm=new TableModel();
			table=new JTable(tm);
			table.setAutoResizeMode(NORMAL);
			table.setFont(new Font("Time New Roman", Font.PLAIN,13));
			JScrollPane sp=new JScrollPane(table);
			add(sp);
			
		}
		public void afficher() {		
			try {
		
				Socket socket = new Socket("localhost", port);
				InputStream inStream = socket.getInputStream();
				OutputStream outStream = socket.getOutputStream();

				ObjectOutputStream os = new ObjectOutputStream(outStream);
				ObjectInputStream is = new ObjectInputStream(inStream);
				
				os.writeObject("Annonces");
				String id=Integer.toString(idPro);
				os.writeObject(id);
				
				result = (ArrayList<Logement>) is.readObject();
			
				socket.close();
			}catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		public class TableModel extends AbstractTableModel{
			private String[] columnName= {"Titre","Adresse","Ville","CP","Type","Prix","Surface","Description"};
			@Override
			public int getColumnCount() {	
				return 8;
			}

			@Override
			public int getRowCount() {	
				return result.size();
			}

			@Override
			public Object getValueAt(int row, int column) {

				switch(column) {
				case 0:
					return result.get(row).getTitre();
				case 1:
					return result.get(row).getAdresse();
				case 2:
					return result.get(row).getVille();
				case 3:
					return result.get(row).getCP();
				case 4:
					return result.get(row).getType();
				case 5:
					return result.get(row).getPrix();
				case 6:
					return result.get(row).getSurface();				
				case 7:
					return result.get(row).getDes();			
				default:
					return null;
						
				}
				
				}
			public String getColumnName(int column) {
				return columnName[column];
		}
	
		}
	}
}
