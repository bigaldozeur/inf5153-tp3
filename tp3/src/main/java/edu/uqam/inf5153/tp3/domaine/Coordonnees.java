package edu.uqam.inf5153.tp3.domaine;

public class Coordonnees {
	private Adresse adresse = new Adresse();
	private String telephone = new String();
	private String courriel = new String();
	
	public String toString() {
		return getAdresse().toString() + "\r\n" + getTelephone() + "\r\n" + getCourriel();
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCourriel() {
		return courriel;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}
}
