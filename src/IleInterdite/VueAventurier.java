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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 
public class VueAventurier implements ActionListener  {
    
    private Controleur leControleur = new Controleur();
    
    private final JPanel panelBoutons ;
    private final JPanel panelCentre ;
    private final JPanel panelCentrePosition ;
    private final JPanel panelCentreCarte ;
    private final JFrame window;
    private final JPanel panelAventurier;
    private final JPanel mainPanel;
    private final JButton btnAller  ;
    private final JButton btnAssecher;
    private final JButton btnAutreAction;
    private final JButton btnTerminerTour;
    private final JTextField champCommande;
    
    private final JLabel textePosition;
    
    public VueAventurier (Aventurier unAventurier){

        this.window = new JFrame();
        window.setSize(350, 400);

        window.setTitle(unAventurier.getNomJoueur());
        mainPanel = new JPanel(new BorderLayout());
        this.window.add(mainPanel);

        mainPanel.setBackground(new Color(230, 230, 230));
        mainPanel.setBorder(BorderFactory.createLineBorder(unAventurier.getPion().getCouleur(), 2)) ;

        // =================================================================================
        // NORD : le titre = nom de l'aventurier + nom du joueur sur la couleurActive du pion

        this.panelAventurier = new JPanel();
        panelAventurier.setBackground(unAventurier.getPion().getCouleur());
        panelAventurier.add(new JLabel(unAventurier.getNomRole(),SwingConstants.CENTER ));
        mainPanel.add(panelAventurier, BorderLayout.NORTH);
   
        // =================================================================================
        // CENTRE : 1 ligne pour position courante
        this.panelCentre = new JPanel(new GridLayout(2, 1));
        mainPanel.add(this.panelCentre, BorderLayout.CENTER);
        
        this.panelCentreCarte = new JPanel (new GridLayout(6,6));
        panelCentre.add(this.panelCentreCarte,BorderLayout.NORTH);
        
        this.panelCentrePosition = new JPanel(new GridLayout(2, 1));
        this.panelCentrePosition.setOpaque(false);
        this.panelCentrePosition.setBorder(new MatteBorder(0, 0, 2, 0, unAventurier.getPion().getCouleur()));
        panelCentre.add(this.panelCentrePosition, BorderLayout.SOUTH);
        
        
       // while (){
            
       // }
        textePosition = new JLabel();
        textePosition.setText("[" + unAventurier.getX() + "," + unAventurier.getY() + "]");
        textePosition.setHorizontalAlignment(SwingConstants.CENTER);
        panelCentrePosition.add(textePosition);
        champCommande = new  JTextField(30); 
        champCommande.setText("En attente d'une action...");
        champCommande.setHorizontalAlignment(CENTER);
        panelCentrePosition.add(champCommande);
        
        


        // =================================================================================
        // SUD : les boutons
        this.panelBoutons = new JPanel(new GridLayout(2,2));
        this.panelBoutons.setOpaque(false);
        mainPanel.add(this.panelBoutons, BorderLayout.SOUTH);

        this.btnAller = new JButton("Aller") ;
        this.btnAssecher = new JButton("Assecher");
        this.btnAutreAction = new JButton("AutreAction") ;
        this.btnTerminerTour = new JButton("Terminer Tour") ;
        
        this.panelBoutons.add(btnAller);
        this.panelBoutons.add(btnAssecher);
        this.panelBoutons.add(btnAutreAction);
        this.panelBoutons.add(btnTerminerTour);
        
        this.btnAller.addActionListener(this);
        this.btnTerminerTour.addActionListener(this);

        this.window.setVisible(true);
        mainPanel.repaint();
        
        
    }  

     public JButton getBtnAutreAction() {
        return btnAutreAction;
    }

    public void setChampCommande(String pos) {
        this.champCommande.setText(pos);
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
 

    
     public static void main(String [] args) {
        // Instanciation de la fenêtre 
        //VueAventurier vueAventurier = new VueAventurier ("Manon", "Explorateur",Pion.ROUGE.getCouleur() );
        Controleur leControleur = new Controleur();
        leControleur.InitialiserTestPartie();
        VueAventurier vueAventurierActuel = new VueAventurier(leControleur.getAventurierActuel());
        while (!leControleur.isPartieFinie()){
            if (leControleur.isFinDuTour()){
                leControleur.finDuTour();
            }
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAller){
            leControleur.getAventurierActuel().SeDeplacer(leControleur.getLaGrille(), champCommande);
            textePosition.setText("[" + leControleur.getAventurierActuel().getX() + "," + leControleur.getAventurierActuel().getY() + "]");
        }
        if (e.getSource() == btnTerminerTour){
            leControleur.setFinDuTour(true);
        }
    }
}

 


