/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author behats
 */
public class FenetreDebut implements ActionListener {

    private final JFrame window;
    // Déclaration de la combobox pour la liste de joueurs
    private final JComboBox listeDeroulante;
    // Déclaration de la combobox pour la liste de niveaux

    private final JComboBox listeDeroulante2;

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

    private final JTextField champNom;

    private final JTextField champNom2;
    private final JTextField champNom3;
    private final JTextField champNom4;

    private final JButton btnContinuer;
    private final JButton btnAlea;
    private final JButton btnQuitter;

    //
    private ArrayList<String> aventuriers = new ArrayList();

    public FenetreDebut() {
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

        for (int i = 0; i < 6; i++) {
            aventuriers.add(aventuriersProposes[i]);
        }

        window = new JFrame();

        // Définit la taille de la fenêtre en pixels
        window.setSize(750, 400);

        // Indique de sortir du programme lorsque la fenêtre est fermée par l'utilisateur
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                fermer();
            }
        });

        //Panel case pour créer le positionnement
        JPanel mainPanel = new JPanel(new BorderLayout());
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

        //Panel positionné pour le joueur n°4
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

        // Propriétés de JLabel: Nom du jeu
        JLabel nomJeu = new JLabel("Île Interdite");

        panelHaut.add(nomJeu);

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

        champNom = new JTextField();
        panelJoueur1.add(new JLabel("Nom:"));
        panelJoueur1.add(champNom);
        panelJoueur1.add(new JLabel("Rôle:"));
        // On crée une liste déroulante en lui affectant directement tous
        // les items de aventuriers
        //listeRole = new JComboBox();
        for (String s : aventuriers) {
            listeRole.addItem(s);
            listeRole2.addItem(s);
            listeRole3.addItem(s);
            listeRole4.addItem(s);
        }

        // listeRole = new JComboBox(aventuriersProposes);
        panelJoueur1.add(listeRole);
        //Pour joueur 2
        panelJoueur2.setBorder(BorderFactory.createTitledBorder("Joueur n°2"));

        champNom2 = new JTextField();
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

        champNom3 = new JTextField();
        panelJoueur3.add(new JLabel("Nom:"));
        panelJoueur3.add(champNom3);
        panelJoueur3.add(new JLabel("Rôle:"));
        panelJoueur3.setVisible(false);

        // On crée une liste déroulante en lui affectant directement tous
        // les items de aventuriers
        //listeRole3 = new JComboBox(aventuriersProposes);
        panelJoueur3.add(listeRole3);
        listeRole3.setSelectedItem("Navigateur");

        //POUR JOUEUR 4
        panelJoueur4.setBorder(BorderFactory.createTitledBorder("Joueur n°4"));

        champNom4 = new JTextField();
        panelJoueur4.add(new JLabel("Nom:"));
        panelJoueur4.add(champNom4);
        panelJoueur4.add(new JLabel("Rôle:"));
        panelJoueur4.setVisible(false);

        // On crée une liste déroulante en lui affectant directement tous
        // les items de aventuriers
        //listeRole4 = new JComboBox(aventuriersProposes);
        panelJoueur4.add(listeRole4);
        //On attribue par défaut le rôle Plongeur au joueur 4
        listeRole4.setSelectedItem("Plongeur");

        //Liste qui change en fonction du nombre de joueurs sélectionnés
        listeDeroulante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listeDeroulante.getSelectedItem() == "3") {
                    panelJoueur3.setVisible(true);
                    panelJoueur4.setVisible(false);

                } else if (listeDeroulante.getSelectedItem() == "4") {
                    panelJoueur3.setVisible(true);
                    panelJoueur4.setVisible(true);
                } else if (listeDeroulante.getSelectedItem() == "2") {
                    panelJoueur3.setVisible(false);
                    panelJoueur4.setVisible(false);

                }

            }
        });

        listeRole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listeRole.getSelectedItem() == listeRole2.getSelectedItem()) {

                    System.out.println("j1=j2");

                }
                if (listeRole.getSelectedItem() == listeRole3.getSelectedItem()) {

                    System.out.println("j1=j3");

                }
                if (listeRole.getSelectedItem() == listeRole4.getSelectedItem()) {

                    System.out.println("j1=j3");

                }

            }
        });

        listeRole2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listeRole2.getSelectedItem() == listeRole.getSelectedItem()) {

                    System.out.println("j2=j1");
                }
                if (listeRole2.getSelectedItem() == listeRole3.getSelectedItem()) {

                    System.out.println("j2=j3");

                }
                if (listeRole2.getSelectedItem() == listeRole4.getSelectedItem()) {

                    System.out.println("j2=j4");

                }

            }
        });

        listeRole3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listeRole3.getSelectedItem() == listeRole.getSelectedItem()) {

                    System.out.println("j3=j21");
                }
                if (listeRole3.getSelectedItem() == listeRole2.getSelectedItem()) {

                    System.out.println("j3=j2");

                }
                if (listeRole3.getSelectedItem() == listeRole4.getSelectedItem()) {

                    System.out.println("j3=j4");

                }

            }
        });

        listeRole4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listeRole4.getSelectedItem() == listeRole.getSelectedItem()) {

                    System.out.println("j4=j1");
                }
                if (listeRole4.getSelectedItem() == listeRole2.getSelectedItem()) {

                    System.out.println("j4=j2");

                }
                if (listeRole4.getSelectedItem() == listeRole3.getSelectedItem()) {

                    System.out.println("j4=j3");

                }

            }
        });
        //Liste qui définit le fait que deux joueurs ne peuvent pas avoir le même rôle
        //Ligne Continuer pour lancer une partie et pour quitter le jeu
        panelBas.setLayout(new BorderLayout());

        this.btnQuitter = new JButton("Quitter");
        panelBas.add(btnQuitter, BorderLayout.WEST);
        btnQuitter.addActionListener(this);

        for (int i = 0; i <= 1; i++) {
            panelBas.add(new JLabel());
        }

        this.btnContinuer = new JButton("Continuer");
        panelBas.add(btnContinuer, BorderLayout.EAST);
        btnContinuer.addActionListener(this);

        for (int i = 0; i <= 1; i++) {
            panelBas.add(new JLabel());
        }

        this.btnAlea = new JButton("Aléatoire");
        panelBas.add(btnAlea, BorderLayout.CENTER);
        btnAlea.addActionListener(this);

        //Rendre la fenêtre visible
        window.setVisible(true);

        /*ItemListener itemL = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                aventuriers.remove(e.getItem());
                refresh();

            }

            public void refresh() {
                window.setVisible(false);
                window.setVisible(true);

            }
        };

        listeRole.addItemListener(itemL);
        listeRole2.addItemListener(itemL);
        listeRole3.addItemListener(itemL);
        listeRole4.addItemListener(itemL);*/
    }

    public static void main(String[] args) {
        FenetreDebut f1 = new FenetreDebut();
    }

    //ActionListener de l'interface de début
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == listeRole) {
            System.out.println(listeRole.getSelectedItem());

        }
        if (e.getSource() == btnContinuer) {

            if (listeRole.getSelectedItem() == listeRole2.getSelectedItem() || listeRole.getSelectedItem() == listeRole3.getSelectedItem() || listeRole.getSelectedItem() == listeRole4.getSelectedItem() || listeRole2.getSelectedItem() == listeRole.getSelectedItem() || listeRole2.getSelectedItem() == listeRole3.getSelectedItem() || listeRole2.getSelectedItem() == listeRole4.getSelectedItem() || listeRole3.getSelectedItem() == listeRole.getSelectedItem() || listeRole3.getSelectedItem() == listeRole2.getSelectedItem() || listeRole3.getSelectedItem() == listeRole4.getSelectedItem() || listeRole4.getSelectedItem() == listeRole.getSelectedItem() || listeRole4.getSelectedItem() == listeRole2.getSelectedItem() || listeRole4.getSelectedItem() == listeRole3.getSelectedItem()) {
                System.out.println("\n*****************************************************************");
                System.out.println("Deux joueurs ne peuvent pas avoir le même rôle !");
                System.out.println("*****************************************************************");

            } else {

                System.out.println("\n*****************************************************************");
                System.out.println("                 *  Choix de la partie       *");
                System.out.println("*****************************************************************");
                System.out.println();
                // Relecture du niveau choisi
                System.out.println("Vous avez choisi le niveau:" + niveauxProposes[listeDeroulante2.getSelectedIndex()]);
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
                }

                if (listeDeroulante.getSelectedItem() == "4") {

                    System.out.print(champNom3.getText());
                    System.out.println(" qui prend le rôle: " + aventuriersProposes[listeRole3.getSelectedIndex()]);

                    System.out.print(champNom4.getText());
                    System.out.println(" qui prend le rôle: " + aventuriersProposes[listeRole4.getSelectedIndex()]);
                }
            }
        }

        if (e.getSource() == btnQuitter) {

            System.out.println("\nA la prochaine sur l'île Interdite !");
            System.exit(0);
        }

        if (e.getSource() == btnAlea) {

        }

    }

    public void fermer() {
        int reponse = JOptionPane.showConfirmDialog(window,
                "Voulez-vous quitter l'application",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (reponse == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

}
