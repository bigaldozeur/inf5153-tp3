package edu.uqam.inf5153.tp3.servicesTechniques.bd;

import java.sql.Connection;
import java.sql.SQLException;

public class Connexion {

	private static String bd = "DossierMedical.db";
	private Connexion(){}
	
	public static Connection connecter()  throws ClassNotFoundException, SQLException{
		return SqliteConnexion.getConnexion(bd);
    }
	
	public static boolean estConnecter(){
		return SqliteConnexion.estConnecter();
	}
	
	public static void setBd(String bd){
		Connexion.bd = bd;
	}
	
	
}
