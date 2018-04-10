package edu.uqam.inf5153.tp3.application;

import javax.swing.JPanel;

public class ControleurDeGuiApp {
	static public SessionFrm window;

	public ControleurDeGuiApp(SessionFrm sessionFrm){
		window = sessionFrm;
	}
	
	/**
	 * Permet d'afficher n'importe quel panneau dans le panneau principal.
	 * */
	public void AfficherPanneau(JPanel jp) throws Exception {
		JPanel panel = jp;
		window.frmDossierMdicalCentralis.setContentPane(panel);
		window.frmDossierMdicalCentralis.revalidate();
	}

	
}
