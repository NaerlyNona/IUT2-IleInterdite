/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author chenavje
 */
public class IhmFin extends JFrame implements ActionListener {
    private JLabel texteF;
    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(true);
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        texteF = new JLabel("Félicitations ! Vous avez perdu.");
        this.setSize(300, 100);
        this.add(texteF);
        this.setVisible(true);
    }
    
}
