package edu.uqam.inf5153.tp3.domaine;

public class Visite {
	
	private Etablissement etablissement = new Etablissement();
	private Personne medecinVu = new Personne();	
	private String date = new String();
	private String diagnostique = new String();
	private String traitement = new String();
	private String resume =  new String();
	private String notes = new String();
	
	public Visite(){}

	public Visite(Etablissement etablissement, Personne medecinVu, String date, String diagnostique, String traitement,
			String resume, String notes) {
		super();
		this.setEtablissement(etablissement);
		this.setMedecinVu(medecinVu);
		this.setDate(date);
		this.setDiagnostique(diagnostique);
		this.setTraitement(traitement);
		this.setResume(resume);
		this.setNotes(notes);
	}

	public Etablissement getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(Etablissement etablissement) {
		this.etablissement = etablissement;
	}

	public Personne getMedecinVu() {
		return medecinVu;
	}

	public void setMedecinVu(Personne medecinVu) {
		this.medecinVu = medecinVu;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	};
	
}
