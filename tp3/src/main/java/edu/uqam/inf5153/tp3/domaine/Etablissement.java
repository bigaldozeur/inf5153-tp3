package edu.uqam.inf5153.tp3.domaine;

public class Etablissement {
	private String nom = new String();
	private Adresse adr = new Adresse();
	Etablissement(){}
	public Etablissement(String nom, Adresse adr){super(); this.setNom(nom); this.setAdr(adr);}
	public String toString() {
		return getNom() + "\r\n" + getAdr().toString();
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Adresse getAdr() {
		return adr;
	}
	public void setAdr(Adresse adr) {
		this.adr = adr;
	}
}
