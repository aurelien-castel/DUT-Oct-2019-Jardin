/**
*@author Thomas Guillemot
*/
package view;

import javax.swing.*;
import java.awt.*;
import view.*;

import controller.*;
import models.*;

/**
 * classe pour reunir le card layout, le models et la vue de l'historique
 */
public class VueHistorique extends JPanel {
	private VuenavHistorique nav;
	private ControlnavHist nav_control;

	private VuepanelHistorique grille;
	private ControlpanelHistorique grille_control;

	private VueMenu viewMenu;

	/**
	 * constructeur
	 * 
	 * @param viewMenu
	 * @param p0
	 */
	public VueHistorique(VueMenu viewMenu, Parcelle p0) {

		setLayout(new BorderLayout());

		nav = new VuenavHistorique(viewMenu);
		nav_control = new ControlnavHist(viewMenu, nav);

		this.add(nav, BorderLayout.NORTH);

		/* M models */
		AbstractActionFactory aabd = ActionFactoryBD.getActionFactoryBD();

		// MVC models
		grille = new VuepanelHistorique(p0, aabd);
		grille_control = new ControlpanelHistorique(grille, p0, aabd); 
		// --> controller a besoins de la vue et du modele

		this.add(grille);

	}
}