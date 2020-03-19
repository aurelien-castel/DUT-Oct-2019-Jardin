/**
*
* @version 1.1
* @author Aurélien Castel & Thomas Guillemot
*/
package view;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import javax.swing.event.*;
import java.util.ArrayList;

import controller.*;

public class VueAddNewJardin{
	private JDialog newparcelle;
	private Fenetre fenetre;

    public VueAddNewJardin(JPanel fond) {
    	fenetre = (Fenetre) SwingUtilities.windowForComponent(fond);
    	newparcelle = new JDialog(fenetre,"Ajout d'un nouveau jardin",true);

        newparcelle.setSize(400, 400);
        newparcelle.setVisible(true);
        newparcelle.setResizable(false);

    }
}