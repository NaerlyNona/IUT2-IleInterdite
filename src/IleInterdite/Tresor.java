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
public class Tresor {
   private String nom;
   private Utils.TypeTresor type;
   
   
   public Tresor(String nom, Utils.TypeTresor type ){
       this.nom = nom;
       this.type = type;
   }
}
