/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

/**
 *
 * @author monnetlu
 */
public class Plongeur extends Aventurier{
    private String nomRole;
    
    public Plongeur(String leNomJoueur) {
        super(leNomJoueur, Utils.Pion.VIOLET);
        setNomRole("Plongeur");
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
