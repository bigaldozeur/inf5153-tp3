package edu.uqam.inf5153.tp3.presentation;


import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import edu.uqam.inf5153.tp3.application.ControleurDeGuiApp;
import edu.uqam.inf5153.tp3.presentation.SessionFrm;
import edu.uqam.inf5153.tp3.domaine.Adresse;
import edu.uqam.inf5153.tp3.domaine.Antecedent;
import edu.uqam.inf5153.tp3.domaine.Coordonnees;
import edu.uqam.inf5153.tp3.domaine.Dossier;
import edu.uqam.inf5153.tp3.domaine.Etablissement;
import edu.uqam.inf5153.tp3.domaine.GENRE;
import edu.uqam.inf5153.tp3.domaine.Personne;
import edu.uqam.inf5153.tp3.domaine.Visite;

import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Component;
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
	
	private Personne per = new Personne();
	/**
	 * Create the panel.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public PnlDossier() throws ClassNotFoundException, SQLException {
		 
		//setBounds(new Rectangle(0, 0, 750, 800));
	    //init("abc1234");		      	        	
	}
	
	public PnlDossier(String noRAMQ, String user) throws ClassNotFoundException, SQLException {
		this.noRAMQ = noRAMQ;
		cgui = SessionFrm.getControleurGui();
		cgui.setNoRamq(noRAMQ);
		dossier =  new Dossier();
		dossier = cgui.getDossier(noRAMQ);
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
    	
   	
    	posX = AidePanneau.getInstance().AjouterEntreePanneau("NomPrenomPat","Nom et prénom : ", dossier.getNom() + " " + dossier.getPrenom(), posX, enableChamp, this);     
    	posX = AidePanneau.getInstance().AjouterEntreePanneau("DateNaiss","Date de naissance : ", dossier.getDateDeNaissance(), posX, enableChamp, this);     
    	posX = AidePanneau.getInstance().AjouterEntreePanneau("Genre","Genre : ", dossier.getGenre(), posX, enableChamp, this);
    	posX = AidePanneau.getInstance().AjouterEntreePanneau("Parents connus : ", "", false, posX, 20, enableChamp, this);
    	posX = AidePanneau.getInstance().AjouterEntreePanneau("Pere","Père : ", dossier.getPere(), posX, enableChamp, this);
    	posX = AidePanneau.getInstance().AjouterEntreePanneau("Mere","Mère : ", dossier.getMere(), posX, enableChamp, this); 	
    	posX = AidePanneau.getInstance().AjouterEntreePanneau("VilleNaissance","Ville de naissance : ", dossier.getVilleNaissance(), posX, enableChamp, this);
    	posX = AidePanneau.getInstance().AjouterEntreePanneau("Coor","Coordonnées : ", dossier.getCoordonnees(), posX, enableChamp,  this); 	
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
    		posX = AidePanneau.getInstance().AjouterEntreePanneau("AntDiagnostique_"+i, "Diagnostique : ", antecedents[i].getDiagnostique(), posX, enableChamp, this);
    		posX = AidePanneau.getInstance().AjouterEntreePanneau("AntTraitement_"+i, "Traitement : ", antecedents[i].getTraitement(), posX, enableChamp, this);
    		posX = AidePanneau.getInstance().AjouterEntreePanneau("AntMedecinTraitant_"+i,"Médecin traitant : ", antecedents[i].getMedecinTraitant().toString(), posX, enableChamp, this);
    		posX = AidePanneau.getInstance().AjouterEntreePanneau("AntDebutMaladie_"+i, "Debut de la maladie : ", antecedents[i].getDebutMaladie(), posX, enableChamp, this);
    		posX = AidePanneau.getInstance().AjouterEntreePanneau("AntFinMaladie_"+i, "Fin de la maladie : ", antecedents[i].getFinMaladie(), posX, enableChamp, this);
    	    		
    	}
    	
    	
    	posX += bond;
    	
    	Visite[] visites;
    	visites = dossier.getVisites();
    	
    	posX = AidePanneau.getInstance().AjouterEntreePanneau("Visites : " + (visites.length >0?"":"aucune"), "", false, posX, 20, true, this);
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
    		posX = AidePanneau.getInstance().AjouterEntreePanneau("VisEtablissementVisite_"+i,"Établissement visité : ", visites[i].getEtablissement().getNom(), posX, enableChamp, this);
    		posX = AidePanneau.getInstance().AjouterEntreePanneau("VisMedecinVu_"+i, "Médecin vu : ", visites[i].getMedecinVu().toString(), posX, enableChamp, this);
    		posX = AidePanneau.getInstance().AjouterEntreePanneau("VisDateVisite_"+i, "Date de la visite: ", visites[i].getDate(), posX, enableChamp, this);		
    		posX = AidePanneau.getInstance().AjouterEntreePanneau("VisDiagnostique_"+i, "Diagnostique établi, si applicable : ", visites[i].getDiagnostique(), posX, enableChamp, this);
    		posX = AidePanneau.getInstance().AjouterEntreePanneau("VisTraitement_"+i, "Traitement, si applicable : ", visites[i].getTraitement(), posX, enableChamp, this);
    		posX = AidePanneau.getInstance().AjouterEntreePanneau("VisResume_"+i, "Résumé de la visite : ", visites[i].getResume(), posX, enableChamp, this);
    		posX = AidePanneau.getInstance().AjouterEntreePanneau("VisNotes_"+i, "Notes pour les autres médecins : ", visites[i].getNotes(), posX, enableChamp, this);
    	}
    	
    	posX += bond;
    	
    	//posX = AidePanneau.getInstance().AjouterEntreePanneau("maladie : ", dossier.getMaladie(), posX, enableChamp, this); // TODO : à enlever, en attendant
    	//posX = AidePanneau.getInstance().AjouterEntreePanneau("medecin : ", dossier.getMedecin(), posX, enableChamp, this); // TODO : à enlever, en attendant
        	
    	HashMap<String,Component> componentMap = createComponentMap(this); // pris exemple ici et modifié. https://stackoverflow.com/questions/4958600/get-a-swing-component-by-name
	
    	
    	addInputMethodEventJTextField("NomPrenomPat", dossier, componentMap);
		addInputMethodEventJTextField("DateNaiss", dossier, componentMap);
		addInputMethodEventJTextField("Genre", dossier, componentMap);
		addInputMethodEventJTextField("Pere", dossier, componentMap);
		addInputMethodEventJTextField("Mere", dossier, componentMap);
		addInputMethodEventJTextField("VilleNaissance", dossier, componentMap);
		addInputMethodEventJTextField("Coor", dossier, componentMap);
	
		for(int i = 0; i < antecedents.length; i++) {
    		addInputMethodEventJTextField("AntDiagnostique_"+i, dossier, componentMap);
    		addInputMethodEventJTextField("AntTraitement_"+i, dossier, componentMap);
    		addInputMethodEventJTextField("AntMedecinTraitant_"+i, dossier, componentMap);
    		addInputMethodEventJTextField("AntDebutMaladie_"+i, dossier, componentMap);
    		addInputMethodEventJTextField("AntFinMaladie_"+i, dossier, componentMap);
		}

		for(int i = 0; i < visites.length; i++) {
			addInputMethodEventJTextField("VisEtablissementVisite_"+i, dossier, componentMap);
			addInputMethodEventJTextField("VisMedecinVu_"+i, dossier, componentMap);
			addInputMethodEventJTextField("VisDateVisite_"+i, dossier, componentMap);
			addInputMethodEventJTextField("VisDiagnostique_"+i, dossier, componentMap);
			addInputMethodEventJTextField("VisTraitement_"+i, dossier, componentMap);
			addInputMethodEventJTextField("VisResume_"+i, dossier, componentMap);
			addInputMethodEventJTextField("VisNotes_"+i, dossier, componentMap);
		}	
	}
	
	// TODO : besoin de mega refactoring ici!
	private boolean addInputMethodEventJTextField(final String nomComp, final Dossier dossier, HashMap<String,Component> componentMap) {
		final Component comp = getComponentByName(nomComp, componentMap);
		final String valeur = ((JTextField)comp).getText().toString();
		final JTextField textField;
		if(comp != null) {
			textField = ((JTextField)comp);
			// Listen for changes in the text https://stackoverflow.com/questions/3953208/value-change-listener-to-jtextfield
				textField.addFocusListener(new FocusListener() {
			    public void focusGained(FocusEvent e) {
			    };
			    public void focusLost(FocusEvent e) {
			    	  EnregistrerVisite(valeur);
			    }
				private void EnregistrerVisite(String valeur) { 
					 switch(nomComp)
						{
						case "NomPrenomPat": 
							// TODO : a faire comme il faut
							dossier.setNom(valeur);
						
						break;
						case "DateNaiss" :
							dossier.setDateDeNaissance(valeur);
						break;
						case "Genre": 
							dossier.setGenre(GENRE.valueOf(valeur));
						break;
						case "Pere": dossier.setPere(valeur);
						break;
						case "Mere": dossier.setMere(valeur);;
						break;
						case "Ville": dossier.setVilleNaissance(valeur);
						break;
						case "Coor": 
							Coordonnees maCoor = new Coordonnees();
							// TODO : à faire au complet ici
							Adresse a = new Adresse("", "", "", valeur, "Québec", "");
							maCoor.setAdresse(a);
							maCoor.setCourriel(""); // TODO : 
							maCoor.setTelephone(""); //TODO : 
							dossier.setCoor(maCoor);
						break;
						case "NoRamq": // TODO : je ne sais pas si nécessaire
						break;
					}	
					
			
					
					// Partie Antecedent
					if(nomComp.contains("AntDiagnostique_")) {
						String no  = nomComp.substring((nomComp.lastIndexOf("_") + 1));
						int indice = Integer.parseInt(no.replaceAll("[\\D]", "")); //https://stackoverflow.com/questions/2338790/get-int-from-string-also-containing-letters-in-java
						Antecedent a = dossier.getAntecedents()[indice];
							a.setDiagnostique(valeur);
							dossier.setAntecedents(a, indice);
					}
					else if (nomComp.contains("AntTraitement_")) {
						String no  = nomComp.substring((nomComp.lastIndexOf("_") + 1));
						int indice = Integer.parseInt(no.replaceAll("[\\D]", "")); //https://stackoverflow.com/questions/2338790/get-int-from-string-also-containing-letters-in-java
							Antecedent a = dossier.getAntecedents()[indice];
							a.setTraitement(valeur);
							dossier.setAntecedents(a, indice);
					}
					else if (nomComp.contains("AntMedecinTraitant_")) {
						String no  = nomComp.substring((nomComp.lastIndexOf("_") + 1));
						int indice = Integer.parseInt(no.replaceAll("[\\D]", "")); //https://stackoverflow.com/questions/2338790/get-int-from-string-also-containing-letters-in-java
							Antecedent a = dossier.getAntecedents()[indice];
							Personne p = new Personne();
							p.setNom(valeur); // TODO : le prénom
							a.setMedecinTraitant(p);
							dossier.setAntecedents(a, indice);
					}
					else if(nomComp.contains("AntDebutMaladie_")) {
						String no  = nomComp.substring((nomComp.lastIndexOf("_") + 1));
						int indice = Integer.parseInt(no.replaceAll("[\\D]", "")); //https://stackoverflow.com/questions/2338790/get-int-from-string-also-containing-letters-in-java
						
							Antecedent a = dossier.getAntecedents()[indice];
							a.setDebutMaladie(valeur);
							dossier.setAntecedents(a, indice);
					}
					else if(nomComp.contains("AntFinMaladie_")) {
						String no  = nomComp.substring((nomComp.lastIndexOf("_") + 1));
						int indice = Integer.parseInt(no.replaceAll("[\\D]", "")); //https://stackoverflow.com/questions/2338790/get-int-from-string-also-containing-letters-in-java
						Antecedent a = dossier.getAntecedents()[indice];
							a.setFinMaladie(valeur);
							dossier.setAntecedents(a, indice);
					}
						// Fin partie Antecedent
			    		
			    		// Partie visite
					else if (nomComp.contains("VisEtablissementVisite_")) {
						String no  = nomComp.substring((nomComp.lastIndexOf("_") + 1));
						int indice = Integer.parseInt(no.replaceAll("[\\D]", "")); //https://stackoverflow.com/questions/2338790/get-int-from-string-also-containing-letters-in-java
						Visite a = dossier.getVisites()[indice];
						
						Etablissement e = new Etablissement(valeur, new Adresse()); // TODO : pour l'adresse
							a.setEtablissement(e);
							dossier.setVisite(a, indice);
					}
					else if (nomComp.contains("VisMedecinVu_")) {
						String no  = nomComp.substring((nomComp.lastIndexOf("_") + 1));
						int indice = Integer.parseInt(no.replaceAll("[\\D]", "")); //https://stackoverflow.com/questions/2338790/get-int-from-string-also-containing-letters-in-java
							Visite a = dossier.getVisites()[indice];
							Personne pers = new Personne();
							pers.setNom(valeur); // TODO : le prenom du medecin
							a.setMedecinVu(pers);
							dossier.setVisite(a, indice);
					}
					else if (nomComp.contains("VisDateVisite_")) {
						String no  = nomComp.substring((nomComp.lastIndexOf("_") + 1));
						int indice = Integer.parseInt(no.replaceAll("[\\D]", "")); //https://stackoverflow.com/questions/2338790/get-int-from-string-also-containing-letters-in-java
							Visite a = dossier.getVisites()[indice];
							a.setDate(valeur);
							dossier.setVisite(a, indice);
					}
					else if (nomComp.contains("VisDiagnostique_")) {
						String no  = nomComp.substring((nomComp.lastIndexOf("_") + 1));
						int indice = Integer.parseInt(no.replaceAll("[\\D]", "")); //https://stackoverflow.com/questions/2338790/get-int-from-string-also-containing-letters-in-java	
						Visite a = dossier.getVisites()[indice];
							a.setDiagnostique(valeur);
							dossier.setVisite(a, indice);
					}
					else if (nomComp.contains("VisTraitement_")) {
						String no  = nomComp.substring((nomComp.lastIndexOf("_") + 1));
						int indice = Integer.parseInt(no.replaceAll("[\\D]", "")); //https://stackoverflow.com/questions/2338790/get-int-from-string-also-containing-letters-in-java
							Visite a = dossier.getVisites()[indice];
							a.setTraitement(valeur);
							dossier.setVisite(a, indice);
					}
					else if (nomComp.contains("VisResume_")) {
						String no  = nomComp.substring((nomComp.lastIndexOf("_") + 1));
						int indice = Integer.parseInt(no.replaceAll("[\\D]", "")); //https://stackoverflow.com/questions/2338790/get-int-from-string-also-containing-letters-in-java
							Visite a = dossier.getVisites()[indice];
							a.setResume(valeur);
							dossier.setVisite(a, indice);
					}
					else if (nomComp.contains("VisNotes_")) {
						String no  = nomComp.substring((nomComp.lastIndexOf("_") + 1));
						int indice = Integer.parseInt(no.replaceAll("[\\D]", "")); //https://stackoverflow.com/questions/2338790/get-int-from-string-also-containing-letters-in-java
							Visite a = dossier.getVisites()[indice];
							a.setNotes(valeur);
							dossier.setVisite(a, indice);
					}
						// Fin partie visite
					
					 //dossier.setAntecedents(ant);
				}
			    
			    });
			return true;
		}	
		
		return false;
	}

	private HashMap<String,Component> createComponentMap(JComponent comp) {
		HashMap<String,Component> componentMap = new HashMap<String,Component>();
        Component[] components = comp.getComponents();
        for (int i=0; i < components.length; i++) {
                componentMap.put(components[i].getName(), components[i]);
        }
		return componentMap;
	}

	public Component getComponentByName(String name, HashMap<String, Component> componentMap) {
        if (componentMap.containsKey(name)) {
                return (Component) componentMap.get(name);
        }
        else return null;
	}

}
