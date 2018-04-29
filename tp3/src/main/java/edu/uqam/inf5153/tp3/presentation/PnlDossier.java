package edu.uqam.inf5153.tp3.presentation;


import java.sql.SQLException;


import javax.swing.JLabel;
import javax.swing.JScrollPane;

import org.json.simple.parser.ParseException;

import edu.uqam.inf5153.tp3.application.ControleurDeGuiApp;
import edu.uqam.inf5153.tp3.presentation.SessionFrm;
import edu.uqam.inf5153.tp3.domaine.Antecedent;
import edu.uqam.inf5153.tp3.domaine.Dossier;
import edu.uqam.inf5153.tp3.domaine.Visite;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
/*
 * Design pattern Observer
 * inspiré de : https://www.tutorialspoint.com/design_pattern/observer_pattern.htm
 * voir : classe Observateur et méthode attach et informerLesUtilisateurs
 * */
public class PnlDossier extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControleurDeGuiApp cgui = null;
	private Dossier dossier = null;
	private String noRAMQ;
	/**
	 * Create the panel.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public PnlDossier() throws ClassNotFoundException, SQLException {
		 
		//setBounds(new Rectangle(0, 0, 750, 800));
	    //init("abc1234");		      	        	
	}
	
	public PnlDossier(String noRAMQ, String user) throws ClassNotFoundException, SQLException, ParseException {
		this.noRAMQ = noRAMQ;
		cgui = SessionFrm.getControleurGui();
		cgui.setNoRamq(noRAMQ);
		dossier =  new Dossier();
		dossier = cgui.getDossier(noRAMQ);
		dossier.setNoRamq(noRAMQ);
		dossier.attach(cgui);

	    init(noRAMQ, user);	
	}
	        
	private void init(final String noRAMQ, final String user) throws SQLException, ClassNotFoundException {
		
		
		setLayout(null);
		setBounds(new Rectangle(0, 0, 750, 800));
		Dimension a = new Dimension();
		a.height = 800;
		a.width = 750;
		this.setPreferredSize(a);
		JLabel lblDossierMdical = new JLabel("Dossier médical du patient");
		lblDossierMdical.setBounds(50, 83, 246, 14);
		add(lblDossierMdical);
		
		
		// Aller chercher les droits
		boolean enableChamp = cgui.getEnableChamp(user)	;
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionFrm.mainWindow.frmDossierMdicalCentralis.setContentPane(new PnlNoDossier(user));
				SessionFrm.mainWindow.frmDossierMdicalCentralis.revalidate();
			}
		});
		btnRetour.setBounds(658, 11, 89, 23);
		add(btnRetour);
		
    	int posX = 120;
    	int bond = 20;
    	
    	

    	     	
    	posX = AidePanneau.getInstance().AjouterEntreePanneau("Nom et prénom : ", dossier.getNom() + " " + dossier.getPrenom(), posX, enableChamp, this);     
    	posX = AidePanneau.getInstance().AjouterEntreePanneau("Date de naissance : ", dossier.getDateDeNaissance(), posX, enableChamp, this);     
    	posX = AidePanneau.getInstance().AjouterEntreePanneau("Genre : ", dossier.getGenre(), posX, enableChamp, this);
    	posX = AidePanneau.getInstance().AjouterEntreePanneau("Parents connus : ", "", false, posX, 20, enableChamp, this);
    	posX = AidePanneau.getInstance().AjouterEntreePanneau("Père : ", dossier.getPere(), posX, enableChamp, this);
    	posX = AidePanneau.getInstance().AjouterEntreePanneau("Mère : ", dossier.getMere(), posX, enableChamp, this); 	
    	posX = AidePanneau.getInstance().AjouterEntreePanneau("Ville de naissance : ", dossier.getVilleNaissance(), posX, enableChamp, this);
    	posX = AidePanneau.getInstance().AjouterEntreePanneau("Coordonnées : ", dossier.getCoordonnees(), posX, enableChamp,  this); 	
    	posX = AidePanneau.getInstance().AjouterEntreePanneau("Numéro d'assurance maladie : ", noRAMQ, posX, false, this);
    	
    	posX += bond;
    	// Obtenir la liste des antécédents médicaux
    	Antecedent[] antecedents;
    	antecedents = dossier.getAntecedents();

    	posX = AidePanneau.getInstance().AjouterEntreePanneau("Antecedents médicaux : " + (antecedents.length >0?"":"aucun"), "", false, posX, 20, enableChamp, this);
    	
    	// Si c'est un médecin seulement on peut afficher le bouton pour ajouter des antecedents, si c'est un personnel médical, on ne peut pas.
    	if(enableChamp) {
	    	JButton btnAddAntecedent = new JButton("Ajouter un antécedent");
			btnAddAntecedent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SessionFrm.mainWindow.frmDossierMdicalCentralis.setContentPane(new PnlAntecedent(noRAMQ, dossier, user));
					SessionFrm.mainWindow.frmDossierMdicalCentralis.revalidate();
				}
			});
			btnAddAntecedent.setBounds(658, posX-bond, 189, 23);
			add(btnAddAntecedent);
    	}
    	
    	// Afficher la liste des antecedents medicaux.
    	for(int i = 0; i<antecedents.length; i++)
    	{
    		posX = AidePanneau.getInstance().AjouterEntreePanneau("Diagnostique : ", antecedents[i].getDiagnostique(), posX, enableChamp, this);
    		posX = AidePanneau.getInstance().AjouterEntreePanneau("Traitement : ", antecedents[i].getTraitement(), posX, enableChamp, this);
    		posX = AidePanneau.getInstance().AjouterEntreePanneau("Médecin traitant : ", antecedents[i].getMedecinTraitant().toString(), posX, enableChamp, this);
    		posX = AidePanneau.getInstance().AjouterEntreePanneau("Debut de la maladie : ", antecedents[i].getDebutMaladie(), posX, enableChamp, this);
    		posX = AidePanneau.getInstance().AjouterEntreePanneau("Fin de la maladie : ", antecedents[i].getFinMaladie(), posX, enableChamp, this);
    	}
    	
    	
    	posX += bond;
    	
    	Visite[] visites;
    	visites = dossier.getVisites();
    	
    	posX = AidePanneau.getInstance().AjouterEntreePanneau("Visites : " + (visites.length >0?"":"aucune"), "", false, posX, 20, enableChamp, this);
    	// Si c'est un médecin seulement on peut afficher le bouton pour ajouter des visites, si c'est un personnel médical, on ne peut pas.
    	if(enableChamp) {
	    	JButton btnAddVisite = new JButton("Ajouter une visite");
			btnAddVisite.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SessionFrm.mainWindow.frmDossierMdicalCentralis.setContentPane(new PnlVisite(noRAMQ, dossier, user));
					SessionFrm.mainWindow.frmDossierMdicalCentralis.revalidate();
				}
			});
			btnAddVisite.setBounds(658, posX-bond, 189, 23);
			add(btnAddVisite);
    	}
    	
    	// Afficher la liste des visites.
    	for(int i = 0; i<visites.length; i++)
    	{
    		posX = AidePanneau.getInstance().AjouterEntreePanneau("Établissement visité : ", visites[i].getEtablissement().getNom(), posX, enableChamp, this);
    		posX = AidePanneau.getInstance().AjouterEntreePanneau("Médecin vu : ", visites[i].getMedecinVu().toString(), posX, enableChamp, this);
    		posX = AidePanneau.getInstance().AjouterEntreePanneau("Date de la visite: ", visites[i].getDate(), posX, enableChamp, this);		
    		posX = AidePanneau.getInstance().AjouterEntreePanneau("Diagnostique établi, si applicable : ", visites[i].getDiagnostique(), posX, enableChamp, this);
    		posX = AidePanneau.getInstance().AjouterEntreePanneau("Traitement, si applicable : ", visites[i].getTraitement(), posX, enableChamp, this);
    		posX = AidePanneau.getInstance().AjouterEntreePanneau("Résumé de la visite : ", visites[i].getResume(), posX, enableChamp, this);
    		posX = AidePanneau.getInstance().AjouterEntreePanneau("Notes pour les autres médecins : ", visites[i].getNotes(), posX, enableChamp, this);
    	}
    	
    	posX += bond;
    	
    	posX = AidePanneau.getInstance().AjouterEntreePanneau("maladie : ", dossier.getMaladie(), posX, enableChamp, this); // TODO : à enlever, en attendant
    	posX = AidePanneau.getInstance().AjouterEntreePanneau("medecin : ", dossier.getMedecin(), posX, enableChamp, this); // TODO : à enlever, en attendant
        
	}
}
