package edu.uqam.inf5153.tp3.application.session;


import edu.uqam.inf5153.tp3.servicesTechniques.securite.ControlleurDeBdSecurite;

/*
 * Le code suivant est inspiré de 
 * https://www.youtube.com/watch?v=xv2xltxnnBU
 * 
 * */
public class Session {
	private String utilisateur = "";
	private String motPasse="";
	private boolean persoMed = true; 
	
	public Session(){
		
	
	}
	
	public boolean authentifier() throws Exception  {
		
			boolean auth = ControlleurDeBdSecurite.authentifier(utilisateur, motPasse);
			if(auth)
				this.setPersoMed(ControlleurDeBdSecurite.isPersonnelMedical(utilisateur));
			return auth;
	}

	public String getUtilisateur(){
		return utilisateur;
	}
	public String getMotPasse(){
		return motPasse;
	}
	public void setUtilisateur(String u){
		utilisateur = u;
	}
	public void setMotPasse(char[] pwd){
		motPasse = String.valueOf(pwd);
	}
public boolean isPersoMed() {
		return persoMed;
	}

	
	public void setPersoMed(boolean persoMed) {
		this.persoMed = persoMed;
	}
}
