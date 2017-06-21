/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

/**
 *
 * @author chenavje
 */
public abstract class Carte {
    private String nomCarte;
    private Utils.TypeCarte type;
    
    public Carte(String nom, Utils.TypeCarte type){
        this.nomCarte = nom;
        this.type = type;
    }
    
    
    
    
    /**
     * @return the nomCarte
     */
    public String getNomCarte() {
        return nomCarte;
    }

    /**
     * @param nomCarte the nomCarte to set
     */
    public void setNomCarte(String nomCarte) {
        this.nomCarte = nomCarte;
    }
}
