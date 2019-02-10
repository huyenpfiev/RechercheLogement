package BD;


import java.io.Serializable;
import java.util.Comparator;
public class Logement implements Serializable {
	public float note;
	public Integer prix,surface,idType;	
	public String ville,adresse,CP,des,titre,photo,type;

	public Logement() {
		
	}
	public Logement(String adresse,String ville,String CP,Integer prix,Integer surface,Integer idType,String type,String des,String titre, String photo) {
		this.note=0;
		this.adresse=adresse;
		this.ville=ville;
		this.CP=CP;
		this.prix=prix;
		this.surface=surface;
		this.idType=idType;
		this.type=type;
		this.des=des;
		this.titre=titre;
		this.photo=photo;
	}
	
	public float getNote() {
		return note;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCP() {
		return CP;
	}
	public void setCP(String cP) {
		CP = cP;
	}
	public Integer getPrix() {
		return prix;
	}
	public void setPrix(Integer prix) {
		this.prix = prix;
	}
	public Integer getSurface() {
		return surface;
	}
	public void setSurface(Integer surface) {
		this.surface = surface;
	}
	public Integer getIdType() {
		return idType;
	}
	public void setIdType(Integer idType) {
		this.idType = idType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
	
	
}

