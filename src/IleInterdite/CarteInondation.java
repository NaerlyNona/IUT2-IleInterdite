/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import IleInterdite.Tuile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author chenavje
 */
public class CarteInondation {
    private Tuile tuile;
    private int niveau;
    

    CarteInondation(Tuile uneTuile) {
      setTuile(uneTuile);
    }
    
 
    public Tuile getTuile() {
        return tuile;
    }

    public void setTuile(Tuile tuile) {
        this.tuile = tuile;
    }
    
    
    public void getInonde(Tuile tuile){
        if (tuile.getEtat()==Utils.EtatTuile.ASSECHEE){
            tuile.setEtat(Utils.EtatTuile.INONDEE);
           
        } else if (tuile.getEtat()==Utils.EtatTuile.INONDEE) {
            tuile.setEtat(Utils.EtatTuile.COULEE);
        } 
    }
    
    public void Inonder(int niveau, ArrayList<CarteInondation> cartes ){
        while (niveau != 0){
            int indiceAuHasard = (int) (Math.random() * (cartes.size() - 1));
            cartes.get(indiceAuHasard).getInonde(tuile);
            niveau = niveau-1;
        }
    }
    
    public String getNom(){
        return tuile.getNom();
    }
    
    public void melanger(ArrayList<CarteInondation> cartes){
        Collections.shuffle(cartes);
        
    }
    
    
    
}
