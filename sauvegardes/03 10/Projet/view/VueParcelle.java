/**
*@author Thomas Guillemot
*/
package view;
import javax.swing.*;
import java.awt.*;
import view.*;

import controller.*;

public class VueParcelle extends JPanel{
	private JPanel panneau_grille,panneau_boutons;
	private JButton accueil,historique;


	public VueParcelle(String bouton1 , String bouton2,ControlMenu control){
		panneau_grille = new JPanel();

		accueil = new JButton(bouton1);
		accueil.addActionListener(control);

		historique = new JButton(bouton2);
		historique.addActionListener(control);

		this.add(accueil);
		this.add(historique);
	}
}