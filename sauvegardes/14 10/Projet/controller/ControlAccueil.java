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

import view.*;

public class ControlAccueil {
	private JPanel fond;
	private CardLayout layout;

	private VueAccueil viewAccueil;
	private VueMenu viewMenu;

	public ControlAccueil(VueMenu viewMenu, VueAccueil viewAccueil) {
		this.viewAccueil = viewAccueil;
		this.viewMenu = viewMenu;

		fond = viewMenu.getFond();

		viewAccueil.getAdd().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				VueAddNewJardin vaddnewjardin = new VueAddNewJardin(fond);
			}
		});
	}

}