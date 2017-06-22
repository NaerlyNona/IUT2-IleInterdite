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
public class IhmFin extends JFrame implements ActionListener {
    private JLabel texteF;
    private JButton btnQuitter;
    private JButton btnRestart;
    private JButton  btnMenu;
    private JPanel panelBouton;
    private IHMIleInterdite ihmIle;
    
    // 0 = perdu
    // 1 = Gagné
    public IhmFin(int valeur, IHMIleInterdite ihm){
        this.setLayout(new BorderLayout());
        btnQuitter = new JButton("Abandonner");
        btnMenu = new JButton("Menu");
        this.ihmIle = ihm;
        
        
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(400, 200));
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
 
        if (valeur == 0) {
            texteF = new JLabel("<html><b><p align=\"center\">Félicitations ! Vous avez gagné!", SwingConstants.CENTER);
        } else {
            texteF = new JLabel("<html><b><p align=\"center\">Dommage ! Vous avez perdu. <br>", SwingConstants.CENTER);
            if (valeur == 1){
                texteF.setText(texteF.getText() + "Votre hélicoptère a coulé...");
            } else if (valeur == 2){
                texteF.setText(texteF.getText() + "Un des aventuriers est bloqué...");
            } else if (valeur == 3){
                texteF.setText(texteF.getText() + "Le niveau d'eau est devenu trop elevée...");
            } else {
                texteF.setText(texteF.getText() + "Un des trésors a été englouti...");
            }
            
        }
        
        
        
        this.setSize(400, 200);
        this.add(texteF, BorderLayout.NORTH);
        
        panelBouton = new JPanel(new GridLayout(1,3));
        panelBouton.add(btnMenu);
        panelBouton.add(btnQuitter);
        this.add(panelBouton, BorderLayout.SOUTH);
        
        
        
        
        
        
        this.setVisible(true);
        
        
       
        btnQuitter.addActionListener(this);
        btnMenu.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == btnQuitter){
            this.setVisible(false);
            new IhmAbandon(this);
        } else if (e.getSource() == btnMenu){
            this.setVisible(false);
            new FenetreDebut();
            ihmIle.fermer();
            
        }
    }
    
}
 
