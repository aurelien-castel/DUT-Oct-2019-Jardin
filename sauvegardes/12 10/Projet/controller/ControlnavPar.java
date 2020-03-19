/**
*@author Thomas Guillemot
*/
package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import view.*;

public class ControlnavPar {

	private VueMenu viewMenu;

	private JPanel fond;
	private CardLayout layout;
	private final String[] nomboutons;

	public ControlnavPar(VueMenu viewMenu, VuenavParcelle viewnav) {
		this.viewMenu = viewMenu;

		fond = viewMenu.getFond();
		layout = viewMenu.getLayout();
		nomboutons = viewMenu.getNomboutons();

		viewnav.getAccueil().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				layout.show(fond, nomboutons[0]);
			}
		});

		viewnav.getParcelle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				layout.show(fond, nomboutons[1]);
			}
		});

		viewnav.getHistorique().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				layout.show(fond, nomboutons[2]);
			}
		});

	}
}