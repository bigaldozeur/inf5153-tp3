package edu.uqam.inf5153.tp3.presentation;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import edu.uqam.inf5153.tp3.domaine.Dossier;
import edu.uqam.inf5153.tp3.domaine.Personne;
import edu.uqam.inf5153.tp3.domaine.Visite;

import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class PnlVisite extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private Visite vis = new Visite();
	private Personne per = new Personne();

	/**
	 * Create the panel.
	 */
	public PnlVisite() {
		init("", null);
	}
	
	public PnlVisite(String noRAMQ, Dossier dossier) {
		init(noRAMQ, dossier);
	}

	private void init(final String noRAMQ, Dossier dossier) {
		
		setLayout(null);
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PnlDossier pnlDossier;
				try {
					pnlDossier = new PnlDossier(noRAMQ);
					JScrollPane sp = new JScrollPane(pnlDossier);
					sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					sp.setBounds(50, 30, 400, 100);
					
					SessionFrm.mainWindow.frmDossierMdicalCentralis.getContentPane().add(sp);
					
					SessionFrm.mainWindow.frmDossierMdicalCentralis.setContentPane(sp);
					SessionFrm.mainWindow.frmDossierMdicalCentralis.setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRetour.setBounds(658, 11, 89, 23);
		add(btnRetour);
		int posX = 120;
		int bond = 20;
		 
		posX = AidePanneau.getInstance().AjouterEntreePanneau("NomEtablissement", "Établissement : ", "Hôpital du médecin", posX, this);
		
		posX = AidePanneau.getInstance().AjouterEntreePanneau("Médecin vu: ", "", false, posX, bond, this);
		posX = AidePanneau.getInstance().AjouterEntreePanneau("NomMed", "Nom : ", "", posX, this);
		posX = AidePanneau.getInstance().AjouterEntreePanneau("PrenomMed","Prénom : ", "", posX, this);
		posX = AidePanneau.getInstance().AjouterEntreePanneau("DateVisite", "Date de la visite: ", "", posX, this);		
		posX = AidePanneau.getInstance().AjouterEntreePanneau("Diagnostique","Diagnostique établi, si applicable : ", "", posX, this);
		posX = AidePanneau.getInstance().AjouterEntreePanneau("Traitement","Traitement, si applicable : ", "", posX, this);
		posX = AidePanneau.getInstance().AjouterEntreePanneau("Resume", "Résumé de la visite : ", "", posX, this);
		posX = AidePanneau.getInstance().AjouterEntreePanneau("Notes","Notes pour les autres médecins : ", "", posX, this);
		
		Visite vis = new Visite();
		HashMap<String,Component> componentMap = createComponentMap(this); // pris exemple ici et modifié. https://stackoverflow.com/questions/4958600/get-a-swing-component-by-name
		addInputMethodEventJTextField("NomMed", dossier, componentMap);
		addInputMethodEventJTextField("PrenomMed", dossier, componentMap);
		addInputMethodEventJTextField("DateVisite", dossier, componentMap);
		addInputMethodEventJTextField("Diagnostique", dossier, componentMap);
		addInputMethodEventJTextField("Traitement", dossier, componentMap);
		addInputMethodEventJTextField("Resume", dossier, componentMap);
		addInputMethodEventJTextField("Notes", dossier, componentMap);
	}
	
	private boolean addInputMethodEventJTextField(final String nomComp, final Dossier dossier, HashMap<String,Component> componentMap) {
		final Component comp = getComponentByName(nomComp, componentMap);
		final String valeur = ((JTextField)comp).getText().toString();
		final JTextField textField;
		if(comp != null) {
			textField = ((JTextField)comp);
			// Listen for changes in the text https://stackoverflow.com/questions/3953208/value-change-listener-to-jtextfield
			textField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    modifierVisite(valeur);
			  }
			  public void removeUpdate(DocumentEvent e) {
			    modifierVisite(valeur);
			  }
			  public void insertUpdate(DocumentEvent e) {
			    modifierVisite(valeur);
			  }

			  public void modifierVisite(String valeur) {
				  switch(nomComp)
					{
					case "NomMed": 	per.setNom(valeur);
									vis.setMedecinVu(per);
									break;
					case "PrenomMed": 	per.setPrenom(valeur);
										vis.setMedecinVu(per);
					break;
					case "DateVisite": vis.setDate(valeur);
					break;
					case "Diagnostique": vis.setDiagnostique(valeur);
					break;
					case "Traitement": vis.setTraitement(valeur);
					break;
					case "Resume": vis.setResume(valeur);
					break;
					case "Notes": vis.setNotes(valeur);
					break;
					default:
					}
			     }
			});
			
			textField.addFocusListener(new FocusListener() {
			    public void focusGained(FocusEvent e) {
			    };
			    public void focusLost(FocusEvent e) {
			    	  EnregistrerVisite(valeur);
			    }
				private void EnregistrerVisite(String valeur) {
					 switch(nomComp)
						{
						case "NomMed": 	per.setNom(valeur);
										vis.setMedecinVu(per);
										// TODO : enregistrer la visite au complet dans dossier
										
										break;
						case "PrenomMed": 	per.setPrenom(valeur);
											vis.setMedecinVu(per);
						break;
						case "DateVisite": vis.setDate(valeur);
						break;
						case "Diagnostique": vis.setDiagnostique(valeur);
						break;
						case "Traitement": vis.setTraitement(valeur);
						break;
						case "Resume": vis.setResume(valeur);
						break;
						case "Notes": vis.setNotes(valeur);
						break;
						default:
						}	
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