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
    private Tresor tresor ;
    
    public CarteTrésor(String nom,Tresor type, Utils.TypeCarte typeC){
        super(nom, typeC);
        this.tresor = type;
    }
}
