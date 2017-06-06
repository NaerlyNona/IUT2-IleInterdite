/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import IleInterdite.Utils.Pion;
import java.util.ArrayList;

/**
 *
 * @author monnetlu
 */
public class Explorateur extends Aventurier {

    private String nomRole;

    public Explorateur(String leNomJoueur) {
        super(leNomJoueur, Pion.VERT);
        setNomRole("Explorateur");
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
        ArrayList<Integer> lesDeplacements = new ArrayList();
        lesDeplacements.clear();
        System.out.println(laGrille.getTuile(getX(), getY()).getNom());

        // Aller à Droite possible?
        // Si on sort pas de la grille, qu'elle n'est pas nulle et qu'elle n'est pas coulée, on l'ajoute à lesDeplacements
        if ((this.getX() + 1 <= 5) && (laGrille.getTuile(getX() + 1, getY()) != null) && (laGrille.getTuile(getX() + 1, getY()).getEtat() != Utils.EtatTuile.COULEE)) {
            lesDeplacements.add(Integer.valueOf(String.valueOf(getX() + 1) + String.valueOf(getY())));
        }

        // Aller à Gauche possible?
        if ((this.getX() - 1 >= 0) && (laGrille.getTuile(getX() - 1, getY()) != null) && (laGrille.getTuile(getX() - 1, getY()).getEtat() != Utils.EtatTuile.COULEE)) {
            lesDeplacements.add(Integer.valueOf(String.valueOf(getX() - 1) + String.valueOf(getY())));
        }

        // Aller en Bas possible?
        if ((this.getY() + 1 <= 5) && (laGrille.getTuile(getX(), getY() + 1) != null) && (laGrille.getTuile(getX(), getY() + 1).getEtat() != Utils.EtatTuile.COULEE)) {
            lesDeplacements.add(Integer.valueOf(String.valueOf(getX()) + String.valueOf(getY() + 1)));
        }

        // Aller en Haut possible?
        if ((this.getY() - 1 >= 0) && (laGrille.getTuile(getX(), getY() - 1) != null) && (laGrille.getTuile(getX(), getY() - 1).getEtat() != Utils.EtatTuile.COULEE)) {
            lesDeplacements.add(Integer.valueOf(String.valueOf(getX()) + String.valueOf(getY() - 1)));
        }

        // Aller HautDroite possible?
        if ((this.getX() + 1 <= 5) && (this.getY() + 1 <= 5) && (laGrille.getTuile(getX() + 1, getY() + 1) != null) && (laGrille.getTuile(getX() + 1, getY() + 1).getEtat() != Utils.EtatTuile.COULEE)) {
            lesDeplacements.add(Integer.valueOf(String.valueOf(getX() + 1) + String.valueOf(getY())));
        }

        // Aller à HautGauche possible?
        if ((this.getX() - 1 >= 0) && (this.getY() + 1 <= 5) && (laGrille.getTuile(getX() - 1, getY() + 1) != null) && (laGrille.getTuile(getX() - 1, getY() + 1).getEtat() != Utils.EtatTuile.COULEE)) {
            lesDeplacements.add(Integer.valueOf(String.valueOf(getX() - 1) + String.valueOf(getY())));
        }

        // Aller en BasDroite possible?
        if ((this.getX() + 1 <= 5) && (this.getY() - 1 >= 0) && (laGrille.getTuile(getX() + 1, getY() - 1) != null) && (laGrille.getTuile(getX() + 1, getY() - 1).getEtat() != Utils.EtatTuile.COULEE)) {
            lesDeplacements.add(Integer.valueOf(String.valueOf(getX()) + String.valueOf(getY() + 1)));
        }

        // Aller en BasGauche possible?
        if ((this.getX() - 1 >= 0) && (this.getY() - 1 >= 0) && (laGrille.getTuile(getX() - 1, getY() - 1) != null) && (laGrille.getTuile(getX() - 1, getY() - 1).getEtat() != Utils.EtatTuile.COULEE)) {
            lesDeplacements.add(Integer.valueOf(String.valueOf(getX()) + String.valueOf(getY() - 1)));
        }

        return lesDeplacements;
    }
}
