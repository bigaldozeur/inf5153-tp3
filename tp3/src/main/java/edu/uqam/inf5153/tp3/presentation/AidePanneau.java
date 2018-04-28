package edu.uqam.inf5153.tp3.presentation;

import java.awt.Font;

import javax.swing.JComponent;


// Singleton
public class AidePanneau {
	static private AidePanneau instance = null;
	
	static public AidePanneau getInstance() {
		if(instance == null) {
			instance = new AidePanneau();
		}
		return instance;	
	} 
	/**
	 * Permet d'ajouter une entrée dans le panneau
	 * Retourne la prochaine position x pour un label.
	 * */
	public int AjouterEntreePanneau(String nomLabel, String valeur, boolean afficherTextField, int posX, int bond, boolean persoMed, JComponent panneau) {

		return AjouterEntreePanneau("", nomLabel, valeur, afficherTextField, posX, bond, persoMed, panneau);
	}
	
	/**
	 * Permet d'ajouter une entrée dans le panneau
	 * Retourne la prochaine position x pour un label.
	 * */
	public int AjouterEntreePanneau(String nomLabel, String valeur, int posX, boolean persoMed, JComponent panneau) {
		return AjouterEntreePanneau(nomLabel, valeur, true, posX, 20, persoMed, panneau);
	}
	
	public int AjouterEntreePanneau(String nomComponentText, String nomLabel, String valeur, int posX, boolean persoMed, JComponent panneau) {
		return AjouterEntreePanneau(nomComponentText, nomLabel, valeur, true, posX, 20, persoMed, panneau);
	}


	/**
	 * Permet d'ajouter une entrée dans le panneau
	 * Retourne la prochaine position x pour un label.
	 * */
	public int AjouterEntreePanneau(String nomTextField, String textLabel, String valeurTextField, boolean afficherTextField, int posX, int bond, boolean persoMed, JComponent panneau) {
		
		// Abstract Factory (pourrait être un label pour un dossier avec différentes caractéristiques et un label pour un autre type)
		LabelTextFactory controls = new DossierLabelTextFactory();
		if(afficherTextField) {
			DossierTextField dtf = controls.createTextField(nomTextField, valeurTextField, posX, persoMed == false?true:false, panneau);
			dtf.setFont(new Font("arial", Font.PLAIN, 12));
		}
        controls.createLabel("", textLabel, posX, panneau);
			
		posX+=bond;
    	return posX;
	}


}
