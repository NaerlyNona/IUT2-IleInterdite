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
public class CarteTrésor extends Carte {
    private Utils.TypeTresor type ;
    
    public CarteTrésor(String nom, Utils.TypeTresor type){
        super(nom);
        this.type = type;
    }
}
