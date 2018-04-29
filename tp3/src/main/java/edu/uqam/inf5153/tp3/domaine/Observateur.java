package edu.uqam.inf5153.tp3.domaine;

import java.sql.SQLException;

import org.json.simple.parser.ParseException;

/*
 * Design pattern Observer
 * inspiré de : https://www.tutorialspoint.com/design_pattern/observer_pattern.htm
 * 
 * */
public abstract class Observateur {
     protected Dossier dossierObs;
     protected void setSujetObserve(Dossier dossierObs){
         this.dossierObs = dossierObs;
     };
     public abstract void update(Dossier dossier)  throws ClassNotFoundException, ParseException, SQLException;
}
