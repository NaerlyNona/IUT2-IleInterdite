/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
/**
 *
 * @author monnetlu
 */
public class JButtonTuile extends JButtonSpecial {
    private int posX;
    private int posY;

    
    public JButtonTuile(int posX, int posY) {
        super(0);
        setPosX(posX);
        setPosY(posY);
        this.setLayout(new GridLayout(2,2));
        this.setMargin(new Insets(50,0,0,0));
    }

    /**
     * @return the posX
     */
    public int getPosX() {
        return posX;
    }

    /**
     * @param posX the posX to set
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * @return the posY
     */
    public int getPosY() {
        return posY;
    }

    /**
     * @param posY the posY to set
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }
    
    
}
