/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import IleInterdite.Utils.Pion;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Image;
import javax.swing.BorderFactory;

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
    private JLabel labelIcone = new JLabel();
    private String iconPath;

    private boolean pouvoirPossible;

    public Aventurier(String leNomJoueur, Pion lePion) {
        setNomJoueur(leNomJoueur);
        setPion(lePion);
        setPosition(2, 3);
        main = new ArrayList();
        setMaxPA(3);
        setPA(getMaxPA());
        setPouvoirPossible(false);
    }

    public ArrayList<String> donnerPossible(Controleur controleur) {
        ArrayList<String> test = new ArrayList();

        for (Aventurier a : controleur.getLesAventuriers()) {

            if ((a.getX() == getX() && a.getY() == getY()) && (a != this)) {
                test.add(a.getNomJoueur());

            }

        }
        return test;
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

    public ArrayList<Integer> Helicoptère (Grille laGrille){
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
    
    public ArrayList<Integer> SacDeSable (Grille laGrille){
        ArrayList<Integer> lesDeplacements = new ArrayList();
        lesDeplacements.clear();
 
        for (int x = 0; x <= 5; x++) {
            for (int y = 0; y <= 5; y++) {
                if (laGrille.getTuile(x, y) != null) {
                    if (laGrille.getTuile(x, y).getEtat() != Utils.EtatTuile.COULEE && laGrille.getTuile(x, y).getEtat() != Utils.EtatTuile.ASSECHEE ) {
                        lesDeplacements.add(Integer.valueOf(String.valueOf(x) + String.valueOf(y)));
                    }
                }
            }
        }
        
        
        return lesDeplacements;
     
    }
    
    public void UtiliserSacDeSable(Grille laGrille, String laPosition) {
 
        ArrayList<Integer> DeplacementPossible = SacDeSable(laGrille);
 
        int x = Character.getNumericValue(laPosition.charAt(0));
        int y = Character.getNumericValue(laPosition.charAt(1));
 
        for (int unDeplacementPossible : DeplacementPossible) {
 
            if (unDeplacementPossible == (Integer.valueOf(String.valueOf(x) + String.valueOf(y)))) {
                laGrille.getTuile(x, y).setEtat(Utils.EtatTuile.ASSECHEE);
                break;
            }
        }
    }
    
    
    public void SeDeplacerHelicoptère(Grille laGrille, String laPosition) {
 
        ArrayList<Integer> DeplacementPossible = Helicoptère(laGrille);
 
        int x = Character.getNumericValue(laPosition.charAt(0));
        int y = Character.getNumericValue(laPosition.charAt(1));
 
        for (int unDeplacementPossible : DeplacementPossible) {
 
            if (unDeplacementPossible == (Integer.valueOf(String.valueOf(x) + String.valueOf(y)))) {
                this.setPosition(x, y);
                break;
            }
        }
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
                break;
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

        int x = Character.getNumericValue(laPosition.charAt(0));
        int y = Character.getNumericValue(laPosition.charAt(1));

        for (int unAssechementPossible : AssechementPossible) {
            if (unAssechementPossible == (Integer.valueOf(String.valueOf(x) + String.valueOf(y)))) {
                laGrille.getTuile(x, y).setEtat(Utils.EtatTuile.ASSECHEE);
                setPA(getPA() - 1);
            }
        }
    }

    public ArrayList<Integer> getTuilesAdjacentes(Grille laGrille) {

        ArrayList<Integer> tuilesAdjacentes = new ArrayList();

        //Aller à Droite possible?
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

    /**
     * @return the icone
     */
    public JLabel getLabelIcone() {
        return labelIcone;
    }

    /**
     * @param icone the icone to set
     */
    public void setLabelIcone(ImageIcon icone) {
        //this.icone = icone;
        Image img = icone.getImage();
        Image newimg = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        icone = new ImageIcon(newimg);
        labelIcone.setIcon(icone);
        labelIcone.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 20));
    }

    protected ImageIcon createImageIcon(String path,
            String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * @return the iconPath
     */
    public String getIconPath() {
        return iconPath;
    }

    /**
     * @param iconPath the iconPath to set
     */
    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

}
