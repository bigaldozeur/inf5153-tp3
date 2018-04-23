package edu.uqam.inf5153.tp3.domaine;

public class Personne {
	String nom = new String();
	String prenom = new String();
	
	public Personne(){}
	
	Personne(String nom, String prenom){
		super();
		this.setNom(nom); this.setPrenom(prenom);
	}
	public String toString() {
		return getPrenom() + " " + getNom();
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}
