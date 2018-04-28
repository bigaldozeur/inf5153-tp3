package edu.uqam.inf5153.tp3.domaine;
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
     public abstract void update();
}