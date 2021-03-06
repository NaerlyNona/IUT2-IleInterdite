/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;

/**
 *
 * @author monnetlu
 */
public class IHMDefausse extends JFrame implements ActionListener {

     private Observateur observateur;
     private Aventurier aventurier;
     private int nbDefausse;
     private int partieFinDuTour;
     
     private JLabel texteDefausse;
     private JComboBox listeCarte;
     private JButton btnConfirmer;
     private JPanel panelMain;
     
     
     
     private String[] lesCartes;
     
     public IHMDefausse (Aventurier aventurier, Observateur observateur, int nbDefausse, int partieFinDuTour){
         this.observateur = observateur;
         this.aventurier = aventurier;
         this.nbDefausse = nbDefausse;
         this.partieFinDuTour = partieFinDuTour;
         
         
         
         lesCartes = new String[aventurier.getMain().size()];
         
         this.setSize(new Dimension(300,125));
         this.setLocationRelativeTo(null);
         this.setAlwaysOnTop(true);
         this.setUndecorated(true);
         //this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
         
         panelMain = new JPanel(new BorderLayout());
         this.add(panelMain);
         panelMain.setBorder(BorderFactory.createRaisedBevelBorder());
         panelMain.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5, true), panelMain.getBorder()));
         
         texteDefausse = new JLabel("<html><p align=\"center\">"+aventurier.getNomJoueur() +"<br>Choississez une carte à défausser:<br>(Encore " + nbDefausse + ")" , SwingConstants.CENTER);
         panelMain.add(texteDefausse,BorderLayout.NORTH);
         
         int i = 0;
         for (Carte uneCarte: aventurier.getMain()){
             lesCartes[i] = uneCarte.getNomCarte();
             i++;
         }
         listeCarte = new JComboBox(lesCartes);
         panelMain.add(listeCarte,BorderLayout.CENTER);
         
         btnConfirmer = new JButton("Confirmer");
         panelMain.add(btnConfirmer, BorderLayout.SOUTH);
         btnConfirmer.addActionListener(this);
         this.setVisible(true);
     }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConfirmer){
            Message m = new Message();
            m.type = TypesMessage.DEFAUSSER;
                 
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
            m = new Message();
            if (partieFinDuTour == 1){
                m.type = TypesMessage.TERMINER_TOUR_PARTIE1;
            } else {
                m.type = TypesMessage.TERMINER_TOUR_PARTIE1;
            } 
            observateur.traiterMessage(m);
            this.dispose();
        }
    }
    
}
