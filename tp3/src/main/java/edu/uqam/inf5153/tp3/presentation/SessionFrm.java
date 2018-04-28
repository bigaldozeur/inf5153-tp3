package edu.uqam.inf5153.tp3.presentation;

import edu.uqam.inf5153.tp3.application.ControleurDeGuiApp;

import java.awt.EventQueue;

import javax.swing.JFrame;


public class SessionFrm {

	public JFrame frmDossierMdicalCentralis;
	protected static SessionFrm mainWindow;
	private boolean initialise = false;
	protected static boolean isPersMed = true; // TODO : éventuellement deplacer dans autorisation. Le Personnel Médical ne peut pas changer les choses
	static ControleurDeGuiApp controleurGui;
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
