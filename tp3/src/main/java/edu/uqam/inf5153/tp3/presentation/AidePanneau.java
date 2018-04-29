package edu.uqam.inf5153.tp3.presentation;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JComponent;
import javax.swing.JScrollPane;

import org.json.simple.parser.ParseException;


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
	public int AjouterEntreePanneau(String nomLabel, String valeur, int posX, boolean enable, JComponent panneau) {
		return AjouterEntreePanneau(nomLabel, valeur, true, posX, 20, enable, panneau);
	}
	
	public int AjouterEntreePanneau(String nomComponentText, String nomLabel, String valeur, int posX, boolean enable, JComponent panneau) {
		return AjouterEntreePanneau(nomComponentText, nomLabel, valeur, true, posX, 20, enable, panneau);
	}


	/**
	 * Permet d'ajouter une entrée dans le panneau
	 * Retourne la prochaine position x pour un label.
	 * */
	public int AjouterEntreePanneau(String nomTextField, String textLabel, String valeurTextField, boolean afficherTextField, int posX, int bond, boolean enable, JComponent panneau) {
		
		// Abstract Factory (pourrait être un label pour un dossier avec différentes caractéristiques et un label pour un autre type)
		LabelTextFactory controls = new DossierLabelTextFactory();
		if(afficherTextField) {
			DossierTextField dtf = controls.createTextField(nomTextField, valeurTextField, posX, enable, panneau);
			dtf.setFont(new Font("arial", Font.PLAIN, 12));
		}
        controls.createLabel("", textLabel, posX, panneau);
			
		posX+=bond;
    	return posX;
	}

	public void retourAuDossier(final String noRAMQ, final String user) throws ClassNotFoundException, SQLException {
		PnlDossier pnlDossier;
		JScrollPane sp;
		try {
			pnlDossier = new PnlDossier(noRAMQ, user);
			sp = new JScrollPane(pnlDossier);
			sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			sp.setBounds(50, 30, 400, 100);
			
			SessionFrm.mainWindow.frmDossierMdicalCentralis.getContentPane().add(sp);
			
			SessionFrm.mainWindow.frmDossierMdicalCentralis.setContentPane(sp);
			SessionFrm.mainWindow.frmDossierMdicalCentralis.setVisible(true);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
