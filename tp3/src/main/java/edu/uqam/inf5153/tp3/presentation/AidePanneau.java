package edu.uqam.inf5153.tp3.presentation;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

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
	public int AjouterEntreePanneau(String nomLabel, String valeur, boolean afficherTextField, int posX, int bond, JComponent panneau) {

		return AjouterEntreePanneau("", nomLabel, valeur, afficherTextField, posX, bond, panneau);
	}
	
	/**
	 * Permet d'ajouter une entrée dans le panneau
	 * Retourne la prochaine position x pour un label.
	 * */
	public int AjouterEntreePanneau(String nomLabel, String valeur, int posX, JComponent panneau) {
		return AjouterEntreePanneau(nomLabel, valeur, true, posX, 20, panneau);
	}
	
	public int AjouterEntreePanneau(String nomComponentText, String nomLabel, String valeur, int posX, JComponent panneau) {
		return AjouterEntreePanneau(nomComponentText, nomLabel, valeur, true, posX, 20, panneau);
	}


	/**
	 * Permet d'ajouter une entrée dans le panneau
	 * Retourne la prochaine position x pour un label.
	 * */
	public int AjouterEntreePanneau(String nomComponentText, String nomLabel, String valeur, boolean afficherTextField, int posX, int bond, JComponent panneau) {
		
		JLabel label = new JLabel(nomLabel);
		label.setBounds(50, posX, 100, 14);
    	panneau.add(label);

		if(afficherTextField) {
			
			JTextField tf = new JTextField(valeur);
			tf.setName(nomComponentText);
			tf.setBounds(180, posX, 200, 16);
			panneau.add(tf);
		}
    	posX+=bond;
    	return posX;
	}

}
