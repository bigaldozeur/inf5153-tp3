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
import java.awt.Rectangle;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class PnlSession extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPasswordField passwordField;
	private boolean personnelMedical = false;
	 	
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
		textField.setBounds(156, 70, 284, 20);
		add(textField);
		
		JLabel label_1 = new JLabel("Mot de passe :");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(69, 116, 88, 17);
		add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(lKeyChk);
		passwordField.setBounds(156, 114, 284, 20);
		add(passwordField);
		
		JButton btnEntrer = new JButton("Entrer");
		btnEntrer.addKeyListener(lKeyChk);
		btnEntrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(verifierChampsSession() == true){
					accepterUtilisateur();
				}
			}

		});
		btnEntrer.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEntrer.setBounds(156, 168, 89, 23);
		add(btnEntrer);
		
		JButton btnCreateNewUser = new JButton("Créer nouvel utilisateur");
		btnCreateNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(verifierChampsSession() == true){
					creerUtilisateur();
				}
			}
		});
		btnCreateNewUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCreateNewUser.setBounds(255, 168, 185, 23);
		add(btnCreateNewUser);
		
		JCheckBox chckbxPersoMedical = new JCheckBox("Personnel médical");
		chckbxPersoMedical.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				 
				if(arg0.getStateChange() == ItemEvent.SELECTED)
					personnelMedical = true;
				else 
					personnelMedical = false;
			}
		});
		chckbxPersoMedical.setToolTipText("Permet seulement de consulter les dossiers");
		chckbxPersoMedical.setBounds(255, 198, 132, 23);
		add(chckbxPersoMedical);

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
				StringBuilder sb = new StringBuilder();
				boolean result = SessionFrm.controleurGui.verifierSession(textField.getText(), passwordField.getPassword(), sb);
				if(result == false) {
					JOptionPane.showMessageDialog(SessionFrm.mainWindow.frmDossierMdicalCentralis,sb.toString(),"Authentification",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(SessionFrm.controleurGui.verifierAuthentifier(textField.getText(), passwordField.getPassword())) {
						boolean isPersMed = SessionFrm.controleurGui.VerifierUtilisateur(textField.getText());
						SessionFrm.mainWindow.setPersMed(isPersMed);
						SessionFrm.mainWindow.frmDossierMdicalCentralis.setContentPane(new PnlNoDossier(textField.getText()));
						SessionFrm.mainWindow.frmDossierMdicalCentralis.revalidate();
				}
				else{
						JOptionPane.showMessageDialog(SessionFrm.mainWindow.frmDossierMdicalCentralis,"Une mauvaise combinaison utilisateur et mot de passe a été entré ou une erreur s'est produit, veuillez recommencez.","Authentification",JOptionPane.ERROR_MESSAGE);
				}
			
		}
		catch (Exception ex) {
			  System.err.println("Exception: " + ex.getMessage());
		}

	}
	
	private void creerUtilisateur() {
		try {
				StringBuilder sb = new StringBuilder();
				boolean result = SessionFrm.controleurGui.creerVerifierSession(textField.getText(), passwordField.getPassword(), sb, personnelMedical);
				if(result == false) {
					JOptionPane.showMessageDialog(SessionFrm.mainWindow.frmDossierMdicalCentralis,sb.toString(),"Authentification",JOptionPane.ERROR_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(SessionFrm.mainWindow.frmDossierMdicalCentralis,sb.toString(),"Authentification",JOptionPane.INFORMATION_MESSAGE);
				
					if(SessionFrm.controleurGui.verifierAuthentifier(textField.getText(), passwordField.getPassword())) {
							SessionFrm.mainWindow.frmDossierMdicalCentralis.setContentPane(new PnlNoDossier(textField.getText()));
							SessionFrm.mainWindow.frmDossierMdicalCentralis.revalidate();
					}
					else{
							JOptionPane.showMessageDialog(SessionFrm.mainWindow.frmDossierMdicalCentralis,"Une erreur d'authenfication s'est produite suite à la création, veuillez vous authentifier de nouveau.","Authentification",JOptionPane.ERROR_MESSAGE);
					}
				}
			
		}
		catch (Exception ex) {
			  System.err.println("Exception: " + ex.getMessage());
		}

	}
}
