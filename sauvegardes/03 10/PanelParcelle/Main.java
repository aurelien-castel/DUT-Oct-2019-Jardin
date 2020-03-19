import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import javax.swing.event.*;
import java.util.ArrayList;

//CASTEL

class PanneauDessin extends JPanel implements MouseWheelListener
{
    private JLabel cible;
    private int compte;

    PanneauDessin(JLabel c)
    {
    this.cible=c;
	Color myBlack = new Color(100, 100, 100);
      setBackground(myBlack);
    setPreferredSize(new Dimension(500, 50));

	addMouseWheelListener(this);
    }

    int getCompte() {
        return compte;
    }

    public void mouseWheelMoved(MouseWheelEvent evt)
    {

        if (evt.getWheelRotation() < 0) {
    compte = Integer.parseInt(this.cible.getText()); //notre compteur
    compte++;

             } else { compte--; }

    this.cible.setText("" + compte); //concatenation pour transformer en string
	repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
	super.paintComponent(g); //appelle la methode definie dans la classe JPanel
    int i=0;
    int CANVAS_WIDTH = this.getWidth(); //largeur

    int x= (CANVAS_WIDTH)/10*1;
     g.setColor(Color.YELLOW);
     for (; i<compte ; x=((CANVAS_WIDTH)/10)*i, i++) {
       g.fillOval(x,0,50,50);
     }

       g.setColor(Color.GRAY);
     for (; x<CANVAS_WIDTH ; x=((CANVAS_WIDTH)/10)*i, i++) {
       g.fillOval(x,0,50,50);
     }
    }
}

class Dessin extends JFrame //mise en place fenetre
{
    JButton boutonCouleur = new JButton("couleur");
    //SablierComponent s = new SablierComponent();

    JLabel c = new JLabel("0");
    PanneauDessin panneaudessin = new PanneauDessin(c);
    //Component compo = new Component(panneaudessin.getCompte());

    Dessin()
    {
        super("Volume");
        JPanel panneau = new JPanel();
    GridLayout gestionnaire = new GridLayout(1,1);
    panneau.setLayout(gestionnaire);

        JPanel pannelcompteur = new JPanel();
        pannelcompteur.add(c);

        System.out.print(panneaudessin.getCompte());
        panneau.add(pannelcompteur);

        //panneau.add(compteur);
	add(panneau, BorderLayout.NORTH);
    add(panneaudessin, BorderLayout.CENTER);

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocation(200, 200);
    pack();
	setVisible(true);
    }

}

class Main
{
    public static void main(String[] arg)
    {
	new Dessin();
    }
}
