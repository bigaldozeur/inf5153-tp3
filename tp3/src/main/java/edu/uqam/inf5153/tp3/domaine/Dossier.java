package edu.uqam.inf5153.tp3.domaine;

public class Dossier {
	
	
	private String maladie = new String();
	private String medecin = new String();
	private Personne patient = new Personne();
	private GENRE genre = GENRE.NOT_KNOWN;
	private String dateDeNaissance = new String(); // TODO : Devrait être un champs Date
	private String pere = new String();
	private String mere = new String();
	private String villeNaissance = new String();
	private Coordonnees coor = new Coordonnees();
	private Visite[] visites;
	private Antecedent[] antecedents;
	
	public Dossier(){}
	
	public String getNom() {
		return patient.nom;
	}
	
	public String getPrenom() {
		return patient.prenom;
	}
	
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

	public String getGenre() {
		return genre.toString();
	}

	public void setGenre(GENRE genre) {
		this.genre = genre;
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
		return this.coor.toString();
	}

	public Antecedent[] getAntecedents() {	
		if(antecedents == null) {
			// TODO : enlever, c'est un antécédent par défaut en attendant pour voir
			Antecedent ant = new Antecedent("Le diagnostique est...", 
					"Le traitement est ...\r\n Je ne sais pas",
					new Personne("nom", "prenom"),
					"2000-01-01", 
					"2000-02-02");
			
			antecedents = new Antecedent[]{ant};	
		}
		return antecedents;
	}
	
	public Visite[] getVisites() {
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
			
		return visites;
	}

}
