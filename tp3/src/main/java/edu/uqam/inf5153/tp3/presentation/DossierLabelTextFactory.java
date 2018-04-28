package edu.uqam.inf5153.tp3.presentation;

import javax.swing.JComponent;


public class DossierLabelTextFactory implements LabelTextFactory {

	@Override
	public DossierLabel createLabel(String nom, String valeur, int posX, JComponent composant) {
		return new DossierLabel(nom, valeur, posX, composant);
	}

	@Override
	public DossierTextField createTextField(String nom, String valeur, int posX, JComponent composant) {
		return new DossierTextField(nom, valeur, posX, composant);
	}

}
