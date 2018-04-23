package edu.uqam.inf5153.tp3.domaine;

public class Adresse {
	String noCivique = new String();
	String rue = new String();
	String app = new String();
	String ville = new String();
	String province = new String();
	String codePostal = new String();
	
	public Adresse() {}
	public Adresse(String noCivique, String rue, String app, String ville, String province, String codePostal) {
		this.noCivique = noCivique; this.rue = rue; this.ville = ville; this.province = province; this.app = app; this.codePostal = codePostal;
	}
	
	public String toString(){
		return noCivique + " " + rue + " "+ app + "\r\n" + ville + "\r\n" + province + "\r\n" + codePostal;
	}
}
