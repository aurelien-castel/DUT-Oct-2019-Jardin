/**
*
* @version 1.1
* @author Thomas Guillemot
*/
package view;

import javax.swing.*;
import java.awt.*;
import controller.*;

public class VueAddNewJardin {

	private JDialog newparcelle;
	private Fenetre fenetre;

	private JPanel fond, menu, panneau_boutons;
	private JButton ajouter;
	private JLabel nom, taille_x, taille_y, Instructions;
	private JTextField nom_text, taille_x_text, taille_y_text;

	private ControlAddNewJardin control;

	public VueAddNewJardin(JPanel f) {
		fenetre = (Fenetre) SwingUtilities.windowForComponent(f);

		newparcelle = new JDialog(fenetre, "Ajout d'un nouveau jardin", true);
		newparcelle.setSize(400, 400);

		// control = new
		// ControlAddNewJardin(fond,nom_text.getText(),taille_x_text.getText(),taille_y_text.getText());

		nom = new JLabel("Nom :");
		nom_text = new JTextField(" ");

		taille_x = new JLabel("Largeur :");
		taille_x_text = new JTextField(" ");

		taille_y = new JLabel("Hauteur :");
		taille_y_text = new JTextField(" ");

		ajouter = new JButton("Ajouter ");
		// ajouter.addActionListener(control);

		fond = new JPanel();
		fond.setLayout(new BorderLayout());

		menu = new JPanel();
		menu.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0; // make this component tall
		c.insets = new Insets(0, 0, 10, 0);
		c.weightx = .5;
		c.weighty = .5;
		c.gridwidth = 0;
		c.gridx = 0;
		c.gridy = 0;
		Instructions = new JLabel("Instructions");
		menu.add(Instructions, c);
		Instructions.setHorizontalAlignment(JLabel.CENTER);

		panneau_boutons = new JPanel();
		panneau_boutons.setLayout(new GridBagLayout());

		panneau_boutons.add(nom);
		panneau_boutons.add(nom_text);

		panneau_boutons.add(taille_x);
		panneau_boutons.add(taille_x_text);

		panneau_boutons.add(taille_y);
		panneau_boutons.add(taille_y_text);

		centred.setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0; // make this component tall
		c.weightx = 0.0;
		c.gridwidth = 0;
		c.gridx = 0;
		c.gridy = 0;

		// fond.setLayout(new GridBagLayout());
		fond.add(centred, c);
		// fond.add(ajouter);

		newparcelle.setContentPane(fond);
		newparcelle.setResizable(false);
		newparcelle.setVisible(true);

	}
}