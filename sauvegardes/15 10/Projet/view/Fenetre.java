/**
* La classe <code>Fenetre</code> est utilisée pour afficher une fênetre.
*
* @version 1.1
* @author Thomas Guillemot
*/

/** 
* Librairies utilisées :
*/
package view;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {

	/**
	 * Constructeur :
	 *
	 * @param nom   correspondant au nom de la fênetre.
	 * @param dim_y correspondant à la hauteur de la fenêtre.
	 * @param dim_x correspondant à la largeur de la fenêtre.
	 */

	public Fenetre(String nom, int dim_y, int dim_x) {
		super(nom);
		this.setSize(dim_y, dim_x);

		/*
		 * Pas de redimension par l'utilisateur.
		 */
		// this.setResizable(false);

		centreWindow(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}
}