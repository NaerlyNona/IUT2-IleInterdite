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
public class Controleur implements Observateur {

    private IHMIleInterdite ihmIleInterdite;
    private int nombreAventurier = 4;
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
        ihmIleInterdite.MAJJoueur(getAventurierActuel());
        ihmIleInterdite.MAJTuile(laGrille, lesAventuriers, aventurierActuel);
    }

    public void InitialiserTestPartie() {
        setPartieFinie(false);
        setFinDuTour(false);

        defausseInondation = new ArrayList();
        bannieInondation = new ArrayList();
        piocheInondation = new ArrayList();
        piocheTresor = new ArrayList();
        defausseTresor = new ArrayList();
        tresors = new ArrayList();
        tresorsRestant = new ArrayList();

        Tresor calice = new Tresor("Le Calice de l’onde", Utils.TypeTresor.BLEU);
        tresorsRestant.add(calice);
        Tresor pierre = new Tresor("La Pierre sacrée", Utils.TypeTresor.GRIS);
        tresorsRestant.add(pierre);
        Tresor statue = new Tresor("La Statue du zéphyr", Utils.TypeTresor.JAUNE);
        tresorsRestant.add(statue);
        Tresor cristal = new Tresor("Le Cristal ardent", Utils.TypeTresor.ROUGE);
        tresorsRestant.add(cristal);

        carte = new CarteMontée("Montée");
        piocheTresor.add(carte);
        carte = new CarteMontée("Montée");
        piocheTresor.add(carte);
        for (int i = 0; i < 5; i++) {
            carte = new CarteTrésor("Le Calice de l’onde", calice);
            piocheTresor.add(carte);
            carte = new CarteTrésor("La Pierre sacrée", pierre);
            piocheTresor.add(carte);
            carte = new CarteTrésor("La Statue du zéphyr", statue);
            piocheTresor.add(carte);
            carte = new CarteTrésor("Le Cristal ardent", cristal);
            piocheTresor.add(carte);
        }
        carte = new CarteSpéciale("Sac de sable", Utils.TypeSpéciale.SacDeSable);
        piocheTresor.add(carte);
        carte = new CarteSpéciale("Sac de sable", Utils.TypeSpéciale.SacDeSable);
        piocheTresor.add(carte);
        carte = new CarteSpéciale("houloucouptère", Utils.TypeSpéciale.Helicoptère);
        piocheTresor.add(carte);
        carte = new CarteSpéciale("houloucouptère", Utils.TypeSpéciale.Helicoptère);
        piocheTresor.add(carte);
        carte = new CarteSpéciale("houloucouptère", Utils.TypeSpéciale.Helicoptère);
        piocheTresor.add(carte);

        Collections.shuffle(piocheTresor);

        lesAventuriers = new ArrayList();
        getLesAventuriers().add(new Explorateur("Joueur1"));
        getLesAventuriers().add(new Messager("Joueur2"));

        for (Aventurier unAventurier : getLesAventuriers()) {
            System.out.println(unAventurier.getNomJoueur());
            if (unAventurier.getNomRole() == "Messager") {
                unAventurier.setPosition(3, 5);
            }

            if (unAventurier.getNomRole() == "Pilote") {
                unAventurier.setPosition(2, 3);
            }

            if (unAventurier.getNomRole() == "Explorateur") {
                unAventurier.setPosition(1, 2);
            }
        }

        System.out.println("Nearly la petite pute");
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
        getLaGrille().addTuile(1, 1, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileSpawn("La Porte De Fer", Pion.VIOLET);
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
        getLaGrille().getTuile(2, 2).setEtat(Utils.EtatTuile.COULEE);
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
        getLaGrille().getTuile(3, 1).setEtat(Utils.EtatTuile.INONDEE);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new Tuile("Le Marais Brumeux");
        getLaGrille().addTuile(3, 2, uneTuile);
        getLaGrille().getTuile(3, 2).setEtat(Utils.EtatTuile.COULEE);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new Tuile("Observatoire");
        getLaGrille().addTuile(3, 3, uneTuile);
        getLaGrille().getTuile(3, 3).setEtat(Utils.EtatTuile.INONDEE);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new Tuile("Le Rocher Fantome");
        getLaGrille().addTuile(3, 4, uneTuile);
        getLaGrille().getTuile(3, 4).setEtat(Utils.EtatTuile.COULEE);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileTresor("La Caverne Du Brasier", cristal);
        getLaGrille().addTuile(3, 5, uneTuile);
        getLaGrille().getTuile(3, 5).setEtat(Utils.EtatTuile.INONDEE);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileTresor("Le Temple Du Soleil", pierre);
        getLaGrille().addTuile(4, 1, uneTuile);
        uneCarte = new CarteInondation(uneTuile);
        getPiocheInondation().add(uneCarte);

        uneTuile = new TuileTresor("Le Temple De La Lune", pierre);
        getLaGrille().addTuile(4, 2, uneTuile);
        getLaGrille().getTuile(4, 2).setEtat(Utils.EtatTuile.COULEE);
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
        getLaGrille().getTuile(5, 3).setEtat(Utils.EtatTuile.INONDEE);

        uneCarte = new CarteInondation(uneTuile); // On créer une carte lié a la tuile et on l'ajoute dans la pioche innondation
        getPiocheInondation().add(uneCarte); // A faire pour toute les cases :/ :$

        Collections.shuffle(piocheInondation);
        System.out.println(getLaGrille().getTuile(5, 3).getNom());

        setAventurierActuel(lesAventuriers.get(0));

        /*for (CarteInondation test : getPiocheInondation()){
            System.out.println(test.getNom());
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

    public void finDuTour() {
        if (isGagne()) {
            System.out.println("c gagné");
        }
        if (isPerdu()) {

        }
        
        piocheFinTour();
        
        this.getAventurierActuel().reset();
        if (this.getLesAventuriers().size() - 1 == this.getLesAventuriers().lastIndexOf(getAventurierActuel())) {
            setAventurierActuel(getLesAventuriers().get(0));
        } else {
            setAventurierActuel(getLesAventuriers().get(this.getLesAventuriers().lastIndexOf(getAventurierActuel()) + 1));
        }

        
        InonderFinTour(getNiveauEau(), piocheInondation);

        setFinDuTour(false);

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
                System.out.println("Bite");
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
        System.out.println(piocheTresor.size());
        getAventurierActuel().modifMain();

    }

    public void InonderFinTour(double niveau, ArrayList<CarteInondation> cartes) {
        melanger(cartes);
        while ((int) niveau != 0) {
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
        System.out.println("test3");
        if (laGrille.getTuile(getAventurierActuel().getX(), getAventurierActuel().getY()).getTresor() != null) {
            System.out.println("Il y a un trésor ici starfoulila");
            for (Carte carte : getAventurierActuel().getMain()) {
                if (carte.getNomCarte() == laGrille.getTuile(getAventurierActuel().getX(), getAventurierActuel().getY()).getTresor().getNom()) {
                    System.out.println(carte.getNomCarte());
                    System.out.println(laGrille.getTuile(getAventurierActuel().getX(), getAventurierActuel().getY()).getTresor().getNom());
                    i++;
                }
            }
            if (i == 4) {
                tresors.add(laGrille.getTuile(getAventurierActuel().getX(), getAventurierActuel().getY()).getTresor());
                tresorsRestant.remove(laGrille.getTuile(getAventurierActuel().getX(), getAventurierActuel().getY()).getTresor());
                System.out.println("recuperation du trésor en cours... ah c fini laul");
                for (Carte main : getAventurierActuel().getMain()) {
                    if (main.getNomCarte() == laGrille.getTuile(getAventurierActuel().getX(), getAventurierActuel().getY()).getTresor().getNom()) {
                     
                        defausseTresor.add(main);
                        getAventurierActuel().removeMain(main);
                    }
                }
            }
             
            } else {
                System.out.println("Il n'y a pas de trésor ici starfoulila");
            }
        }
    
    public void donnerCarte(){
        if (getAventurierActuel().getMain().size() > 0){
            int i = 1;
            System.out.println("====Carte a donner====");
            for (Carte c : getAventurierActuel().getMain()){   
                System.out.println("Carte n°"+i+" : "+c.getNomCarte());
                i++;
            }
            Scanner sc = new Scanner(System.in);
            int choix = sc.nextInt();
            
            for (Aventurier a : lesAventuriers){
                System.out.println("====Donner a qui====");
                if (a.getX()==getAventurierActuel().getX() && a.getY()==getAventurierActuel().getY()){
                    
                }
            }
                
            //a.ajouterMain(getAventurierActuel().getMain().get(choix-1));
            getAventurierActuel().getMain().remove(choix-1);
        } else { System.out.println("starfoulila");
        
            
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
        switch (msg.type) {
            case DEMARRER:
                ihmIleInterdite.MAJJoueur(getAventurierActuel());
                ihmIleInterdite.MAJTuile(laGrille, lesAventuriers, aventurierActuel);
                break;

            case DEPLACER:
                if (getAventurierActuel().getPA() > 0) {
                    getAventurierActuel().SeDeplacer(laGrille, "" + msg.posX + msg.posY);
                    ihmIleInterdite.MAJJoueur(getAventurierActuel());
                    ihmIleInterdite.MAJTuile(laGrille, lesAventuriers, aventurierActuel);
                }
                break;

            case ASSECHER:
                if (getAventurierActuel().getPA() > 0) {
                    getAventurierActuel().Assécher(laGrille, "" + msg.posX + msg.posY);
                    ihmIleInterdite.MAJJoueur(getAventurierActuel());
                    ihmIleInterdite.MAJTuile(laGrille, lesAventuriers, aventurierActuel);
                }
                break;

            case TERMINER_TOUR:
                finDuTour();
                ihmIleInterdite.MAJMain(getAventurierActuel());
                ihmIleInterdite.MAJJoueur(getAventurierActuel());
                ihmIleInterdite.MAJTuile(laGrille, lesAventuriers, aventurierActuel);
                break;
            case AUTREACTION:
                System.out.println("test2");
                recupererTresor();
                break;
        }

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
    public boolean isPerdu() {

        if (laGrille.getTuile(2, 3).getEtat() == Utils.EtatTuile.COULEE) {
            System.out.println("l'houloucouptère a coulé");
            return true;
        }
        int j = 0;
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
            return false;
        } else {
            System.out.println("le trésor a coulé");
            return true;
        }

    }

}
