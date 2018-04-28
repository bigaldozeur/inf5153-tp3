package edu.uqam.inf5153.tp3.presentation;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class DossierLabel implements Label {

	private String valeur = "";
	private String nom = "";
	private int posX = 0;
	private JComponent composant = null;
	

	public DossierLabel(String nom, String valeur, int posX, JComponent composant) {
		this.nom = nom;
		this.valeur = valeur;
		this.setPosX(posX);
		this.setComposant(composant);
		
		JLabel label = new JLabel(valeur);
		label.setName(nom);
		label.setBounds(50, posX, 200, 14);
    	composant.add(label);
	}
	
	@Override
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public JComponent getComposant() {
		return composant;
	}

	public void setComposant(JComponent composant) {
		this.composant = composant;
	}	
	
}
