package edu.uqam.inf5153.tp3.presentation;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	public PnlNoDossier(final String user) {
		setLayout(null);
		
		JLabel lblDossierMdical = new JLabel("Dossier médical");
		lblDossierMdical.setBounds(50, 83, 146, 14);
		add(lblDossierMdical);
		
		// Fait la même chose que le bouton Entrer et permet de valider les champs lorsqu'on clique sur le clavier Enter.
		KeyAdapter lKeyChk = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					getPnlDossier(user);
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
				getPnlDossier(user);
			}
		});
		btnOk.setBounds(378, 79, 89, 23);
		add(btnOk);

	}
	
	void getPnlDossier(String user) {
			try {
				if(SessionFrm.getControleurGui().existe(textNoRamq.getText())) {
					PnlDossier pnlDossier = new PnlDossier(textNoRamq.getText(), user);
					
					JScrollPane sp = new JScrollPane(pnlDossier);
					sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					sp.setBounds(50, 30, 400, 100);
					
					SessionFrm.mainWindow.frmDossierMdicalCentralis.add(sp);
					
					SessionFrm.mainWindow.frmDossierMdicalCentralis.setContentPane(sp);
					//SessionFrm.mainWindow.frmDossierMdicalCentralis.revalidate();
					SessionFrm.mainWindow.frmDossierMdicalCentralis.setVisible(true);
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
