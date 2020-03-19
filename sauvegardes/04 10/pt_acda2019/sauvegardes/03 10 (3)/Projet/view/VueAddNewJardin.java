/**
*
* @version 1.1
* @author Thomas Guillemot
*/
package view;
import javax.swing.*;
import java.awt.*;
import controller.*;

public class VueAddNewJardin{
	private JDialog newparcelle;
	private Fenetre fenetre;
	private JPanel fond,panneau_boutons;
	private JButton ajouter;
	private JLabel nom,taille_x,taille_y;
	private JTextField nom_text,taille_x_text,taille_y_text;

	private ControlAddNewJardin control;

    public VueAddNewJardin(JPanel f) {
    	fenetre = (Fenetre) SwingUtilities.windowForComponent(f);

    	newparcelle = new JDialog(fenetre,"Ajout d'un nouveau jardin",true);
        newparcelle.setSize(400, 400);

        //control = new ControlAddNewJardin(fond,nom_text.getText(),taille_x_text.getText(),taille_y_text.getText());

        nom = new JLabel("Nom :");
        nom_text= new JTextField(" ");

		taille_x= new JLabel("Largeur :");
		taille_x_text= new JTextField(" ");

		taille_y = new JLabel("Hauteur :");
		taille_y_text= new JTextField(" ");

		ajouter = new JButton("Ajouter ");
		//ajouter.addActionListener(control);

		panneau_boutons = new JPanel();
		panneau_boutons.setLayout(new GridLayout(3,2));

		panneau_boutons.add(nom);
		panneau_boutons.add(nom_text);

		panneau_boutons.add(taille_x);
		panneau_boutons.add(taille_x_text);

		panneau_boutons.add(taille_y);
		panneau_boutons.add(taille_y_text);

		fond = new JPanel();
        fond.setLayout(new GridLayout(2,1));
		fond.add(panneau_boutons);
		fond.add(ajouter);

		newparcelle.setContentPane(fond);
        newparcelle.setResizable(false);
        newparcelle.setVisible(true);
        
    }
}