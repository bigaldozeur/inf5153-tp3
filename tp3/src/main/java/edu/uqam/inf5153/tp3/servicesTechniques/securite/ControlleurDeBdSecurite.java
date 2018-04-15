package edu.uqam.inf5153.tp3.servicesTechniques.securite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import edu.uqam.inf5153.tp3.fondation.EncrypterString;
import edu.uqam.inf5153.tp3.servicesTechniques.bd.Connexion;

public class ControlleurDeBdSecurite {
	private static Connection conn = null;
	private static boolean authificationEstValide = false;
	
	private static void seConnecter() throws ClassNotFoundException, SQLException{
		conn = Connexion.connecter();
	}

	public static boolean estAuthentifier(){
		return authificationEstValide;
	}
	public static boolean authentifier(String utilisateur, String pwd) throws Exception{
		if( !Connexion.estConnecter() || conn == null ){
			seConnecter();
		}
		String sql = "select pwdhash from utilisateurs where utilisateur=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, utilisateur);
		ResultSet rs = pstmt.executeQuery();
		
		
		String mpbd = rs.getString("pwdhash");	
		  
	
		System.out.println(mpbd);
		System.out.println(EncrypterString.encrypter(pwd));
		
		if (EncrypterString.verifierHash(pwd, mpbd)){
			authificationEstValide = true;
		}else{
			authificationEstValide = false;
		}

		
		rs.close();
		return authificationEstValide;
	}
	
	public static void creerUtilisateur(String utilisateur,String pwd) throws Exception{
		
	    
	    if( !Connexion.estConnecter() || conn == null ){
			seConnecter();
		}
	    
	    
	    String pwdhash = "";
	    pwdhash = EncrypterString.encrypter(pwd);
	    String sql = "insert into utilisateurs(utilisateur, pwdhash) values (?,?)";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    
	    pstmt.setString(1, utilisateur);
	    pstmt.setString(2,pwdhash);
	   
	    
	    pstmt.executeUpdate();
	}
	public static boolean utilisateurExiste(String utilisateur) throws ClassNotFoundException, SQLException{
		if( !Connexion.estConnecter() || conn == null ){
			seConnecter();
		}
		String sql = "select count(*) as cnt from utilisateurs where utilisateur=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, utilisateur);
		ResultSet rs = pstmt.executeQuery();
		
		 int compteUtilisateur = rs.getInt("cnt");
		 	
		rs.close();
		return compteUtilisateur > 0;
		
	}
}