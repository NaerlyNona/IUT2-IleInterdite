/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import javax.swing.JButton;

/**
 *
 * @author monnetlu
 */
public class JButtonSpecial extends JButton {
    private int type;
    /* 0
    0 = Tuile
    1 = Main
    */
    
    public JButtonSpecial(int type){
        super();
        setType(type);
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