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

	private JPanel fond, menu;
	private JButton ajouter;
	private JLabel nom, taille_x, taille_y, Instructions;
	private JTextField nom_text, taille_x_text, taille_y_text;

	private ControlAddNewJardin control;

	public VueAddNewJardin(JPanel f) {
		fenetre = (Fenetre) SwingUtilities.windowForComponent(f);

		newparcelle = new JDialog(fenetre,"Ajout d'un nouveau jardin",true);
		newparcelle.setSize(400, 400);

		
		nom = new JLabel("Nom :");
		nom_text= new JTextField(10);

		taille_x= new JLabel("Largeur :");
		taille_x_text= new JTextField(10);

		taille_y = new JLabel("Hauteur :");
		taille_y_text= new JTextField(10);

		ajouter = new JButton("Ajouter ");
		control = new ControlAddNewJardin(fond,nom_text,taille_x_text,taille_y_text);
		ajouter.addActionListener(control);

		menu = new JPanel();
        menu.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

			c.fill = GridBagConstraints.HORIZONTAL;
			c.ipady = 0; // make this component tall
			c.insets = new Insets(0,0,10,0);
			c.weightx = .5;
			c.weighty = .5;
			c.gridwidth = 0;
			c.gridx = 0;
			c.gridy = 0;
			Instructions = new JLabel("Instructions");
			menu.add(Instructions, c);
			Instructions.setHorizontalAlignment(JLabel.CENTER);
			
			c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 0; // make this component tall
            c.insets = new Insets(0,5,1,5);
            c.weightx = .5;
            c.weighty = .5;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 1;
			menu.add(nom, c);
			
			c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 10; // make this component tall
            c.insets = new Insets(0,5,1,5);
            c.weightx = 0.0;
            c.gridwidth = 2;
            c.gridx = 1;
            c.gridy = 1;
			menu.add(nom_text, c);
			
			c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 0; // make this component tall
            c.insets = new Insets(0,5,1,5);
            c.weightx = .5;
            c.weighty = .5;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 2;
			menu.add(taille_x, c);
			
			c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 10; // make this component tall
            c.insets = new Insets(0,5,1,5);
            c.weightx = 0.0;
            c.gridwidth = 2;
            c.gridx = 1;
            c.gridy = 2;
            menu.add(taille_x_text, c);
			
			c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 0; // make this component tall
            c.insets = new Insets(0,5,10,5);
            c.weightx = .5;
            c.weighty = .5;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 3;
			menu.add(taille_y, c);
			
			c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 10; // make this component tall
            c.insets = new Insets(0,5,10,5);
            c.weightx = 0.0;
            c.gridwidth = 2;
            c.gridx = 1;
            c.gridy = 3;
			menu.add(taille_y_text, c);
			
			c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 10; // make this component tall
            c.insets = new Insets(0,5,1,5);
            c.weightx = 0.0;
            c.gridwidth = 3;
            c.gridx = 0;
            c.gridy = 4;
            menu.add(ajouter, c);

		fond = new JPanel();
		fond.setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0; // make this component tall
        c.weightx = 0.0;
        c.gridwidth = 0;
        c.gridx = 0;
		c.gridy = 0;
		
		fond.add(menu,c);


		newparcelle.setContentPane(fond);
		newparcelle.setResizable(false);
		newparcelle.setVisible(true);
		
	}
}