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
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.WindowConstants;

public class IHMDonner extends JFrame implements ActionListener {

    private Observateur observateur;
    private Aventurier aventurier;
    private Controleur controleur;

    private JLabel texteDonner;
    private JLabel texteJoueur;
    private JComboBox listeCarte;
    private JComboBox listeJoueur;
    private JButton btnConfirmer;

    private String[] lesCartes;
    private String[] lesJoueurs;

    public IHMDonner(Aventurier aventurier, Observateur observateur, Controleur controleur) {
        this.observateur = observateur;
        this.aventurier = aventurier;
        this.controleur = controleur;
        this.setSize(new Dimension(200, 400));
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setUndecorated(false);
        lesCartes = new String[aventurier.getMain().size()];
        
        lesJoueurs = new String[controleur.getNbJoueur(aventurier)];

        this.setLayout(new GridLayout(5, 1));

        texteDonner = new JLabel("Choississez une carte à donner:");
        texteJoueur = new JLabel("Choississez un joueur à donner");
        this.add(texteDonner, BorderLayout.NORTH);

        int i = 0;
        for (Carte uneCarte : aventurier.getMain()) {
            //if (uneCarte.getType() != Utils.TypeCarte.Spéciale){
                lesCartes[i] = uneCarte.getNomCarte();
                i++;
            //}
            
        }
        listeCarte = new JComboBox(lesCartes);
        this.add(listeCarte, BorderLayout.NORTH);
        this.add(texteJoueur, BorderLayout.NORTH);

        i = 0;
        
        for (Aventurier a : controleur.getLesAventuriers()) {

            if ((a.getX() == aventurier.getX() && a.getY() == aventurier.getY()) && (a != aventurier) && (a.getMain().size()<5)) {
                lesJoueurs[i] = a.getNomJoueur();
                i++;
            }

        }
        listeJoueur = new JComboBox(lesJoueurs);
        this.add(listeJoueur, BorderLayout.NORTH);

        btnConfirmer = new JButton("Confirmer");
        this.add(btnConfirmer, BorderLayout.SOUTH);
        btnConfirmer.addActionListener(this);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConfirmer) {
            Message m = new Message();
            m.type = TypesMessage.DONNER;

            String nomCarte = ((String) (listeCarte.getSelectedItem()));
            
            boolean carteTrouvé = false;
            int i = 0;
            while (!carteTrouvé) {
                if (nomCarte.equals(aventurier.getMain().get(i).getNomCarte())) {
                    m.carte = aventurier.getMain().get(i);
                    carteTrouvé = true;
                }
                i++;
            }
            
            String nomJoueur = ((String) (listeJoueur.getSelectedItem()));
            for (Aventurier a : controleur.getLesAventuriers()) {
                if (nomJoueur.equals(a.getNomJoueur())) {
                    m.aventurier =a;
                }
               
            }
            this.dispose();
            observateur.traiterMessage(m);
            
            
           

        }
    }

}
