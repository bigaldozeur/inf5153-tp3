package edu.uqam.inf5153.tp3.domaine;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import edu.uqam.inf5153.tp3.application.ControleurDeGuiApp;
import edu.uqam.inf5153.tp3.servicesTechniques.ControlleurDeBd;

public class GestionDossier {
	
	public GestionDossier(){
		
	}
	
	public boolean existe(String noRamq) {
		try {
			if(noRamq != null || ControlleurDeBd.dossierExiste(noRamq))
				return true;
			else
				return false;
		} catch (ClassNotFoundException e) {
			return false;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public boolean modifier(String noRamq, Object obj) throws ParseException {
		Gson dossierJson = new Gson();
		dossierJson = (Gson)obj;
		try {
			if(noRamq != null){
				ControlleurDeBd.modifier(noRamq, dossierJson.toString());
				return true;
			}
			else
				return false;
		} catch (ClassNotFoundException e) {
			return false;
		} catch (SQLException e) {
			return false;
		}
	}
	
	/**
	 * Permet de construire un dossier à partir d'un json
	 * */
	private Dossier jsonStrToDossier(String json){
		Gson g = new Gson();
		return (g.fromJson(json, Dossier.class));
	}
	
	public Dossier rechercher(String noRamq) throws ClassNotFoundException, SQLException {
		
		ResultSet rs;
		rs = ControlleurDeBd.consulterDossier(noRamq);
		Dossier dossier = null;
		while(rs.next()) {
			dossier = jsonStrToDossier(rs.getString("dossier"));
		}
		return dossier;
	}
	
}
