package edu.uqam.inf5153.tp3.presentation;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PnlSession extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPasswordField passwordField;

	
	/**
	 * Create the panel.
	 * @param aidePanneau 
	 */
	public PnlSession() {
		
		setLayout(null);
		
		JLabel label = new JLabel("Utilisateur :");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(85, 72, 72, 17);
		add(label);
		
		// Fait la même chose que le bouton Entrer et permet de valider les champs lorsqu'on clique sur le clavier Enter.
		KeyAdapter lKeyChk = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(verifierChampsSession() == true){
						accepterUtilisateur();
					}
				}
			}
		};
		
		textField = new JTextField();
		textField.addKeyListener(lKeyChk);
		textField.setColumns(10);
		textField.setBounds(156, 70, 246, 20);
		add(textField);
		
		JLabel label_1 = new JLabel("Mot de passe :");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(69, 116, 88, 17);
		add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(lKeyChk);
		passwordField.setBounds(156, 114, 246, 20);
		add(passwordField);
		
		JButton button = new JButton("Entrer");
		button.addKeyListener(lKeyChk);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(verifierChampsSession() == true){
					accepterUtilisateur();
				}
			}

		});
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBounds(156, 168, 89, 23);
		add(button);

	}
	
	/**
	 * Permet de vérifier que les champs utilisateurs et mot de passe ne soient pas vides.
	 * */
	private boolean verifierChampsSession() {
		if(textField.getText().replaceAll("\\s+","").isEmpty() || passwordField.getPassword().length == 0) {
			String message = "Veuillez entrer votre code d'utilisateur et votre mot de passe.";
			JOptionPane.showMessageDialog(SessionFrm.mainWindow.frmDossierMdicalCentralis, message, "Session", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else
			return true;
	}
	
	private void accepterUtilisateur() {
		try {
			
				SessionFrm.controleurGui.creerVerifierSession(textField.getText(), passwordField.getPassword());
				if(SessionFrm.controleurGui.verifierAuthentifier(textField.getText(), passwordField.getPassword())) {
						SessionFrm.mainWindow.frmDossierMdicalCentralis.setContentPane(new PnlNoDossier());
						SessionFrm.mainWindow.frmDossierMdicalCentralis.revalidate();
				}
				else{
						JOptionPane.showMessageDialog(SessionFrm.mainWindow.frmDossierMdicalCentralis,"Erreur Authenfication","Authentification",JOptionPane.ERROR_MESSAGE);
				}
			
		}
		catch (Exception ex) {
			  System.err.println("Exception: " + ex.getMessage());
		}

	}


}
