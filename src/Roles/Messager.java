/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Roles;

import IleInterdite.Aventurier;
import IleInterdite.Utils;

/**
 *
 * @author monnetlu
 */
public class Messager extends Aventurier{
    private String nomRole;
    
    public Messager(String leNomJoueur) {
        super(leNomJoueur, Utils.Pion.ORANGE);
        setNomRole("Messager");
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
    
}
