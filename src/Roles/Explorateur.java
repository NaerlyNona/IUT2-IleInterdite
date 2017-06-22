/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Roles;

import IleInterdite.Aventurier;
import IleInterdite.Grille;
import IleInterdite.Utils;
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
        setIconPath("/img/resources/logo/RoleTable_Icon_Explorer@2x.png");
        setLabelIcone(createImageIcon(getIconPath(), getNomRole()));
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
        ArrayList<Integer> lesTuiles = getTuilesDiagonales(laGrille);
        // On récupère les tuiles adjacentes déjà triés dans le DeplacementPossible de Aventurier
        ArrayList<Integer> lesDeplacements = super.DeplacementPossible(laGrille);
        //Parcourt les Tuiles, si la tuile n'est pas coulée, on l'ajoute à la liste des déplacements possibles
        for (int uneTuile : lesTuiles) {
            if (laGrille.getTuile(Utils.getChiffre(uneTuile, 2), Utils.getChiffre(uneTuile, 1)).getEtat() != Utils.EtatTuile.COULEE) {
                lesDeplacements.add(uneTuile);
            }
        }

        return lesDeplacements;
    }
    
    @Override
    public ArrayList<Integer> AssechementPossible(Grille laGrille) {
        ArrayList<Integer> lesTuiles = getTuilesDiagonales(laGrille);
        ArrayList<Integer> lesAssechements = super.AssechementPossible(laGrille);
        //Parcourt les Tuiles, si la tuile est inondé, on l'ajoute à la liste des assèchements possibles
        for (int uneTuile : lesTuiles) {
            if (laGrille.getTuile(Utils.getChiffre(uneTuile, 2), Utils.getChiffre(uneTuile, 1)).getEtat() == Utils.EtatTuile.INONDEE) {
                lesAssechements.add(uneTuile);
            }
        }

        return lesAssechements;
    }
    
    public ArrayList<Integer> getTuilesDiagonales(Grille laGrille) {

        ArrayList<Integer> tuilesAdjacentes = new ArrayList();
        //HautDroite
        if ((this.getX() + 1 <= 5) && (this.getY() + 1 <= 5) && (laGrille.getTuile(getX() + 1, getY() + 1) != null)) {
            tuilesAdjacentes.add(Integer.valueOf(String.valueOf(getX() + 1) + String.valueOf(getY()+1)));
        }

        //HautGauche
        if ((this.getX() - 1 >= 0) && (this.getY() + 1 <= 5) && (laGrille.getTuile(getX() - 1, getY() + 1) != null)) {
            tuilesAdjacentes.add(Integer.valueOf(String.valueOf(getX() - 1) + String.valueOf(getY()+1)));
        }

        //BasDroite
        if ((this.getX() + 1 <= 5) && (this.getY() - 1 >= 0) && (laGrille.getTuile(getX() + 1, getY() - 1) != null)) {
            tuilesAdjacentes.add(Integer.valueOf(String.valueOf(getX()+1) + String.valueOf(getY() - 1)));
        }

        //BasGauche
        if ((this.getX() - 1 >= 0) && (this.getY() - 1 >= 0) && (laGrille.getTuile(getX() - 1, getY() - 1) != null)) {
            tuilesAdjacentes.add(Integer.valueOf(String.valueOf(getX()-1) + String.valueOf(getY() - 1)));
        }

        return tuilesAdjacentes;

    }
}
