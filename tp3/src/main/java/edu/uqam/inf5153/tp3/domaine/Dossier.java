package edu.uqam.inf5153.tp3.domaine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.json.simple.parser.ParseException;

/*
 * Design pattern Observer
 * inspiré de : https://www.tutorialspoint.com/design_pattern/observer_pattern.htm
 * voir : classe Observateur et méthode attach et informerLesUtilisateurs
 * */
public class Dossier {
	private List<Observateur> observateurs = new ArrayList<Observateur>();

	
	private String noRamq = "";
	private String maladie = new String();
	private String medecin = new String();
	private Personne patient = new Personne();
	private GENRE genre = GENRE.NOT_KNOWN;
	private String dateDeNaissance = new String(); 
	private String pere = new String();
	private String mere = new String();
	private String villeNaissance = new String();
	private Coordonnees coor = new Coordonnees();
	private ArrayList<Visite> visites = new ArrayList<>();
	private ArrayList<Antecedent> antecedents = new ArrayList<>();
	
	public Dossier(){}
	
	public void setNoRamq(String noRamq){
		this.noRamq = noRamq;
		
	}
	public String getNoRamq(){
		return noRamq;
	}
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
	public void setMaladie(String maladie) throws ClassNotFoundException, ParseException, SQLException {
		this.maladie = maladie;
		informerLesObservateurs();
	}
	public String getMedecin() {
		return medecin;
	}
	public void setMedecin(String medecin) throws ClassNotFoundException, ParseException, SQLException {
		this.medecin = medecin;
		informerLesObservateurs();
	}

	public String getGenre() {
		return genre.toString();
	}

	public void setGenre(GENRE genre) throws ClassNotFoundException, ParseException, SQLException {
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
	
	public void setAntecedents(Antecedent ant) throws ClassNotFoundException, ParseException, SQLException {	
		antecedents.add(ant);
		informerLesObservateurs();
		
	}
	
	public void setAntecedents(Antecedent ant, int ind) throws ClassNotFoundException, ParseException, SQLException {	
		antecedents.set(ind, ant);
		informerLesObservateurs();
		
	}
	public Visite[] getVisites() {
		
		Visite[] vis = new Visite[visites.size()];;
		vis = visites.toArray(vis);
		return vis;
		
		
	}
	public void setVisite(Visite vis) throws ClassNotFoundException, ParseException, SQLException {	
		visites.add(vis);
		informerLesObservateurs();
		
	}
	
	public void setVisite(Visite vis, int ind) throws ClassNotFoundException, ParseException, SQLException {	
		visites.set(ind, vis);
		informerLesObservateurs();
		
	}
	/*public boolean getPersoMed() {
		return this.persoMed == 0 ?false:true;
	}*/
	public void attach(Observateur obs){
		observateurs.add(obs);		
	}

	public void informerLesObservateurs() throws ClassNotFoundException, ParseException, SQLException{
		for (Observateur observateur : observateurs) {
			observateur.update(this);
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
