/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import IleInterdite.Utils.TypeTresor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.CENTER;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 *
 * @author monnetlu
 */
public class IHMIleInterdite extends JFrame implements ActionListener {

    private Observateur observateur;

    //Joueur dont c'est le tour
    private Aventurier joueurCourant;

    private JLabel texteF;
    private JFrame fenetreF;

    private JPanel panelMain = new JPanel(new BorderLayout());

    private JPanel panelPlateau = new JPanel(); // le panel du milieu où le plateau est présent
    private JButtonTuile[][] tuiles = new JButtonTuile[6][6];

    private JPanel panelInterface = new JPanel(); // le panel de droite où l'interface est présente: contient panel Info, Boutons et Terminer Tour

    private JPanel panelInfo = new JPanel(); // le panel de droite où se trouve le panel joueur,trésor et niveau d'eau

    private JPanel panelJoueur = new JPanel(); // le panel du haut-droite où est affiché les informations du joueur courant
    private JLabel nomJoueur = new JLabel("Nom", SwingConstants.CENTER);
    private JLabel roleJoueur = new JLabel("Role", SwingConstants.CENTER);

    private JPanel panelTresor = new JPanel();
    private JButton[] btnTresor = new JButton[4];

    private JPanel panelNiveauEau = new JPanel();
    private JLabel labelNiveauEau = new JLabel("", SwingConstants.CENTER);
    private double niveauEau = 0;
    private JSlider sliderNiveauEau = new JSlider(0, 20, 0);

    private JPanel panelBoutons = new JPanel(); // le panel du bas-droite où les boutons sont présents
    private JLabel paJoueur = new JLabel("PA : X", SwingConstants.CENTER);
    private JButton btnDeplacer = new JButton("Déplacer");
    private JButton btnAssecher = new JButton("Assécher");
    private JButton btnDonner = new JButton("Donner");
    private JButton btnRecuperer = new JButton("Récupérer");

    private JButtonSpecial[] main = new JButtonSpecial[5];

    private JButton btnTerminerTour = new JButton("Terminer Tour");

    private int mode = 0;

    /*  Mode en cours
     0 = Se Déplacer
     1 = Assécher
    
     */
    public IHMIleInterdite(Observateur o) {
        super("Ile Interdite");
        setObservateur(o);

        initialisationFenetre();

        btnDeplacer.addActionListener(this);
        btnAssecher.addActionListener(this);
        btnRecuperer.addActionListener(this);
        btnTerminerTour.addActionListener(this);
        btnDonner.addActionListener(this);

        for (JButton button : main) {
            button.setEnabled(false);
            button.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Message m = new Message();
        if (e.getSource() == btnDeplacer) {
 
            mode = 0;
            btnDeplacer.setEnabled(false);
            btnAssecher.setEnabled(true);
            m.type = TypesMessage.BTNDEPLACER;
            observateur.traiterMessage(m);
        } else if (e.getSource() == btnAssecher) {
            mode = 1;
            btnDeplacer.setEnabled(true);
            btnAssecher.setEnabled(false);
            m.type = TypesMessage.BTNDEPLACER;
            observateur.traiterMessage(m);
 

        } else if (e.getSource() == btnDeplacer) {
            mode = 0;
            btnDeplacer.setEnabled(false);
            btnAssecher.setEnabled(true);
        } else if (e.getSource() == btnAssecher) {

            mode = 1;
            btnDeplacer.setEnabled(true);
            btnAssecher.setEnabled(false);
        } else if (e.getSource() == btnTerminerTour) {
            m.type = TypesMessage.TERMINER_TOUR_INITIALISATION;
            observateur.traiterMessage(m);
        } else if (e.getSource() == btnDonner) {
            m.type = TypesMessage.BTNDONNER;
            observateur.traiterMessage(m);
        } else if (e.getSource() == btnRecuperer) {
            System.out.println("test");
            m.type = TypesMessage.RECUPERER;
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

        } else if (((JButtonSpecial) (e.getSource())).getType() == 2) {

            System.out.println("TEST1");
            m.type = TypesMessage.HELICOPTERE;
            observateur.traiterMessage(m);

        } else if (((JButtonSpecial) (e.getSource())).getType() == 3) {

            System.out.println("TEST2");
            m.type = TypesMessage.SACDESABLE;
            observateur.traiterMessage(m);

        }
    }

    /*public JButton getSpeciale(){
     for(JButton button : main){
     if (button.getName() == "houloucouptère" || button.getName()=="SacDeSable"){
     return button;
     } else {return null; }
     }
     }*/
    public void setObservateur(Observateur o) {
        observateur = o;
    }

    private void initialisationFenetre() {
        this.add(panelMain);

        //panelPlateau
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
        panelMain.add(panelPlateau, BorderLayout.CENTER);

        //panelInterface
        panelInterface.setLayout(new BorderLayout());
        //panelInfo
        panelInfo.setLayout(new BorderLayout());
        //panelJoueur
        panelJoueur.setLayout(new GridLayout(1, 2));
        panelJoueur.setBorder(BorderFactory.createRaisedBevelBorder());
        panelJoueur.add(nomJoueur);
        panelJoueur.add(roleJoueur);
        //panelTresor          
        panelTresor.setLayout(new GridLayout(1, 4));
        
        /*panelTresor.add(btnCalice);
        btnCalice.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCalice.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnCalice.setBackground(Color.BLACK);
        btnCalice.setForeground(Color.BLACK);
        btnCalice.setEnabled(false);*/

        btnTresor[0] = new JButton("<html><b>Calice");
        btnTresor[1] = new JButton("<html><b>Pierre");
        btnTresor[2] = new JButton("<html><b>Statue");
        btnTresor[3] = new JButton("<html><b>Cristal");
        for (JButton unBouton: btnTresor){
            unBouton.setHorizontalTextPosition(SwingConstants.CENTER);
            unBouton.setVerticalTextPosition(SwingConstants.BOTTOM);
            unBouton.setBackground(Color.BLACK);
            unBouton.setForeground(Color.BLACK);
            unBouton.setEnabled(false);
            unBouton.setMargin(new Insets(5,5,5,5));
            panelTresor.add(unBouton);
        }
        /*panelTresor.add(btnPierre);
        btnPierre.setHorizontalTextPosition(SwingConstants.CENTER);
       //btnPierre.setBackground(Color.DARK_GRAY);
        //btnCalice.setForeground(Color.LIGHT_GRAY);
        
        panelTresor.add(btnStatue);
        btnStatue.setHorizontalTextPosition(SwingConstants.CENTER);
        //btnCalice.setBackground(Color.DARK_GRAY);
        //btnCalice.setForeground(Color.LIGHT_GRAY);
        
        panelTresor.add(btnCristal);
        btnCristal.setHorizontalTextPosition(SwingConstants.CENTER);
        //btnCalice.setBackground(Color.DARK_GRAY);
        //btnCalice.setForeground(Color.LIGHT_GRAY);*/
        
        //panelNiveauEau
        panelNiveauEau.setLayout(new BorderLayout());
        labelNiveauEau.setText("<html><p align=\"center\">Niveau de l'eau: " + niveauEau);
        panelNiveauEau.add(labelNiveauEau, BorderLayout.NORTH);
        sliderNiveauEau.setEnabled(false);
        sliderNiveauEau.setMajorTickSpacing(2);
        sliderNiveauEau.setMinorTickSpacing(1);
        sliderNiveauEau.setPaintTicks(true);
        panelNiveauEau.add(sliderNiveauEau, BorderLayout.CENTER);

        panelInfo.add(panelJoueur, BorderLayout.NORTH);
        panelInfo.add(panelTresor, BorderLayout.CENTER);
        panelInfo.add(panelNiveauEau, BorderLayout.SOUTH);

        panelInterface.add(panelInfo, BorderLayout.NORTH);

        //panelBoutons
        panelBoutons.setLayout(new GridBagLayout());
        //panelBoutons.setBackground(Color.GREEN);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelBoutons.add(paJoueur, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        btnDeplacer.setEnabled(false);
        panelBoutons.add(btnDeplacer, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        panelBoutons.add(btnAssecher, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        panelBoutons.add(btnDonner, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        panelBoutons.add(btnRecuperer, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        panelBoutons.add(new JLabel("Cartes", SwingConstants.CENTER), gbc);

        for (int i = 0; i <= 4; i++) {
            main[i] = new JButtonSpecial(1);
            main[i].setHorizontalTextPosition(SwingConstants.CENTER);
            main[i].setText("Vide");
            this.main[i].addActionListener(this);
        }

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        panelBoutons.add(main[0], gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        panelBoutons.add(main[1], gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        panelBoutons.add(main[2], gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        panelBoutons.add(main[3], gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        panelBoutons.add(main[4], gbc);

        panelInterface.add(panelBoutons, BorderLayout.CENTER);

        panelInterface.add(btnTerminerTour, BorderLayout.SOUTH);

        panelMain.add(panelInterface, BorderLayout.EAST);
    }

    public void afficher() {
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        //setSize(900, 600);
        setSize(1080, 780);
        setVisible(true);
        this.setResizable(false);

        fenetreF = new JFrame();

        texteF = new JLabel("Félicitations ! Vous avez perdu.");
        fenetreF.setSize(300, 100);
        fenetreF.add(texteF);
        fenetreF.setVisible(false);

    }

    public void fin() {
        this.setVisible(false);
        fenetreF.setVisible(true);
        fenetreF.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }

    public void MAJJoueur(Aventurier aventurier) {
        nomJoueur.setText(aventurier.getNomJoueur());
        paJoueur.setText("<html><p align=\"center\"> PA: " + aventurier.getPA());
        roleJoueur.setText(aventurier.getNomRole());
        if (aventurier.isPouvoirPossible()) {
            paJoueur.setText(paJoueur.getText() + "<br> Pouvoir Disponible");
        }
    }

    public void MAJInfo(Controleur leControleur) {
        niveauEau = leControleur.getNiveauEau();
        labelNiveauEau.setText("<html><p align=\"center\">Niveau de l'eau: " + niveauEau);
        sliderNiveauEau.setValue((int) (niveauEau * 2));
        mode = 5;
        btnDeplacer.setEnabled(true);
        btnAssecher.setEnabled(true);
    }

    public void MAJTresor(Controleur leControleur) {
        
                
        Tuile uneTuile;
        ImageIcon icon;
        String iconPath;
        Image img;
        Image newimg;
        
        iconPath = ("/img/resources/treasures/Calice.png");
        btnTresor[0].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));
        icon = createImageIcon(iconPath, "Calice");
        img = icon.getImage();
        newimg = img.getScaledInstance( btnTresor[0].getHeight(), btnTresor[0].getHeight(),  java.awt.Image.SCALE_SMOOTH ) ;  
        icon = new ImageIcon( newimg );
        btnTresor[0].setIcon(icon);
        
        iconPath = ("/img/resources/treasures/Pierre.png");
        btnTresor[1].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));
        icon = createImageIcon(iconPath, "Pierre");
        img = icon.getImage();
        newimg = img.getScaledInstance( btnTresor[1].getHeight(), btnTresor[1].getHeight(),  java.awt.Image.SCALE_SMOOTH ) ;  
        icon = new ImageIcon( newimg );
        btnTresor[1].setIcon(icon);
        
        iconPath = ("/img/resources/treasures/Statue.png");
        btnTresor[2].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));
        icon = createImageIcon(iconPath, "Statue");
        img = icon.getImage();
        newimg = img.getScaledInstance( btnTresor[2].getHeight(), btnTresor[2].getHeight(),  java.awt.Image.SCALE_SMOOTH ) ;  
        icon = new ImageIcon( newimg );
        btnTresor[2].setIcon(icon);
        
        iconPath = ("/img/resources/treasures/Statue.png");
        btnTresor[3].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));
        icon = createImageIcon(iconPath, "Statue");
        img = icon.getImage();
        newimg = img.getScaledInstance( btnTresor[3].getHeight(), btnTresor[3].getHeight(),  java.awt.Image.SCALE_SMOOTH ) ;  
        icon = new ImageIcon( newimg );
        btnTresor[3].setIcon(icon);
        
        for (Tresor unTresor : leControleur.getTresors()) {
            if (unTresor.getType() == Utils.TypeTresor.BLEU) {
                btnTresor[0].setBackground(Color.WHITE);
                btnTresor[0].setEnabled(true);
            } else if (unTresor.getType() == Utils.TypeTresor.GRIS) {
                btnTresor[1].setBackground(Color.WHITE);
                btnTresor[1].setEnabled(true);
            } else if (unTresor.getType() == Utils.TypeTresor.JAUNE) {
                btnTresor[2].setBackground(Color.WHITE);
                btnTresor[2].setEnabled(true);
            } else if (unTresor.getType() == Utils.TypeTresor.ROUGE) {
                btnTresor[3].setBackground(Color.WHITE);
                btnTresor[3].setEnabled(true);
            }
        }

    }

    public void MAJBoutons(Aventurier aventurier, Controleur controleur) {
        boolean pouvoirDonner = false;
        if (aventurier.getMain().size() > 0 && aventurier.getPA() > 0) {
            if (controleur.getNbJoueurSurCase() > 0) {
                System.out.println("CEST BON FILS DE PUITE" + controleur.getNbJoueurSurCase());
                pouvoirDonner = true;
            } else if ((aventurier.getNomRole() == "Messager") && (aventurier.isPouvoirPossible())) {
                pouvoirDonner = true;
            }
        }

        btnDonner.setEnabled(pouvoirDonner);

        int i = 0;
        if (controleur.getLaGrille().getTuile(controleur.getAventurierActuel().getX(), controleur.getAventurierActuel().getY()).getTresor() != null) {
            for (Carte carte : controleur.getAventurierActuel().getMain()) {
                if (carte.getNomCarte() == controleur.getLaGrille().getTuile(controleur.getAventurierActuel().getX(), controleur.getAventurierActuel().getY()).getTresor().getNom()) {

                    i++;
                }
            }
            if (i == 4) {
                btnRecuperer.setEnabled(true);
            }

        } else {
            btnRecuperer.setEnabled(false);
        }

    }

    public void MAJMain(Aventurier aventurier) {
        for (JButton button : main) {
            button.addActionListener(this);
        }
        ArrayList<Carte> laMain = aventurier.getMain();
        int i = 0;

        while ((i < laMain.size()) && (i <= 4)) {

            main[i].setText(laMain.get(i).getNomCarte());
            if (laMain.get(i).getNomCarte() == "houloucouptère") {
                main[i].setType(2);
            }
            if (laMain.get(i).getNomCarte() == "Sac de sable") {
                main[i].setType(3);
            }
            if (aventurier.getMain().get(i).getType() == Utils.TypeCarte.Spéciale) {
                main[i].setEnabled(true);
            } else {
                main[i].setEnabled(false);
            }
            i++;
        }

        while ((i <= 4)) {
            main[i].setText("Vide");
            i++;
        }

    }

    public void MAJTuile(Grille laGrille, ArrayList<Aventurier> lesAventuriers, Aventurier aventurier) {
        Tuile uneTuile;
        ImageIcon icon;
        String iconPath;
        ArrayList<JLabel> labelIcon = new ArrayList();
        for (int l = 0; l <= 5; l++) {
            for (int c = 0; c <= 5; c++) {
                uneTuile = laGrille.getTuile(l, c);
                if (uneTuile == null) {
                    tuiles[l][c].setBorder(null);
                    tuiles[l][c].setBackground(Color.LIGHT_GRAY);
                    tuiles[l][c].setEnabled(false);
                } else {
                    //tuiles[l][c].setText(laGrille.getTuile(l, c).getNom()+"<html><br>");
                    tuiles[l][c].setText("<html><p align=\"center\">");  
   
                    iconPath = ("/images/tuiles/" + uneTuile.getNom() + ".png");
                    tuiles[l][c].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));
                    if (laGrille.getTuile(l, c).getEtat() == Utils.EtatTuile.ASSECHEE) {
                        //tuiles[l][c].setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.ORANGE));
                        tuiles[l][c].setBackground(Color.WHITE);
                        tuiles[l][c].setEnabled(true);
                    } else if (laGrille.getTuile(l, c).getEtat() == Utils.EtatTuile.INONDEE) {
                        //tuiles[l][c].setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.CYAN));
                        tuiles[l][c].setBackground(Color.WHITE);
                        tuiles[l][c].setEnabled(true);
                        iconPath = ("/images/tuiles/" + uneTuile.getNom() + "_Inonde.png");
                    } else if (laGrille.getTuile(l, c).getEtat() == Utils.EtatTuile.COULEE) {
                        tuiles[l][c].setBorder(null);
                        tuiles[l][c].setBackground(Color.GRAY);
                        tuiles[l][c].setEnabled(false);
                    }
                    icon = createImageIcon(iconPath, uneTuile.getNom());
                    Image img = icon.getImage();
                    Image newimg = img.getScaledInstance( tuiles[l][c].getWidth(), tuiles[l][c].getHeight(),  java.awt.Image.SCALE_SMOOTH ) ;  
                    icon = new ImageIcon( newimg );
                    tuiles[l][c].setIcon(icon);
                }

                if ((aventurier.getX() == l) && (aventurier.getY() == c)) {
                    tuiles[l][c].setBackground(aventurier.getPion().getCouleur());
                }

            }
        }
        // MAJ TUILE DEPLACEMENT
        if (mode == 0) {
            for (int tuile : aventurier.DeplacementPossible(laGrille)) {
 
                tuiles[Utils.getChiffre(tuile, 2)][Utils.getChiffre(tuile, 1)].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GREEN));
            }
        }
        // MAJ TUILE ASSECHEMENT
        if (mode == 1) {
            for (int tuileAssechable : aventurier.AssechementPossible(laGrille)) {
 
                tuiles[Utils.getChiffre(tuileAssechable, 2)][Utils.getChiffre(tuileAssechable, 1)].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.CYAN));
            }
        }

        for (Aventurier unAventurier : lesAventuriers) {
            tuiles[unAventurier.getX()][unAventurier.getY()].setText(tuiles[unAventurier.getX()][unAventurier.getY()].getText() + unAventurier.getNomJoueur() + "<br>");
            tuiles[unAventurier.getX()][unAventurier.getY()].setIcon(unAventurier.getIcone());
        }

    }
    
    protected ImageIcon createImageIcon(String path,
                                           String description) {
    java.net.URL imgURL = getClass().getResource(path);
    if (imgURL != null) {
        return new ImageIcon(imgURL, description);
    } else {
        System.err.println("Couldn't find file: " + path);
        return null;
    }
}

}
