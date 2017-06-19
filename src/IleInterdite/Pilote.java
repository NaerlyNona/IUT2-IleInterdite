/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JTextField;

/**
 *
 * @author monnetlu
 */
public class Pilote extends Aventurier {

    private String nomRole;
    private boolean pouvoirPossible;

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

    /**
     * @return the pouvoirPossible
     */
    public boolean isPouvoirPossible() {
        return pouvoirPossible;
    }

    /**
     * @param pouvoirPossible the pouvoirPossible to set
     */
    public void setPouvoirPossible(boolean pouvoirPossible) {
        this.pouvoirPossible = pouvoirPossible;
    }

    public ArrayList<Integer> DeplacementPossiblePouvoir(Grille laGrille) {
        ArrayList<Integer> lesDeplacements = new ArrayList();
        lesDeplacements.clear();
        System.out.println(laGrille.getTuile(getX(), getY()).getNom());

        for (int x = 0; x <= 5; x++) {
            for (int y = 0; y <= 5; y++) {
                if (laGrille.getTuile(x, y) != null) {
                    lesDeplacements.add(Integer.valueOf(String.valueOf(x) + String.valueOf(y)));
                }
            }
        }
        
        System.out.println("TEST");
        for (int unDeplacementPossible : lesDeplacements) {
            System.out.println(unDeplacementPossible);
        }
System.out.println("TEST");
        return lesDeplacements;
    }

   /* @Override
    public void SeDeplacer(Grille laGrille, JTextField leChampCommande) {

        ArrayList<Integer> DeplacementPossible = DeplacementPossible(laGrille);
        ArrayList<Integer> DeplacementPossiblePouvoir = DeplacementPossiblePouvoir(laGrille);

        boolean deplacementFait = false;

        System.out.println("Déplacements possibles:");
        for (int unDeplacementPossible : DeplacementPossible) {
            System.out.println(unDeplacementPossible);
        }

        System.out.println("Avant " + getX() + "," + getY());

        int x = Character.getNumericValue(leChampCommande.getText().charAt(0));
        int y = Character.getNumericValue(leChampCommande.getText().charAt(leChampCommande.getText().length() - 1));
        for (int unDeplacementPossible : DeplacementPossible) {
            if (unDeplacementPossible == (Integer.valueOf(String.valueOf(x) + String.valueOf(y)))) {
                this.setPosition(x, y);
                setPA(getPA() - 1);
                deplacementFait = true;
            }
        }

        if (!deplacementFait && isPouvoirPossible()) {
            for (int unDeplacementPossiblePouvoir : DeplacementPossiblePouvoir) {
                if (unDeplacementPossiblePouvoir == (Integer.valueOf(String.valueOf(x) + String.valueOf(y)))) {
                    this.setPosition(x, y);
                    setPA(getPA() - 1);
                    setPouvoirPossible(false);
                    deplacementFait = true;
                }
            }
            System.out.println("Pouvoir utilisé");
        }
        System.out.println("Apres " + getX() + "," + getY());
    }
*/
}
