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

    public ArrayList<Integer> DeplacementPossible(Grille laGrille) {
        ArrayList<Integer> lesDeplacements = new ArrayList();
        lesDeplacements.clear();
        System.out.println(laGrille.getTuile(getX(), getY()).getNom()); // <<<<<<<<<<<<<<< FAIRE ATTENTION CA SALUT NEARLY
        // Aller à Droite
        if ((this.getX()+1 <= 5) /*&& (laGrille.getTuile(getX()+1, getY()) != null)*/){
            lesDeplacements.add(Integer.valueOf(String.valueOf(getX()+1) + String.valueOf(getY())));
        }
        
        // Aller à Gauche
        if ((this.getX()-1 >= 0) && (laGrille.getTuile(getX()-1, getY()) != null)){
            lesDeplacements.add(Integer.valueOf(String.valueOf(getX()-1) + String.valueOf(getY())));
        }
         
        // Aller en Bas
        if ((this.getY()+1 <= 5) && (laGrille.getTuile(getX(), getY()+1) != null)){
            lesDeplacements.add(Integer.valueOf(String.valueOf(getX()) + String.valueOf(getY())+1));
        }
        
        // Aller en Haut
        if ((this.getY()-1 >= 0) && (laGrille.getTuile(getX(), getY()-1) != null)){
            lesDeplacements.add(Integer.valueOf(String.valueOf(getX()) + String.valueOf(getY()-1)));
        }
        
        return lesDeplacements;
    }

    public void SeDeplacer(Grille laGrille, JTextField leChampCommande) {
        
        ArrayList<Integer> DeplacementPossible = DeplacementPossible(laGrille);
        System.out.println("Déplacements possibles:");
        for (int unDeplacementPossible: DeplacementPossible){
            System.out.println(unDeplacementPossible);
        }
        
        System.out.println("Avant " + this.X +","+ this.Y);

        int x = Character.getNumericValue(leChampCommande.getText().charAt(0));
        int y = Character.getNumericValue(leChampCommande.getText().charAt(leChampCommande.getText().length()-1));
        this.setPosition(x, y);
        System.out.println("Apres "+ this.X +","+ this.Y);
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

}
