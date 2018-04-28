package edu.uqam.inf5153.tp3.presentation;

import javax.swing.JComponent;

public interface LabelTextFactory {
	    DossierLabel createLabel(String nom, String valeur, int posX, JComponent composant);
	    DossierTextField createTextField(String nom, String valeur, int posX, JComponent composant);
}
