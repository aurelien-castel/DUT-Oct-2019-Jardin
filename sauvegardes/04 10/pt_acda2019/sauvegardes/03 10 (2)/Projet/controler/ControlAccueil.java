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

public class ControlAccueil implements ActionListener {
	private JPanel fond;
	private CardLayout layout;
	public ControlAccueil(JPanel fond,CardLayout layout){
		this.fond = fond;
		this.layout = layout;		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		VueAddNewJardin vaddnewjardin = new VueAddNewJardin(fond);
	}
}