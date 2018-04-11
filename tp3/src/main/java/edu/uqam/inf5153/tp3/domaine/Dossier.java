package edu.uqam.inf5153.tp3.domaine;

public class Dossier {
	
	
	private String maladie = new String();
	private String medecin = new String();
	
	public Dossier(){}
	
	public String getMaladie() {
		return maladie;
	}
	public void setMaladie(String maladie) {
		this.maladie = maladie;
	}
	public String getMedecin() {
		return medecin;
	}
	public void setMedecin(String medecin) {
		this.medecin = medecin;
	}

}
