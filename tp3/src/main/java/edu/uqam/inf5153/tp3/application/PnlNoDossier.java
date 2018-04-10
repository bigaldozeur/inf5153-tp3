package edu.uqam.inf5153.tp3.application;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PnlNoDossier extends JPanel {
	private JTextField textNoRamq;

	/**
	 * Create the panel.
	 */
	public PnlNoDossier() {
		setLayout(null);
		
		JLabel lblDossierMdical = new JLabel("Dossier médical");
		lblDossierMdical.setBounds(50, 83, 146, 14);
		add(lblDossierMdical);
		
		textNoRamq = new JTextField();
		textNoRamq.setBounds(157, 80, 211, 20);
		add(textNoRamq);
		textNoRamq.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(378, 79, 89, 23);
		add(btnOk);

	}

}
