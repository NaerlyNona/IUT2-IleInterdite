/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author monnetlu
 */
public class Plongeur extends Aventurier {

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

    public ArrayList<Integer> DeplacementPossible(int X, int Y, Grille laGrille, boolean premier) {
        ArrayList<Integer> lesDeplacements = new ArrayList();
        ArrayList<Integer> lesPlongées = DeplacementPossiblePlongée(X, Y, laGrille, true);

        lesDeplacements.clear();
        System.out.println(laGrille.getTuile(X, Y).getNom());

        // Aller à Droite possible?
        // Si on sort pas de la grille, qu'elle n'est pas nulle et qu'elle n'est pas coulée, on l'ajoute à lesDeplacements
        if ((X + 1 <= 5) && (laGrille.getTuile(X + 1, Y) != null) && (laGrille.getTuile(X + 1, Y).getEtat() != Utils.EtatTuile.COULEE)) {
            lesDeplacements.add(Integer.valueOf(String.valueOf(X + 1) + String.valueOf(Y)));
        }

        // Aller à Gauche possible?
        if ((X - 1 >= 0) && (laGrille.getTuile(X - 1, Y) != null) && (laGrille.getTuile(X - 1, Y).getEtat() != Utils.EtatTuile.COULEE)) {
            lesDeplacements.add(Integer.valueOf(String.valueOf(X - 1) + String.valueOf(Y)));
        }

        // Aller en Bas possible?
        if ((Y + 1 <= 5) && (laGrille.getTuile(X, Y + 1) != null) && (laGrille.getTuile(X, Y + 1).getEtat() != Utils.EtatTuile.COULEE)) {
            lesDeplacements.add(Integer.valueOf(String.valueOf(X) + String.valueOf(Y + 1)));
        }

        // Aller en Haut possible?
        if ((Y - 1 >= 0) && (laGrille.getTuile(X, Y - 1) != null) && (laGrille.getTuile(X, Y - 1).getEtat() != Utils.EtatTuile.COULEE)) {
            lesDeplacements.add(Integer.valueOf(String.valueOf(X) + String.valueOf(Y - 1)));
        }

        for (int unePlongée : lesPlongées) {
            if ((X + 1 <= 5) && (laGrille.getTuile((unePlongée/(10^2))%10 + 1, (unePlongée/(10^1))%10) != null) && (laGrille.getTuile((unePlongée/(10^2))%10 + 1, (unePlongée/(10^1))%10).getEtat() != Utils.EtatTuile.COULEE) && !lesDeplacements.contains(unePlongée)) {
                lesDeplacements.add(Integer.valueOf(String.valueOf((unePlongée/(10^2))%10 + 1) + String.valueOf((unePlongée/(10^1))%10)));
            }

            // Aller à Gauche possible?
            if ((X - 1 >= 0) && (laGrille.getTuile((unePlongée/(10^2))%10 - 1, (unePlongée/(10^1))%10) != null) && (laGrille.getTuile((unePlongée/(10^2))%10 - 1, (unePlongée/(10^1))%10).getEtat() != Utils.EtatTuile.COULEE) && !lesDeplacements.contains(unePlongée)) {
                lesDeplacements.add(Integer.valueOf(String.valueOf((unePlongée/(10^2))%10 - 1) + String.valueOf((unePlongée/(10^1))%10)));
            }

            // Aller en Bas possible?
            if ((Y + 1 <= 5) && (laGrille.getTuile((unePlongée/(10^2))%10, (unePlongée/(10^1))%10 + 1) != null) && (laGrille.getTuile((unePlongée/(10^2))%10, (unePlongée/(10^1))%10 + 1).getEtat() != Utils.EtatTuile.COULEE) && !lesDeplacements.contains(unePlongée)) {
                lesDeplacements.add(Integer.valueOf(String.valueOf((unePlongée/(10^2))%10) + String.valueOf((unePlongée/(10^1))%10 + 1)));
            }

            // Aller en Haut possible?
            if ((Y - 1 >= 0) && (laGrille.getTuile((unePlongée/(10^2))%10, (unePlongée/(10^1))%10 - 1) != null) && (laGrille.getTuile((unePlongée/(10^2))%10, (unePlongée/(10^1))%10 - 1).getEtat() != Utils.EtatTuile.COULEE) && !lesDeplacements.contains(unePlongée)) {
                lesDeplacements.add(Integer.valueOf(String.valueOf((unePlongée/(10^2))%10) + String.valueOf((unePlongée/(10^1))%10 - 1)));
            }

        }

        return lesDeplacements;
    }

    public ArrayList<Integer> DeplacementPossiblePlongée(int X, int Y, Grille laGrille, boolean premier) {
        ArrayList<Integer> lesDeplacements = new ArrayList();

        lesDeplacements.clear();
        System.out.println("PLONGEUR:"+laGrille.getTuile(X, Y).getNom());

        // Aller à Droite possible?
        // Si on sort pas de la grille, qu'elle n'est pas nulle et qu'elle n'est pas coulée, on l'ajoute à lesDeplacements
        if ((X + 1 <= 5) && (laGrille.getTuile(X + 1, Y) != null) && (laGrille.getTuile(X + 1, Y).getEtat() == Utils.EtatTuile.COULEE) && !lesDeplacements.contains(Integer.valueOf(String.valueOf(X + 1) + String.valueOf(Y)))) {
            lesDeplacements.add(Integer.valueOf(String.valueOf(X + 1) + String.valueOf(Y)));
            lesDeplacements.addAll(DeplacementPossiblePlongée(X + 1, Y, laGrille, false));
        }

        // Aller à Gauche possible?
        if ((X - 1 >= 0) && (laGrille.getTuile(X - 1, Y) != null) && (laGrille.getTuile(X - 1, Y).getEtat() == Utils.EtatTuile.COULEE) && !lesDeplacements.contains(Integer.valueOf(String.valueOf(X - 1) + String.valueOf(Y)))) {
            lesDeplacements.add(Integer.valueOf(String.valueOf(X - 1) + String.valueOf(Y)));
            lesDeplacements.addAll(DeplacementPossiblePlongée(X - 1, Y, laGrille, false));
        }

        // Aller en Bas possible?
        if ((Y + 1 <= 5) && (laGrille.getTuile(X, Y + 1) != null) && (laGrille.getTuile(X, Y + 1).getEtat() == Utils.EtatTuile.COULEE) && !lesDeplacements.contains(Integer.valueOf(String.valueOf(X) + String.valueOf(Y+1)))) {
            lesDeplacements.add(Integer.valueOf(String.valueOf(X) + String.valueOf(Y + 1)));
            lesDeplacements.addAll(DeplacementPossiblePlongée(X - 1, Y, laGrille, false));
        }

        // Aller en Haut possible?
        if ((Y - 1 >= 0) && (laGrille.getTuile(X, Y - 1) != null) && (laGrille.getTuile(X, Y - 1).getEtat() == Utils.EtatTuile.COULEE) && !lesDeplacements.contains(Integer.valueOf(String.valueOf(X) + String.valueOf(Y-1)))) {
            lesDeplacements.add(Integer.valueOf(String.valueOf(X) + String.valueOf(Y - 1)));
            lesDeplacements.addAll(DeplacementPossiblePlongée(X - 1, Y, laGrille, false));
        }

        return lesDeplacements;
    }

    @Override
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

}
