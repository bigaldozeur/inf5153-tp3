package edu.uqam.inf5153.tp3.domaine;

import java.sql.SQLException;

import edu.uqam.inf5153.tp3.servicesTechniques.ControlleurDeBd;

public class GestionDossier {
	
	GestionDossier(){
		
	}
	
	boolean rechercher(Object obj) {
		String noRamq = String.valueOf(obj);
		try {
			if(noRamq != null || ControlleurDeBd.rechercher(noRamq))
				return true;
			else
				return false;
		} catch (ClassNotFoundException e) {
			return false;
		} catch (SQLException e) {
			return false;
		}
	}
}
