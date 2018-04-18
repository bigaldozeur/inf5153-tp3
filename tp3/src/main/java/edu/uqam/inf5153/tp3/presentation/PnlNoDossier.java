package edu.uqam.inf5153.tp3.presentation;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class PnlNoDossier extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textNoRamq;

	/**
	 * Create the panel.
	 */
	public PnlNoDossier() {
		setLayout(null);
		
		JLabel lblDossierMdical = new JLabel("Dossier médical");
		lblDossierMdical.setBounds(50, 83, 146, 14);
		add(lblDossierMdical);
		
		// Fait la même chose que le bouton Entrer et permet de valider les champs lorsqu'on clique sur le clavier Enter.
		KeyAdapter lKeyChk = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					verifierNoRamq();
				}
			}
		};
		
		textNoRamq = new JTextField();
		textNoRamq.addKeyListener(lKeyChk);
		textNoRamq.setBounds(157, 80, 211, 20);
		textNoRamq.setColumns(10);
		add(textNoRamq);
		
		
		JButton btnOk = new JButton("OK");
		btnOk.addKeyListener(lKeyChk);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verifierNoRamq();
			}
		});
		btnOk.setBounds(378, 79, 89, 23);
		add(btnOk);

	}
	
	void verifierNoRamq() {
			try {
				if(SessionFrm.controleurGui.index(textNoRamq.getText())) {
					SessionFrm.mainWindow.frmDossierMdicalCentralis.setContentPane(new PnlDossier(textNoRamq.getText()));
					SessionFrm.mainWindow.frmDossierMdicalCentralis.revalidate();
				}
				else {
					JOptionPane.showMessageDialog(SessionFrm.mainWindow.frmDossierMdicalCentralis,"Erreur, le numéro d'assurance maladie entré n'existe pas ou un problème est survenu. Veuillez recommencer.","Numéro d'assurance maladie",JOptionPane.ERROR_MESSAGE);
				}
					
			}
			catch (Exception ex) {
				JOptionPane.showMessageDialog(SessionFrm.mainWindow.frmDossierMdicalCentralis,"Erreur, un problème est survenu. Veuillez recommencer.","Numéro d'assurance maladie",JOptionPane.ERROR_MESSAGE);
				  System.err.println("Exception: " + ex.getMessage());
			}

	}

}
