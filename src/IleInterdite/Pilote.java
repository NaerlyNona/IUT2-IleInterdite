/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import java.util.Scanner;

/**
 *
 * @author monnetlu
 */
public class Pilote extends Aventurier{
    private String nomRole;
    
    public Pilote(String leNomJoueur) {
        super(leNomJoueur, Utils.Pion.BLEU);
        setNomRole("Pilote");
    }

    /**
     * @return the nomRole
     */
    public String getNomRole() {
        return nomRole;
    }

    /**
     * @param nomRole the nomRole to set
     */
    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }
    
    public void SeDeplacer(Grille laGrille) {
        
        
    }
    
}
