/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;

/**
 *
 * @author monnetlu
 */
public class IHMDefausse extends JFrame implements ActionListener {

     private Observateur observateur;
     private Aventurier aventurier;
     private boolean finDuTour;
     
     private JLabel texteDefausse;
     private JComboBox listeCarte;
     private JButton btnConfirmer;
     
     
     
     private String[] lesCartes = new String[7];
     
     public IHMDefausse (Aventurier aventurier, Observateur observateur, boolean finDuTour){
         this.observateur = observateur;
         this.aventurier = aventurier;
         this.finDuTour = finDuTour;
         
         this.setSize(new Dimension(300,100));
         this.setLocationRelativeTo(null);
         this.setAlwaysOnTop(true);
         this.setUndecorated(true);
         //this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
         this.setLayout(new BorderLayout());
         
         texteDefausse = new JLabel("Choississez une carte à défausser:");
         this.add(texteDefausse,BorderLayout.NORTH);
         
         int i = 0;
         for (Carte uneCarte: aventurier.getMain()){
             lesCartes[i] = uneCarte.getNomCarte();
             i++;
         }
         listeCarte = new JComboBox(lesCartes);
         this.add(listeCarte,BorderLayout.CENTER);
         
         btnConfirmer = new JButton("Confirmer");
         this.add(btnConfirmer, BorderLayout.SOUTH);
         btnConfirmer.addActionListener(this);
         this.setVisible(true);
     }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConfirmer){
            Message m = new Message();
            if (finDuTour){
                 m.type = TypesMessage.DEFAUSSERFINDUTOUR;
            } else {
                 m.type = TypesMessage.DEFAUSSER;
            }
            String nomCarte = ((String)(listeCarte.getSelectedItem()));
            boolean carteTrouvé = false;
            int i = 0;
            while(!carteTrouvé){
                if (nomCarte == aventurier.getMain().get(i).getNomCarte()){
                    m.carte = aventurier.getMain().get(i);
                    carteTrouvé = true;
                }
                i++;
            }
            
            observateur.traiterMessage(m);
            this.dispose();

        }
    }
    
}
