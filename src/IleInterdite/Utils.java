/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
//import model.aventuriers.Aventurier;

/**
 *
 * @author Eric
 */
public class Utils {

    public static enum EtatTuile {

        ASSECHEE("Asséchée"),
        INONDEE("Inondée"),
        COULEE("Coulée");

        String libelle;

        EtatTuile(String libelle) {
            this.libelle = libelle;
        }

        @Override
        public String toString() {
            return this.libelle;
        }
    }

    public static enum TypeTuile {

        Tresor, Spawn, Normale
    }

    public static enum TypeTresor {

        GRIS, ROUGE, JAUNE, BLEU
    }

    public static enum TypeSpéciale {

        SacDeSable, Helicoptère
    }

    public static enum TypeCarte {

        Montée, Spéciale, Trésor
    }

    public static enum Pion {

        ROUGE("Rouge", new Color(255, 0, 0)), // Ingénieur 
        VERT("Vert", new Color(0, 195, 0)), // Explorateur
        BLEU("Bleu", new Color(55, 194, 198)), // Pilote
        GRISCLAIR("Orange", Color.LIGHT_GRAY), // Messager
        GRISFONCE("Violet", Color.DARK_GRAY), // Plongeur
        JAUNE("Jaune", new Color(255, 255, 0)); // Navigateur

        private final String libelle;
        private final Color couleur;

        Pion(String libelle, Color couleur) {
            this.libelle = libelle;
            this.couleur = couleur;
        }

        @Override
        public String toString() {
            return this.libelle;
        }

        public Color getCouleur() {
            return this.couleur;
        }

        static Pion getFromName(String name) {
            if (ROUGE.name().equals(name)) {
                return ROUGE;
            }
            if (VERT.name().equals(name)) {
                return VERT;
            }
            if (BLEU.name().equals(name)) {
                return BLEU;
            }
            if (GRISCLAIR.name().equals(name)) {
                return GRISCLAIR;
            }
            if (GRISFONCE.name().equals(name)) {
                return GRISFONCE;
            }
            if (JAUNE.name().equals(name)) {
                return JAUNE;
            }
            return null;
        }
    }

    public static ArrayList<Aventurier> melangerAventuriers(ArrayList<Aventurier> arrayList) {
        if (Parameters.ALEAS) {
            Collections.shuffle(arrayList);
        }
        return arrayList;
    }

    /**
     * Permet de poser une question à laquelle l'utilisateur répond par oui ou
     * non
     *
     * @param question texte à afficher
     * @return true si l'utilisateur répond oui, false sinon
     */
    public static Boolean poserQuestion(String question) {
        System.out.println("Divers.poserQuestion(" + question + ")");
        int reponse = JOptionPane.showConfirmDialog(null, question, "", JOptionPane.YES_NO_OPTION);
        System.out.println("\tréponse : " + (reponse == JOptionPane.YES_OPTION ? "Oui" : "Non"));
        return reponse == JOptionPane.YES_OPTION;
    }

    /**
     * Permet d'afficher un message d'information avec un bouton OK
     *
     * @param message Message à afficher
     */
    public static void afficherInformation(String message) {
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.OK_OPTION);
    }

    public static int getChiffre(int leNombre, int numChiffre) {
        String Chiffres = Integer.toString(leNombre);
        if (numChiffre - 1 >= Chiffres.length()) {
            return 0;
        } else {
            return Character.digit(Chiffres.charAt((Chiffres.length() - 1) - (numChiffre - 1)), 10);
        }
    }

    public ImageIcon createImageIcon(String path,
            String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
