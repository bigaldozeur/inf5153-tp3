package edu.uqam.inf5153.tp3.servicesTechniques;

import java.sql.*;
import java.time.LocalDateTime;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.uqam.inf5153.tp3.servicesTechniques.bd.Connexion;
/*NOTE :
 * modifier le nom parametre de id a numAssuranceMaladie
 *  Retour : ResultSet et non ArrayList
 *  insertion #assmaladie et json dossier
 *  modification jsondossier et #ass maladie
 *  
 * */
public class ControlleurDeBd  {
	private static Connection conn = null;
	
	private static void seConnecter() throws ClassNotFoundException, SQLException{
		conn = Connexion.connecter();
	}
	
	public static void initialiserBD(){
		
		 
		/*
        create table DossierMedical(
        id integer primary key autoincrement, 
	  	  numAssuranceMaladie string, 
	  	  dossier string, 
	  	  medecin string, 
	  	  dateInsertion datetime, 
		  utilisateur string);
		
	*/
	}
	public static ResultSet obtenirHistorique(String numAssuranceMaladie) throws ClassNotFoundException, SQLException{
		if( !Connexion.estConnecter() || conn == null ){
			seConnecter();
		}
		String sql = "select * from DossierMedical where numAssuranceMaladie=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, numAssuranceMaladie);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	
	public static ResultSet obtenirListeMedecinTraitant(String numAssuranceMaladie) throws ClassNotFoundException, SQLException{
		if( !Connexion.estConnecter() || conn == null ){
			seConnecter();
		}
		
		String sql = "select distinct medecin from DossierMedical where numAssuranceMaladie=? order by medecin";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, numAssuranceMaladie);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	
	public static ResultSet consulterDossier(String numAssuranceMaladie) throws ClassNotFoundException, SQLException{
		if( !Connexion.estConnecter() || conn == null ){
			seConnecter();
		}
		String sql = "select * from DossierMedical where numAssuranceMaladie = ? ";
		sql += " and id = (select max(id) from DossierMedical where numAssuranceMaladie = ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		 
		pstmt.setString(1, numAssuranceMaladie);
		pstmt.setString(2, numAssuranceMaladie);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	
	public static ResultSet consulterDossier(String numAssuranceMaladie, String date) throws ClassNotFoundException, SQLException{
		if( !Connexion.estConnecter() || conn == null ){
			seConnecter();
		}
		String sql = "select * from DossierMedical where ";
		sql += " id = (select max(id) from DossierMedical where numAssuranceMaladie = ? and date(dateInsertion) = ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		 
		pstmt.setString(1, numAssuranceMaladie);
		pstmt.setString(2, date);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	
	public static boolean rechercher(String numAssuranceMaladie) throws ClassNotFoundException, SQLException{
		if( !Connexion.estConnecter() || conn == null ){
			seConnecter();
		}
		
		String sql = "select 1 from DossierMedical where numAssuranceMaladie = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		 
		pstmt.setString(1, numAssuranceMaladie);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next())
			return true;
		return false;
	}
	
	
	public static void ajouter(String numAssuranceMaladie , String dossierJson) throws ParseException, ClassNotFoundException, SQLException{
		JSONParser parser = new JSONParser();
    	Object obj = null;
		obj = parser.parse(dossierJson);
        JSONObject jsonObject = (JSONObject) obj;
        String medecin = (String) jsonObject.get("medecin");
        String utilisateur = "system";
        
        if( !Connexion.estConnecter() || conn == null ){
			seConnecter();
		}
        
        String sql = "insert into DossierMedical(numAssuranceMaladie, dossier, medecin, utilisateur, dateInsertion) values (?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        pstmt.setString(1, numAssuranceMaladie);
        pstmt.setString(2,jsonObject.toJSONString());
        pstmt.setString(3,medecin);
        pstmt.setString(4,utilisateur);
        pstmt.setString(5,LocalDateTime.now().toString());
        
        pstmt.executeUpdate();
        
        
	}
	
	public static void modifier(String numAssuranceMaladie , String dossierJson) throws ParseException, ClassNotFoundException, SQLException{
		JSONParser parser = new JSONParser();
    	Object obj = null;
		obj = parser.parse(dossierJson);
        JSONObject jsonObject = (JSONObject) obj;
        String medecin = (String) jsonObject.get("medecin");
        String utilisateur = "system";
        
        if( !Connexion.estConnecter() || conn == null ){
			seConnecter();
		}
        
        String sql = "update DossierMedical set dossier=?, medecin=?, utilisateur=? where ";
        sql += " id = (select max(id) from DossierMedical where numAssuranceMaladie = ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
       
        pstmt.setString(1,jsonObject.toJSONString());
        pstmt.setString(2,medecin);
        pstmt.setString(3,utilisateur);
        pstmt.setString(4,numAssuranceMaladie);
        pstmt.executeUpdate();
        
        
	}
	
	
}


