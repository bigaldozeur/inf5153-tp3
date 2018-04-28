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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import edu.uqam.inf5153.tp3.domaine.Dossier;
import edu.uqam.inf5153.tp3.domaine.Personne;
import edu.uqam.inf5153.tp3.domaine.Antecedent;

public class PnlAntecedent extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Antecedent ant = new Antecedent();
	private Personne per = new Personne();

	/**
	 * Create the panel.
	 */
	public PnlAntecedent() {
		init("", null, "");
	}
	
	public PnlAntecedent(String noRAMQ, Dossier dossier, String user) {
		init(noRAMQ, dossier, user);
	}

	private void init(final String noRAMQ, Dossier dossier, final String user) {
		
		setLayout(null);
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AidePanneau.getInstance().retourAuDossier(noRAMQ, user);
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
		 		
		posX = AidePanneau.getInstance().AjouterEntreePanneau("Diagnostique", "Diagnostique : ", "", posX, true, this);
		posX = AidePanneau.getInstance().AjouterEntreePanneau("Traitement","Traitement : ", "", posX, true, this);
		posX = AidePanneau.getInstance().AjouterEntreePanneau("MedecinTraitant", "Médecin traitant: ", "", posX, true,this);		
		posX = AidePanneau.getInstance().AjouterEntreePanneau("DebutMaladie","Début de la maladie : ", "", posX, true, this);
		posX = AidePanneau.getInstance().AjouterEntreePanneau("FinMaladie","Fin de la maladie : ", "", posX, true, this);
		
		Antecedent ant = new Antecedent();
		HashMap<String,Component> componentMap = createComponentMap(this); // pris exemple ici et modifié. https://stackoverflow.com/questions/4958600/get-a-swing-component-by-name
		addInputMethodEventJTextField("Diagnostique", dossier, componentMap);
		addInputMethodEventJTextField("Traitement", dossier, componentMap);
		addInputMethodEventJTextField("MedecinTraitant", dossier, componentMap);
		addInputMethodEventJTextField("DebutMaladie", dossier, componentMap);
		addInputMethodEventJTextField("FinMaladie", dossier, componentMap);
	}
	
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
						case "Diagnostique": ant.setDiagnostique(valeur);
						break;
						case "Traitement": 	ant.setTraitement(valeur);
						break;
						case "MedecinTraitant": 
							per.setNom(valeur);
							ant.setMedecinTraitant(per);
						break;
						case "DebutMaladie": ant.setDebutMaladie(valeur);
						break;
						case "FinMaladie": ant.setFinMaladie(valeur);
						break;
						default:
						}	
					 dossier.setAntecedents(ant);
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
