package edu.uqam.inf5153.tp3.application;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import edu.uqam.inf5153.tp3.application.SessionFrm;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PnlSession extends JPanel {

	
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public PnlSession() {
		setLayout(null);
		
		JLabel label = new JLabel("Utilisateur :");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(85, 72, 72, 17);
		add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(156, 70, 246, 20);
		add(textField);
		
		JLabel label_1 = new JLabel("Mot de passe :");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(69, 116, 88, 17);
		add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(156, 114, 246, 20);
		add(passwordField);
		
		JButton button = new JButton("Entrer");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionFrm session = new SessionFrm();
				if(textField.getText().replaceAll("\\s+","").isEmpty() || passwordField.getPassword().length == 0) {
					String message = "Veuillez entrer votre code d'utilisateur et votre mot de passe.";
					session.getControleurGui();
					JOptionPane.showMessageDialog(ControleurDeGuiApp.window.frmDossierMdicalCentralis, message, "Session", JOptionPane.WARNING_MESSAGE);
				}
				else {
					
					try {
						session.getControleurGui().AfficherPanneau(new PnlNoDossier());
					}
					catch (Exception ex) {
						  System.err.println("Exception: " + ex.getMessage());
					}
				}
					
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBounds(156, 168, 89, 23);
		add(button);

	}

}
