/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Roles;

import IleInterdite.Aventurier;
import IleInterdite.Carte;
import IleInterdite.Controleur;
import IleInterdite.Utils;
import java.util.ArrayList;

/**
 *
 * @author monnetlu
 */
public class Messager extends Aventurier {

    private String nomRole;

    public Messager(String leNomJoueur) {
        super(leNomJoueur, Utils.Pion.ORANGE);
        setNomRole("Messager");
        setIconPath("/img/resources/logo/RoleTable_Icon_Messenger@2x.png");
        super.setLabelIcone(createImageIcon(getIconPath(), getNomRole()));
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
    public ArrayList<String> donnerPossible(Controleur controleur) {
        ArrayList<String> listeJoueur = new ArrayList();
        listeJoueur.clear();
        if (isPouvoirPossible()) {
            for (Aventurier a : controleur.getLesAventuriers()) {

                if (a != this) {
                    listeJoueur.add(a.getNomJoueur());

                }

            }

        } else {
            for (Aventurier a : controleur.getLesAventuriers()) {

                if (a.getX() == getX() && a.getY() == getY() && (a != this)) {
                    listeJoueur.add(a.getNomJoueur());

                }
            }

        }
        return listeJoueur;
    }

    @Override
    public void donnerC(Aventurier aventurier, Carte carte) {
        setPA(getPA()-1);
        System.out.println("La carte " + carte.getNomCarte() + " a été donné a " + aventurier.getNomJoueur());
        aventurier.ajouterMain(carte);
        this.removeMain(carte);
        System.out.println(aventurier.getX());
        System.out.println("TEST OUI: "+((aventurier.getX()!=getX()) || (aventurier.getY()!=getY())));
        if ((aventurier.getX()!=getX()) || (aventurier.getY()!=getY())){
            setPouvoirPossible(false);
        }

    }
}
