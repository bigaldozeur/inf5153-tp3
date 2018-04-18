package edu.uqam.inf5153.tp3.presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.uqam.inf5153.tp3.application.ControleurDeGuiApp;
import edu.uqam.inf5153.tp3.application.session.Session;

import java.awt.Font;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SessionFrm {

	public JFrame frmDossierMdicalCentralis;
	protected static SessionFrm mainWindow;
	private boolean initialise = false;
	public static ControleurDeGuiApp controleurGui;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {	
					mainWindow = new SessionFrm();
					mainWindow.frmDossierMdicalCentralis.setVisible(true);
					
					controleurGui = new ControleurDeGuiApp();
					
					mainWindow.frmDossierMdicalCentralis.setContentPane(new PnlSession());
					mainWindow.frmDossierMdicalCentralis.revalidate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SessionFrm() {
		if(initialise == false) {
			initialize();
			initialise = true;
		}
	}

	
	/** 
	 * Permet d'obtenir le contrôleur.
	 * */
	public ControleurDeGuiApp getControleurGui(){
		return controleurGui;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDossierMdicalCentralis = new JFrame();
		frmDossierMdicalCentralis.setTitle("Dossier médical centralisé - Session");
		frmDossierMdicalCentralis.setBounds(100, 100, 768, 347);
		frmDossierMdicalCentralis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDossierMdicalCentralis.getContentPane().setLayout(null);
	}
}
