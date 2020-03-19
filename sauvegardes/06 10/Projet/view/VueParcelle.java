/**
*@author Thomas Guillemot
*/
package view;

import javax.swing.*;
import java.awt.*;
import view.*;

import controller.*;

public class VueParcelle extends JPanel {

	private VuenavParcelle nav;
	private ControlnavParcelle nav_control;

	private VuepanelParcelle grille;
	private ControlpanelParcelle grille_control;

	private VueMenu viewMenu;

	public VueParcelle(VueMenu viewMenu) {

		this.viewMenu = viewMenu;

		setLayout(new BorderLayout());

		nav = new VuenavParcelle(viewMenu);
		nav_control = new ControlnavParcelle(viewMenu, nav);

		this.add(nav, BorderLayout.NORTH);

		// MVC models
		grille = new VuepanelParcelle();
		grille_control = new ControlpanelParcelle(grille); // --> controller a besoins de la vue et du modele

		this.add(grille);
	}
}