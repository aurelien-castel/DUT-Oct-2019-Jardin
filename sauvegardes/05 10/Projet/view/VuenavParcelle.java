/**
*@author Thomas Guillemot
*/
package view;
import javax.swing.*;
import java.awt.*;
import view.*;

import controller.*;

public class VuenavParcelle extends JPanel{
	private JPanel panneau_grille,panneau_boutons;
	private JButton accueil,historique;


	public VuenavParcelle(String bouton1 , String bouton2,ControlnavParcelle control){

		accueil = new JButton(bouton1);
		accueil.setPreferredSize(new Dimension(100, 40));
		accueil.addActionListener(control);

		historique = new JButton(bouton2);
		historique.setPreferredSize(new Dimension(100, 40));
		historique.addActionListener(control);

		this.add(accueil);
		this.add(historique);
	}
}