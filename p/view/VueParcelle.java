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
 * classe pour reunir le card layout, le models et la vue
 * de la parcelle
 */
public class VueParcelle extends JPanel {

	private VuenavParcelle nav;
	private ControlnavPar nav_control;

	private VuepanelParcelle grille;
	private ControlpanelParcelle grille_control;

	private VueMenu viewMenu;

	public VueParcelle(VueMenu viewMenu, Parcelle p0) {

		this.viewMenu = viewMenu;

		setLayout(new BorderLayout());

		nav = new VuenavParcelle(viewMenu);
		nav_control = new ControlnavPar(viewMenu, nav);

		this.add(nav, BorderLayout.NORTH);

		// M : models
		AbstractJardinFactory jfbd = JardinFactoryBD.getJardinFactoryBD();
		AbstractLegumeFactory jlbd = LegumeFactoryBD.getLegumeFactoryBD();
		//jfbd.setLegumeFactory(jlbd);

		// VC : view , controller
		grille = new VuepanelParcelle(p0, jlbd); // la vue n'a que des composants graphiques
		grille_control = new ControlpanelParcelle(grille, viewMenu, p0, jfbd, jlbd);
		// --> controller a besoins de la
		// vue et du modele

		this.add(grille);
	}
}