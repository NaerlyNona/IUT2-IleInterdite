/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import IleInterdite.Utils.Pion;
import java.util.ArrayList;
import java.util.Scanner;
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
    private int maxPA;
    private Utils.Pion pion;
    private ArrayList<Carte> main;

    public Aventurier(String leNomJoueur, Pion lePion) {
        setNomJoueur(leNomJoueur);
        setPion(lePion);
        setPosition(2, 3);
        main = new ArrayList();
        setMaxPA(3);
        setPA(getMaxPA());

    }

    public void donnerC(Aventurier aventurier, Carte carte) {

        
        
        
        
        System.out.println("La carte " + carte.getNomCarte() + " a été donné a " + aventurier.getNomJoueur());
        aventurier.ajouterMain(carte);
        this.removeMain(carte);
        setPA(getPA() - 1);
    }

    public void ajouterMain(Carte carte) {
        getMain().add(carte);

    }

    public void removeMain(Carte carte) {
        getMain().remove(carte);
    }

    public void modifMain() {
        int i = 1;
        System.out.println("====MAIN====");
        for (Carte c : main) {
            System.out.println(c.getNomCarte());
        }
        if (getMain().size() > 5) {

            System.out.println("====Carte a defausse====");
            for (Carte c : main) {
                System.out.println("Carte n°" + i + " : " + c.getNomCarte());
                i++;
            }
        }

        if (getMain().size() == 6) {
            //System.out.println("Saisissez numéro carte a défausser:");
            /*Scanner sc = new Scanner(System.in);
             int choix = sc.nextInt();*/
            getInput();
        }
        if (getMain().size() == 7) {
            //System.out.println("Saisissez numéro carte a défausser:");
            /*Scanner sc = new Scanner(System.in);
             int choix = sc.nextInt();*/
            getInput();
            System.out.println("====Carte a defausse====");
            i = 1;
            for (Carte c : main) {
                System.out.println("Carte n°" + i + " : " + c.getNomCarte());
                i++;
            }

            //System.out.println("Saisissez numéro carte a défausser:");
            //int choix2 = sc.nextInt();
            getInput();
        }
    }

    public void getInput() {
        try {
            System.out.println("Saisissez la carte a défausser : ");
            Scanner sc = new Scanner(System.in);
            int choix = sc.nextInt();

            main.remove(choix - 1);
            System.out.println("La carte n°" + choix + " a été défaussée");
        } catch (IndexOutOfBoundsException ime) {
            System.out.println("Entrée invalide, recommence fdp.");
            getInput();
        }
    }

    public void donnerCarte(Aventurier a, int choix) {


        /* for (Aventurier a : controleur.getLesAventuriers()){
         System.out.println("a.getNomRole()"+a.getNomRole());
         System.out.println("role"+role);
         if (a.getNomRole() == role){ 
         System.out.println("Test5"); */
        if (a.getMain().size() < 5) {
            System.out.println("La carte " + getMain().get(choix - 1).getNomCarte() + " a été envoyé à " + a.getNomRole());
            a.ajouterMain(getMain().get(choix - 1));
            getMain().remove(choix - 1);
        } else {
            System.out.println("starfoulila");

        }
    }

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

    public void reset() {
        setPA(maxPA);
    }

    public ArrayList<Integer> DeplacementPossible(Grille laGrille) {
        ArrayList<Integer> lesTuiles = getTuilesAdjacentes(laGrille);
        ArrayList<Integer> lesDeplacements = new ArrayList();

        //Parcourt les Tuiles, si la tuile n'est pas coulée, on l'ajoute à la liste des déplacements possibles    
        for (int uneTuile : lesTuiles) {
            if (laGrille.getTuile(Utils.getChiffre(uneTuile, 2), Utils.getChiffre(uneTuile, 1)).getEtat() != Utils.EtatTuile.COULEE && uneTuile != Integer.valueOf(String.valueOf(getX()) + String.valueOf(getY()))) {
                lesDeplacements.add(uneTuile);
            }
        }

        return lesDeplacements;
    }

    public void SeDeplacer(Grille laGrille, String laPosition) {

        ArrayList<Integer> DeplacementPossible = DeplacementPossible(laGrille);

        int x = Character.getNumericValue(laPosition.charAt(0));
        int y = Character.getNumericValue(laPosition.charAt(1));

        for (int unDeplacementPossible : DeplacementPossible) {
            if (unDeplacementPossible == (Integer.valueOf(String.valueOf(x) + String.valueOf(y)))) {
                this.setPosition(x, y);
                setPA(getPA() - 1);
            }
        }

    }

    public ArrayList<Integer> AssechementPossible(Grille laGrille) {
        ArrayList<Integer> lesTuiles = getTuilesAdjacentes(laGrille);
        ArrayList<Integer> lesAssechements = new ArrayList();

        //Parcourt les Tuiles, si la tuile est inondé, on l'ajoute à la liste des assèchements possibles
        for (int uneTuile : lesTuiles) {
            if (laGrille.getTuile(Utils.getChiffre(uneTuile, 2), Utils.getChiffre(uneTuile, 1)).getEtat() == Utils.EtatTuile.INONDEE) {
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
        System.out.println("Essai sur : " + x + y);
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

        if ((laGrille.getTuile(getX(), getY()) != null)) {
            tuilesAdjacentes.add(Integer.valueOf(String.valueOf(getX()) + String.valueOf(getY())));
        }

        return tuilesAdjacentes;

    }

    /**
     * @return the maxPA
     */
    public int getMaxPA() {
        return maxPA;
    }

    /**
     * @param maxPA the maxPA to set
     */
    public void setMaxPA(int maxPA) {
        this.maxPA = maxPA;
    }

    /**
     * @return the main
     */
    public ArrayList<Carte> getMain() {
        return main;
    }

    /**
     * @param main the main to set
     */
    public void setMain(ArrayList<Carte> main) {
        this.main = main;
    }

}
