package edu.uqam.inf5153.tp3.application;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.gson.Gson;

import edu.uqam.inf5153.tp3.domaine.Dossier;

public class ControleurDeGuiApp {
	static public SessionFrm window;

	public ControleurDeGuiApp(SessionFrm sessionFrm){
		window = sessionFrm;
	}
	
	/**
	 * Permet d'afficher n'importe quel panneau dans le panneau principal.
	 * */
	public void AfficherPanneau(JPanel jp) throws Exception {
		window.frmDossierMdicalCentralis.setContentPane(jp);
		window.frmDossierMdicalCentralis.revalidate();
	}
	/**
	 * Permet d'afficher n'importe quel panneau dans le panneau principal avec un JComponent en parametres.
	 * */
	public void AfficherPanneau(JPanel jp, JComponent jcompo) {
		// TODO : bon, trouver le moyen de passer ça :(
		//jp.add(jcompo);
		//jp.setVisible(false);
		window.frmDossierMdicalCentralis.setContentPane(jp);
		window.frmDossierMdicalCentralis.revalidate();
	}
	
	/**
	 * Permet de créer un nouveau dossier à partir d'un json
	 * */
	public static Dossier Create(String json){
		Gson g = new Gson();
		return (g.fromJson(json, Dossier.class));
	}

	
}
