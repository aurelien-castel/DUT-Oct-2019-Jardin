/**
*@author Thomas Guillemot
*/
package controller;

import javax.swing.*;

import models.*;

import view.VueAddNewJardin;

import java.awt.*;
import java.awt.event.*;

/**
 * controleur pour ajouter un nouveau jardin
 */
public class ControlAddNewJardin implements ActionListener {
	private JPanel fond;
	private JTextField nom, taille_x, taille_y;
	private VueAddNewJardin view;

	/**
	 * constructeur
	 * @param view
	 */
	public ControlAddNewJardin(VueAddNewJardin view) {
		this.view = view;

		fond = view.getFond();
		nom = view.getNom_text();
		taille_x = view.getTaille_x_text();
		taille_y = view.getTaille_y_text();

		view.getAjouter().addActionListener(this);/*
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

							AbstractJardinFactory jfbd = JardinFactoryBD.getJardinFactoryBD();

							boolean success = false;
							try {

								jfbd.AddJardin(nom.getText(), x, y);
								success = true;
							} catch (Exception ex) {

								JOptionPane.showMessageDialog(null, "Probleme nom deja utilise");

							}
							if (success) {
								view.getNewparcelle().dispose();

								// Create a stream to hold the output

								JOptionPane.showMessageDialog(null, "Creation du jardin " + nom.getText() + " de " + x
										+ " par " + y + " dans la base\nRedemarrez l'application");
							}
						}
					}

				} catch (NumberFormatException n) {
					JOptionPane.showMessageDialog(null, "Les dimensions ne sont pas des nombres !");
				}
			}
		});*/
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == view.getAjouter()) {

			try {
				int x = Integer.parseInt(taille_x.getText());
				int y = Integer.parseInt(taille_y.getText());
				if (x <= 0 || y <= 0) {

					JOptionPane.showMessageDialog(null, "Entrez des dimensions positives !");
				} else {
					if (nom.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Entrez un nom valide !");
					} else {

						AbstractJardinFactory jfbd = JardinFactoryBD.getJardinFactoryBD();

						boolean success = false;
						try {

							jfbd.AddJardin(nom.getText(), x, y);
							success = true;
						} catch (Exception ex) {

							JOptionPane.showMessageDialog(null, "Probleme nom deja utilise");

						}
						if (success) {
							view.getNewparcelle().dispose();

							// Create a stream to hold the output

							JOptionPane.showMessageDialog(null, "Creation du jardin " + nom.getText() + " de " + x
									+ " par " + y + " dans la base\nRedemarrez l'application");
						}
					}
				}

			} catch (NumberFormatException n) {
				JOptionPane.showMessageDialog(null, "Les dimensions ne sont pas des nombres !");
			}
		}
	}
}
