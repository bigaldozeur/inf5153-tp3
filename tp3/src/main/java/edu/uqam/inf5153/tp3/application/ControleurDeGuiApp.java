package edu.uqam.inf5153.tp3.application;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.gson.Gson;

import edu.uqam.inf5153.tp3.domaine.Dossier;
import edu.uqam.inf5153.tp3.presentation.SessionFrm;

public class ControleurDeGuiApp {

	public ControleurDeGuiApp(){
	}
	

	/**
	 * Permet de créer un nouveau dossier à partir d'un json
	 * */
	public static Dossier Create(String json){
		Gson g = new Gson();
		return (g.fromJson(json, Dossier.class));
	}

	
}
