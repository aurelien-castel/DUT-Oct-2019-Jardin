/**
*@author Thomas Guillemot
*/
package view;

import javax.swing.*;
import java.awt.*;
import view.*;

import controller.*;

public class VuenavParcelle extends JPanel {
	
	private JButton accueil, historique;

	private VueMenu viewMenu;

	public VuenavParcelle(VueMenu viewMenu) {

		this.viewMenu = viewMenu;
		String[] nomboutons = viewMenu.getNomboutons();

		accueil = new JButton(nomboutons[0]);
		accueil.setPreferredSize(new Dimension(100, 40));
		// accueil.addActionListener(control);

		historique = new JButton(nomboutons[2]);
		historique.setPreferredSize(new Dimension(100, 40));
		// historique.addActionListener(control);

		this.add(accueil);
		this.add(historique);
	}

	/**
	 * @return the accueil
	 */
	public JButton getAccueil() {
		return accueil;
	}

	/**
	 * @return the historique
	 */
	public JButton getHistorique() {
		return historique;
	}
}