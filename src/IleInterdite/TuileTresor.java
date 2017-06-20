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
public class TuileTresor extends Tuile {
    
    private Tresor tresor;
    private Utils.TypeTuile type;
    
    public TuileTresor(String nom, Tresor tresor) {
        super(nom);
        setTresor(tresor);
        this.type = Utils.TypeTuile.Tresor;
    }

    /**
     * @return the tresor
     */
    @Override
    public Tresor getTresor() {
        return tresor;
    }

    /**
     * @param tresor the tresor to set
     */
    public void setTresor(Tresor tresor) {
        this.tresor = tresor;
    }
    
}
