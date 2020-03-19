/**
*@author Thomas Guillemot
*/
package controller;

import javax.swing.*;

import view.VueAddNewJardin;

import java.awt.*;
import java.awt.event.*;

public class ControlAddNewJardin {
	private JPanel fond;
	private JTextField nom, taille_x, taille_y;
	private VueAddNewJardin view;

	public ControlAddNewJardin(VueAddNewJardin view) {
		this.view = view;

		fond = view.getFond();
		nom = view.getNom_text();
		taille_x = view.getTaille_x_text();
		taille_y = view.getTaille_y_text();

		view.getAjouter().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					int x = Integer.parseInt(taille_x.getText());
					int y = Integer.parseInt(taille_y.getText());
					if (x <= 0 || y <= 0) {

						JOptionPane.showMessageDialog(null, "Entrez des dimensions positives !");
					} else {
						if (nom.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Entrez un nom valide !");
						} else {
							view.getNewparcelle().dispose();
							JOptionPane.showMessageDialog(null,
									"Creation du jardin " + nom.getText() + " de " + x + " par " + y);
						}
					}

				} catch (NumberFormatException n) {
					JOptionPane.showMessageDialog(null, "Les dimensions ne sont pas des nombres !");
				}
			}
		});
	}
}
