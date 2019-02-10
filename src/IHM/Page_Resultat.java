package IHM;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import BD.Logement;

public class Page_Resultat extends JFrame{
	Titre_Panneau tPanel;
	Dessus_Panneau dPanel;
	Principal_Panneau pPanel;
	String villeOuCP;
	int selectedIndex;
	String prixMin,prixMax,surfaceMin,surfaceMax;
	public Page_Resultat(String villeOuCP,int selectedIndex,String prixMin,String prixMax,String surfaceMin,String surfaceMax ) {
		this.villeOuCP=villeOuCP;
		this.selectedIndex=selectedIndex;
		this.prixMin=prixMin;
		this.prixMax=prixMax;
		this.surfaceMax=surfaceMax;
		this.surfaceMin=surfaceMin;
		
		setTitle("Recherche");
		setBounds(700,0,600,600);
		setResizable(false);
		tPanel=new Titre_Panneau();
		dPanel=new Dessus_Panneau();
		pPanel=new Principal_Panneau();
		JPanel panel=new JPanel(new GridLayout(2,1,0,0));
		panel.add(tPanel);
		panel.add(dPanel);
		add(panel,"North");
		add(pPanel);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public static void main(String[] args) {


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
			
			//String nombre="";
			JLabel annonce=new JLabel(" 10 Annonces");
			annonce.setFont(new Font("Time New Roman", Font.CENTER_BASELINE,14));
			add(annonce);
			
			ImageIcon searchIcon=new ImageIcon("search.png");
			JLabel recherche=new JLabel("Nouvelle recherche");
			recherche.setFont(new Font("Time New Roman", Font.CENTER_BASELINE,14));
			recherche.setIcon(searchIcon);
			add(recherche);
			recherche.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					dispose();
					new Page_Recherche();
				}
				
			});
			
			JComboBox triCb=new JComboBox();
			triCb.addItem("Tri: Prix croissants");
			triCb.addItem("Tri: Prix decroissants");
			add(triCb);
			
		}
	}
	public class Principal_Panneau extends JPanel {
		JTable table;
		ArrayList<Logement> result1,result;
		public Principal_Panneau() {
			search();
			TableModel tm=new TableModel();
			table=new JTable(tm);
			table.setAutoResizeMode(NORMAL);
			table.setFont(new Font("Time New Roman", Font.PLAIN,13));
			JScrollPane sp=new JScrollPane(table);
			add(sp);
			
		}
		public void search() {
			Socket socket;
			try {
				socket = new Socket("localhost", 9500);
				InputStream inStream = socket.getInputStream();
				OutputStream outStream = socket.getOutputStream();

				ObjectOutputStream os = new ObjectOutputStream(outStream);
				ObjectInputStream is = new ObjectInputStream(inStream);
				
				os.writeObject("Rechercher");
				Vector<String> input = new Vector<String>();
				input.add(villeOuCP);
				input.add(Integer.toString(selectedIndex));
				input.add(prixMin);
				input.add(prixMax);
				input.add(surfaceMin);
				input.add(surfaceMax);
				os.writeObject(input);
				
				result1 = (ArrayList<Logement>) is.readObject();
				result=new ArrayList<Logement>();
				for(int i=0;i<10;i++) {
					result.add(result1.get(i));
				}
				socket.close();
			}catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		public class TableModel extends AbstractTableModel{
			private String[] columnName= {"Note","Adresse","Ville","Code Postal","Type","Prix","Surface","Des","Titre","Photo"};
			@Override
			public int getColumnCount() {	
				return 10;
			}

			@Override
			public int getRowCount() {	
				return result.size();
			}

			@Override
			public Object getValueAt(int row, int column) {

				switch(column) {
				case 0:
					return result.get(row).getNote();
				case 1:
					return result.get(row).getAdresse();
				case 2:
					return result.get(row).getVille();
				case 3:
					return result.get(row).getCP();
				case 5:
					return result.get(row).getPrix();
				case 6:
					return result.get(row).getSurface();
				case 4:
					return result.get(row).getType();
				case 7:
					return result.get(row).getDes();
				case 8:
					return result.get(row).getTitre();
				case 9:
					return result.get(row).getPhoto();
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
