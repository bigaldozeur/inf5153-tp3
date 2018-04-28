package edu.uqam.inf5153.tp3.presentation;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import edu.uqam.inf5153.tp3.application.ControleurDeGuiApp;
import edu.uqam.inf5153.tp3.domaine.Antecedent;
import edu.uqam.inf5153.tp3.domaine.Dossier;
import edu.uqam.inf5153.tp3.domaine.Visite;
import edu.uqam.inf5153.tp3.servicesTechniques.ControlleurDeBd;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Rectangle;

public class PnlDossier extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Create the panel.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public PnlDossier() throws ClassNotFoundException, SQLException {
		setBounds(new Rectangle(0, 0, 750, 800));
	    init("abc1234");		      	        	
	}
	
	public PnlDossier(String noRAMQ) throws ClassNotFoundException, SQLException {
	    init(noRAMQ);	
	}
	        
	private void init(final String noRAMQ) throws SQLException, ClassNotFoundException {
		
		setLayout(null);
		setBounds(new Rectangle(0, 0, 750, 800));
		Dimension a = new Dimension();
		a.height = 800;
		a.width = 750;
		this.setPreferredSize(a);
		JLabel lblDossierMdical = new JLabel("Dossier médical du patient");
		lblDossierMdical.setBounds(50, 83, 246, 14);
		add(lblDossierMdical);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionFrm.mainWindow.frmDossierMdicalCentralis.setContentPane(new PnlNoDossier());
				SessionFrm.mainWindow.frmDossierMdicalCentralis.revalidate();
			}
		});
		btnRetour.setBounds(658, 11, 89, 23);
		add(btnRetour);
		   
		
		// TODO aller le chercher le dossier dans gestionDossier va retourner un dossier
		
		
	
		// TODO : il doit y avoir encore moyen d'améliorer ici!
		// // It creates and displays the table ref: https://stackoverflow.com/questions/10620448/most-simple-code-to-populate-jtable-from-resultset
	    // JTable table = new JTable(buildTableModel(rs));
	    // JOptionPane.showMessageDialog(null, new JScrollPane(table));
        	
        	int posX = 120;
        	int bond = 20;
        	
        	final Dossier dossier = ControleurDeGuiApp.getDossier(noRAMQ);//ControleurDeGuiApp.create(rs.getString("dossier"));
        	     	
        	posX = AidePanneau.getInstance().AjouterEntreePanneau("Nom et prénom : ", dossier.getNom() + " " + dossier.getPrenom(), posX, this);     
        	posX = AidePanneau.getInstance().AjouterEntreePanneau("Date de naissance : ", dossier.getDateDeNaissance(), posX, this);     
        	posX = AidePanneau.getInstance().AjouterEntreePanneau("Genre : ", dossier.getGenre(), posX, this);
        	posX = AidePanneau.getInstance().AjouterEntreePanneau("Parents connus : ", "", false, posX, 20, this);
        	posX = AidePanneau.getInstance().AjouterEntreePanneau("Père : ", dossier.getPere(), posX, this);
        	posX = AidePanneau.getInstance().AjouterEntreePanneau("Mère : ", dossier.getMere(), posX, this); 	
        	posX = AidePanneau.getInstance().AjouterEntreePanneau("Ville de naissance : ", dossier.getVilleNaissance(), posX, this);
        	posX = AidePanneau.getInstance().AjouterEntreePanneau("Coordonnées : ", dossier.getCoordonnees(), posX, this); 	
        	posX = AidePanneau.getInstance().AjouterEntreePanneau("Numéro d'assurance maladie : ", noRAMQ, posX, this);
        	
        	posX += bond;
        	// Obtenir la liste des antécédents médicaux
        	Antecedent[] antecedents;
        	antecedents = dossier.getAntecedents();
   	
        	posX = AidePanneau.getInstance().AjouterEntreePanneau("Antecedents médicaux : " + (antecedents.length >0?"":"aucun"), "", false, posX, 20, this);
        	
        	// Afficher la liste des antecedents medicaux.
        	for(int i = 0; i<antecedents.length; i++)
        	{
        		posX = AidePanneau.getInstance().AjouterEntreePanneau("Diagnostique : ", antecedents[i].getDiagnostique(), posX, this);
        		posX = AidePanneau.getInstance().AjouterEntreePanneau("Traitement : ", antecedents[i].getTraitement(), posX, this);
        		posX = AidePanneau.getInstance().AjouterEntreePanneau("Médecin traitant : ", antecedents[i].getMedecinTraitant().toString(), posX, this);
        		posX = AidePanneau.getInstance().AjouterEntreePanneau("Debut de la maladie : ", antecedents[i].getDebutMaladie(), posX, this);
        		posX = AidePanneau.getInstance().AjouterEntreePanneau("Fin de la maladie : ", antecedents[i].getFinMaladie(), posX, this);
        	}
        	
        	
        	posX += bond;
        	
        	Visite[] visites;
        	visites = dossier.getVisites();
        	
        	posX = AidePanneau.getInstance().AjouterEntreePanneau("Visites : " + (visites.length >0?"":"aucune"), "", false, posX, 20, this);
        	JButton btnAddVisite = new JButton("Ajouter une visite");
    		btnAddVisite.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				SessionFrm.mainWindow.frmDossierMdicalCentralis.setContentPane(new PnlVisite(noRAMQ, dossier));
    				SessionFrm.mainWindow.frmDossierMdicalCentralis.revalidate();
    			}
    		});
    		btnAddVisite.setBounds(658, posX-bond, 189, 23);
    		add(btnAddVisite);
    		
        	// Afficher la liste des visites.
        	for(int i = 0; i<visites.length; i++)
        	{
        		posX = AidePanneau.getInstance().AjouterEntreePanneau("Établissement visité : ", visites[i].getEtablissement().getNom(), posX, this);
        		posX = AidePanneau.getInstance().AjouterEntreePanneau("Médecin vu : ", visites[i].getMedecinVu().toString(), posX, this);
        		posX = AidePanneau.getInstance().AjouterEntreePanneau("Date de la visite: ", visites[i].getDate(), posX, this);		
        		posX = AidePanneau.getInstance().AjouterEntreePanneau("Diagnostique établi, si applicable : ", visites[i].getDiagnostique(), posX, this);
        		posX = AidePanneau.getInstance().AjouterEntreePanneau("Traitement, si applicable : ", visites[i].getTraitement(), posX, this);
        		posX = AidePanneau.getInstance().AjouterEntreePanneau("Résumé de la visite : ", visites[i].getResume(), posX, this);
        		posX = AidePanneau.getInstance().AjouterEntreePanneau("Notes pour les autres médecins : ", visites[i].getNotes(), posX, this);
        	}
        	
        	posX += bond;
        	
        	posX = AidePanneau.getInstance().AjouterEntreePanneau("maladie : ", dossier.getMaladie(), posX, this); // TODO : à enlever, en attendant
        	posX = AidePanneau.getInstance().AjouterEntreePanneau("medecin : ", dossier.getMedecin(), posX, this); // TODO : à enlever, en attendant
        
	}
	
	
	

	// TODO : Inutilisé pour l'instant. À supprimer
	// ref : https://stackoverflow.com/questions/10620448/most-simple-code-to-populate-jtable-from-resultset
	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}
}
