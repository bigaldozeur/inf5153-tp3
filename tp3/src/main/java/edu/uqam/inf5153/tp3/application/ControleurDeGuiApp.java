package edu.uqam.inf5153.tp3.application;

import java.sql.SQLException;

import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import edu.uqam.inf5153.tp3.application.session.Session;
import edu.uqam.inf5153.tp3.domaine.Dossier;
import edu.uqam.inf5153.tp3.domaine.GestionDossier;
import edu.uqam.inf5153.tp3.servicesTechniques.securite.ControlleurDeBdSecurite;

public class ControleurDeGuiApp {
	
	private Session maSession = null;
	GestionDossier gd = null;
	public ControleurDeGuiApp(){
		maSession = new Session();
		gd = new GestionDossier();
	}
	
	
	/**
	 * Permet de modifier un dossier
	 * @throws ParseException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * */
	public void edit(String noRamq, Dossier dossier) throws ParseException, ClassNotFoundException, SQLException{
		Gson g = new Gson();
		
		gd.modifier(noRamq, g.toJson(dossier)); 
	
	}
	
	

	// Permet de verifier un index
	public boolean existe(Object obj) throws ClassNotFoundException, SQLException
	{
		String noRamq = String.valueOf(obj);
		
		// Aller vérifier si le numéro de ramq existe dans la bd.
		return gd.existe(noRamq);
		
	}
	
	/**
	 * Permet de créer l'utilisateur et son mot de passe, s'il n'existe pas, le crée. (Pour les besoin du TP seulement)
	 * @param sb 
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * retourne un message sur le résultat
	 * */
	public boolean creerVerifierSession(String user, char[] password, StringBuilder sb) throws ClassNotFoundException, SQLException, Exception {
								
		String mp = String.valueOf(password);
		if(!ControlleurDeBdSecurite.utilisateurExiste(user)){
				ControlleurDeBdSecurite.creerUtilisateur(user, mp);	
				sb.append("Utilisateur créé avec succès!");
			return true;
		}
		else {
			sb.append("L'utilisateur existe déjà ou une erreur s'est produite, veuillez recommencez.");
			return false;
			
		}
			
		
	}
	
	/**
	 * Permet de vérifier l'utilisateur
	 * @param sb
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * retourne un message d'erreur si pas résussi, sinon null
	 * */
	public boolean verifierSession(String user, char[] password, StringBuilder sb) throws ClassNotFoundException, SQLException, Exception {
								
		String mp = String.valueOf(password);
		if(!ControlleurDeBdSecurite.utilisateurExiste(user)){
			sb.append("L'utilisateur entré n'existe pas, vous devez le créer.");
			return false;
		}
		else {
			return true;
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

	public Dossier getDossier(String noRAMQ) throws ClassNotFoundException, SQLException {
		
		return gd.rechercher(noRAMQ);
	}
	
	

	
}
