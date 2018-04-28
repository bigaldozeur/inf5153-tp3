package edu.uqam.inf5153.tp3.presentation;

import javax.swing.JComponent;
import javax.swing.JTextField;

public class DossierTextField implements TextField{

	private String nom = "";
	private String valeur = "";
	private boolean afficher= true;
	private int posX = 0;
	private JComponent composant = null;

	public DossierTextField(String nom, String valeur, int posX, JComponent composant) {
		this.nom = nom;
		this.valeur= valeur;
		this.setPosX(posX);
		this.setComposant(composant);
		
		JTextField tf = new JTextField(valeur);
		tf.setName(nom);
		tf.setBounds(280, posX, 200, 16);
		composant.add(tf);
	}
		@Override
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@Override
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	// Pour savoir si le text field sera visible ou non
	public void setAfficher(boolean afficher) {
		this.afficher= afficher;
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
