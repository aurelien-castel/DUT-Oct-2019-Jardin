/**
*
* @version 1.1
* @author Aurélien Castel & Thomas Guillemot
*/
package controller;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import javax.swing.event.*;
import java.util.ArrayList;

import models.*;
import view.*;

/**
 * le controleur de la vue accueil:
 * navigation des differents jardin
 * de l'utilisateur
 */
public class ControlAccueil implements ActionListener {
	private JPanel fond;
	private CardLayout layout;

	private VueAccueil viewAccueil;
	private VueMenu viewMenu;
	private final String[] nomboutons;

	private VueParcelle parcelle;
	private VueHistorique historique;

	private Parcelle p;

	private Fenetre fenetre;

	private AbstractJardinFactory jfbd;

	/**
	 * constructeur
	 * @param viewMenu
	 * @param fenetre
	 * @param viewAccueil
	 */
	public ControlAccueil(VueMenu viewMenu, Fenetre fenetre, VueAccueil viewAccueil) {
		this.viewAccueil = viewAccueil;
		this.viewMenu = viewMenu;
		this.fenetre = fenetre;

		jfbd = JardinFactoryBD.getJardinFactoryBD();

		fond = viewMenu.getFond();
		layout = viewMenu.getLayout();
		nomboutons = viewMenu.getNomboutons();

		viewAccueil.getAdd().addActionListener(this);
		/*
		viewAccueil.getAdd().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				VueAddNewJardin vaddnewjardin = new VueAddNewJardin(fond);
			}
		});*/
		for (JButton e : viewAccueil.getListButton()) {
			e.addActionListener(this);
		}
		/*
		for (JButton e : viewAccueil.getListButton()) {

			e.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) { // creation de l'affichage parcelle selectionnee
					System.out.println("Jardin selectionee: " + e.getText());

					boolean success = false;
					try {
						p = jfbd.getJardin(e.getText());

						success = true;
								

					} catch (Exception ex) {

						JOptionPane.showMessageDialog(null, "Probleme ce jardin n'est plus existant redemarrez l'application pour ne plus le voir");

					}
					if (success) {

						parcelle = new VueParcelle(viewMenu, p);
						historique = new VueHistorique(viewMenu, p);

						fond.add(viewAccueil, nomboutons[0]);
						fond.add(parcelle, nomboutons[1]);
						fond.add(historique, nomboutons[2]);

						layout.show(fond, nomboutons[0]);
						fenetre.setContentPane(fond);
						fond.revalidate();

						layout.show(fond, nomboutons[1]);
					}
				}
			});

		}*/

	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		Object source = ae.getSource();

		if (source == viewAccueil.getAdd()) {
			VueAddNewJardin vaddnewjardin = new VueAddNewJardin(fond);
		}

		for (JButton e : viewAccueil.getListButton()) {

			if (source == e) {
				System.out.println("Jardin selectionee: " + e.getText());

					boolean success = false;
					try {
						p = jfbd.getJardin(e.getText());

						success = true;

					} catch (Exception ex) {

						JOptionPane.showMessageDialog(null, "Probleme ce jardin n'est plus existant redemarrez l'application pour ne plus le voir");

					}
					if (success) {

						parcelle = new VueParcelle(viewMenu, p);
						historique = new VueHistorique(viewMenu, p);

						fond.add(viewAccueil, nomboutons[0]);
						fond.add(parcelle, nomboutons[1]);
						fond.add(historique, nomboutons[2]);

						layout.show(fond, nomboutons[0]);
						fenetre.setContentPane(fond);
						fond.revalidate();

						layout.show(fond, nomboutons[1]);
					}
			}

		}
		
	}

}