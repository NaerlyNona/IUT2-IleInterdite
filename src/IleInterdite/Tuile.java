/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

/**
 *
 * @author monnetlu
 */
public class Tuile {
    private String nom;
    private Utils.EtatTuile etat;
    private int type;
    
    /*
        0 = tuile normal
        1 = tuile trésor
        2 = tuile spawn
    */
    
    
    public Tuile(String nom){
        setNom(nom);
        setType(0);
        setEtat(Utils.EtatTuile.ASSECHEE);
    }

    /**

     * @return the nom
     */
    public String getNom() {
        return nom;
    }
    
    public Tresor getTresor() {
        return null;
    }
    
    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the etat
     */
    public Utils.EtatTuile getEtat() {
        return etat;
    }

    /**
     * @param etat the etat to set
     */
    public void setEtat(Utils.EtatTuile etat) {
        this.etat = etat;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }
    
  
}
