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
import java.util.Scanner;
import javax.swing.JTextField;

/**
 *
 * @author monnetlu
 */
public class Pilote extends Aventurier {

    private String nomRole;

    public Pilote(String leNomJoueur) {
        super(leNomJoueur, Utils.Pion.BLEU);
        setNomRole("Pilote");
        setPouvoirPossible(true);
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

    public ArrayList<Integer> DeplacementPossiblePouvoir(Grille laGrille) {
        ArrayList<Integer> lesDeplacements = new ArrayList();
        lesDeplacements.clear();

        for (int x = 0; x <= 5; x++) {
            for (int y = 0; y <= 5; y++) {
                if (laGrille.getTuile(x, y) != null) {
                    if (laGrille.getTuile(x, y).getEtat() != Utils.EtatTuile.COULEE) {
                        lesDeplacements.add(Integer.valueOf(String.valueOf(x) + String.valueOf(y)));
                    }
                }
            }
        }
        
        for (int uneTuile : (ArrayList<Integer>)lesDeplacements.clone()) {
            if ((Utils.getChiffre(uneTuile, 2) == getX()) && (Utils.getChiffre(uneTuile, 1) == getY())) {
                lesDeplacements.remove( ((Object)(uneTuile)) );
            }
        }

        return lesDeplacements;
    }

    @Override
    public void SeDeplacer(Grille laGrille, String laPosition) {

        ArrayList<Integer> DeplacementPossible = DeplacementPossible(laGrille);
        ArrayList<Integer> DeplacementPossiblePouvoir = DeplacementPossiblePouvoir(laGrille);

        int x = Character.getNumericValue(laPosition.charAt(0));
        int y = Character.getNumericValue(laPosition.charAt(1));

        boolean deplacementFait = false;

        for (int unDeplacementPossible : DeplacementPossible) {

            if (unDeplacementPossible == (Integer.valueOf(String.valueOf(x) + String.valueOf(y)))) {
                this.setPosition(x, y);
                setPA(getPA() - 1);
                deplacementFait = true;
                break;
            }
        }

        if (isPouvoirPossible() && !deplacementFait) {
            for (int unDeplacementPossiblePouvoir : DeplacementPossiblePouvoir) {

                if (unDeplacementPossiblePouvoir == (Integer.valueOf(String.valueOf(x) + String.valueOf(y)))) {
                    this.setPosition(x, y);
                    setPouvoirPossible(false);
                    setPA(getPA() - 1);
                    break;
                }
            }
        }

    }

    @Override
    public void reset() {
        super.reset();
        setPouvoirPossible(true);
    }
}
