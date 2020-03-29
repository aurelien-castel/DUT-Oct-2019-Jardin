/**
*@author Thomas Guillemot
*/
package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import view.*;

/**
 * controleur pour le panel de navigation (card layout)
 */
public class ControlnavPar implements ActionListener {

	private VueMenu viewMenu;
	private VuenavParcelle viewnav;

	private JPanel fond;
	private CardLayout layout;
	private final String[] nomboutons;

	/**
	 * constructeur
	 * @param viewMenu
	 * @param viewnav
	 */
	public ControlnavPar(VueMenu viewMenu, VuenavParcelle viewnav) {
		this.viewMenu = viewMenu;
		this.viewnav = viewnav;

		fond = viewMenu.getFond();
		layout = viewMenu.getLayout();
		nomboutons = viewMenu.getNomboutons();

		viewnav.getAccueil().addActionListener(this);/*
		viewnav.getAccueil().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				layout.show(fond, nomboutons[0]);
			}
		});*/

		viewnav.getParcelle().addActionListener(this);/*
		viewnav.getParcelle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				layout.show(fond, nomboutons[1]);
			}
		});*/

		viewnav.getHistorique().addActionListener(this);/*
		viewnav.getHistorique().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				layout.show(fond, nomboutons[2]);
			}
		});*/

	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		Object source = ae.getSource();

		if (source == viewnav.getAccueil()) {
			layout.show(fond, nomboutons[0]);
		}

		if (source == viewnav.getParcelle()) {
			layout.show(fond, nomboutons[1]);
		}

		if (source == viewnav.getHistorique()) {
			layout.show(fond, nomboutons[2]);
		}
		
	}
}