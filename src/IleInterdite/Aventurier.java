/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import IleInterdite.Utils.Pion;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author monnetlu
 */
public class Aventurier {

    private String nomJoueur;
    private String nomRole;
    private int X;
    private int Y;
    private int PA;
    private Utils.Pion pion;

    public Aventurier(String leNomJoueur, Pion lePion) {
        setNomJoueur(leNomJoueur);
        setPion(lePion);
        setPosition(2, 3);
        setPA(3);
    }

    /**
     * @return the nom
     */
    public String getNomJoueur() {
        return nomJoueur;
    }

    /**
     * @param nom the nom to set
     */
    public void setNomJoueur(String nom) {
        this.nomJoueur = nom;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(int x, int y) {
        setX(x);
        setY(y);
    }

    /**
     * @return the pion
     */
    public Utils.Pion getPion() {
        return pion;
    }

    /**
     * @param pion the pion to set
     */
    public void setPion(Utils.Pion pion) {
        this.pion = pion;
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
     * @return the X
     */
    public int getX() {
        return X;
    }

    /**
     * @param X the X to set
     */
    public void setX(int X) {
        this.X = X;
    }

    /**
     * @return the Y
     */
    public int getY() {
        return Y;
    }

    /**
     * @param Y the Y to set
     */
    public void setY(int Y) {
        this.Y = Y;
    }
    
        /**
     * @return the PA
     */
    public int getPA() {
        return PA;
    }

    /**
     * @param PA the PA to set
     */
    public void setPA(int PA) {
        this.PA = PA;
    }
    
    public void reset(){
        setPA(3);
    }

    public ArrayList<Integer> DeplacementPossible(Grille laGrille) {
        ArrayList<Integer> lesTuiles = getTuilesAdjacentes(laGrille);
        ArrayList<Integer> lesDeplacements = new ArrayList();
        //Parcourt les Tuiles, si la tuile est inondé, on l'ajoute à la liste des assèchements possibles
        
        
        for (int uneTuile : lesTuiles) {
            System.out.println(Utils.getChiffre(uneTuile, 1) + "," + Utils.getChiffre(uneTuile, 2));
            if (laGrille.getTuile(Utils.getChiffre(uneTuile, 1), Utils.getChiffre(uneTuile, 2)).getEtat() != Utils.EtatTuile.COULEE) {
                lesDeplacements.add(uneTuile);
            }
        }

        return lesDeplacements;
    }

    public void SeDeplacer(Grille laGrille, String laPosition) {

        ArrayList<Integer> DeplacementPossible = DeplacementPossible(laGrille);
        System.out.println("Déplacements possibles:");
        for (int unDeplacementPossible : DeplacementPossible) {
            System.out.println(unDeplacementPossible);
        }

        System.out.println("Avant " + getX() + "," + getY());

        int x = Character.getNumericValue(laPosition.charAt(0));
        int y = Character.getNumericValue(laPosition.charAt(1));
        for (int unDeplacementPossible : DeplacementPossible) {
            if (unDeplacementPossible == (Integer.valueOf(String.valueOf(x) + String.valueOf(y)))) {
                this.setPosition(x, y);
                setPA(getPA() - 1);
            }
        }
        System.out.println("Apres " + getX() + "," + getY());
    }

    public ArrayList<Integer> AssechementPossible(Grille laGrille) {
        ArrayList<Integer> lesTuiles = getTuilesAdjacentes(laGrille);
        ArrayList<Integer> lesAssechements = new ArrayList();
        //Parcourt les Tuiles, si la tuile est inondé, on l'ajoute à la liste des assèchements possibles
        for (int uneTuile : lesTuiles) {
            if (laGrille.getTuile(Utils.getChiffre(uneTuile, 1), Utils.getChiffre(uneTuile, 2)).getEtat() == Utils.EtatTuile.INONDEE) {
                lesAssechements.add(uneTuile);
            }
        }

        return lesAssechements;
    }

    public void Assécher(Grille laGrille, String laPosition) {

        ArrayList<Integer> AssechementPossible = AssechementPossible(laGrille);
        System.out.println("Assèchement possibles:");
        for (int unAssechementPossible : AssechementPossible) {
            System.out.println(unAssechementPossible);
        }

        int x = Character.getNumericValue(laPosition.charAt(0));
        int y = Character.getNumericValue(laPosition.charAt(1));

        System.out.println("Avant: " + laGrille.getTuile(x, y).getEtat());

        for (int unAssechementPossible : AssechementPossible) {
            if (unAssechementPossible == (Integer.valueOf(String.valueOf(x) + String.valueOf(y)))) {
                laGrille.getTuile(x, y).setEtat(Utils.EtatTuile.ASSECHEE);
                setPA(getPA() - 1);
            }
        }
        System.out.println("Après: " + laGrille.getTuile(x, y).getEtat());
    }

    public ArrayList<Integer> getTuilesAdjacentes(Grille laGrille) {

        ArrayList<Integer> tuilesAdjacentes = new ArrayList();

        if ((this.getX() + 1 <= 5) && (laGrille.getTuile(getX() + 1, getY()) != null)) {
            tuilesAdjacentes.add(Integer.valueOf(String.valueOf(getX() + 1) + String.valueOf(getY())));
        }

        // Aller à Gauche possible?
        if ((this.getX() - 1 >= 0) && (laGrille.getTuile(getX() - 1, getY()) != null)) {
            tuilesAdjacentes.add(Integer.valueOf(String.valueOf(getX() - 1) + String.valueOf(getY())));
        }

        // Aller en Bas possible?
        if ((this.getY() + 1 <= 5) && (laGrille.getTuile(getX(), getY() + 1) != null)) {
            tuilesAdjacentes.add(Integer.valueOf(String.valueOf(getX()) + String.valueOf(getY() + 1)));
        }

        // Aller en Haut possible?
        if ((this.getY() - 1 >= 0) && (laGrille.getTuile(getX(), getY() - 1) != null)) {
            tuilesAdjacentes.add(Integer.valueOf(String.valueOf(getX()) + String.valueOf(getY() - 1)));
        }

        return tuilesAdjacentes;

    }

}
