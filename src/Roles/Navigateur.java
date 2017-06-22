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
public class Navigateur extends Aventurier{
    private String nomRole;
    
    public Navigateur(String leNomJoueur) {
        super(leNomJoueur, Utils.Pion.JAUNE);
        setNomRole("Navigateur");
        setMaxPA(4);
        setPA(getMaxPA());
        setIconPath("/img/resources/adventurer/Player_Card_Navigator_Icon@2x.png");
        super.setIcone(createImageIcon(getIconPath(), getNomRole()));
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
    
    @Override
    public void reset(){
        setPA(4);
    }
    
}
