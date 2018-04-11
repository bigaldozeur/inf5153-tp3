package edu.uqam.inf5153.tp3.application;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		//textNoRamq.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionFrm session = new SessionFrm();
				try {
					session.getControleurGui().AfficherPanneau(new PnlDossier(), textNoRamq);
				}
				catch (Exception ex) {
					  System.err.println("Exception: " + ex.getMessage());
				}

			}
		});
		btnOk.setBounds(378, 79, 89, 23);
		add(btnOk);

	}

}
