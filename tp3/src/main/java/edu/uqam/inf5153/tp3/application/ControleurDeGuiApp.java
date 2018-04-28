package edu.uqam.inf5153.tp3.application;

import java.sql.SQLException;

import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import edu.uqam.inf5153.tp3.application.session.Session;
import edu.uqam.inf5153.tp3.domaine.Dossier;
import edu.uqam.inf5153.tp3.domaine.GestionDossier;
import edu.uqam.inf5153.tp3.servicesTechniques.ControlleurDeBd;
import edu.uqam.inf5153.tp3.servicesTechniques.securite.ControlleurDeBdSecurite;

public class ControleurDeGuiApp {
	
	private Session maSession = null;
	
	public ControleurDeGuiApp(){
		maSession = new Session();
	}
	
	/**
	 * Permet de construire un dossier à partir d'un json
	 * */
	public static Dossier create(String json){
		Gson g = new Gson();
		return (g.fromJson(json, Dossier.class));
	}
	
	
	/**
	 * Permet de modifier un dossier
	 * @throws ParseException 
	 * */
	public boolean edit(String noRamq, Dossier dossier) throws ParseException{
		Gson g = new Gson();
		GestionDossier gd = new GestionDossier();
		boolean retour = gd.modifier(noRamq, g.toJson(dossier)); 
		return (retour);
	}
	
	

	// Permet de verifier un index
	public boolean index(Object obj) throws ClassNotFoundException, SQLException
	{
		String noRamq = String.valueOf(obj);
		if(noRamq != null) {
			// Aller vérifier si le numéro de ramq existe dans la bd.
			GestionDossier gd = new GestionDossier();
			return gd.rechercher(noRamq);
		}
		return false;
	}
	
	/**
	 * Permet de vérifier l'utilisateur et son mot de passe, s'il n'existe pas, le crée. (Pour les besoin du TP seulement)
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * */
	public void creerVerifierSession(String user, char[] password) throws ClassNotFoundException, SQLException, Exception {
								
		String mp = String.valueOf(password);
		if(!ControlleurDeBdSecurite.utilisateurExiste(user)){
			ControlleurDeBdSecurite.creerUtilisateur(user, mp);
			System.out.println("utilisateur cree");
			System.exit(0);
		}
		
	}

	/**
	 * Vérifie l'authentification, 
	 * */
	public boolean verifierAuthentifier(String user, char[] password) {
		maSession.setUtilisateur(user);
		String mp = String.valueOf(password);
		maSession.setMotPasse(mp.toCharArray());

		try {
			if(maSession.authentifier()){
				return true;
			}
			else{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	

	
}
