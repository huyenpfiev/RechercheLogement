package BD;

public class Compte {
	Integer idPro;
	String mail,mdp;
	
	public Compte() {
		
	}
	public Compte(Integer idPro, String mail, String mdp) {
		
		this.idPro = idPro;
		this.mail = mail;
		this.mdp = mdp;
	}
	public Integer getIdPro() {
		return idPro;
	}
	public void setIdPro(Integer idPro) {
		this.idPro = idPro;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	
}
