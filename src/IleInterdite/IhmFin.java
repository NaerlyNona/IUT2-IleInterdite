/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author chenavje
 */
public class IhmFin extends JFrame implements ActionListener {
    private JLabel texteF;
    private JButton btnQuitter;
    private JButton btnRestart;
    private JButton  btnMenu;
    private JPanel panelBouton;
    
    public IhmFin(){
        this.setLayout(new BorderLayout());
        btnQuitter = new JButton("Abandonner");
        btnRestart = new JButton("Recommencer");
        btnMenu = new JButton("Menu");
        
        
        
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(200, 400));
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
  
        texteF = new JLabel("Félicitations ! Vous avez perdu.");
        
        
        this.setSize(400, 200);
        this.add(texteF, BorderLayout.NORTH);
        
        panelBouton = new JPanel(new GridLayout(1,3));
      
        panelBouton.add(btnRestart);
        panelBouton.add(btnMenu);
        panelBouton.add(btnQuitter);
        this.add(panelBouton, BorderLayout.SOUTH);
        
        
        
        
        
        
        this.setVisible(true);
        
        
        
        btnRestart.addActionListener(this);
        btnQuitter.addActionListener(this);
        btnMenu.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == btnQuitter){
            this.setVisible(false);
            System.exit(0);
        } else if (e.getSource() == btnMenu){
            this.setVisible(false);
            new FenetreDebut();
        } else if (e.getSource() == btnRestart){
            this.setVisible(false);
            new Controleur();
        }
    }
    
}
