/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.CENTER;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author monnetlu
 */
public class IHMIleInterdite extends JFrame implements ActionListener {

    private Observateur observateur;

    //Joueur dont c'est le tour
    private Aventurier joueurCourant;

    private JPanel panelJoueur = new JPanel(); // le panel du haut où est affiché les informations du joueur courant
    private JLabel nomJoueur;
    private JLabel roleJoueur;
    private JLabel paJoueur;

    private JPanel panelPlateau = new JPanel(); // le panel du milieu où le plateau est présent
    private JButtonTuile[][] tuiles = new JButtonTuile[6][6];

    private JPanel panelMain = new JPanel(); // le panel du bas où les boutons sont présents
    private JButton[] main = new JButton[5];

    private JPanel panelBoutons = new JPanel(); // le panel du bas où les boutons sont présents
    private JButton btnDeplacer;
    private JButton btnAssecher;
    private JButton btnTerminerTour;
    private JButton btnAutresActions;

    private int mode = 0;

    /*  Mode en cours
        0 = Se Déplacer
        1 = Assécher
    
     */
    public IHMIleInterdite(Observateur o) {
        super("Ile Interdite");
        setObservateur(o);

        this.pack();
        this.setSize(new Dimension(500, 350));
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        initialisationFenetre();

        btnDeplacer.addActionListener(this);
        btnAssecher.addActionListener(this);
        btnTerminerTour.addActionListener(this);
        btnAutresActions.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Message m = new Message();
        if (e.getSource() == btnDeplacer) {
            mode = 0;
            btnDeplacer.setEnabled(false);
            btnAssecher.setEnabled(true);
        } else if (e.getSource() == btnAssecher) {
            mode = 1;
            btnDeplacer.setEnabled(true);
            btnAssecher.setEnabled(false);
        } else if (e.getSource() == btnTerminerTour) {
            m.type = TypesMessage.TERMINER_TOUR;
            observateur.traiterMessage(m);
        } else if (e.getSource() == btnAutresActions) {
            System.out.println("test");
            m.type = TypesMessage.AUTREACTION;
            observateur.traiterMessage(m);
        } else if (((JButtonSpecial) (e.getSource())).getType() == 0) {
            m.posX = ((JButtonTuile) (e.getSource())).getPosX();
            m.posY = ((JButtonTuile) (e.getSource())).getPosY();
            if (mode == 0) {
                m.type = TypesMessage.DEPLACER;
                observateur.traiterMessage(m);
            } else if (mode == 1) {
                m.type = TypesMessage.ASSECHER;
                observateur.traiterMessage(m);
            } else {
                System.out.println("Mode non reconnu");
            }
        } else if (((JButtonSpecial) (e.getSource())).getType() == 1) {
            System.out.println("Main");
        }
    }

    public void setObservateur(Observateur o) {
        observateur = o;
    }

    private void initialisationFenetre() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //PanelJoueur
        panelJoueur.setLayout(new GridLayout(1, 3));
        nomJoueur = new JLabel("Nom du joueur", SwingConstants.CENTER);
        paJoueur = new JLabel("PA: X", SwingConstants.CENTER);
        roleJoueur = new JLabel("Role du joueur", SwingConstants.CENTER);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 6;
        gbc.anchor = GridBagConstraints.PAGE_START;
        panelJoueur.add(nomJoueur);
        panelJoueur.add(paJoueur);
        panelJoueur.add(roleJoueur);
        this.add(panelJoueur, gbc);

        //PanelPlateau
        panelPlateau.setLayout(new GridLayout(6, 6));
        for (int l = 0; l <= 5; l++) {
            for (int c = 0; c <= 5; c++) {
                tuiles[l][c] = new JButtonTuile(l, c);
                tuiles[l][c].setHorizontalTextPosition(SwingConstants.CENTER);
                //tuiles[l][c].setFont(new Font("Serif",Font.PLAIN, 10));
                panelPlateau.add(tuiles[l][c]);
                this.tuiles[l][c].addActionListener(this);
            }
        }

        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 6;
        gbc.gridheight = 6;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(panelPlateau, gbc);

        //PanelMain 
        panelMain.setLayout(new GridLayout(1, 5));
        for (int i = 0; i <= 4; i++) {
            main[i] = new JButtonMain();
            main[i].setHorizontalTextPosition(SwingConstants.CENTER);
            main[i].setText("Vide");
            panelMain.add(main[i]);
            this.main[i].addActionListener(this);
        }

        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.gridwidth = 8;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.fill = GridBagConstraints.CENTER;
        this.add(panelMain, gbc);

        //PanelBoutons
        panelBoutons.setLayout(new GridLayout(2, 2));
        btnDeplacer = new JButton("Deplacer");
        btnAssecher = new JButton("Assecher");
        btnAutresActions = new JButton("Autres Actions");
        btnTerminerTour = new JButton("Terminer Tour");
        panelBoutons.add(btnDeplacer);
        btnDeplacer.setEnabled(false);
        panelBoutons.add(btnAssecher);
        panelBoutons.add(btnAutresActions);
        panelBoutons.add(btnTerminerTour);

        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridheight = 1;
        gbc.gridwidth = 7;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.PAGE_END;
        this.add(panelBoutons, gbc);
    }

    public void afficher() {
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setSize(650, 700);
        setVisible(true);
    }

    public void MAJJoueur(Aventurier aventurier) {
        nomJoueur.setText(aventurier.getNomJoueur());
        paJoueur.setText("PA: " + aventurier.getPA());
        roleJoueur.setText(aventurier.getNomRole());
    }

    public void MAJMain(Aventurier aventurier) {
        ArrayList<Carte> laMain = aventurier.getMain();
        int i = 0;

        while ((i < laMain.size()) && (i <= 4)) {
            main[i].setText(laMain.get(i).getNomCarte());
            i++;
        }

        while ((i <= 4)) {
            main[i].setText("Vide");
            i++;
        }

    }

    public void MAJTuile(Grille laGrille, ArrayList<Aventurier> lesAventuriers, Aventurier aventurier) {
        for (int l = 0; l <= 5; l++) {
            for (int c = 0; c <= 5; c++) {
                if (laGrille.getTuile(l, c) == null) {
                    tuiles[l][c].setBorder(null);
                    tuiles[l][c].setBackground(Color.LIGHT_GRAY);
                    tuiles[l][c].setEnabled(false);
                } else {
                    //tuiles[l][c].setText(laGrille.getTuile(l, c).getNom()+"<html><br>");
                    tuiles[l][c].setText("<html>");
                    if (laGrille.getTuile(l, c).getEtat() == Utils.EtatTuile.ASSECHEE) {
                        tuiles[l][c].setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.ORANGE));
                        tuiles[l][c].setBackground(Color.WHITE);
                        tuiles[l][c].setEnabled(true);
                    } else if (laGrille.getTuile(l, c).getEtat() == Utils.EtatTuile.INONDEE) {
                        tuiles[l][c].setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.CYAN));
                        tuiles[l][c].setBackground(Color.WHITE);
                        tuiles[l][c].setEnabled(true);
                    } else if (laGrille.getTuile(l, c).getEtat() == Utils.EtatTuile.COULEE) {
                        tuiles[l][c].setBorder(null);
                        tuiles[l][c].setBackground(Color.GRAY);
                        tuiles[l][c].setEnabled(false);
                    }

                    if (laGrille.getTuile(l, c).getType() == 1) {
                        tuiles[l][c].setText(tuiles[l][c].getText() + ((TuileTresor) (laGrille.getTuile(l, c))).getTresor().getNom() + "<br>" );
                    }
                }

                if ((aventurier.getX() == l) && (aventurier.getY() == c)) {
                    tuiles[l][c].setBackground(aventurier.getPion().getCouleur());
                }

            }
        }

        for (Aventurier unAventurier : lesAventuriers) {
            tuiles[unAventurier.getX()][unAventurier.getY()].setText(tuiles[unAventurier.getX()][unAventurier.getY()].getText() + unAventurier.getNomJoueur() + "<br>");
        }

    }

}
