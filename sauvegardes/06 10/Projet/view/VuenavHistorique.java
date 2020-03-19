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

public class VuenavHistorique extends JPanel {
	
	private JButton accueil, parcelle;

	private VueMenu viewMenu;

	public VuenavHistorique(VueMenu viewMenu) {

		this.viewMenu = viewMenu;
		String[] nomboutons = viewMenu.getNomboutons();

		accueil = new JButton(nomboutons[0]);
		accueil.setPreferredSize(new Dimension(100, 40));
		//accueil.addActionListener(control);

		parcelle = new JButton(nomboutons[1]);
		parcelle.setPreferredSize(new Dimension(100, 40));
		//historique.addActionListener(control);

		this.add(accueil);
		this.add(parcelle);
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
}