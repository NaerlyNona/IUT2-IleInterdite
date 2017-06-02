/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import IleInterdite.Utils.Pion;

/**
 *
 * @author monnetlu
 */
public class Explorateur extends Aventurier {
    
        private String nomRole;
    
    public Explorateur(String leNomJoueur) {
        super(leNomJoueur, Pion.VERT);
        setNomRole("Explorateur");
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
    
 /*     
        @Override
    public void SeDeplacer(Grille laGrille,) {
        Position caseDroite = new Position(getPosition().getX()+1,getPosition().getY());
        Position caseGauche = new Position(getPosition().getX()-1,getPosition().getY());
        Position caseHaut = new Position(getPosition().getX(),getPosition().getY()+1);
        Position caseBas = new Position(getPosition().getX(),getPosition().getY()-1);
        Position caseHautDroit = new Position(getPosition().getX()+1,getPosition().getY()+1);
        Position caseHautGauche = new Position(getPosition().getX()+1,getPosition().getY()-1);
        Position caseBasDroite = new Position(getPosition().getX()-1,getPosition().getY()-1);
        Position caseBasGauche = new Position(getPosition().getX()-1,getPosition().getY()+1);
        int i = 0;
        
        System.out.println("Cases où les déplacements sont possibles :");
        
        if (laGrille.lesTuiles.containsKey(caseDroite) && laGrille.lesTuiles.get(caseDroite).getEtat() != Utils.EtatTuile.COULEE) {
            System.out.println("La case de droite est :" + caseDroite);
            i += 1;
        }
        
        if (laGrille.lesTuiles.containsKey(caseGauche) && laGrille.lesTuiles.get(caseGauche).getEtat() != Utils.EtatTuile.COULEE) {
            System.out.println("La case de gauche est :" + caseGauche);
            i += 1;
        }
        
        if (laGrille.lesTuiles.containsKey(caseHaut) && laGrille.lesTuiles.get(caseHaut).getEtat() != Utils.EtatTuile.COULEE) {
            System.out.println("La case du haut est :" + caseHaut);
            i += 1;
        }
        
        if (laGrille.lesTuiles.containsKey(caseBas) && laGrille.lesTuiles.get(caseBas).getEtat() != Utils.EtatTuile.COULEE) {
            System.out.println("La case du bas est :" + caseBas);
            i += 1;
        }
        
        if (laGrille.lesTuiles.containsKey(caseHautGauche) && laGrille.lesTuiles.get(caseHautGauche).getEtat() != Utils.EtatTuile.COULEE) {
            System.out.println("La case HautGauche est :" + caseHautGauche);
            i += 1;
        }
        
        if (laGrille.lesTuiles.containsKey(caseHautDroit) && laGrille.lesTuiles.get(caseHautDroit).getEtat() != Utils.EtatTuile.COULEE) {
            System.out.println("La case HautDroit est :" + caseHautDroit);
            i += 1;
        }
        
        if (laGrille.lesTuiles.containsKey(caseBasGauche) && laGrille.lesTuiles.get(caseBasGauche).getEtat() != Utils.EtatTuile.COULEE) {
            System.out.println("La case basGauche est :" + caseBasGauche);
            i += 1;
        }
        
        if (laGrille.lesTuiles.containsKey(caseBasDroite) && laGrille.lesTuiles.get(caseBasDroite).getEtat() != Utils.EtatTuile.COULEE) {
            System.out.println("La case basDroite est :" + caseBasDroite);
            i += 1;
        }
        
        
        if ( i == 0 ) {
            System.out.println("Aucun déplacement possible");
        }
    }*/
}
