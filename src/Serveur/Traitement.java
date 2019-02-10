package Serveur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import java.sql.Statement;
import BD.Logement;
public class Traitement  {
	Connection cnn;
	Statement stm;
	
	public void connecter() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cnn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/logement", "root", "huyendien0978");
			stm = (Statement) cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	public Logement extractLogement(ResultSet rs) throws SQLException{
		Logement loge=new Logement();
		
        loge.setAdresse( rs.getString("adresse") );
        loge.setCP(rs.getString("codePostal"));
        loge.setVille(rs.getString("ville"));
        loge.setPrix(rs.getInt("prix"));
        loge.setSurface(rs.getInt("surface"));
        loge.setDes(rs.getString("description"));
        loge.setPhoto(rs.getString("photo"));
        loge.setTitre(rs.getString("titre"));
        loge.setIdType(rs.getInt("idType"));
        loge.setType(rs.getString("type"));
        return loge;
	}
	public ArrayList<Logement> afficher(int idPro) {
			this.connecter();
			String sql="select adresse,ville,codePostal,prix,surface,logement.idType,typelogement.type,description,titre,photo from logement.logement,logement.typelogement where logement.idType=typelogement.idType and idProprietaire="+idPro+"";
			ArrayList<Logement> array=new ArrayList<>();
            try {
			ResultSet rs = this.stm.executeQuery(sql);
            while(rs.next())
            {
                array.add(extractLogement(rs));
            }
            return array;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            return null;
	}
	
    public ArrayList<Logement> getTousLogements(){
    	this.connecter();
    	ArrayList array=new ArrayList();
    	String sql="select idLogement,adresse,ville,codePostal,prix,surface,logement.idType,typelogement.type,description,titre,photo "
    			+ "from logement.logement,logement.typelogement "
    			+ "where logement.idType=typelogement.idType ";
    	try {
    		ResultSet rs=this.stm.executeQuery(sql);
    		while(rs.next()) {
    			
    			array.add(extractLogement(rs));
    		}
    		return array;
    	}
    	catch(SQLException ex) {
    		ex.printStackTrace();
    	}
    	return null;
    }
  
    public ArrayList<Logement> SearchResult(Vector<String> input){
    	Integer idType=Integer.parseInt(input.get(1));
    	String s=input.get(0);
    	String villeOuCP;
    	if(s.equals(""))
    		villeOuCP=s;
    	else
    		villeOuCP=s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
    	
    	Integer prixMin,prixMax,surfaceMin,surfaceMax;
    	if(input.get(2).equals(""))
			prixMin=0;
		else
			prixMin=Integer.parseInt(input.get(2));
		if(input.get(3).equals(""))
			prixMax=5000;
		else
			prixMax=Integer.parseInt(input.get(3));
		if(input.get(4).equals(""))
			surfaceMin=0;
		else
			surfaceMin=Integer.parseInt(input.get(4));
		if(input.get(5).equals(""))
			surfaceMax=1000;
		else
			surfaceMax=Integer.parseInt(input.get(5));
    	
		ArrayList<Logement> tableau=getTousLogements();
		Iterator<Logement> iterator=tableau.iterator();
		ArrayList<Logement> array=new ArrayList<>();
		
		while(iterator.hasNext()) {
			Logement loge=iterator.next();
			if(villeOuCP.equals(loge.ville)|| villeOuCP.equals(loge.CP)) {
				loge.note=loge.note +8;
			}
			else
			{
				if(!villeOuCP.equals("")) {
					if(loge.ville.startsWith(villeOuCP)||loge.CP.startsWith(villeOuCP.substring(0, 2)))
						loge.note=(float) (loge.note+1.5);
				}
				
			}
			if(loge.idType==idType)
				loge.note=loge.note+6;
			else
			{
				if (idType>4) {
					if(idType%2==1 && loge.idType==(idType+1)) {
						loge.note=(float) (loge.note+1);
					}
					if(idType%2==0 && loge.idType==(idType-1)) {
						loge.note=(float) (loge.note+1);
					}
				}
				if(idType>0 && idType<5) {
					if(loge.idType<5)
						loge.note=(float) (loge.note+1);
				}
			}
			if((loge.prix>(prixMin-1) ) && (loge.prix<(prixMax+1)))
				loge.note=loge.note+4;
			if((loge.surface>(surfaceMin-1) ) && (loge.surface<(surfaceMax+1)))
				loge.note=loge.note+2;
			array.add(loge);
		}
		Collections.sort(array, new sort());
    	return array;
    }
    static class sort implements Comparator<Logement>{
		public int compare(Logement a,Logement b) {
			if(a.note<b.note) return 1;
			else if(a.note==b.note) return 0;
			else return -1;
		}
	}
    
}

