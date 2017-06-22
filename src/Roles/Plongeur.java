/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Roles;

import IleInterdite.Aventurier;
import IleInterdite.Grille;
import IleInterdite.Tuile;
import IleInterdite.Utils;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author monnetlu
 */
public class Plongeur extends Aventurier {

    private String nomRole;

    public Plongeur(String leNomJoueur) {
        super(leNomJoueur, Utils.Pion.GRISFONCE);
        setNomRole("Plongeur");
        setIconPath("/img/resources/logo/RoleTable_Icon_Diver@2x.png");
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
    public ArrayList<Integer> DeplacementPossible(Grille laGrille) {
        // On récupère les tuiles adjacentes déjà triés dans le DeplacementPossible de Aventurier

        //La liste des tuiles Accessibles
        ArrayList<Integer> lesAccessibles = new ArrayList();
        //La liste des tuiles qui ont été traités
        ArrayList<Integer> déjàTraité = new ArrayList();
        //La liste des tuiles qui doivent être traités
        ArrayList<Integer> aTraité = new ArrayList();

        //On commence par la tuile où se situe le plongeur
        aTraité.add(Integer.valueOf(String.valueOf(getX() + "" + getY())));
        //Tant qu'il y a des tuiles à traités, on continue
        while (!aTraité.isEmpty()) {
            for (int uneTuile : aTraité) {

                ArrayList<Integer> sauvegardeATraité = new ArrayList<Integer>(aTraité);

                //Ssi elle est asseché, on l'ajoute à la liste des tuiles accessibles (si ce n'est pas déjà le cas)
                if (laGrille.getTuile(Utils.getChiffre(uneTuile, 2), Utils.getChiffre(uneTuile, 1)).getEtat() == Utils.EtatTuile.ASSECHEE && (!lesAccessibles.contains(uneTuile))) {
                    lesAccessibles.add(uneTuile);
                    //Si elle est inondé, on l'ajoute à la liste des tuiles accessibles ET a traité (si ce n'est pas déjà le cas)
                } else if (laGrille.getTuile(Utils.getChiffre(uneTuile, 2), Utils.getChiffre(uneTuile, 1)).getEtat() == Utils.EtatTuile.INONDEE && (!lesAccessibles.contains(uneTuile)) ) {
                    lesAccessibles.add(uneTuile);
                } else if (laGrille.getTuile(Utils.getChiffre(uneTuile, 2), Utils.getChiffre(uneTuile, 1)).getEtat() == Utils.EtatTuile.INONDEE && (!déjàTraité.contains(uneTuile)) ) {
                    sauvegardeATraité.add(uneTuile);
                    //Si elle est coulée, on l'ajoute à la liste des tuiles a traité
                } else if (laGrille.getTuile(Utils.getChiffre(uneTuile, 2), Utils.getChiffre(uneTuile, 1)).getEtat() == Utils.EtatTuile.COULEE && (!déjàTraité.contains(uneTuile)) ) {
                    sauvegardeATraité.add(uneTuile);
                }
                
                déjàTraité.add(uneTuile);

                //On récupère les tuiles adjacentes à une tuile à traité. Si elle n'appartient ni aux tuiles Accessibles ou Plongées, c'est qu'elle n'a pas été traité et on l'ajoute à la liste
                for (int uneAutreTuile : this.getTuilesAdjacentes(laGrille, Utils.getChiffre(uneTuile, 2), Utils.getChiffre(uneTuile, 1))) {
                    if (!déjàTraité.contains(uneAutreTuile) && !lesAccessibles.contains(uneAutreTuile) && !sauvegardeATraité.contains(uneAutreTuile)) {
                        sauvegardeATraité.add(uneAutreTuile);
                    }
                }
                
                sauvegardeATraité.remove((Object) uneTuile);
                aTraité = sauvegardeATraité;
            }
        }

        //On retourne les tuiles qui sont accessibles (Asséché + Inondée)
        return lesAccessibles;
    }

    public ArrayList<Integer> DeplacementPossiblePlongée(Grille laGrille, int x, int y, ArrayList<Integer> coulées) {
        ArrayList<Integer> lesTuiles = getTuilesAdjacentes(laGrille, x, y);
        // On récupère les tuiles adjacentes déjà triés dans le DeplacementPossible de Aventurier
        ArrayList<Integer> lesCoulées = coulées;
        //Parcourt les Tuiles, si la tuile n'est pas coulée, on l'ajoute à la liste des déplacements possibles
        for (int uneTuile : lesTuiles) {
            if (laGrille.getTuile(Utils.getChiffre(uneTuile, 2), Utils.getChiffre(uneTuile, 1)).getEtat() != Utils.EtatTuile.ASSECHEE && (!lesCoulées.contains(uneTuile))) {
                lesCoulées.add(uneTuile);
                lesCoulées.addAll(DeplacementPossiblePlongée(laGrille, Utils.getChiffre(uneTuile, 2), Utils.getChiffre(uneTuile, 1), lesCoulées));
            }
        }

        return lesCoulées;
    }

    /*@Override
     public void SeDeplacer(Grille laGrille, JTextField leChampCommande) {

     ArrayList<Integer> DeplacementPossible = DeplacementPossible(getX(), getY(), laGrille, true);

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
     }
     }

     System.out.println("Apres " + getX() + "," + getY());
     }
     */
    public ArrayList<Integer> getTuilesAdjacentes(Grille laGrille, int X, int Y) {

        ArrayList<Integer> tuilesAdjacentes = new ArrayList();

        //Aller à Droite possible?
        if ((X + 1 <= 5) && (laGrille.getTuile(X + 1, Y) != null)) {
            tuilesAdjacentes.add(Integer.valueOf(String.valueOf(X + 1) + String.valueOf(Y)));
        }

        // Aller à Gauche possible?
        if ((X - 1 >= 0) && (laGrille.getTuile(X - 1, Y) != null)) {
            tuilesAdjacentes.add(Integer.valueOf(String.valueOf(X - 1) + String.valueOf(Y)));
        }

        // Aller en Bas possible?
        if ((Y + 1 <= 5) && (laGrille.getTuile(X, Y + 1) != null)) {
            tuilesAdjacentes.add(Integer.valueOf(String.valueOf(X) + String.valueOf(Y + 1)));
        }

        // Aller en Haut possible?
        if ((Y - 1 >= 0) && (laGrille.getTuile(X, Y - 1) != null)) {
            tuilesAdjacentes.add(Integer.valueOf(String.valueOf(X) + String.valueOf(Y - 1)));
        }

        /*if ((laGrille.getTuile(X, Y) != null)) {
         tuilesAdjacentes.add(Integer.valueOf(String.valueOf(X) + String.valueOf(Y)));
         }*/
        return tuilesAdjacentes;

    }
}
