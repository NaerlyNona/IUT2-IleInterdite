package IleInterdite;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.MatteBorder;
import IleInterdite.Utils.Pion;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

public class VueAventurier implements ActionListener {
    
    private static final Controleur leControleur = new Controleur();
    
    private final JPanel panelBoutons;// Panneau ou se trouve les boutons aller etc
    private final JPanel panelCentre; // Ou se trouve les tuiles
    private final JPanel panelCentrePosition;
    private final JFrame window;
    private final JPanel panelAventurier;
    private final JPanel mainPanel;
    private final JPanel panelSud;
    private final JButton btnAller;
    private final JButton btnAssecher;
    private final JButton btnAutreAction;
    private final JButton btnTerminerTour;
    
    private JLabel texteNom; // Nom du joueur et son role
    private JLabel textePosition; // Position du joueur
    private JLabel textePA;
    
    private JButton[][] tuiles = new JButton[6][6];
    
    private int mode = 0;

    /*  Mode en cours
        0 = Se Déplacer
        1 = Assécher
        
    
     */
    
    public VueAventurier(Aventurier unAventurier) {
        
        this.window = new JFrame();
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //window.setUndecorated(true);
        window.setAlwaysOnTop(true);
        window.setResizable(false);
        window.setSize(750, 900);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        window.setTitle("Ile Interdite");
        mainPanel = new JPanel(new BorderLayout(50, 50));
        this.window.add(mainPanel);
        
        mainPanel.setBackground(new Color(230, 230, 230));
        mainPanel.setBorder(BorderFactory.createLineBorder(unAventurier.getPion().getCouleur(), 2));

        // =================================================================================
        // NORD : le titre = nom de l'aventurier + nom du joueur sur la couleurActive du pion
        this.panelAventurier = new JPanel(new GridLayout(2, 1));
        //panelAventurier.setBackground(unAventurier.getPion().getCouleur());
        texteNom = new JLabel(unAventurier.getNomJoueur() + " | " + unAventurier.getNomRole(), SwingConstants.CENTER);
        panelAventurier.add(texteNom);
        textePosition = new JLabel("[" + unAventurier.getX() + "," + unAventurier.getY() + "]", SwingConstants.CENTER);
        panelAventurier.add(textePosition);
        mainPanel.add(panelAventurier, BorderLayout.NORTH);

        // =================================================================================
        // CENTRE : Carte
        this.panelCentre = new JPanel(new GridLayout(6, 6));
        panelCentre.setMinimumSize(new Dimension(500, 500));
        panelCentre.setBorder(BorderFactory.createBevelBorder(CENTER));
        mainPanel.add(this.panelCentre, BorderLayout.CENTER);
        
        this.panelCentrePosition = new JPanel(new GridLayout(2, 1));
        this.panelCentrePosition.setOpaque(false);

        //Grille
        JPanel grille = new JPanel(new GridLayout(6, 6));
        //panelCentreCarte.add(grille);
        // System.out.println(leControleur.getLaGrille().getTuile(0, 3).getNom());

        for (int l = 0; l <= 5; l++) {
            for (int c = 0; c <= 5; c++) {
                tuiles[l][c] = new JButtonTuile(l, c);
                tuiles[l][c].setHorizontalTextPosition(SwingConstants.CENTER);
                
                if (leControleur.getLaGrille().getTuile(l, c) == null) {
                    tuiles[l][c].setBackground(Color.LIGHT_GRAY);
                    tuiles[l][c].setBorder(null);
                    //tuiles[l][c].setIcon(new ImageIcon("img/resources/tiles/extra/Tile_FloodWater.png"));
                    tuiles[l][c].setEnabled(false);
                } else {
                    tuiles[l][c].setText(leControleur.getLaGrille().getTuile(l, c).getNom() + "[" + l + "," + c + "]");

                    /*if (leControleur.getLaGrille().getTuile(l, c).getEtat() == Utils.EtatTuile.COULEE) {
                        tuiles[l][c].setIcon(new ImageIcon("img/resources/tiles/extra/Tile_FloodWater.png"));
                    }
                    if (leControleur.getLaGrille().getTuile(l, c).getNom() == "La Porte De Bronze") {
                        ImageIcon icon = new ImageIcon(new ImageIcon("img/resources/tiles/" + leControleur.getLaGrille().getTuile(l, c).getNom() + ".png").getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
                        tuiles[l][c].setIcon(icon);
                    }*/
                    //tuiles[l][c].setIcon(new ImageIcon("img/defaultTuile.jpg"));
                }
                panelCentre.add(tuiles[l][c]);
                this.tuiles[l][c].addActionListener(this);
            }
        }
        MAJTuile();

        // =================================================================================
        // SUD : les boutons
        this.panelSud = new JPanel(new GridLayout(2, 1));
        mainPanel.add(panelSud, BorderLayout.SOUTH);
        
        textePA = new JLabel("X PA", SwingConstants.CENTER);
        panelSud.add(textePA);
        
        this.panelBoutons = new JPanel(new GridLayout(2, 2));
        this.panelBoutons.setOpaque(false);
        panelSud.add(this.panelBoutons, BorderLayout.SOUTH);
        
        this.btnAller = new JButton("Aller");
        this.btnAssecher = new JButton("Assecher");
        this.btnAutreAction = new JButton("AutreAction");
        this.btnTerminerTour = new JButton("Terminer Tour");
        
        this.panelBoutons.add(btnAller);
        this.panelBoutons.add(btnAssecher);
        this.panelBoutons.add(btnAutreAction);
        this.panelBoutons.add(btnTerminerTour);
        
        this.btnAller.addActionListener(this);
        this.btnAssecher.addActionListener(this);
        this.btnTerminerTour.addActionListener(this);
        this.btnAutreAction.addActionListener(this);
        this.window.setVisible(true);
        //window.setResizable(false);
        mainPanel.repaint();
        
    }
    
    public JButton getBtnAutreAction() {
        return btnAutreAction;
    }
    
    public JButton getBtnAller() {
        return btnAller;
    }
    
    public JButton getBtnAssecher() {
        return btnAssecher;
    }
    
    public JButton getBtnTerminerTour() {
        return btnTerminerTour;
    }
    
    public static void main(String[] args) {
        // Instanciation de la fenêtre 
        //VueAventurier vueAventurier = new VueAventurier ("Manon", "Explorateur",Pion.ROUGE.getCouleur() );
        leControleur.InitialiserTestPartie();
        VueAventurier vueAventurierActuel = new VueAventurier(leControleur.getAventurierActuel());
        while (!leControleur.isPartieFinie()) {
            if (leControleur.isFinDuTour()) {
                leControleur.finDuTour();
            }
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAller) {
            mode = 0;            
        } else if (e.getSource() == btnAssecher) {
            mode = 1;
        } else if (e.getSource() == btnTerminerTour) {
            leControleur.finDuTour();
            System.out.println(leControleur.getAventurierActuel().getNomJoueur());
            MAJTuile();
            MAJFenetre();
        } else if (e.getSource() == btnAutreAction) {
            System.out.println("bite");
            int i = 1;
            for (Aventurier av : leControleur.getLesAventuriers()) {                
                System.out.println("joueur n°" + i + " : " + av.getNomRole());
                i++;
            }
            
            System.out.println("Donner a qui : ");
            Scanner sc = new Scanner(System.in);
            int choix = sc.nextInt();
            
            leControleur.getAventurierActuel().donnerCarte(leControleur.getLesAventuriers().get(choix - 1));
        } else if (mode == 0) {
            leControleur.getAventurierActuel().SeDeplacer(leControleur.getLaGrille(), ("" + ((JButtonTuile) (e.getSource())).getPosX() + ((JButtonTuile) (e.getSource())).getPosY()));
            MAJTuile();
            MAJFenetre();
        } else if (mode == 1) {
            leControleur.getAventurierActuel().Assécher(leControleur.getLaGrille(), ("" + ((JButtonTuile) (e.getSource())).getPosX() + ((JButtonTuile) (e.getSource())).getPosY()));
            MAJTuile();
        } else {
            System.out.println("Mode non reconnu");
        }        
        
    }
    
    public void MAJTuile() {
        for (int l = 0; l <= 5; l++) {
            for (int c = 0; c <= 5; c++) {
                if (leControleur.getLaGrille().getTuile(l, c) != null) {
                    if (leControleur.getLaGrille().getTuile(l, c).getEtat() == Utils.EtatTuile.ASSECHEE) {
                        tuiles[l][c].setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.ORANGE));
                        tuiles[l][c].setBackground(Color.WHITE);
                    } else if (leControleur.getLaGrille().getTuile(l, c).getEtat() == Utils.EtatTuile.INONDEE) {
                        tuiles[l][c].setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.CYAN));
                        tuiles[l][c].setBackground(Color.WHITE);
                    } else if (leControleur.getLaGrille().getTuile(l, c).getEtat() == Utils.EtatTuile.COULEE) {
                        tuiles[l][c].setBorder(null);
                        tuiles[l][c].setBackground(Color.GRAY);
                    }
                    
                    if ((leControleur.getAventurierActuel().getX() == l) && (leControleur.getAventurierActuel().getY() == c)) {
                        tuiles[l][c].setBackground(leControleur.getAventurierActuel().getPion().getCouleur());
                    }
                }
            }
        }
    }
    
    public void MAJFenetre() {
        texteNom.setText(leControleur.getAventurierActuel().getNomJoueur() + " | " + leControleur.getAventurierActuel().getNomRole());
        textePosition.setText("[" + leControleur.getAventurierActuel().getX() + "," + leControleur.getAventurierActuel().getY() + "]");
        mainPanel.setBorder(BorderFactory.createLineBorder(leControleur.getAventurierActuel().getPion().getCouleur(), 2));
    }
}
