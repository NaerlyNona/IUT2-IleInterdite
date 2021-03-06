/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author chenavje
 */
public class IHMRegles extends JFrame implements ActionListener {

    private JButton btnSuivant;
    private JButton btnPrecedent;
private JButton btnQuitter;
    private JPanel panelBouton;

    CardLayout c1 = new CardLayout();
    JPanel content = new JPanel();
    private JPanel[] pages = new JPanel[8];
    private JPanel page1 = new JPanel();

    String[] listContent = {"Regle1", "Regle2", "Regle3", "Regle4", "Regle5", "Regle6", "Regle7", "Regle8"};
    int indice = 0;

    public IHMRegles() {

        this.setTitle("Règles");
        this.setSize(720, 1000);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(false);

        ImageIcon icon;
        String iconPath;
        Image img;
        Image newimg;

        for (int i = 1; i < 8; i++) {
            pages[i] = new JPanel();
            iconPath = ("/img/resources/regles/ReglesPage" + i + ".png");
            icon = createImageIcon(iconPath, "Regles" + i);
            img = icon.getImage();
            //newimg = img.getScaledInstance(720, 1080, java.awt.Image.SCALE_SMOOTH);
            newimg = img.getScaledInstance(this.getWidth(), this.getHeight()-50, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);
            JLabel regle = new JLabel(icon);
            System.out.println(i);
            pages[i].add(regle);
        }

        btnSuivant = new JButton("Suivant");
        btnPrecedent = new JButton("Precedent");
        btnQuitter = new JButton("Quitter");
        panelBouton = new JPanel(new GridLayout(1, 3));
        panelBouton.setBorder(BorderFactory.createEmptyBorder());
        panelBouton.add(btnPrecedent);
        panelBouton.add(btnQuitter);
        panelBouton.add(btnSuivant);
        this.add(panelBouton, BorderLayout.NORTH);
        this.add(content);

        content.setLayout(c1);
        btnSuivant.addActionListener(this);
        btnPrecedent.addActionListener(this);
        btnQuitter.addActionListener(this);
        for (int i = 1; i < 8; i++) {
            content.add(pages[i], listContent[i]);
            pages[i].setBackground(Color.MAGENTA);

        }

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSuivant) {
            c1.next(content);

        } else if (e.getSource() == btnPrecedent) {
            c1.previous(content);
        } else {
            this.dispose();
        }

    }

    protected ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

}
