/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import IleInterdite.Utils.Pion;

/**
 *
 * @author monnetlu
 */
public class TuileSpawn extends Tuile{
    
    private Pion spawnAventurier;
    
    public TuileSpawn(String nom, Pion spawnAventurier) {
        super(nom);
        super.setType(2);
        setSpawnAventurier(spawnAventurier);
    }

    /**
     * @return the spawnAventurier
     */
    
    public Pion getSpawnAventurier() {
        return spawnAventurier;
    }

    /**
     * @param spawnAventurier the spawnAventurier to set
     */
    public void setSpawnAventurier(Pion spawnAventurier) {
        this.spawnAventurier = spawnAventurier;
    }
    
    
    
}
