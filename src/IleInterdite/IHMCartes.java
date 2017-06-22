/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
 * @author naerl
 */
public class IHMCartes extends JFrame implements ActionListener {

    private JPanel panelMain = new JPanel();
    private JPanel[] panelCartes;
    private JLabel[] labelJoueur;
    private JButton[] cartes;

    private JButton btnOK;
    private JPanel panelBouton;

    private IHMIleInterdite ihmIle;

    // 0 = perdu
    // 1 = Gagné
    public IHMCartes(ArrayList<Aventurier> lesAventuriers, IHMIleInterdite ihm) {
        this.setLayout(new BorderLayout());
        btnOK = new JButton("OK");
        this.ihmIle = ihm;

        this.setSize(new Dimension(400, 400));
        this.setLocationRelativeTo(ihmIle);
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);
        this.add(new JLabel("Cartes des Aventuriers", SwingConstants.CENTER), BorderLayout.NORTH);
        if (lesAventuriers.size() > 2) {
            this.panelMain.setLayout(new GridLayout(2, 2));
        } else {
            this.panelMain.setLayout(new GridLayout(1, 2));
        }
        panelCartes = new JPanel[lesAventuriers.size()];
        labelJoueur = new JLabel[lesAventuriers.size()];

        panelBouton = new JPanel(new GridLayout(1, 1));
        panelBouton.add(btnOK);
        this.add(panelBouton, BorderLayout.SOUTH);

        //panelCartes = new JPanel[lesAventuriers.size()];
        int u = 0;
        for (Aventurier unAventurier : lesAventuriers) {
            panelCartes[u] = new JPanel();
            labelJoueur[u] = new JLabel("",SwingConstants.CENTER);
            if(unAventurier.getMain().size() > 5 ) {
                panelCartes[u].setLayout(new GridLayout(unAventurier.getMain().size() + 1, 1));
            } else {
                panelCartes[u].setLayout(new GridLayout(6, 1));
            }
            labelJoueur[u].setText(unAventurier.getNomJoueur() + " | " + unAventurier.getNomRole());
            labelJoueur[u].setForeground(unAventurier.getPion().getCouleur());
            panelCartes[u].add(labelJoueur[u]);
            cartes = new JButton[unAventurier.getMain().size()];
            for (int i = 0; i < unAventurier.getMain().size(); i++) {
                cartes[i] = new JButton(unAventurier.getMain().get(i).getNomCarte());
                cartes[i].setBorder(BorderFactory.createLineBorder(unAventurier.getPion().getCouleur(), 2, true));
                cartes[i].setEnabled(false);
                panelCartes[u].add(cartes[i]);
            }
            panelMain.add(panelCartes[u]);
            u++;
        }

        this.add(panelMain, BorderLayout.CENTER);

        this.setVisible(true);

        btnOK.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnOK) {
            this.ihmIle.setEnabled(true);
            this.dispose();
        }
    }

}
