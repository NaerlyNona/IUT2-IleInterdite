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
public class CarteSpéciale extends Carte {
    private String effet;
    private Utils.TypeSpéciale type;
    
    public CarteSpéciale(String nom, Utils.TypeSpéciale type){
        super(nom);
        this.type = type;
    }

    /**
     * @return the effet
     */
    public String getEffet() {
        return effet;
    }

    /**
     * @param effet the effet to set
     */
    public void setEffet(String effet) {
        this.effet = effet;
    }
}
