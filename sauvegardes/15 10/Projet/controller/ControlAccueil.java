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

public class ControlAccueil {
	private JPanel fond;
	private CardLayout layout;

	private VueAccueil viewAccueil;
	private VueMenu viewMenu;
	private final String[] nomboutons;

	public ControlAccueil(VueMenu viewMenu, VueAccueil viewAccueil) {
		this.viewAccueil = viewAccueil;
		this.viewMenu = viewMenu;

		AbstractJardinFactory jfbd = JardinFactoryBD.getJardinFactoryBD();

		fond = viewMenu.getFond();
		layout = viewMenu.getLayout();
		nomboutons = viewMenu.getNomboutons();

		viewAccueil.getAdd().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				VueAddNewJardin vaddnewjardin = new VueAddNewJardin(fond);
			}
		});

		for (JButton e : viewAccueil.getListButton()){

			e.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					System.out.println(e.getText());
					//Parcelle p = jfbd.getJardin(e.getName());
					layout.show(fond, nomboutons[1]);
				}
			});

		}

	}

}