/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import Roles.Messager;
import IleInterdite.Utils.Pion;
import Roles.Ingenieur;
import Roles.Navigateur;
import Roles.Explorateur;
import Roles.Pilote;
import Roles.Plongeur;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.*;

/**
 *
 * @author monnetlu
 */
public class Controleur implements Observateur {

    private IHMIleInterdite ihmIleInterdite;
    private int nombreAventurier = 2;
    private double niveauEau = 2;
    private Grille laGrille = new Grille();
    private Tuile uneTuile;
    private CarteInondation uneCarte;
    private Carte carte;
    private Aventurier aventurierActuel;
    private ArrayList<Aventurier> lesAventuriers;
    private boolean PartieFinie;
    private boolean finDuTour;
    private ArrayList<CarteInondation> defausseInondation;
    private ArrayList<CarteInondation> bannieInondation;
    private ArrayList<CarteInondation> piocheInondation;
    private ArrayList<Carte> piocheTresor;
    private ArrayList<Carte> defausseTresor;
    private ArrayList<Tresor> tresors;
    private ArrayList<Tresor> tresorsRestant;

    public Controleur() {
        setAventurierActuel(new Aventurier("init", Pion.VERT));
        InitialiserTestPartie();

        ihmIleInterdite = new IHMIleInterdite(this);
        ihmIleInterdite.afficher();
        ihmIleInterdite.setJoueurCourant(getAventurierActuel());
        ihmIleInterdite.initialisationTuile(laGrille);
        ihmIleInterdite.MAJInfo(this);
        ihmIleInterdite.MAJTresor(this);
        ihmIleInterdite.MAJJoueur(getAventurierActuel());
        ihmIleInterdite.MAJBoutons(getAventurierActuel(), this);
        ihmIleInterdite.MAJMain(getAventurierActuel());
        ihmIleInterdite.MAJTuile(laGrille, lesAventuriers, aventurierActuel);
        ihmIleInterdite.afficher();
    }

    public Controleur(ArrayList<Aventurier> lesAventuriers, double difficulté) {
        setAventurierActuel(new Aventurier("init", Pion.VERT));
        setLesAventuriers(lesAventuriers);
        setNiveauEau(difficulté);
        InitialiserPartie();

        ihmIleInterdite = new IHMIleInterdite(this);
        ihmIleInterdite.afficher();
        ihmIleInterdite.setJoueurCourant(getAventurierActuel());
        ihmIleInterdite.initialisationTuile(laGrille);
        ihmIleInterdite.MAJInfo(this);
        ihmIleInterdite.MAJTresor(this);
        ihmIleInterdite.MAJJoueur(getAventurierActuel());
        ihmIleInterdite.MAJBoutons(getAventurierActuel(), this);
        ihmIleInterdite.MAJMain(getAventurierActuel());
        ihmIleInterdite.MAJTuile(laGrille, lesAventuriers, aventurierActuel);
        ihmIleInterdite.afficher();
    }

    public void InitialiserPartie() {
        setPartieFinie(false);
        setFinDuTour(false);

        defausseInondation = new ArrayList();
        bannieInondation = new ArrayList();
        piocheInondation = new ArrayList();
        piocheTresor = new ArrayList();
        defausseTresor = new ArrayList();
        setTresors((ArrayList<Tresor>) new ArrayList());
        tresorsRestant = new ArrayList();

        Tresor calice = new Tresor("Le Calice de l’onde", Utils.TypeTresor.BLEU);
        tresorsRestant.add(calice);
        Tresor pierre = new Tresor("La Pierre sacrée", Utils.TypeTresor.GRIS);
        tresorsRestant.add(pierre);
        Tresor statue = new Tresor("La Statue du zéphyr", Utils.TypeTresor.JAUNE);
        tresorsRestant.add(statue);
        Tresor cristal = new Tresor("Le Cristal ardent", Utils.TypeTresor.ROUGE);
        tresorsRestant.add(cristal);
        carte = new CarteMontée("Montée", Utils.TypeCarte.Montée);
        piocheTresor.add(carte);
        carte = new CarteMontée("Montée", Utils.TypeCarte.Montée);
        piocheTresor.add(carte);
        for (int i = 0; i < 5; i++) {
            carte = new CarteTrésor("Le Calice de l’onde", calice, Utils.TypeCarte.Trésor);
            piocheTresor.add(carte);
            carte = new CarteTrésor("La Pierre sacrée", pierre, Utils.TypeCarte.Trésor);
            piocheTresor.add(carte);
            carte = new CarteTrésor("La Statue du zéphyr", statue, Utils.TypeCarte.Trésor);
            piocheTresor.add(carte);
            carte = new CarteTrésor("Le Cristal ardent", cristal, Utils.TypeCarte.Trésor);
            piocheTresor.add(carte);
        }
        carte = new CarteSpéciale("Sac de sable", Utils.TypeSpéciale.SacDeSable, Utils.TypeCarte.Spéciale);
        piocheTresor.add(carte);
        carte = new CarteSpéciale("Sac de sable", Utils.TypeSpéciale.SacDeSable, Utils.TypeCarte.Spéciale);
        piocheTresor.add(carte);
        carte = new CarteSpéciale("houloucouptère", Utils.TypeSpéciale.Helicoptère, Utils.TypeCarte.Spéciale);
        piocheTresor.add(carte);
        carte = new CarteSpéciale("houloucouptère", Utils.TypeSpéciale.Helicoptère, Utils.TypeCarte.Spéciale);
        piocheTresor.add(carte);
        carte = new CarteSpéciale("houloucouptère", Utils.TypeSpéciale.Helicoptère, Utils.TypeCarte.Spéciale);
        piocheTresor.add(carte);

        Collections.shuffle(piocheTresor);

        for (Aventurier unAventurier : getLesAventuriers()) {
            System.out.println(unAventurier.getNomJoueur() + " : " + unAventurier.getNomRole());
            if (unAventurier.getNomRole() == "Plongeur") {
                unAventurier.setPosition(1, 1);
            } else if (unAventurier.getNomRole() == "Pilote") {
                unAventurier.setPosition(2, 3);
            } else if (unAventurier.getNomRole() == "Explorateur") {
                unAventurier.setPosition(2, 4);
            } else if (unAventurier.getNomRole() == "Messager") {
                unAventurier.setPosition(1, 2);
            } else if (unAventurier.getNomRole() == "Navigateur") {
                unAventurier.setPosition(1, 3);
            } else if (unAventurier.getNomRole() == "Ingenieur") {
                unAventurier.setPosition(0, 3);
            }
        }

        //En Haut à Gauche
        getLaGrille().addTuile(0, 0, null);
        getLaGrille().addTuile(0, 1, null);
        getLaGrille().addTuile(1, 0, null);

        //En Haut à Droite
        getLaGrille().addTuile(0, 4, null);
        getLaGrille().addTuile(0, 5, null);
        getLaGrille().addTuile(1, 5, null);

        //En Bas à Gauche
        getLaGrille().addTuile(4, 0, null);
        getLaGrille().addTuile(5, 0, null);
        getLaGrille().addTuile(5, 1, null);

        //En Haut à Droite
        getLaGrille().addTuile(5, 4, null);
        getLaGrille().addTuile(5, 5, null);
        getLaGrille().addTuile(4, 5, null);

        uneTuile = new Tuile("Le Pont Des Abimes");
        getLaGrille().addTuile(0, 2, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileSpawn("La Porte De Bronze", Pion.ROUGE);
        getLaGrille().addTuile(0, 3, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileTresor("La Caverne Des Ombres", cristal);
        getLaGrille().addTuile(1, 1, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileSpawn("La Porte De Fer", Pion.GRISFONCE);
        getLaGrille().addTuile(1, 2, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileSpawn("La Porte d'Or", Pion.JAUNE);
        getLaGrille().addTuile(1, 3, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new Tuile("Les Falaises de L'Oubli");
        getLaGrille().addTuile(1, 4, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileTresor("Le Palais de Corail", calice);
        getLaGrille().addTuile(2, 0, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new Tuile("La Porte d'Argent");
        getLaGrille().addTuile(2, 1, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new Tuile("Les Dunes De L'Illusion");
        getLaGrille().addTuile(2, 2, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileSpawn("Heliport", Pion.BLEU);
        getLaGrille().addTuile(2, 3, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileSpawn("La Porte De Cuivre", Pion.VERT);
        getLaGrille().addTuile(2, 4, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileTresor("Le Jardin Des Hurlements", statue);
        getLaGrille().addTuile(2, 5, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new Tuile("La Foret Pourpre");
        getLaGrille().addTuile(3, 0, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new Tuile("Le Lagon Perdu");
        getLaGrille().addTuile(3, 1, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new Tuile("Le Marais Brumeux");
        getLaGrille().addTuile(3, 2, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new Tuile("Observatoire");
        getLaGrille().addTuile(3, 3, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new Tuile("Le Rocher Fantome");
        getLaGrille().addTuile(3, 4, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileTresor("La Caverne Du Brasier", cristal);
        getLaGrille().addTuile(3, 5, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileTresor("Le Temple Du Soleil", pierre);
        getLaGrille().addTuile(4, 1, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileTresor("Le Temple De La Lune", pierre);
        getLaGrille().addTuile(4, 2, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileTresor("Le Palais Des Marees", calice);
        getLaGrille().addTuile(4, 3, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new Tuile("Le Val Du Crepuscule");
        getLaGrille().addTuile(4, 4, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new Tuile("La Tour Du Guet");
        getLaGrille().addTuile(5, 2, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileTresor("Le Jardin Des Murmures", statue);
        getLaGrille().addTuile(5, 3, uneTuile);

        uneCarte = new CarteInondation(uneTuile); // On créer une carte lié a la tuile et on l'ajoute dans la pioche innondation
        getPiocheInondation().add(uneCarte); // A faire pour toute les cases :/ :$

        Collections.shuffle(piocheInondation);

        setAventurierActuel(lesAventuriers.get(0));

        for (CarteInondation test : getPiocheInondation()){
         System.out.println(test.getNom());
         }
        for (Aventurier aventurier : lesAventuriers) {
            int i = 0;
            while (i != 2) {
                Carte cartePioche = piocheTresor.get(piocheTresor.size() - 1);
                if (cartePioche.getType() != Utils.TypeCarte.Montée) {
                    aventurier.ajouterMain(cartePioche);
                    defausseTresor.add(cartePioche);
                    piocheTresor.remove(cartePioche);
                    i++;
                    System.out.println("Je pioche");
                } else {
                    System.out.println("Montée des eaux on remélange");
                    Collections.shuffle(piocheTresor);
                }

            }

        }
        

    }

    public void InitialiserTestPartie() {
        setPartieFinie(false);
        setFinDuTour(false);

        defausseInondation = new ArrayList();
        bannieInondation = new ArrayList();
        piocheInondation = new ArrayList();
        piocheTresor = new ArrayList();
        defausseTresor = new ArrayList();
        setTresors((ArrayList<Tresor>) new ArrayList());
        tresorsRestant = new ArrayList();

        Tresor calice = new Tresor("Le Calice de l’onde", Utils.TypeTresor.BLEU);
        tresorsRestant.add(calice);
        Tresor pierre = new Tresor("La Pierre sacrée", Utils.TypeTresor.GRIS);
        tresorsRestant.add(pierre);
        Tresor statue = new Tresor("La Statue du zéphyr", Utils.TypeTresor.JAUNE);
        tresorsRestant.add(statue);
        Tresor cristal = new Tresor("Le Cristal ardent", Utils.TypeTresor.ROUGE);
        tresorsRestant.add(cristal);
        carte = new CarteMontée("Montée", Utils.TypeCarte.Montée);
        piocheTresor.add(carte);
        carte = new CarteMontée("Montée", Utils.TypeCarte.Montée);
        piocheTresor.add(carte);
        for (int i = 0; i < 5; i++) {
            carte = new CarteTrésor("Le Calice de l’onde", calice, Utils.TypeCarte.Trésor);
            piocheTresor.add(carte);
            carte = new CarteTrésor("La Pierre sacrée", pierre, Utils.TypeCarte.Trésor);
            piocheTresor.add(carte);
            carte = new CarteTrésor("La Statue du zéphyr", statue, Utils.TypeCarte.Trésor);
            piocheTresor.add(carte);
            carte = new CarteTrésor("Le Cristal ardent", cristal, Utils.TypeCarte.Trésor);
            piocheTresor.add(carte);
        }
        carte = new CarteSpéciale("Sac de sable", Utils.TypeSpéciale.SacDeSable, Utils.TypeCarte.Spéciale);
        piocheTresor.add(carte);
        carte = new CarteSpéciale("Sac de sable", Utils.TypeSpéciale.SacDeSable, Utils.TypeCarte.Spéciale);
        piocheTresor.add(carte);
        carte = new CarteSpéciale("houloucouptère", Utils.TypeSpéciale.Helicoptère, Utils.TypeCarte.Spéciale);
        piocheTresor.add(carte);
        carte = new CarteSpéciale("houloucouptère", Utils.TypeSpéciale.Helicoptère, Utils.TypeCarte.Spéciale);
        piocheTresor.add(carte);
        carte = new CarteSpéciale("houloucouptère", Utils.TypeSpéciale.Helicoptère, Utils.TypeCarte.Spéciale);
        piocheTresor.add(carte);

        Collections.shuffle(piocheTresor);

        lesAventuriers = new ArrayList();
        getLesAventuriers().add(new Ingenieur("Joueur1"));
        getLesAventuriers().add(new Messager("Joueur2"));
        getLesAventuriers().add(new Pilote("Joueur3"));
        getLesAventuriers().add(new Plongeur("Joueur4"));

        for (Aventurier unAventurier : getLesAventuriers()) {
            System.out.println(unAventurier.getNomJoueur());
            if (unAventurier.getNomRole() == "Plongeur") {
                unAventurier.setPosition(2, 3);
            } else if (unAventurier.getNomRole() == "Pilote") {
                unAventurier.setPosition(2, 0);
            } else if (unAventurier.getNomRole() == "Explorateur") {
                unAventurier.setPosition(2, 4);
            } else if (unAventurier.getNomRole() == "Messager") {
                unAventurier.setPosition(1, 4);
            } else if (unAventurier.getNomRole() == "Navigateur") {
                unAventurier.setPosition(1, 3);
            } else if (unAventurier.getNomRole() == "Ingenieur") {
                unAventurier.setPosition(2, 1);
            }
        }

        //En Haut à Gauche
        getLaGrille().addTuile(0, 0, null);
        getLaGrille().addTuile(0, 1, null);
        getLaGrille().addTuile(1, 0, null);

        //En Haut à Droite
        getLaGrille().addTuile(0, 4, null);
        getLaGrille().addTuile(0, 5, null);
        getLaGrille().addTuile(1, 5, null);

        //En Bas à Gauche
        getLaGrille().addTuile(4, 0, null);
        getLaGrille().addTuile(5, 0, null);
        getLaGrille().addTuile(5, 1, null);

        //En Haut à Droite
        getLaGrille().addTuile(5, 4, null);
        getLaGrille().addTuile(5, 5, null);
        getLaGrille().addTuile(4, 5, null);

        uneTuile = new Tuile("Le Pont Des Abimes");
        getLaGrille().addTuile(0, 2, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileSpawn("La Porte De Bronze", Pion.ROUGE);
        getLaGrille().addTuile(0, 3, uneTuile);
        getLaGrille().getTuile(0, 3).setEtat(Utils.EtatTuile.INONDEE);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileTresor("La Caverne Des Ombres", cristal);
        uneTuile.setEtat(Utils.EtatTuile.INONDEE);
        getLaGrille().addTuile(1, 1, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileSpawn("La Porte De Fer", Pion.GRISFONCE);
        uneTuile.setEtat(Utils.EtatTuile.INONDEE);
        getLaGrille().addTuile(1, 2, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileSpawn("La Porte d'Or", Pion.JAUNE);
        getLaGrille().addTuile(1, 3, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new Tuile("Les Falaises de L'Oubli");
        getLaGrille().addTuile(1, 4, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileTresor("Le Palais de Corail", calice);
        uneTuile.setEtat(Utils.EtatTuile.INONDEE);
        getLaGrille().addTuile(2, 0, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new Tuile("La Porte d'Argent");
        uneTuile.setEtat(Utils.EtatTuile.INONDEE);
        getLaGrille().addTuile(2, 1, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new Tuile("Les Dunes De L'Illusion");
        uneTuile.setEtat(Utils.EtatTuile.INONDEE);
        getLaGrille().addTuile(2, 2, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileSpawn("Heliport", Pion.BLEU);
        getLaGrille().addTuile(2, 3, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileSpawn("La Porte De Cuivre", Pion.VERT);
        uneTuile.setEtat(Utils.EtatTuile.INONDEE);
        getLaGrille().addTuile(2, 4, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileTresor("Le Jardin Des Hurlements", statue);
        uneTuile.setEtat(Utils.EtatTuile.INONDEE);
        getLaGrille().addTuile(2, 5, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new Tuile("La Foret Pourpre");
        uneTuile.setEtat(Utils.EtatTuile.COULEE);
        getLaGrille().addTuile(3, 0, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new Tuile("Le Lagon Perdu");
        uneTuile.setEtat(Utils.EtatTuile.COULEE);
        getLaGrille().addTuile(3, 1, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new Tuile("Le Marais Brumeux");
        uneTuile.setEtat(Utils.EtatTuile.COULEE);
        getLaGrille().addTuile(3, 2, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new Tuile("Observatoire");
        uneTuile.setEtat(Utils.EtatTuile.COULEE);
        getLaGrille().addTuile(3, 3, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new Tuile("Le Rocher Fantome");
        uneTuile.setEtat(Utils.EtatTuile.INONDEE);
        getLaGrille().addTuile(3, 4, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileTresor("La Caverne Du Brasier", cristal);
        uneTuile.setEtat(Utils.EtatTuile.COULEE);
        getLaGrille().addTuile(3, 5, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileTresor("Le Temple Du Soleil", pierre);
        uneTuile.setEtat(Utils.EtatTuile.COULEE);
        getLaGrille().addTuile(4, 1, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileTresor("Le Temple De La Lune", pierre);
        getLaGrille().addTuile(4, 2, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileTresor("Le Palais Des Marees", calice);
        uneTuile.setEtat(Utils.EtatTuile.COULEE);
        getLaGrille().addTuile(4, 3, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new Tuile("Le Val Du Crepuscule");
        uneTuile.setEtat(Utils.EtatTuile.ASSECHEE);
        getLaGrille().addTuile(4, 4, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new Tuile("La Tour Du Guet");
        uneTuile.setEtat(Utils.EtatTuile.COULEE);
        getLaGrille().addTuile(5, 2, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileTresor("Le Jardin Des Murmures", statue);
        uneTuile.setEtat(Utils.EtatTuile.ASSECHEE);
        getLaGrille().addTuile(5, 3, uneTuile);
        uneCarte = new CarteInondation(uneTuile); // On créer une carte lié a la tuile et on l'ajoute dans la pioche innondation
        getPiocheInondation().add(uneCarte); // A faire pour toute les cases :/ :$

        Collections.shuffle(piocheInondation);

        setAventurierActuel(lesAventuriers.get(0));

        /*for (Aventurier aventurier : lesAventuriers) {
            int i = 0;
            while (i != 2) {
                Carte cartePioche = piocheTresor.get(piocheTresor.size() - 1);
                if (cartePioche.getType() != Utils.TypeCarte.Montée) {
                    aventurier.ajouterMain(cartePioche);
                    defausseTresor.add(cartePioche);
                    piocheTresor.remove(cartePioche);
                    i++;
                System.out.println("Je pioche");
                } else {
                    System.out.println("Montée des eaux on remélange");
                    Collections.shuffle(piocheTresor);
                }
            }

        }*/

    }

    /**
     * @return the laGrille
     */
    public Grille getLaGrille() {
        return laGrille;
    }

    /**
     * @param laGrille the laGrille to set
     */
    public void setLaGrille(Grille laGrille) {
        this.laGrille = laGrille;
    }

    /**
     * @return the lesAventuriers
     */
    public ArrayList<Aventurier> getLesAventuriers() {
        return lesAventuriers;
    }

    /**
     * @param lesAventuriers the lesAventuriers to set
     */
    public void setLesAventuriers(ArrayList<Aventurier> lesAventuriers) {
        this.lesAventuriers = lesAventuriers;
    }

    /**
     * @return the aventurierActuel
     */
    public Aventurier getAventurierActuel() {
        return aventurierActuel;
    }

    /**
     * @param aventurierActuel the aventurierActuel to set
     */
    public void setAventurierActuel(Aventurier aventurierActuel) {
        this.aventurierActuel = aventurierActuel;
    }

    /**
     * @return the PartieFinie
     */
    public boolean isPartieFinie() {
        return PartieFinie;
    }

    /**
     * @param PartieFinie the PartieFinie to set
     */
    public void setPartieFinie(boolean PartieFinie) {
        this.PartieFinie = PartieFinie;
    }

    /**
     * @return the finDuTour
     */
    public boolean isFinDuTour() {
        return finDuTour;
    }

    /**
     * @param finDuTour the finDuTour to set
     */
    public void setFinDuTour(boolean finDuTour) {
        this.finDuTour = finDuTour;
    }

    public void finDuTourPartie1() {

        if (isGagne()) {
            System.out.println("Partie gagnée");
            ihmIleInterdite.fin(0);
        } else {
            
            InonderFinTour(getPalierNiveau(), piocheInondation);
            ihmIleInterdite.MAJTuile(laGrille, lesAventuriers, aventurierActuel);

            if (isPerdu() != 0) {
                System.out.println("Partie perdue");
                ihmIleInterdite.fin(isPerdu());
            } else {

            ihmIleInterdite.setEnabled(false);
            piocheFinTour();

            this.getAventurierActuel().reset();
            if (this.getLesAventuriers().size() - 1 == this.getLesAventuriers().lastIndexOf(getAventurierActuel())) {
                setAventurierActuel(getLesAventuriers().get(0));
            } else {
                setAventurierActuel(getLesAventuriers().get(this.getLesAventuriers().lastIndexOf(getAventurierActuel()) + 1));
            }



                ihmIleInterdite.setJoueurCourant(getAventurierActuel());
                ihmIleInterdite.MAJInfo(this);

                ihmIleInterdite.MAJMain(getAventurierActuel());
                ihmIleInterdite.MAJJoueur(getAventurierActuel());
                ihmIleInterdite.MAJTuile(laGrille, lesAventuriers, aventurierActuel);
                ihmIleInterdite.MAJBoutons(getAventurierActuel(), this);
            }
        }
    }

    public void finDuTourPartie2() {

        setFinDuTour(false);

        ihmIleInterdite.MAJMain(getAventurierActuel());
        ihmIleInterdite.setEnabled(true);

    }

    /**
     * @return the bannieInondation
     */
    public ArrayList<CarteInondation> getBannieInondation() {
        return bannieInondation;
    }

    /**
     * @param bannieInondation the bannieInondation to set
     */
    public void setBannieInondation(ArrayList<CarteInondation> bannieInondation) {
        this.bannieInondation = bannieInondation;
    }

    /**
     * @return the piocheInondation
     */
    public ArrayList<CarteInondation> getPiocheInondation() {
        return piocheInondation;
    }

    /**
     * @param piocheInondation the piocheInondation to set
     */
    public void setPiocheInondation(ArrayList<CarteInondation> piocheInondation) {
        this.piocheInondation = piocheInondation;
    }

    public void piocheFinTour() {

        for (int i = 0; i < 2; i++) {

            if (piocheTresor.size() == 0) {
                Collections.shuffle(defausseTresor);
                for (Carte carte : defausseTresor) {
                    piocheTresor.add(carte);

                }
                defausseTresor.clear();
            }

            Carte cartePioche = piocheTresor.get(piocheTresor.size() - 1);
            if (cartePioche.getNomCarte() == "Montée") {
                System.out.println("Montée des eaux");
                setNiveauEau(getNiveauEau() + 0.5);
                defausseTresor.add(cartePioche);
                piocheTresor.remove(cartePioche);
                Collections.shuffle(defausseInondation);
                for (CarteInondation carte : defausseInondation) {
                    piocheInondation.add(carte);

                }
                defausseInondation.clear();

            } else {
                getAventurierActuel().ajouterMain(cartePioche);
                defausseTresor.add(cartePioche);
                piocheTresor.remove(cartePioche);
            }
        }

    }

    public void InonderFinTour(double niveau, ArrayList<CarteInondation> cartes) {
        melanger(cartes);
        while ((int) niveau != 0) {
            if (cartes.isEmpty()) {
                Collections.shuffle(defausseInondation);
                for (CarteInondation carte : defausseInondation) {
                    piocheInondation.add(carte);

                }
                defausseInondation.clear();
            }
            int indiceAuHasard = (int) (Math.random() * (cartes.size() - 1));
            Tuile tuile = cartes.get(indiceAuHasard).getTuile();
            if (tuile.getEtat() == Utils.EtatTuile.ASSECHEE) {
                tuile.setEtat(Utils.EtatTuile.INONDEE);
                defausseInondation.add(piocheInondation.get(indiceAuHasard));
                piocheInondation.remove(indiceAuHasard);

            } else if (tuile.getEtat() == Utils.EtatTuile.INONDEE) {
                tuile.setEtat(Utils.EtatTuile.COULEE);
                bannieInondation.add(piocheInondation.get(indiceAuHasard));
                piocheInondation.remove(indiceAuHasard);
            }

            niveau = niveau - 1;
        }
    }

    public void melanger(ArrayList<CarteInondation> cartes) {
        Collections.shuffle(cartes);

    }

    /*public boolean verifTresor() {
     int x = getAventurierActuel().getX();
     int y = getAventurierActuel().getY();
     return 
     for (int l = 0; l <= 5; l++) {
     for (int c = 0; c <= 5; c++) {

     }*/
    public void recupererTresor() {
        int i = 0;

        if (laGrille.getTuile(getAventurierActuel().getX(), getAventurierActuel().getY()).getTresor() != null) {
            System.out.println("Trésor présent sur la tuile");
            for (Carte carte : getAventurierActuel().getMain()) {
                if (carte.getNomCarte() == laGrille.getTuile(getAventurierActuel().getX(), getAventurierActuel().getY()).getTresor().getNom()) {
                    i++;
                }
            }
            if (i >= 4) {
                getAventurierActuel().setPA(getAventurierActuel().getPA()-1);
                getTresors().add(laGrille.getTuile(getAventurierActuel().getX(), getAventurierActuel().getY()).getTresor());
                tresorsRestant.remove(laGrille.getTuile(getAventurierActuel().getX(), getAventurierActuel().getY()).getTresor());
                tresors.add(laGrille.getTuile(getAventurierActuel().getX(), getAventurierActuel().getY()).getTresor());
                System.out.println("Trésor récupéré");
                int y = 0;
                for (Carte main : (ArrayList<Carte>) (getAventurierActuel().getMain().clone())) {
                    if (y == 4) {
                        break;
                    }
                    if (main.getNomCarte() == laGrille.getTuile(getAventurierActuel().getX(), getAventurierActuel().getY()).getTresor().getNom()) {

                        defausseTresor.add(main);
                        getAventurierActuel().removeMain(main);
                        y++;
                    }
                }
                ihmIleInterdite.MAJBoutons(aventurierActuel, this);
                ihmIleInterdite.MAJMain(aventurierActuel);
            } else {
                System.out.println("Pas assez de carte");
            }

        } else {
            System.out.println("Il n'y a pas de trésor sur cette tuile");
        }
    }

    /**
     * @return the niveauEau
     */
    public double getNiveauEau() {
        return niveauEau;
    }

    /**
     * @param niveauEau the niveauEau to set
     */
    public void setNiveauEau(double niveauEau) {
        this.niveauEau = niveauEau;
    }

    /**
     * @return the defausseInondation
     */
    public ArrayList<CarteInondation> getDefausseInondation() {
        return defausseInondation;
    }

    /**
     * @param defausseInondation the defausseInondation to set
     */
    public void setDefausseInondation(ArrayList<CarteInondation> defausseInondation) {
        this.defausseInondation = defausseInondation;
    }

    @Override
    public void traiterMessage(Message msg) {
        Message m = new Message();
        switch (msg.type) {
            case DEMARRER:
                ihmIleInterdite.setJoueurCourant(getAventurierActuel());
                ihmIleInterdite.MAJJoueur(getAventurierActuel());
                ihmIleInterdite.MAJTuile(laGrille, lesAventuriers, aventurierActuel);
                ihmIleInterdite.MAJBoutons(getAventurierActuel(), this);
                break;
            case BTNDEPLACER:
                ihmIleInterdite.MAJTuile(laGrille, lesAventuriers, aventurierActuel);
                break;

            case DEPLACER:
                VerifPA();
                if (getAventurierActuel().getPA() > 0) {
                    getAventurierActuel().SeDeplacer(laGrille, "" + msg.posX + msg.posY);
                    ihmIleInterdite.MAJJoueur(getAventurierActuel());
                    ihmIleInterdite.MAJTuile(laGrille, lesAventuriers, aventurierActuel);
                    ihmIleInterdite.MAJBoutons(getAventurierActuel(), this);
                }
                break;

            case ASSECHER:
                VerifPA();
                if ((getAventurierActuel().getPA() > 0) || ((getAventurierActuel().getNomRole() == "Ingenieur") && (getAventurierActuel().isPouvoirPossible()))) {
                    getAventurierActuel().Assécher(laGrille, "" + msg.posX + msg.posY);
                    ihmIleInterdite.MAJJoueur(getAventurierActuel());
                    ihmIleInterdite.MAJTuile(laGrille, lesAventuriers, aventurierActuel);
                    ihmIleInterdite.MAJBoutons(getAventurierActuel(), this);
                }
                break;

            case TERMINER_TOUR_INITIALISATION:
                finDuTourPartie1();
                m = new Message();
                m.type = TypesMessage.TERMINER_TOUR_PARTIE1;
                this.traiterMessage(m);
                break;

            case TERMINER_TOUR_PARTIE1:
                if (getAventurierActuel().getMain().size() <= 5) {
                    System.out.println("Fin de tour partie 1");
                    finDuTourPartie2();
                    m = new Message();
                    m.type = TypesMessage.TERMINER_TOUR_PARTIE2;
                    this.traiterMessage(m);
                } else {
                    System.out.println(getAventurierActuel().getMain().size() - 5);
                    new IHMDefausse(getAventurierActuel(), this, getAventurierActuel().getMain().size() - 5, 1);
                }
                break;

            case TERMINER_TOUR_PARTIE2:
                if (getAventurierActuel().getMain().size() <= 5) {
                    System.out.println("Fin de tour partie 2");
                    ihmIleInterdite.setEnabled(true);
                } else {
                    new IHMDefausse(getAventurierActuel(), this, getAventurierActuel().getMain().size() - 5, 2);
                }
                break;

            case RECUPERER:
                VerifPA();
                recupererTresor();
                ihmIleInterdite.MAJTresor(this);
                break;

            case DEFAUSSER:
                getAventurierActuel().removeMain(msg.carte);
                break;

            case DONNER:
                VerifPA();
                if (msg.aventurier != null && msg.carte != null) {
                    getAventurierActuel().donnerC(msg.aventurier, msg.carte);

                }

                ihmIleInterdite.MAJBoutons(getAventurierActuel(), this);
                ihmIleInterdite.MAJMain(aventurierActuel);
                ihmIleInterdite.MAJJoueur(getAventurierActuel());

                break;
            case BTNDONNER:
                new IHMDonner(getAventurierActuel(), this, this);
                break;
            case SACDESABLE:
                VerifPA();
                System.out.println("SAC DE SABLE");
                getAventurierActuel().UtiliserSacDeSable(laGrille, "" + msg.posX + msg.posY);

                for (Carte carte : getAventurierActuel().getMain()) {
                    if (carte.getNomCarte() == "Sac de sable") {
                        getAventurierActuel().removeMain(carte);
                        break;
                    }
                }
                ihmIleInterdite.MAJJoueur(getAventurierActuel());
                ihmIleInterdite.MAJTuile(laGrille, lesAventuriers, aventurierActuel);
                ihmIleInterdite.MAJBoutons(getAventurierActuel(), this);
                ihmIleInterdite.MAJMain(aventurierActuel);

                break;
            case HELICOPTERE:
                VerifPA();
                System.out.println("HELICOPTERE");
                getAventurierActuel().SeDeplacerHelicoptère(laGrille, "" + msg.posX + msg.posY);
                int i = 0;
                for (Carte carte : getAventurierActuel().getMain()) {
                    if (carte.getNomCarte() == "houloucouptère") {
                        getAventurierActuel().removeMain(carte);
                        break;
                    }
                }

                ihmIleInterdite.MAJJoueur(getAventurierActuel());
                ihmIleInterdite.MAJTuile(laGrille, lesAventuriers, aventurierActuel);
                ihmIleInterdite.MAJBoutons(getAventurierActuel(), this);
                ihmIleInterdite.MAJMain(aventurierActuel);
                break;
            case AUTRESCARTES:
                ihmIleInterdite.setEnabled(false);
                new IHMCartes(getLesAventuriers(), ihmIleInterdite);
                break;
                  

        }

    }

    public int getNbJoueurSurCase() {
        int i = 0;
        for (Aventurier a : getLesAventuriers()) {
            if (a.getX() == getAventurierActuel().getX() && a.getY() == getAventurierActuel().getY() && a != getAventurierActuel()) {

                i++;
            }

        }
        return i;
    }

    // Retourne true si c'est gagné
    public boolean isGagne() {
        int i = 0;
        for (Aventurier a : getLesAventuriers()) {
            if (a.getX() == 2 && a.getY() == 3) {
                i++;
            }
        }
        if ((i == nombreAventurier) && (tresorsRestant.size() == 0)) {
            return true;
        } else {
            return false;
        }

    }

    // Retourne true si c'est perdu
    public int isPerdu() {
        
        /*
        0 = pas perdu
        1 = helicoptere
        2 = bloqué
        3 = eau
        4 = tresor
        
        
        */
        
        // On vérifie si l'Héliport a coulé, dans ce cas-ci la partie est perdue
        for (int l = 0; l <= 5; l++) {
            for (int c = 0; c <= 5; c++) {
                if (laGrille.getTuile(l, c) != null) {
                    if ((laGrille.getTuile(l, c).getNom() == "Heliport") && (laGrille.getTuile(l, c).getEtat() == Utils.EtatTuile.COULEE)) {
                        System.out.println("l'houloucouptère a coulé");
                        return 1;
                    }
                }
            }

        }
        // Si la liste des déplacements possibles est vide ou que la tuile sur laquelle est présente un joueur est coulée alors un des joueurs ne peut plus se déplacer 
        if (getAventurierActuel().DeplacementPossible(laGrille).isEmpty() && laGrille.getTuile(getAventurierActuel().getX(), getAventurierActuel().getY()).getEtat() == Utils.EtatTuile.COULEE && getAventurierActuel().getNomRole() != "Plongeur") {
            // /!\ CAS PLONGUEUR NON TRAITE /!\  
            return 2;

        }
        //Si le niveau d'eau atteint le seuil maximal
        if (niveauEau >= 5) {
            return 3;
        }

        int j = 0;
        // On parcourt une liste de trésors restants
        for (Tresor tresor : tresorsRestant) {

            int i = 0;
            for (int l = 0; l <= 5; l++) {
                for (int c = 0; c <= 5; c++) {
                    /*System.out.println(laGrille.getTuile(l, c) != null);
                     System.out.println(laGrille.getTuile(l, c).getEtat() != Utils.EtatTuile.COULEE);
                     System.out.println(laGrille.getTuile(l, c).getTresor() == tresor);*/

                    if (laGrille.getTuile(l, c) != null) {
                        if ((laGrille.getTuile(l, c).getEtat() != Utils.EtatTuile.COULEE) && (laGrille.getTuile(l, c).getTresor() == tresor)) {
                            i++;
                        }
                    }

                }
            }
            if (i != 0) {
                j++;
            }

        }
        if (j == tresorsRestant.size()) {
            return 0;
        } else {
            System.out.println("Trésor coulé");
            return 4;
        }

    }

    /**
     * @return the tresors
     */
    public ArrayList<Tresor> getTresors() {
        return tresors;
    }

    /**
     * @param tresors the tresors to set
     */
    public void setTresors(ArrayList<Tresor> tresors) {
        this.tresors = tresors;
    }

    public int getPalierNiveau() {
        if (niveauEau < 1) {
            return 2;
        } else if (niveauEau < 2.5) {
            return 3;
        } else if (niveauEau < 4) {
            return 4;
        } else {
            return 5;
        }
    }
    
    public void VerifPA() {
           if( getAventurierActuel().getPA() == 0){
               Utils.afficherInformation("Tu n'as pas assez de Points d'Action pour effectuer cette action.");
               
           }
 
    }

}
