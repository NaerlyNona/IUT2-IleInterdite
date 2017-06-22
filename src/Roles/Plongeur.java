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
        ArrayList<Integer> lesTuiles = super.getTuilesAdjacentes(laGrille);
        // On récupère les tuiles adjacentes déjà triés dans le DeplacementPossible de Aventurier
        ArrayList<Integer> lesDeplacements = super.DeplacementPossible(laGrille);

        ArrayList<Integer> lesCoulées = DeplacementPossiblePlongée(laGrille);
        //Parcourt les Tuiles, si la tuile n'est pas coulée, on l'ajoute à la liste des déplacements possibles
        for (int uneTuile : lesTuiles) {
            if (laGrille.getTuile(Utils.getChiffre(uneTuile, 2), Utils.getChiffre(uneTuile, 1)).getEtat() != Utils.EtatTuile.COULEE) {
                lesDeplacements.add(uneTuile);
            }
        }

        for (int uneTuile : lesCoulées) {
            for (int uneAutreTuile : getTuilesAdjacentes(laGrille, Utils.getChiffre(uneTuile, 2), Utils.getChiffre(uneTuile, 1))) {
                if (laGrille.getTuile(Utils.getChiffre(uneAutreTuile, 2), Utils.getChiffre(uneAutreTuile, 1)).getEtat() != Utils.EtatTuile.COULEE) {
                    lesDeplacements.add(uneAutreTuile);
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

    public ArrayList<Integer> DeplacementPossiblePlongée(Grille laGrille) {
        ArrayList<Integer> lesTuiles = super.getTuilesAdjacentes(laGrille);
        // On récupère les tuiles adjacentes déjà triés dans le DeplacementPossible de Aventurier
        ArrayList<Integer> lesCoulées = new ArrayList();
        ArrayList<Integer> lesVraisCoulées = new ArrayList();
        //Parcourt les Tuiles, si la tuile n'est pas coulée, on l'ajoute à la liste des déplacements possibles
        for (int uneTuile : lesTuiles) {
            if (laGrille.getTuile(Utils.getChiffre(uneTuile, 2), Utils.getChiffre(uneTuile, 1)).getEtat() != Utils.EtatTuile.ASSECHEE && (!lesCoulées.contains(uneTuile))) {
                lesCoulées.add(uneTuile);
                lesCoulées.addAll(DeplacementPossiblePlongée(laGrille, Utils.getChiffre(uneTuile, 2), Utils.getChiffre(uneTuile, 1), lesCoulées));
            }
        }

        for (int uneTuile : (ArrayList<Integer>)lesCoulées.clone()){
            for (int uneAutreTuile : (ArrayList<Integer>)lesCoulées.clone()){
                if (uneTuile == uneAutreTuile){
                    lesVraisCoulées.add(uneTuile);
                    break;
                }
            }
        }
        
        for (int uneTuile : lesVraisCoulées){
            System.out.println(uneTuile);
        }

        return lesVraisCoulées;
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
