/**
*@author Thomas Guillemot
*/
package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import view.*;

public class ControlnavParcelle {

	private VueMenu viewMenu;

	private JPanel fond;
	private CardLayout layout;
	private final String[] nomboutons;

	public ControlnavParcelle(VueMenu viewMenu, VuenavParcelle viewnavPar) {
		this.viewMenu = viewMenu;

		fond = viewMenu.getFond();
		layout = viewMenu.getLayout();
		nomboutons = viewMenu.getNomboutons();

		viewnavPar.getAccueil().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				layout.show(fond, nomboutons[0]);
			}
		});

		viewnavPar.getHistorique().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				layout.show(fond, nomboutons[2]);
			}
		});

	}
}