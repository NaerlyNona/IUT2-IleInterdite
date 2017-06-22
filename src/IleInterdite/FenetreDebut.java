/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import Roles.Explorateur;
import Roles.Ingenieur;
import Roles.Messager;
import Roles.Navigateur;
import Roles.Pilote;
import Roles.Plongeur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author behats
 */
public class FenetreDebut implements ActionListener {

    //Création de la fenêtre principale
    private final JFrame window;
    
    // Déclaration de la combobox pour la liste du nombre de joueurs
    private final JComboBox listeDeroulante;
    // Déclaration de la combobox pour la liste de niveaux
    private final JComboBox listeDeroulante2;

    //Déclaration des comboBox des rôles pour chacun des joueurs
    private final JComboBox listeRole;
    private final JComboBox listeRole2;
    private final JComboBox listeRole3;
    private final JComboBox listeRole4;

    //ArrayList qu'on va utiliser pour mémoriser les différents niveaux
    private final String[] niveauxProposes;

    //ArrayList qu'on va utiliser pour mémoriser le nombre de joeurs proposés
    private final String[] nbjoueursProposes;

    // Array list qu'on va utiliser pour mémoriser les aventuriers proposés
    private final String[] aventuriersProposes;

    //Champ texte pour le nom du joueur1
    private final JTextField champNom;
    //Champ texte pour le nom du joueur1
    private final JTextField champNom2;
    //Champ texte pour le nom du joueur3
    private final JTextField champNom3;
    //Champ texte pour le nom du joueur4
    private final JTextField champNom4;

    //Bouton pour quitter/Continuer avec choix et mode aléatoire
    private final JButton btnContinuer;
    private final JButton btnAlea;
    private final JButton btnTestPartie;
    private final JButton btnQuitter;

    //private Controleur controleur;
    //Arraylist des aventuriers proposés pour chacun des joueurs
    private ArrayList<String> aventuriers = new ArrayList();
    private ArrayList<String> aventuriers2 = new ArrayList();
    private ArrayList<String> aventuriers3 = new ArrayList();
    private ArrayList<String> aventuriers4 = new ArrayList();

    //ArrayList pour stocker le mélange de l'Arraylist des aventuriers proposés
    //private ArrayList<String> aventurierAlea = new ArrayList();
    private ArrayList<JComboBox> COMBOBOX = new ArrayList();

    private ArrayList<Aventurier> lesAventuriers = new ArrayList();

    public FenetreDebut() {

        window = new JFrame();
        window.setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        window.setSize(width / 2, height / 2);
        window.setLocationRelativeTo(null);

        // Définit la taille de la fenêtre en pixels
        //window.setSize(600, 400);
        // Indique de sortir du programme lorsque la fenêtre est fermée par l'utilisateur
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        //Panel case pour créer le positionnement
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        mainPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5, true), mainPanel.getBorder()));
        window.add(mainPanel);

        //Panel positionné en haut
        JPanel panelHaut = new JPanel();
        mainPanel.add(panelHaut, BorderLayout.NORTH);

        //Panel positionné au centre
        JPanel panelCentre = new JPanel(new GridLayout(2, 2));
        mainPanel.add(panelCentre, BorderLayout.CENTER);

        //Panel positionné au centre-haut
        JPanel panelHCentre = new JPanel(new GridLayout(5, 2));
        panelCentre.add(panelHCentre, BorderLayout.SOUTH);

        //Panel positionné au centre-bas
        JPanel panelBCentre = new JPanel(new GridLayout(2, 2));
        panelCentre.add(panelBCentre, BorderLayout.NORTH);

        //Panel positionné pour le joueur n°1
        JPanel panelJoueur1 = new JPanel(new GridLayout(2, 2));
        panelBCentre.add(panelJoueur1, BorderLayout.EAST);
        panelBCentre.add(panelJoueur1, BorderLayout.SOUTH);

        //Panel positionné pour le joueur n°2
        JPanel panelJoueur2 = new JPanel(new GridLayout(2, 2));
        panelBCentre.add(panelJoueur2, BorderLayout.WEST);
        panelBCentre.add(panelJoueur2, BorderLayout.NORTH);

        //Panel positionné pour le joueur n°3
        JPanel panelJoueur3 = new JPanel(new GridLayout(2, 2));
        panelBCentre.add(panelJoueur3, BorderLayout.EAST);
        panelBCentre.add(panelJoueur3, BorderLayout.SOUTH);

        //Panel positionné pour le joueur n°4
        JPanel panelJoueur4 = new JPanel(new GridLayout(2, 2));
        panelBCentre.add(panelJoueur4, BorderLayout.WEST);
        panelBCentre.add(panelJoueur4, BorderLayout.SOUTH);

        //Panel positionné en bas
        JPanel panelBas = new JPanel();
        mainPanel.add(panelBas, BorderLayout.SOUTH);

        listeRole = new JComboBox();
        listeRole2 = new JComboBox();
        listeRole3 = new JComboBox();
        listeRole4 = new JComboBox();

        //Liste de choix des niveaux
        niveauxProposes = new String[]{"Novice", "Normal", "Elite", "Légendaire"};

        //Liste de choix du nombre des joueurs
        nbjoueursProposes = new String[]{"2", "3", "4"};

        //Liste de choix des aventuriers
        aventuriersProposes = new String[]{"Explorateur", "Pilote", "Navigateur", "Plongeur", "Ingénieur", "Messager"};

        aventuriers = new ArrayList();
        aventuriers2 = new ArrayList();
        aventuriers3 = new ArrayList();
        aventuriers4 = new ArrayList();

        //On remplit les ArrayList des joueurs avec les rôles proposés
        for (int i = 0; i < 6; i++) {
            aventuriers.add(aventuriersProposes[i]);
        }

        for (int i = 0; i < 6; i++) {
            aventuriers2.add(aventuriersProposes[i]);
        }

        for (int i = 0; i < 6; i++) {
            aventuriers3.add(aventuriersProposes[i]);
        }

        for (int i = 0; i < 6; i++) {
            aventuriers4.add(aventuriersProposes[i]);
        }

        // Propriétés de JLabel: Nom du jeu
        JLabel nomJeu = new JLabel("<html><font size=\"7\"><b>Île Interdite");
        //On l'ajoute au panel haut
        panelHaut.add(nomJeu);

        //Création d'une bordure autour du panel haut-centre
        panelHCentre.setBorder(BorderFactory.createTitledBorder(""));

        for (int i = 0; i <= 1; i++) {
            panelHCentre.add(new JLabel());
        }

        //CHOIX DU NIVEAU
        //Propriétés de JLabel: le choix des niveaux
        JLabel niveaux = new JLabel("Sélectionnez un niveau:");
        panelHCentre.add(niveaux);

        //On crée une liste déroulantes et on lui affecte les éléments des différents niveaux
        listeDeroulante2 = new JComboBox(niveauxProposes);
        panelHCentre.add(listeDeroulante2);

        for (int i = 0; i <= 1; i++) {
            panelHCentre.add(new JLabel());
        }

        //LE NOMBRE DE JOUEURS
        // Propriétés de JLabel: le nombre de joueurs
        JLabel joueurs = new JLabel("Choisissez le nombre de joueurs:");
        panelHCentre.add(joueurs);

        //On crée une liste déroulantes et on lui affecte les éléments du nombre de joueurs
        listeDeroulante = new JComboBox(nbjoueursProposes);
        panelHCentre.add(listeDeroulante);

        //Selon le nombre de joueurs inscrits, on montre les champs associés
        // Choix d'une option par défaut
        //Pour joueur 1
        panelJoueur1.setBorder(BorderFactory.createTitledBorder("Joueur n°1"));

        champNom = new JTextField("Joueur 1");
        panelJoueur1.add(new JLabel("Nom:"));
        panelJoueur1.add(champNom);
        panelJoueur1.add(new JLabel("Rôle:"));

        for (String s : aventuriers) {
            listeRole.addItem(s);
            listeRole2.addItem(s);

        }

        // listeRole = new JComboBox(aventuriersProposes);
        panelJoueur1.add(listeRole);
        //Pour joueur 2
        panelJoueur2.setBorder(BorderFactory.createTitledBorder("Joueur n°2"));

        champNom2 = new JTextField("Joueur 2");
        panelJoueur2.add(new JLabel("Nom:"));
        panelJoueur2.add(champNom2);
        panelJoueur2.add(new JLabel("Rôle:"));
        // On crée une liste déroulante en lui affectant directement tous
        // les items de aventuriers

        //listeRole2 = new JComboBox(aventuriersProposes);
        panelJoueur2.add(listeRole2);
        listeRole2.setSelectedItem("Pilote");

        //POUR JOUEUR 3
        panelJoueur3.setBorder(BorderFactory.createTitledBorder("Joueur n°3"));

        champNom3 = new JTextField("Joueur 3");
        panelJoueur3.add(new JLabel("Nom:"));
        panelJoueur3.add(champNom3);
        panelJoueur3.add(new JLabel("Rôle:"));
        panelJoueur3.setVisible(false);
        listeRole3.setEnabled(false);

        // On crée une liste déroulante en lui affectant directement tous
        // les items de aventuriers
        //listeRole3 = new JComboBox(aventuriersProposes);
        panelJoueur3.add(listeRole3);
        listeRole3.setSelectedItem("Navigateur");

        //POUR JOUEUR 4
        panelJoueur4.setBorder(BorderFactory.createTitledBorder("Joueur n°4"));
        champNom4 = new JTextField("Joueur 4");
        panelJoueur4.add(new JLabel("Nom:"));
        panelJoueur4.add(champNom4);
        panelJoueur4.add(new JLabel("Rôle:"));
        panelJoueur4.setVisible(false);
        listeRole4.setEnabled(false);
        listeRole4.setSelectedItem("Plongeur");
        // On crée une liste déroulante en lui affectant directement tous
        // les items de aventuriers
        //listeRole4 = new JComboBox(aventuriersProposes);
        panelJoueur4.add(listeRole4);
        //On attribue par défaut le rôle Plongeur au joueur 4

        //Liste qui change en fonction du nombre de joueurs sélectionnés
        listeDeroulante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listeDeroulante.getSelectedItem() == "3") {
                    panelJoueur3.setVisible(true);
                    panelJoueur4.setVisible(false);
                    listeRole3.removeAllItems();
                    listeRole4.removeAllItems();
                    for (String s : aventuriers) {

                        listeRole3.addItem(s);

                    }
                    listeRole3.setEnabled(true);
                    listeRole4.setEnabled(false);

                } else if (listeDeroulante.getSelectedItem() == "4") {
                    listeRole3.removeAllItems();
                    listeRole4.removeAllItems();
                    for (String s : aventuriers) {

                        listeRole3.addItem(s);
                        listeRole4.addItem(s);

                    }
                    listeRole3.setEnabled(true);
                    listeRole4.setEnabled(true);
                    panelJoueur3.setVisible(true);
                    panelJoueur4.setVisible(true);

                } else if (listeDeroulante.getSelectedItem() == "2") {
                    panelJoueur3.setVisible(false);
                    panelJoueur4.setVisible(false);
                    listeRole3.removeAllItems();
                    listeRole4.removeAllItems();

                }

            }
        });

        listeRole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listeRole.getSelectedItem() == listeRole2.getSelectedItem()) {

                    System.out.println("Le joueur 1 a le même rôle que le joueur 2");
                    btnContinuer.setEnabled(false);

                } else if (listeRole.getSelectedItem() == listeRole3.getSelectedItem()) {

                    System.out.println("Le joueur 1 a le même rôle que le joueur 3");
                    btnContinuer.setEnabled(false);

                } else if (listeRole.getSelectedItem() == listeRole4.getSelectedItem()) {

                    System.out.println("Le joueur 1 a le même rôle que le joueur 4");
                    btnContinuer.setEnabled(false);

                } else {
                    btnContinuer.setEnabled(true);
                }

            }
        });

        listeRole2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listeRole2.getSelectedItem() == listeRole.getSelectedItem()) {

                    System.out.println("Le joueur 2 a le même rôle que le joueur 1");
                    btnContinuer.setEnabled(false);

                } else if (listeRole2.getSelectedItem() == listeRole3.getSelectedItem()) {

                    System.out.println("Le joueur 2 a le même rôle que le joueur 3");
                    btnContinuer.setEnabled(false);

                } else if (listeRole2.getSelectedItem() == listeRole4.getSelectedItem()) {

                    System.out.println("Le joueur 2 a le même rôle que le joueur 4");
                    btnContinuer.setEnabled(false);

                } else {
                    btnContinuer.setEnabled(true);
                }

            }
        });

        listeDeroulante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listeRole3.getSelectedItem() == listeRole.getSelectedItem()) {

                    System.out.println("Le joueur 3 a le même rôle que le joueur 1");
                    btnContinuer.setEnabled(false);

                } else if (listeRole3.getSelectedItem() == listeRole2.getSelectedItem()) {

                    System.out.println("Le joueur 3 a le même rôle que le joueur 2");
                    btnContinuer.setEnabled(false);

                } else if (listeRole3.getSelectedItem() == listeRole4.getSelectedItem()) {

                    System.out.println("Le joueur 3 a le même rôle que le joueur 4");
                    btnContinuer.setEnabled(false);

                } else {
                    btnContinuer.setEnabled(true);
                }

            }
        });
        listeRole3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listeRole3.getSelectedItem() == listeRole.getSelectedItem()) {

                    System.out.println("Le joueur 3 a le même rôle que le joueur 1");
                    btnContinuer.setEnabled(false);

                } else if (listeRole3.getSelectedItem() == listeRole2.getSelectedItem()) {

                    System.out.println("Le joueur 3 a le même rôle que le joueur 2");
                    btnContinuer.setEnabled(false);

                } else if (listeRole3.getSelectedItem() == listeRole4.getSelectedItem()) {

                    System.out.println("Le joueur 3 a le même rôle que le joueur 4");
                    btnContinuer.setEnabled(false);

                } else {
                    btnContinuer.setEnabled(true);
                }

            }
        });

        listeRole4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listeRole4.getSelectedItem() == listeRole.getSelectedItem()) {

                    System.out.println("Le joueur 4 a le même rôle que le joueur 1");
                    btnContinuer.setEnabled(false);

                } else if (listeRole4.getSelectedItem() == listeRole2.getSelectedItem()) {

                    System.out.println("Le joueur 4 a le même rôle que le joueur 2");
                    btnContinuer.setEnabled(false);

                } else if (listeRole4.getSelectedItem() == listeRole3.getSelectedItem()) {

                    System.out.println("Le joueur 4 a le même rôle que le joueur 3");
                    btnContinuer.setEnabled(false);

                } else {
                    btnContinuer.setEnabled(true);
                }

            }
        });

        //Liste qui définit le fait que deux joueurs ne peuvent pas avoir le même rôle
        //Ligne Continuer pour lancer une partie et pour quitter le jeu
        //Bouton quitter
        panelBas.setLayout(new BorderLayout());
        this.btnQuitter = new JButton("Quitter");
        panelBas.add(btnQuitter, BorderLayout.WEST);
        btnQuitter.addActionListener(this);

        for (int i = 0; i <= 1; i++) {
            panelBas.add(new JLabel());
        }

        //Bouton continuer
        this.btnContinuer = new JButton("Continuer");
        panelBas.add(btnContinuer, BorderLayout.EAST);
        btnContinuer.addActionListener(this);

        for (int i = 0; i <= 1; i++) {
            panelBas.add(new JLabel());
        }

        //Bouton aléatoire
        this.btnAlea = new JButton("Aléatoire");
        panelBas.add(btnAlea, BorderLayout.CENTER);
        btnAlea.addActionListener(this);

        this.btnTestPartie = new JButton("Démonstration");
        panelBas.add(btnTestPartie, BorderLayout.NORTH);
        btnTestPartie.addActionListener(this);

        //Rendre la fenêtre visible
        window.setVisible(true);

    }

    public void alea() {

        ArrayList<String> test = aventuriers;

        Random rand = new Random();

        int randomNum = rand.nextInt((5 - 0) + 1) + 0;

        if (listeDeroulante.getSelectedItem() == "2") {
            listeRole.setSelectedItem(aventuriers.get(randomNum));
            listeRole2.setSelectedItem(aventuriers.get((randomNum + 1) % 5));
        }

        if (listeDeroulante.getSelectedItem() == "3") {
            listeRole.setSelectedItem(aventuriers.get(randomNum));
            listeRole2.setSelectedItem(aventuriers.get((randomNum + 1) % 5));
            listeRole3.setSelectedItem(aventuriers.get((randomNum + 2) % 5));
        }

        if (listeDeroulante.getSelectedItem() == "4") {
            listeRole.setSelectedItem(aventuriers.get(randomNum));
            listeRole2.setSelectedItem(aventuriers.get((randomNum + 1) % 5));
            listeRole3.setSelectedItem(aventuriers.get((randomNum + 2) % 5));
            listeRole4.setSelectedItem(aventuriers.get((randomNum + 3) % 5));
        }

    }

    public static void main(String[] args) {
        FenetreDebut f1 = new FenetreDebut();
    }

    public void RemplirListe() {
        for (String s : aventuriers) {
            listeRole.addItem(s);
            listeRole2.addItem(s);
            listeRole3.addItem(s);
            listeRole4.addItem(s);
        }
    }

    //ActionListener de l'interface de début
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == listeRole) {
            System.out.println(listeRole.getSelectedItem());

        }

        if (e.getSource() == btnContinuer) {

            System.out.println("\n*****************************************************************");
            System.out.println("                 *  Choix de la partie       *");
            System.out.println("*****************************************************************");
            System.out.println();
            // Relecture du niveau choisi
            System.out.println("Vous avez choisi le niveau: " + niveauxProposes[listeDeroulante2.getSelectedIndex()]);
            //Relecture du nb de joueurs choisi
            System.out.println("Vous êtes " + nbjoueursProposes[listeDeroulante.getSelectedIndex()] + " joueurs");
            System.out.println("\n*****************************************************************");

            System.out.print(champNom.getText());
            System.out.println(" qui prend le rôle: " + aventuriersProposes[listeRole.getSelectedIndex()]);
            System.out.print(champNom2.getText());
            System.out.println(" qui prend le rôle: " + aventuriersProposes[listeRole2.getSelectedIndex()]);

            if (listeDeroulante.getSelectedItem() == "3") {
                System.out.print(champNom3.getText());
                System.out.println(" qui prend le rôle: " + aventuriersProposes[listeRole3.getSelectedIndex()]);
            } else if (listeDeroulante.getSelectedItem() == "4") {

                System.out.print(champNom3.getText());
                System.out.println(" qui prend le rôle: " + aventuriersProposes[listeRole3.getSelectedIndex()]);

                System.out.print(champNom4.getText());
                System.out.println(" qui prend le rôle: " + aventuriersProposes[listeRole4.getSelectedIndex()]);

            }

            System.out.println("*****************************************************************");
            System.out.println("Bon jeu et bonne chance sur l'île Interdite :3");
            System.out.println("");

            window.setVisible(false);

            int nbJoueurs = Integer.parseInt((String) listeDeroulante.getSelectedItem());
            System.out.println(nbJoueurs);
            String nom;
            String role;
            double difficulté;

            for (int i = 0; i < nbJoueurs; i++) {
                if (i == 0) {
                    nom = champNom.getText();
                    role = aventuriersProposes[listeRole.getSelectedIndex()];
                } else if (i == 1) {
                    nom = champNom2.getText();
                    role = aventuriersProposes[listeRole2.getSelectedIndex()];
                } else if (i == 2) {
                    nom = champNom3.getText();
                    role = aventuriersProposes[listeRole3.getSelectedIndex()];
                } else {
                    nom = champNom4.getText();
                    role = aventuriersProposes[listeRole4.getSelectedIndex()];
                }

                //"Explorateur", "Pilote", "Navigateur", "Plongeur", "Ingénieur", "Messager"
                if (role == "Ingénieur") {
                    lesAventuriers.add(new Ingenieur(nom));
                } else if (role == "Explorateur") {
                    lesAventuriers.add(new Explorateur(nom));
                } else if (role == "Pilote") {
                    lesAventuriers.add(new Pilote(nom));
                } else if (role == "Navigateur") {
                    lesAventuriers.add(new Navigateur(nom));
                } else if (role == "Messager") {
                    lesAventuriers.add(new Messager(nom));
                } else {
                    lesAventuriers.add(new Plongeur(nom));
                }

            }

            if (niveauxProposes[listeDeroulante2.getSelectedIndex()] == "Novice") {
                difficulté = 0.0;
            } else if (niveauxProposes[listeDeroulante2.getSelectedIndex()] == "Normal") {
                difficulté = 1.0;
            } else if (niveauxProposes[listeDeroulante2.getSelectedIndex()] == "Elite") {
                difficulté = 2.0;
            } else {
                difficulté = 3.0;
            }

            Controleur controleur = new Controleur(lesAventuriers, difficulté);
            COMBOBOX.add(listeRole);
            COMBOBOX.add(listeRole2);
            COMBOBOX.add(listeRole3);
            COMBOBOX.add(listeRole4);

        }

        if (e.getSource() == btnQuitter) {

            System.out.println("\nA la prochaine sur l'île Interdite !");
            System.exit(0);
        }

        if (e.getSource() == btnAlea) {

            alea();

        }

        if (e.getSource() == btnTestPartie) {

            window.setVisible(false);
            Controleur controleur = new Controleur();
        }

    }

    public void getRole(String string) {

    }
}

/*shuffle(aventuriers);
 
 int nbJoueurs = 0;
 
 System.out.println("\n*****************************************************************");
 System.out.println("                 *  Choix de la partie       *");
 System.out.println("*****************************************************************");
 System.out.println();
 // Relecture du niveau choisi
 System.out.println("Vous avez choisi le niveau:" + niveauxProposes[listeDeroulante2.getSelectedIndex()]);
 //Relecture du nb de joueurs choisi
 System.out.println("Vous êtes " + nbjoueursProposes[listeDeroulante.getSelectedIndex()] + " joueurs");
 System.out.println("\n*****************************************************************");
 System.out.println("Mode aléatoire activé");
 System.out.println("*****************************************************************");
 
 if (listeDeroulante.getSelectedItem() == "2") {
 nbJoueurs = 2;
 
 System.out.print(champNom.getText());
 
 }
 
 if (listeDeroulante.getSelectedItem() == "3") {
 nbJoueurs = 3;
 }
 
 if (listeDeroulante.getSelectedItem() == "4") {
 nbJoueurs = 4;
 
 }
 
 for (int i = 0; i < nbJoueurs; i++) {
 System.out.print(champNom2.getText());
 
 System.out.print(" qui prend le rôle: ");
 aventurierAlea.add(aventuriers.get(i));
 System.out.println(aventurierAlea.get(i));
 
 
 /* int indiceAlea =0;
            
 for (int i = 0; i < 4; i++ ) {
            
          
 do {//choix d'un indice aléatoire entre O et la taille du vecteur tmp
 indiceAlea = (int) (Math.random()*aventuriers.size());            
 }
 while (aventuriers.contains(indiceAlea));
                   
                  //lire l'indice aléatoire générer
                  /*roleAlea =aventuriers.get(indiceAlea);
                  
                  //ajout de d au vecteur                
                  aventurierAlea.add(roleAlea);*/
 /*public void fermer() {
 int reponse = JOptionPane.showConfirmDialog(window,
 "Voulez-vous quitter l'application",
 "Confirmation",
 JOptionPane.YES_NO_OPTION,
 JOptionPane.QUESTION_MESSAGE);
 if (reponse == JOptionPane.YES_OPTION) {
 System.exit(0);
 }
 }
 
 addWindowListener(new WindowAdapter() {
 @Override
 public void windowClosing(WindowEvent e) {
 fermer();
 }
 });
 
 */
 /*           if (listeDeroulante.getSelectedItem()== "2"){
                    
 listeJoueurs.add(champNom.getText());
 listeJoueurs.add(champNom2.getText());
 for (String nomJoueur : listeJoueurs){
 for (JComboBox box : COMBOBOX){
 if (aventuriersProposes[box.getSelectedIndex()] == "Explorateur"){
 controleur.getLesAventuriers().add(new Explorateur(nomJoueur));
                        
 }
 if (aventuriersProposes[box.getSelectedIndex()] == "Messager"){
 controleur.getLesAventuriers().add(new Messager(nomJoueur));
                        
 }
 if (aventuriersProposes[box.getSelectedIndex()] == "Ingenieur"){
 controleur.getLesAventuriers().add(new Ingenieur(nomJoueur));
                        
 }
 if (aventuriersProposes[box.getSelectedIndex()] == "Pilote"){
 controleur.getLesAventuriers().add(new Pilote(nomJoueur));
                        
 }
 if (aventuriersProposes[box.getSelectedIndex()] == "Plongeur"){
 controleur.getLesAventuriers().add(new Plongeur(nomJoueur));
                        
 }
 if (aventuriersProposes[box.getSelectedIndex()] == "Navigateur"){
 controleur.getLesAventuriers().add(new Navigateur(nomJoueur));
                        
 }
                        
 }
 }
                    
 controleur.setAventurierActuel(controleur.getLesAventuriers().get(0));
 
 
 }
 
 */
 /* ArrayList<String> listeJoueurs = new ArrayList();
 
        int indiceAlea = 0;
 
        for (int i = 0; i < aventuriers.size(); i++) {
 
            do {//choix d'un indice aléatoire entre O et la taille du vecteur tmp
                indiceAlea = (int) (Math.random() * aventuriers.size());
            } while (aventuriers.contains(indiceAlea));
 
            //lire l'indice aléatoire générer
                  /*roleAlea =aventuriers.get(indiceAlea);
                  
             //ajout de d au vecteur                
             aventurierAlea.add(roleAlea);*/
