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
   
    

    CarteInondation(Tuile uneTuile) {
      setTuile(uneTuile);
    }
    
 
    public Tuile getTuile() {
        return tuile;
    }

    public void setTuile(Tuile tuile) {
        this.tuile = tuile;
    }
    
    
   
    
    public String getNom(){
        return tuile.getNom();
    }
    
}
