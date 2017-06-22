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
import javax.swing.SwingConstants;
 
/**
 *
 * @author chenavje
 */
public class IhmAbandon extends JFrame implements ActionListener {
    private JLabel texteF;
    private JButton btnOui;
    private JButton btnNon;
    
    private JPanel panelBouton;
    private IhmFin finIhm;
    
    // 0 = perdu
    // 1 = Gagné
    public IhmAbandon(IhmFin fin){
        this.setLayout(new BorderLayout());
        btnOui = new JButton("Oui");
        btnNon = new JButton("Non");
       
        
        this.finIhm = fin;
        
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
 
       
        texteF = new JLabel("<html><b><p align=\"center\">Êtes vous faible a ce point ?", SwingConstants.CENTER);
        
          
        
        
        
        
        this.add(texteF, BorderLayout.NORTH);
        
        panelBouton = new JPanel(new GridLayout(1,2));
      
        panelBouton.add(btnOui);
        panelBouton.add(btnNon);
        
        this.add(panelBouton, BorderLayout.SOUTH);
        
        
        
        
        
        
        this.setVisible(true);
        
        
        
        btnOui.addActionListener(this);
        btnNon.addActionListener(this);
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == btnOui){
            this.setVisible(false);
            System.exit(0);
        } else if (e.getSource() == btnNon){
            finIhm.setVisible(true);
            this.setVisible(false);
            
        
    }
    
}
}