package edu.uqam.inf5153.tp3.presentation;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.List;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;

import edu.uqam.inf5153.tp3.application.ControleurDeGuiApp;
import edu.uqam.inf5153.tp3.domaine.Dossier;
import edu.uqam.inf5153.tp3.servicesTechniques.ControlleurDeBd;

public class PnlDossier extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String noRamq = "0";

	/**
	 * Create the panel.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public PnlDossier() throws ClassNotFoundException, SQLException {
	    init("abc1234");		      	        	
	}
	
	public PnlDossier(String noRAMQ) throws ClassNotFoundException, SQLException {
	    init(noRAMQ);		      	        	
	}
	        
	private void init(String noRAMQ) throws SQLException, ClassNotFoundException {
		setLayout(null);
		
		JLabel lblDossierMdical = new JLabel("Dossier médical du patient");
		lblDossierMdical.setBounds(50, 83, 246, 14);
		add(lblDossierMdical);
		
		ResultSet rs;
		rs = ControlleurDeBd.consulterDossier(noRAMQ);
		
	
		// On pourrait utiliser les meta, mais il doit y avoir moyen d'améliorer ici
		// // It creates and displays the table ref: https://stackoverflow.com/questions/10620448/most-simple-code-to-populate-jtable-from-resultset
	    //JTable table = new JTable(buildTableModel(rs));
	    //JOptionPane.showMessageDialog(null, new JScrollPane(table));
        while(rs.next()){
        	JLabel id = new JLabel("id de la bd : ");
        	JTextField textId = new JTextField(rs.getString("id"));
        	textId.setEnabled(false);
        	id.setBounds(50, 100, 100, 14);
        	textId.setBounds(180, 100, 200, 16);
        	add(id);
        	add(textId);
        	
        	JLabel noRamq = new JLabel("Ass Maladie : ");
        	JTextField textNoRamq = new JTextField(rs.getString("numAssuranceMaladie"));
        	noRamq.setBounds(50, 120, 100, 14);
        	textNoRamq.setBounds(180, 120, 200, 16);
        	add(noRamq);
        	add(textNoRamq);
        	
        	Dossier dossier = ControleurDeGuiApp.Create(rs.getString("dossier"));
        	
        	JLabel mal = new JLabel("maladie : ");
        	JTextField textMal = new JTextField(dossier.getMaladie());
        	mal.setBounds(50, 140, 100, 14);
        	textMal.setBounds(180, 140, 200, 16);
        	add(mal);
        	add(textMal);
        	
        	JLabel med = new JLabel("medecin : ");
        	JTextField textMed = new JTextField(dossier.getMedecin());
        	med.setBounds(50, 160, 100, 14);
        	textMed.setBounds(180, 160, 200, 16);
        	add(med);
        	add(textMed);
        
        	JLabel medTrait = new JLabel("medecin traitant : ");
        	JTextField textMedTrait = new JTextField(rs.getString("medecin"));
        	medTrait.setBounds(50, 180, 120, 14);
        	textMedTrait.setBounds(180, 180, 200, 16);
        	add(medTrait);
        	add(textMedTrait);
        	
          	JLabel di = new JLabel("Insere le : ");
        	JTextField textDi = new JTextField(rs.getString("dateInsertion"));
        	di.setBounds(50, 200, 100, 14);
        	textDi.setBounds(180, 200, 200, 16);
        	add(di);
        	add(textDi);
	
        }
	}

	// Inutilisé pour l'instant.
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
