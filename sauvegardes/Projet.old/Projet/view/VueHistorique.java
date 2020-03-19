/**
*@author Thomas Guillemot
*/
package view;

import javax.swing.*;
import java.awt.*;
import view.*;

import controller.*;
import models.*;

public class VueHistorique extends JPanel {
	private VuenavHistorique nav;
	private ControlnavHist nav_control;

	private VuepanelHistorique grille;
	private ControlpanelHistorique grille_control;

	private VueMenu viewMenu;

	public VueHistorique(VueMenu viewMenu, Parcelle p0) {

		setLayout(new BorderLayout());

		nav = new VuenavHistorique(viewMenu);
		nav_control = new ControlnavHist(viewMenu, nav);

		this.add(nav, BorderLayout.NORTH);

		// MVC models
		grille = new VuepanelHistorique(p0);
		grille_control = new ControlpanelHistorique(grille); // --> controller a besoins de la vue et du modele

		this.add(grille);

	}
}