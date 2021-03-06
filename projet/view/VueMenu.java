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

/**
 * classe initialisatrice
 */
public class VueMenu {
	private Fenetre fenetre;

	private JPanel fond;
	private CardLayout layout;

	private VueAccueil accueil;
	private VueParcelle parcelle;
	private VueHistorique historique;

	private ControlAccueil controlaccueil;

	private String[] nomboutons;

	/**
	 * contructeur
	 * @param nom
	 * @param dim_y
	 * @param dim_x
	 */
	public VueMenu(String nom, int dim_y, int dim_x) {
		fenetre = new Fenetre(nom, dim_y, dim_x);
		fond = new JPanel();
		layout = new CardLayout();
		fond.setLayout(layout);

		nomboutons = new String[3];
		nomboutons[0] = "Accueil";
		nomboutons[1] = "Parcelle";
		nomboutons[2] = "Historique";

		boolean success = false;
		try {

			accueil = new VueAccueil();
			controlaccueil = new ControlAccueil(this, fenetre, accueil);

			success = true;

		} catch (Exception ex) {

			JOptionPane.showMessageDialog(null, "Probleme de connection a la base de donnees,\nredemarrez le programme.");

		}
		if (success) {

			// parcelle = new VueParcelle(this);
			// historique = new VueHistorique(this);

			fond.add(accueil, nomboutons[0]);
			// fond.add(parcelle, nomboutons[1]);
			// fond.add(historique, nomboutons[2]);

			layout.show(fond, nomboutons[0]);
			fenetre.setContentPane(fond);
			fond.revalidate();
		}
	}

	/**
	 * @return the fond
	 */
	public JPanel getFond() {
		return fond;
	}

	/**
	 * @return the nomboutons
	 */
	public String[] getNomboutons() {
		return nomboutons;
	}

	/**
	 * @return the layout
	 */
	public CardLayout getLayout() {
		return layout;
	}

}