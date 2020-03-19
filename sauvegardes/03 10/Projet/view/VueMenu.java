/**
*@author Thomas Guillemot
*/
package view;
import javax.swing.*;
import java.awt.*;
import view.*;

import javax.swing.*;
import java.awt.*;

import controller.*;


public class VueMenu{

	private Fenetre fenetre;

	private JPanel fond;
	private CardLayout layout;

	private VueAccueil accueil;
	private VueParcelle parcelle;
	private VueHistorique historique;

	private ControlMenu controlemenu;

	private String nomboutons[];
	public VueMenu(String nom, int dim_y , int dim_x){
		fenetre = new Fenetre(nom,dim_y,dim_x);
		fond = new JPanel();
		layout = new CardLayout();
		fond.setLayout(layout);

		nomboutons = new String[3];
		nomboutons[0]= "Accueil";
		nomboutons[1]= "Parcelle";
		nomboutons[2]= "Historique";
		controlemenu = new ControlMenu(fond,layout,nomboutons);

		accueil = new VueAccueil();
		parcelle = new VueParcelle(nomboutons[0],nomboutons[2],controlemenu);
		historique = new VueHistorique(nomboutons[0],nomboutons[1],controlemenu);

		fond.add(accueil,nomboutons[0]);
		fond.add(parcelle,nomboutons[1]);
		fond.add(historique,nomboutons[2]);


		layout.show(fond,nomboutons[1]);
		fenetre.setContentPane(fond);
		fond.revalidate();
	}
}