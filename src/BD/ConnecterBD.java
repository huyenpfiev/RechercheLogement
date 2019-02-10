package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import Serveur.Serveur;

public class ConnecterBD {

	Connection cnn;
	Statement stm;

	public void connecter() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cnn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/logement", "root", "huyendien0978");
			stm = cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public int SignIn(String email, String motDePasse) {
		this.connecter();
		String sql = "select idProprietaire from logement.COMPTE where email = '" + email + "' and motDePasse= '"
				+ motDePasse + "'";
		int idPro =0;
		
		try {
			ResultSet resultSet  = this.stm.executeQuery(sql);
			resultSet.last();
			 if (resultSet.getRow() == 0) {
			 idPro=0;
			 } else {
			 idPro=resultSet.getInt(1);
			 }
	 
			resultSet.close();
			this.stm.close();
			this.cnn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idPro;
	}

	// ==============================================================================
	public int savePub(ArrayList<String> input) throws SQLException {
		this.connecter();
		int idPro=Integer.parseInt(input.get(7));
		int prix=Integer.parseInt(input.get(4));
		int surface=Integer.parseInt(input.get(5));
		int idType=Integer.parseInt(input.get(8));
	
	
		String sql="insert into logement.logement (idProprietaire,adresse,ville,codePostal,prix,surface,idType,description,titre) values("+idPro+",'"+input.get(1)+"','"+input.get(2)+"','"+input.get(3)+"',"+prix+","+surface+","+idType+",'"+input.get(6)+"','" +input.get(0)+"')";
		
		try {
			stm.executeUpdate(sql);
			stm.close();
			this.cnn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
		
	}
	//==============================================================================
	
	public static void main(String[] args) {
		new ConnecterBD();
	}
}
