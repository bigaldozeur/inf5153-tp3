package edu.uqam.inf5153.tp3.domaine;

import java.sql.SQLException;

import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import edu.uqam.inf5153.tp3.servicesTechniques.ControlleurDeBd;

public class GestionDossier {
	
	public GestionDossier(){
		
	}
	
	public boolean existe(Object obj) {
		String noRamq = String.valueOf(obj);
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
}
