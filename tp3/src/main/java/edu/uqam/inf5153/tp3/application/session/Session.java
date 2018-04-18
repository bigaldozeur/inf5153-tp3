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
		//motPasse = pwd.toString();
	
		
	}
	public Session(){
		
	
	}
	
	
	public boolean authentifier() throws Exception  {
		
			boolean auth = ControlleurDeBdSecurite.authentifier(utilisateur, motPasse);
			return auth;
	}

/*	
	public boolean authentifier(){
		System.setProperty("java.security.auth.login.config", "DossierMedical.config");
		LoginContext login = null;
		try {
			login = new LoginContext("DossierMedicalConfig", new ControlleurLogin());
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	//	while(true){
			try{
				login.login();
				return true;
			}catch(LoginException e){
				//System.out.println(e.getMessage());
				return false;
			}
		}
	//}
*/
}
