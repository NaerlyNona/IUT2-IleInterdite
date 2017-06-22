/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Roles;

import IleInterdite.Aventurier;
import IleInterdite.Grille;
import IleInterdite.Utils;
import java.util.ArrayList;

/**
 *
 * @author monnetlu
 */
public class Ingenieur extends Aventurier {

    private String nomRole;

    public Ingenieur(String leNomJoueur) {
        super(leNomJoueur, Utils.Pion.ROUGE);
        setNomRole("Ingenieur");
        setIconPath("/img/resources/adventurer/Player_Card_Engineer_Icon@2x.png");
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
    public void Assécher(Grille laGrille, String laPosition) {

        ArrayList<Integer> AssechementPossible = AssechementPossible(laGrille);

        int x = Character.getNumericValue(laPosition.charAt(0));
        int y = Character.getNumericValue(laPosition.charAt(1));
        
        for (int unAssechementPossible : AssechementPossible) {
            if (unAssechementPossible == (Integer.valueOf(String.valueOf(x) + String.valueOf(y)))) {
                if (isPouvoirPossible()) {
                    laGrille.getTuile(x, y).setEtat(Utils.EtatTuile.ASSECHEE);
                    setPouvoirPossible(false);
                } else {
                    laGrille.getTuile(x, y).setEtat(Utils.EtatTuile.ASSECHEE);
                    setPA(getPA() - 1);
                    setPouvoirPossible(true);
                }
            }
        }
    }

    @Override
    public void SeDeplacer(Grille laGrille, String laPosition) {

        ArrayList<Integer> DeplacementPossible = DeplacementPossible(laGrille);

        int x = Character.getNumericValue(laPosition.charAt(0));
        int y = Character.getNumericValue(laPosition.charAt(1));

        for (int unDeplacementPossible : DeplacementPossible) {

            if (unDeplacementPossible == (Integer.valueOf(String.valueOf(x) + String.valueOf(y)))) {
                this.setPosition(x, y);
                setPA(getPA() - 1);
                setPouvoirPossible(false);
                break;
            }
        }

    }

}
