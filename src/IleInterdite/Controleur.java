/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import IleInterdite.Utils.Pion;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.*;

/**
 *
 * @author monnetlu
 */
public class Controleur {

    private Grille laGrille;
    private Tuile uneTuile;
    private Aventurier aventurierActuel;
    private ArrayList<Aventurier> lesAventuriers;
    private boolean PartieFinie;
    private boolean finDuTour;
    
    public Controleur(){
        setAventurierActuel(new Aventurier("init", Pion.VERT));
        setLaGrille(new Grille());
    }

    public void InitialiserTestPartie() {
        setPartieFinie(false);
        setFinDuTour(false);

        lesAventuriers = new ArrayList();
        getLesAventuriers().add(new Explorateur("Joueur1"));
        getLesAventuriers().add(new Messager("Joueur2"));

        for (Aventurier unAventurier : getLesAventuriers()) {
            if (unAventurier.getNomRole() == "Messager") {
                unAventurier.setPosition(3, 5);
            }

            if (unAventurier.getNomRole()== "Explorateur") {
                unAventurier.setPosition(2, 3);
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
        getLaGrille().addTuile(0,2, uneTuile);

        uneTuile = new TuileSpawn("La Porte De Bronze", Pion.ROUGE);
        getLaGrille().addTuile(0,3, uneTuile);

        uneTuile = new Tuile("La Caverne Des Ombres");
        getLaGrille().addTuile(1,1, uneTuile);

        uneTuile = new TuileSpawn("La Porte De Fer", Pion.VIOLET);
        getLaGrille().addTuile(1,2, uneTuile);

        uneTuile = new TuileSpawn("La Porte d'Or", Pion.JAUNE);
        getLaGrille().addTuile(1,3, uneTuile);

        uneTuile = new Tuile("Les Falaises de L'Oubli");
        getLaGrille().addTuile(1,4, uneTuile);

        uneTuile = new Tuile("Le Palais de Corail");
        getLaGrille().addTuile(2,0, uneTuile);

        uneTuile = new Tuile("La Porte d'Argent");
        getLaGrille().addTuile(2,1, uneTuile);

        uneTuile = new Tuile("Les Dunes De L'Illusion");
        getLaGrille().addTuile(2,2, uneTuile);

        uneTuile = new TuileSpawn("Heliport", Pion.BLEU);
        getLaGrille().addTuile(2,3, uneTuile);

        uneTuile = new TuileSpawn("La Porte De Cuivre", Pion.VERT);
        getLaGrille().addTuile(2,4, uneTuile);

        uneTuile = new Tuile("Le Jardin Des Hurlements");
        getLaGrille().addTuile(2,5, uneTuile);

        uneTuile = new Tuile("La Foret Pourpre");
        getLaGrille().addTuile(3,0, uneTuile);

        uneTuile = new Tuile("Le Lagon Perdu");
        getLaGrille().addTuile(3,1, uneTuile);

        uneTuile = new Tuile("Le Marais Brumeux");
        getLaGrille().addTuile(3,2, uneTuile);


        uneTuile = new Tuile("Observatoire");
        getLaGrille().addTuile(3,3, uneTuile);

        uneTuile = new Tuile("Le Rocher Fantome");
        getLaGrille().addTuile(3,4, uneTuile);

        uneTuile = new Tuile("La Caverne Du Brasier");
        getLaGrille().addTuile(3,5, uneTuile);

        uneTuile = new Tuile("Le Temple Du Soleil");
        getLaGrille().addTuile(4,1, uneTuile);

        uneTuile = new Tuile("Le Temple De La Lune");
        getLaGrille().addTuile(4,2, uneTuile);

        uneTuile = new Tuile("Le Val Du Crepuscule");
        getLaGrille().addTuile(4,3, uneTuile);

        uneTuile = new Tuile("La Tour Du Guet");
        getLaGrille().addTuile(5,2, uneTuile);

        uneTuile = new Tuile("Le Jardin Des Murmures");
        getLaGrille().addTuile(5,3, uneTuile);
        
        System.out.println(getLaGrille().getTuile(5, 3).getNom());
        
        setAventurierActuel(lesAventuriers.get(0));
        
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
    
    public void finDuTour(){
        if (this.getLesAventuriers().contains(getLesAventuriers().indexOf(getAventurierActuel())+1)){
        this.setAventurierActuel(getLesAventuriers().get(getLesAventuriers().indexOf(getAventurierActuel())+1));
        } else{
            this.setAventurierActuel(getLesAventuriers().get(0));
        }
        setFinDuTour(false);
    }
    
}
