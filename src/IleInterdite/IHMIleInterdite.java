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
import java.awt.Toolkit;
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

    private ArrayList<JLabel> labelIconeAventurier = new ArrayList();

    private int mode = 0;

    private ImageIcon[][] iconTuiles = new ImageIcon[6][6];
    private ImageIcon[][] iconTuilesInondées = new ImageIcon[6][6];


    /*  Mode en cours
     0 = Se Déplacer
     1 = Assécher
     3 = Helicoptere
     4 = Sac de sable
    
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

        /* for (JButton button : main) {
         button.setEnabled(false);
         button.addActionListener(this);
         }*/
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
            } else if (mode == 3) {
                if (e.getSource() == main[0]) {
                    m.numCarte = 0;
                } else if (e.getSource() == main[1]) {
                    m.numCarte = 1;
                } else if (e.getSource() == main[2]) {
                    m.numCarte = 2;
                } else if (e.getSource() == main[3]) {
                    m.numCarte = 3;
                } else if (e.getSource() == main[4]) {
                    m.numCarte = 4;
                }
                m.type = TypesMessage.HELICOPTERE;
                observateur.traiterMessage(m);
            } else if (mode == 4) {
                if (e.getSource() == main[0]) {
                    m.numCarte = 0;
                }
                if (e.getSource() == main[1]) {
                    m.numCarte = 1;
                }
                if (e.getSource() == main[2]) {
                    m.numCarte = 2;
                }
                if (e.getSource() == main[3]) {
                    m.numCarte = 3;
                }
                if (e.getSource() == main[4]) {
                    m.numCarte = 4;
                }
                m.type = TypesMessage.SACDESABLE;
                observateur.traiterMessage(m);
            } else {
                System.out.println("Mode non reconnu");
            }

        } else if (((JButtonSpecial) (e.getSource())).getType() == 1) {
            System.out.println("Main");

        } else if (((JButtonSpecial) (e.getSource())).getType() == 2) {

            System.out.println("TEST1");
            mode = 3;
            /*m.type = TypesMessage.HELICOPTERE;
             observateur.traiterMessage(m);*/

        } else if (((JButtonSpecial) (e.getSource())).getType() == 3) {

            System.out.println("TEST2");
            mode = 4;

        }
    }
    
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
                panelPlateau.add(tuiles[l][c]);
                this.tuiles[l][c].addActionListener(this);
            }
        }
        panelPlateau.setBackground(Color.LIGHT_GRAY);
        panelMain.add(panelPlateau, BorderLayout.CENTER);

        //panelInterface
        panelInterface.setLayout(new BorderLayout());
        //panelInfo
        panelInfo.setLayout(new BorderLayout());
        //panelJoueur
        panelJoueur.setLayout(new GridLayout(1, 3));
        panelJoueur.setBorder(BorderFactory.createRaisedBevelBorder());
        panelJoueur.add(nomJoueur);
        panelJoueur.add(roleJoueur);
        panelJoueur.setBackground(Color.LIGHT_GRAY);
        
        //panelTresor          
        panelTresor.setLayout(new GridLayout(1, 4));
        btnTresor[0] = new JButton("<html><b>Calice");
        btnTresor[1] = new JButton("<html><b>Pierre");
        btnTresor[2] = new JButton("<html><b>Statue");
        btnTresor[3] = new JButton("<html><b>Cristal");
        for (JButton unBouton : btnTresor) {
            unBouton.setHorizontalTextPosition(SwingConstants.CENTER);
            unBouton.setVerticalTextPosition(SwingConstants.BOTTOM);
            unBouton.setBackground(Color.BLACK);
            unBouton.setForeground(Color.BLACK);
            unBouton.setEnabled(false);
            unBouton.setMargin(new Insets(5, 5, 5, 5));
            panelTresor.add(unBouton);
        }

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
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelBoutons.add(paJoueur, gbc);

        ImageIcon icon;
        String iconPath;
        Image img;
        Image newimg;

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        btnDeplacer.setEnabled(false);

        iconPath = ("/img/resources/icones/iconMove.png");
        icon = createImageIcon(iconPath, "Déplacer");
        img = icon.getImage();
        newimg = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        btnDeplacer.setIcon(icon);

        panelBoutons.add(btnDeplacer, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;

        iconPath = ("/img/resources/icones/iconDry.png");
        icon = createImageIcon(iconPath, "Assécher");
        img = icon.getImage();
        newimg = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        btnAssecher.setIcon(icon);

        panelBoutons.add(btnAssecher, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;

        iconPath = ("/img/resources/icones/iconReceive.png");
        icon = createImageIcon(iconPath, "Donner");
        img = icon.getImage();
        newimg = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        btnDonner.setIcon(icon);

        panelBoutons.add(btnDonner, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;

        iconPath = ("/img/resources/icones/iconGet.png");
        icon = createImageIcon(iconPath, "Récupérer");
        img = icon.getImage();
        newimg = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        btnRecuperer.setIcon(icon);

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
            main[i].addActionListener(this);
            main[i].setEnabled(false);

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
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        //setSize(900, 600);
        setSize(1080, 780);
        this.setLocationRelativeTo(null);
        setVisible(true);
        this.setResizable(false);


    }

    public void fin(int valeur) {
        // 0 = Perdu 1 = Gagné
        //this.setVisible(false);
        setEnabled(false);
        System.out.println("test");
        new IhmFin(valeur);
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

        iconPath = ("/img/resources/logo/Treasure_Icon_Calice.png");
        btnTresor[0].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));
        icon = createImageIcon(iconPath, "Calice");
        img = icon.getImage();
        newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        btnTresor[0].setIcon(icon);

        iconPath = ("/img/resources/logo/Treasure_Icon_Pierre.png");
        btnTresor[1].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));
        icon = createImageIcon(iconPath, "Pierre");
        img = icon.getImage();
        newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        btnTresor[1].setIcon(icon);

        iconPath = ("/img/resources/logo/Treasure_Icon_Statue.png");
        btnTresor[2].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));
        icon = createImageIcon(iconPath, "Statue");
        img = icon.getImage();
        newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        btnTresor[2].setIcon(icon);

        iconPath = ("/img/resources/logo/Treasure_Icon_Cristal.png");
        btnTresor[3].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));
        icon = createImageIcon(iconPath, "Cristal");
        img = icon.getImage();
        newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
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
        btnRecuperer.setEnabled(false);
        if (controleur.getLaGrille().getTuile(controleur.getAventurierActuel().getX(), controleur.getAventurierActuel().getY()).getTresor() != null) {
            for (Carte carte : controleur.getAventurierActuel().getMain()) {
                if (carte.getNomCarte() == controleur.getLaGrille().getTuile(controleur.getAventurierActuel().getX(), controleur.getAventurierActuel().getY()).getTresor().getNom()) {

                    i++;
                }
            }
            if (i >= 2 && !controleur.getTresors().contains(controleur.getLaGrille().getTuile(controleur.getAventurierActuel().getX(), controleur.getAventurierActuel().getY()).getTresor())) {
                btnRecuperer.setEnabled(true);
            }

       
            
        }

    }

    public void MAJMain(Aventurier aventurier) {

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
            main[i].setEnabled(false);
            i++;
        }

    }

    public void initialisationTuile(Grille laGrille) {
        Tuile uneTuile;
        ImageIcon icon;
        String iconPath;
        Image img;
        Image newimg;
        for (int l = 0; l <= 5; l++) {
            for (int c = 0; c <= 5; c++) {
                if (laGrille.getTuile(l, c) != null) {
                    uneTuile = laGrille.getTuile(l, c);
                    iconPath = ("/img/resources/tuiles/" + uneTuile.getNom() + ".png");
                    icon = createImageIcon(iconPath, uneTuile.getNom());
                    img = icon.getImage();
                    newimg = img.getScaledInstance(tuiles[l][c].getWidth(), tuiles[l][c].getHeight(), java.awt.Image.SCALE_SMOOTH);
                    icon = new ImageIcon(newimg);
                    iconTuiles[l][c] = icon;

                    iconPath = ("/img/resources/tuiles/" + uneTuile.getNom() + "_Inonde.png");
                    icon = createImageIcon(iconPath, uneTuile.getNom());
                    img = icon.getImage();
                    newimg = img.getScaledInstance(tuiles[l][c].getWidth(), tuiles[l][c].getHeight(), java.awt.Image.SCALE_SMOOTH);
                    icon = new ImageIcon(newimg);
                    iconTuilesInondées[l][c] = icon;
                }
            }
        }
    }

    public void MAJTuile(Grille laGrille, ArrayList<Aventurier> lesAventuriers, Aventurier aventurier) {
        Tuile uneTuile;
        ImageIcon icon;
        String iconPath;
        for (int l = 0; l <= 5; l++) {
            for (int c = 0; c <= 5; c++) {
                uneTuile = laGrille.getTuile(l, c);
                if (uneTuile == null) {
                    tuiles[l][c].setBorder(null);
                    tuiles[l][c].setBackground(null);
                    tuiles[l][c].setEnabled(false);
                } else {
                    //tuiles[l][c].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));
                    tuiles[l][c].setBorder(null);
                    tuiles[l][c].setBackground(Color.WHITE);
                    tuiles[l][c].setIcon(iconTuiles[l][c]);
                    if (laGrille.getTuile(l, c).getEtat() == Utils.EtatTuile.ASSECHEE) {
                        tuiles[l][c].setEnabled(true);

                    } else if (laGrille.getTuile(l, c).getEtat() == Utils.EtatTuile.INONDEE) {
                        //tuiles[l][c].setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.CYAN));
                        tuiles[l][c].setEnabled(true);
                        tuiles[l][c].setIcon(iconTuilesInondées[l][c]);
                    } else if (laGrille.getTuile(l, c).getEtat() == Utils.EtatTuile.COULEE) {
                        tuiles[l][c].setBorder(null);
                        tuiles[l][c].setBackground(null);
                        tuiles[l][c].setEnabled(false);
                        tuiles[l][c].setIcon(null);

                    }
                }

            }
        }
        // MAJ TUILE DEPLACEMENT
        
            
            if (mode == 0) {
                //if (aventurier.getPA() > 0) {
                for (int tuile : aventurier.DeplacementPossible(laGrille)) {

                    tuiles[Utils.getChiffre(tuile, 2)][Utils.getChiffre(tuile, 1)].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GREEN));
               // }
                }
            }
            // MAJ TUILE ASSECHEMENT
            if (mode == 1) {
                for (int tuileAssechable : aventurier.AssechementPossible(laGrille)) {

                    tuiles[Utils.getChiffre(tuileAssechable, 2)][Utils.getChiffre(tuileAssechable, 1)].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.CYAN));
                }
            }
           
        

        //MAJ PIONS
        for (Aventurier unAventurier : lesAventuriers) {
            tuiles[unAventurier.getX()][unAventurier.getY()].add(unAventurier.getLabelIcone());
        }

    }


    protected ImageIcon createImageIcon(String path,String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

}
