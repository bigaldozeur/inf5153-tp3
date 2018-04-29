package edu.uqam.inf5153.tp3.domaine;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import edu.uqam.inf5153.tp3.servicesTechniques.ControlleurDeBd;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class GestionDossier {
	ControlleurDeBd cbd = new ControlleurDeBd();
	public GestionDossier(){
		
	}
	
	public boolean existe(String noRamq) throws ClassNotFoundException, SQLException {
		
		return cbd.dossierExiste(noRamq);
		
	}
	
	public void modifier(String noRamq, Dossier dossier) throws ParseException, ClassNotFoundException, SQLException {
		
		cbd.modifier(noRamq, dossierToJsonStr(dossier));
		
	}
	
	private String dossierToJsonStr(Dossier dossier){
		Gson g = new Gson();
		String json = g.toJson(dossier);
		
		JSONParser parser = new JSONParser();
    	
    	
  	  Object obj = null;
		try {
			obj = parser.parse(json);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Visite[] v = dossier.getVisites();
		String visites = g.toJson(v);

		Antecedent[] a = dossier.getAntecedents();
		String antecedents = g.toJson(a);

		
		StringBuilder jsonFinal = new StringBuilder();
		jsonFinal.append("{");
		jsonFinal.append("\"noRamq\":\""  +  dossier.getNoRamq()  + "\",");
		jsonFinal.append("\"prenom\":\""  +  dossier.getPrenom()  + "\",");
		jsonFinal.append("\"nom\":\""  +  dossier.getNom()  + "\",");
		jsonFinal.append("\"pere\":\""  +  dossier.getPere()  + "\",");
		jsonFinal.append("\"mere\":\""  +  dossier.getMere()  + "\",");
		jsonFinal.append("\"maladie\":\""  +  dossier.getMaladie()  + "\",");
		jsonFinal.append("\"genre\":\""  +  dossier.getGenre()  + "\",");
		jsonFinal.append("\"villeNaissance\":\""  +  dossier.getVilleNaissance()  + "\",");
		jsonFinal.append("\"dateNaissance\":\""  +  dossier.getDateDeNaissance()  + "\",");
		
		jsonFinal.append("\"visites\":"  + visites   + ",");
		jsonFinal.append("\"antecedents\":"  + antecedents  );
		jsonFinal.append("}");
		
		return jsonFinal.toString();
	}
	
	/**
	 * Permet de construire un dossier à partir d'un json
	 * @throws SQLException 
	 * @throws ParseException 
	 * @throws ClassNotFoundException 
	 * */
	private Dossier jsonStrToDossier(String json) throws ClassNotFoundException, ParseException, SQLException{
		Dossier dossier = new Dossier();
				
		JSONParser parser = new JSONParser();
    	
    	
    	  Object obj = null;
		try {
			obj = parser.parse(json);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

          JSONObject jsonObject = (JSONObject) obj;
         // System.out.println(jsonObject);
          System.out.println(jsonObject.toJSONString());
          
          String name = (String) jsonObject.get("medecin");
          System.out.println(name);
          
          dossier.setNoRamq((String) jsonObject.get("noRamq"));
          dossier.setPrenom((String) jsonObject.get("prenom"));
          dossier.setNom((String) jsonObject.get("nom"));
          dossier.setPere((String) jsonObject.get("pere"));
          dossier.setMere((String) jsonObject.get("mere"));
          dossier.setMaladie((String) jsonObject.get("maladie"));
          dossier.setGenre(GENRE.NOT_KNOWN);
          dossier.setVilleNaissance((String) jsonObject.get("villeNaissance"));
          dossier.setDateDeNaissance((String) jsonObject.get("dateNaissance"));
          dossier.setVisite(new Visite());
          dossier.setAntecedents(new Antecedent());
          
		return dossier;
	}
	
	public Dossier rechercher(String noRamq) throws ClassNotFoundException, SQLException, ParseException {
		
		ResultSet rs;
		rs = cbd.consulterDossier(noRamq);
		Dossier dossier = new Dossier();
		while(rs.next()) {
			dossier = jsonStrToDossier(rs.getString("dossier"));
		}
		return dossier;
	}
	
}
