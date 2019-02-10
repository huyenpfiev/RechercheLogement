package Serveur;

import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import BD.ConnecterBD;
import java.util.Collections;
import java.util.Comparator;
import IHM.pageconnexion;
import IHM.Page_Personnel;

import BD.Logement;
public class Serveur extends Thread {
	public static final int port = 9500;
	ServerSocket serverSocket = null;
	Socket socket = null;
	String actionClient;
	ObjectOutputStream os;
	ObjectInputStream is;
	ConnecterBD connecterBD;
	Traitement retreieveLogement;
	OutputStream outStream;
	InputStream inStream;
	
	
	public void run() {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				System.out.println("Waiting");
				socket = serverSocket.accept();
				System.out.println("Connected");

				outStream = socket.getOutputStream();
				inStream = socket.getInputStream();

				os = new ObjectOutputStream(outStream);
				is = new ObjectInputStream(inStream);
				connecterBD = new ConnecterBD();
				retreieveLogement=new Traitement();
				actionClient = "" + is.readObject();
				
				System.out.println("Action: " + actionClient);
				
				if (actionClient.equals("Se Connecter")) {
					Vector<String> compte = (Vector<String>) is.readObject();

					int  c = connecterBD.SignIn(compte.get(0), compte.get(1));
					String check= "" + c;
					os.writeObject(c);
	

				} 
				else if (actionClient.equals("Rechercher"))
				{
					Vector<String> input=(Vector<String>) is.readObject();
					ArrayList<Logement> result=retreieveLogement.SearchResult(input);
					os.writeObject(result);
				}
				else if (actionClient.equals("Publier"))
				{
					ArrayList<String> input=(ArrayList<String>) is.readObject();
					int check=connecterBD.savePub(input);
					
					os.writeObject(""+check);
				}
				else if(actionClient.equals("Annonces"))
				{
					String idPro=""+is.readObject();
					ArrayList<Logement> array=retreieveLogement.afficher(Integer.parseInt(idPro));
					os.writeObject(array);
				}
				socket.close();
			}

			catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

		}
	}

	public static void main(String[] args) {
		new Serveur().start();
	}

}


