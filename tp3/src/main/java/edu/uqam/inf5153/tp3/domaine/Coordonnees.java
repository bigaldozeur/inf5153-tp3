package edu.uqam.inf5153.tp3.domaine;

public class Coordonnees {
	Adresse adresse = new Adresse();
	String telephone = new String();
	String courriel = new String();
	
	public String toString() {
		return adresse.toString() + "\r\n" + telephone + "\r\n" + courriel;
	}
}
