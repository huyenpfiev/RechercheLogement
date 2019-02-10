package BD;

public class Proprietaire {
	Integer idPro,sexe;
	String nom, prenom,villeOuCP,nomSociete,tel;
	public Proprietaire() {
		
	}
	public Proprietaire(Integer idPro,Integer sexe, String nom, String prenom, String villeOuCP, String nomSociete, String tel) {
		this.sexe=sexe;
		this.idPro = idPro;
		this.nom = nom;
		this.prenom = prenom;
		this.villeOuCP = villeOuCP;
		this.nomSociete = nomSociete;
		this.tel = tel;
	}
	public Integer getIdPro() {
		return idPro;
	}
	public void setIdPro(Integer idPro) {
		this.idPro = idPro;
	}
	public Integer getSexe() {
		return sexe;
	}
	public void setSexe(Integer sexe) {
		this.sexe = sexe;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getVilleOuCP() {
		return villeOuCP;
	}
	public void setVilleOuCP(String villeOuCP) {
		this.villeOuCP = villeOuCP;
	}
	public String getNomSociete() {
		return nomSociete;
	}
	public void setNomSociete(String nomSociete) {
		this.nomSociete = nomSociete;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	
	
}
