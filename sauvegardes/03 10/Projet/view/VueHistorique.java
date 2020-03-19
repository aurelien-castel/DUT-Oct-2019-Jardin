/**
*@version 1.1
*@author Thomas Guillemot
*/
/** 
* Librairies utilisées :
*/
package view;
import javax.swing.*;
import java.awt.*;
import view.*;
import controller.*;

public class VueHistorique extends JPanel{
	private JPanel panneau_grille,panneau_boutons;
	private JButton accueil,historique;


	public VueHistorique(String bouton1,String bouton2,ControlMenu control){
		this.setLayout(new BorderLayout(2,1));
		panneau_grille = new JPanel();
		panneau_boutons = new JPanel();

		accueil = new JButton(bouton1);
		accueil.addActionListener(control);

		historique = new JButton(bouton2);
		historique.addActionListener(control);

		panneau_boutons.add(accueil);
		panneau_boutons.add(historique);

		this.add(panneau_boutons,BorderLayout.NORTH);
		this.add(panneau_grille);
	}
}