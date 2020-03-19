/**
*@author Thomas Guillemot
*/
package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import view.*;

public class ControlnavHistorique {

	private VueMenu viewMenu;
	private VuenavHistorique viewnavHist;

	private JPanel fond;
	private CardLayout layout;
	private final String[] nomboutons;

	public ControlnavHistorique(VueMenu viewMenu, VuenavHistorique viewnavHist) {
		this.viewMenu = viewMenu;
		this.viewnavHist = viewnavHist;

		fond = viewMenu.getFond();
		layout = viewMenu.getLayout();
		nomboutons = viewMenu.getNomboutons();

		viewnavHist.getAccueil().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				layout.show(fond, nomboutons[0]);
			}
		});

		viewnavHist.getParcelle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				layout.show(fond, nomboutons[1]);
			}
		});

	}
}