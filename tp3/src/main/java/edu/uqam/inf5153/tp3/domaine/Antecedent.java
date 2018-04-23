package edu.uqam.inf5153.tp3.domaine;

public class Antecedent {
	private String diagnostique = new String();
	private String traitement = new String();
	private Personne medecinTraitant = new Personne();
	private String debutMaladie = new String();
	private String finMaladie = new String();	
	
	public Antecedent() {
		
	}

	public Antecedent(String diagnostique, String traitement, Personne medecinTraitant, String debutMaladie,
			String finMaladie) {
		super();
		this.diagnostique = diagnostique;
		this.traitement = traitement;
		this.medecinTraitant = medecinTraitant;
		this.debutMaladie = debutMaladie;
		this.finMaladie = finMaladie;
	}

	public String getDiagnostique() {
		return diagnostique;
	}

	public void setDiagnostique(String diagnostique) {
		this.diagnostique = diagnostique;
	}

	public String getTraitement() {
		return traitement;
	}

	public void setTraitement(String traitement) {
		this.traitement = traitement;
	}

	public Personne getMedecinTraitant() {
		return medecinTraitant;
	}

	public void setMedecinTraitant(Personne medecinTraitant) {
		this.medecinTraitant = medecinTraitant;
	}

	public String getDebutMaladie() {
		return debutMaladie;
	}

	public void setDebutMaladie(String debutMaladie) {
		this.debutMaladie = debutMaladie;
	}

	public String getFinMaladie() {
		return finMaladie;
	}

	public void setFinMaladie(String finMaladie) {
		this.finMaladie = finMaladie;
	}
}
