/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import java.util.HashMap;

/**
 *
 * @author monnetlu
 */
public class Grille {

    //private HashMap<Position,Tuile> lesTuiles;
    private Tuile[][] lesTuiles;

    public Grille() {
        lesTuiles = new Tuile[6][6];
    }

    public void addTuile(int x, int y, Tuile laTuile){
        lesTuiles[x][y] = laTuile;
    }
    
    public Tuile getTuile(int x, int y){
        return lesTuiles[x][y];
    }
    
    public Tuile[][] getLesTuiles(){
        return lesTuiles;
    }
}
