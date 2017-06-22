/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;
 
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
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
    private JLabel texteF;
    private JButton btnSuivant;
    private JButton btnPrecedent;
    
    private JPanel panelBouton;
    private IhmFin finIhm;
    CardLayout c1 = new CardLayout();
        JPanel content = new JPanel();
        
        
        String [] listContent = {"Regle1", "Regle2", "Regle3"};
        int indice = 0;
    
    public IHMRegles(){
        
        
        this.setTitle("Règles");
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.setSize(200, 400);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        
        JPanel card1 = new JPanel();
        
        JPanel card2 = new JPanel();
        card2.setBackground(Color.red);
        JPanel card3 = new JPanel();
        card3.setBackground(Color.blue);
        ImageIcon icon;
        String iconPath;
        Image img;
        Image newimg;
        
        iconPath = ("/img/resources/regles/ReglesPage1.png");
        //btnTresor[1].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));
        icon = createImageIcon(iconPath, "Regles1");
        img = icon.getImage();
        newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
  
        JLabel regle1 = new JLabel(icon);
        card1.add(regle1);
        
        
        
        btnSuivant = new JButton("Suivant");
        btnPrecedent = new JButton("Precedent");
      
        
        
        
        
 
     
        panelBouton = new JPanel(new GridLayout(1,2));
      
        panelBouton.add(btnSuivant);
        panelBouton.add(btnPrecedent);
        this.add(content);
        this.add(panelBouton, BorderLayout.NORTH);
        
        
        
        
        
        
        this.setVisible(true);
        
        
        content.setLayout(c1);
        btnSuivant.addActionListener(this);
        btnPrecedent.addActionListener(this);
        content.add(card1, listContent[0]);
         content.add(card2, listContent[1]);
            content.add(card3, listContent[2]);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btnSuivant){
            c1.next(content);
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
