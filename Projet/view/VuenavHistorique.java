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

import controller.*;

/**
 * vue pour visualiser le card layout
 */
public class VuenavHistorique extends JPanel {
	
	private JButton accueil, parcelle, historique;

	private VueMenu viewMenu;

	/**
	 * contructeur
	 * @param viewMenu
	 */
	public VuenavHistorique(VueMenu viewMenu) {

		this.viewMenu = viewMenu;
		String[] nomboutons = viewMenu.getNomboutons();

		accueil = new JButton(nomboutons[0]);
		accueil.setPreferredSize(new Dimension(150, 40));
		// accueil.addActionListener(control);

		parcelle = new JButton(nomboutons[1]);
		parcelle.setPreferredSize(new Dimension(150, 40));
		// accueil.addActionListener(control);

		historique = new JButton(nomboutons[2]);
		historique.setPreferredSize(new Dimension(150, 40));
		// historique.addActionListener(control);

		historique.setEnabled(false);

		this.add(accueil);
		this.add(parcelle);
		this.add(historique);
	}

	/**
	 * @return the accueil
	 */
	public JButton getAccueil() {
		return accueil;
	}

	/**
	 * @return the parcelle
	 */
	public JButton getParcelle() {
		return parcelle;
	}

	/**
	 * @return the historique
	 */
	public JButton getHistorique() {
		return historique;
	}

}