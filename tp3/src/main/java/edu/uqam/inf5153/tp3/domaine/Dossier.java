package edu.uqam.inf5153.tp3.domaine;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

/*
 * Design pattern Observer
 * inspiré de : https://www.tutorialspoint.com/design_pattern/observer_pattern.htm
 * voir : classe Observateur et méthode attach et informerLesUtilisateurs
 * */
public class Dossier {
	private List<Observateur> observateurs = new ArrayList<Observateur>();

	
	
	private String maladie = new String();
	private String medecin = new String();
	private Personne patient = new Personne();
	private GENRE genre = GENRE.NOT_KNOWN;
	// TODO : Devrait être un champs Date
	private String dateDeNaissance = new String(); 
	private String pere = new String();
	private String mere = new String();
	private String villeNaissance = new String();
	private Coordonnees coor = new Coordonnees();
	private ArrayList<Visite> visites = new ArrayList<>();
	private ArrayList<Antecedent> antecedents = new ArrayList<>();
	
	public Dossier(){}
	
	public String getNom() {
		return patient.nom;
	}
	public void setNom(String nom) {
		this.patient.nom = nom;
	}
	
	public String getPrenom() {
		return patient.prenom;
	}
	public void setPrenom(String prenom) {
		this.patient.prenom = prenom;
	}
	
	public String getMaladie() {
		return maladie;
	}
	public void setMaladie(String maladie) {
		this.maladie = maladie;
		informerLesObservateurs();
	}
	public String getMedecin() {
		return medecin;
	}
	public void setMedecin(String medecin) {
		this.medecin = medecin;
		informerLesObservateurs();
	}

	public String getGenre() {
		return genre.toString();
	}

	public void setGenre(GENRE genre) {
		this.genre = genre;
		informerLesObservateurs();
	}

	public String getDateDeNaissance() {
		return this.dateDeNaissance;
	}
	
	public String getVilleNaissance() {
		return this.villeNaissance;
	}
	
	public String getPere(){
		return this.pere;
	}
	
	public String getMere(){
		return this.mere;
	}
	
	public String getCoordonnees() {
		return this.getCoor().toString();
	}

	public Antecedent[] getAntecedents() {	
		Antecedent[] ant = new Antecedent[antecedents.size()];;
		ant = antecedents.toArray(ant);
		return ant;
	}
	
	public void setAntecedents(Antecedent ant) {	
		antecedents.add(ant);
		informerLesObservateurs();
		
	}
	
	public void setAntecedents(Antecedent ant, int ind) {	
		antecedents.set(ind, ant);
		informerLesObservateurs();
		
	}
	public Visite[] getVisites() {
		
		Visite[] vis = new Visite[visites.size()];;
		vis = visites.toArray(vis);
		return vis;
		
		/*
		if(visites == null) {
			// TODO : enlever, c'est une visite par défaut
			Visite vis = new Visite(new Etablissement("etablissement 1", new Adresse()),
					new Personne("nom", "prenom"),
					"2000-01-01", 
					"Les diagnostique est...", 
					"Le traitement est ...\r\n Je ne sais pas", 
					"résumé de la visite \r\nça a bien été!", 
					"note aux autres médecin");
			
			visites = new Visite[]{vis};
		}
		*/
		//return visites;
	}
	public void setVisite(Visite vis) {	
		visites.add(vis);
		informerLesObservateurs();
		
	}
	
	public void setVisite(Visite vis, int ind) {	
		visites.set(ind, vis);
		informerLesObservateurs();
		
	}
	/*public boolean getPersoMed() {
		return this.persoMed == 0 ?false:true;
	}*/
	public void attach(Observateur obs){
		observateurs.add(obs);		
	}

	public void informerLesObservateurs(){
		for (Observateur observateur : observateurs) {
			observateur.update();
		}
	}

	public void setPere(String pere) {
		this.pere = pere;
	}

	public void setMere(String mere) {
		this.mere = mere;
	}

	public void setVilleNaissance(String villeNaissance) {
		this.villeNaissance = villeNaissance;
	}

	public void setDateDeNaissance(String dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public Coordonnees getCoor() {
		return coor;
	}

	public void setCoor(Coordonnees coor) {
		this.coor = coor;
	} 	
}
