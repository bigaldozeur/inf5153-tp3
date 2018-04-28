package edu.uqam.inf5153.tp3.domaine;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import edu.uqam.inf5153.tp3.servicesTechniques.ControlleurDeBd;

public class GestionDossier {
	ControlleurDeBd cbd = new ControlleurDeBd();
	public GestionDossier(){
		
	}
	
	public boolean existe(String noRamq) throws ClassNotFoundException, SQLException {
		
		return cbd.dossierExiste(noRamq);
		
	}
	
	public void modifier(String noRamq, Object obj) throws ParseException, ClassNotFoundException, SQLException {
		Gson dossierJson = new Gson();
		dossierJson = (Gson)obj;
		cbd.modifier(noRamq, dossierJson.toString());
		
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
		rs = cbd.consulterDossier(noRamq);
		Dossier dossier = new Dossier();
		while(rs.next()) {
			dossier = jsonStrToDossier(rs.getString("dossier"));
		}
		return dossier;
	}
	
}
