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

public class VueAddNewJardin // mise en place fenetre
{
	private JDialog newparcelle;

    public VueAddNewJardin() {
    	newparcelle = new JDialog("Ajout d'un nouveau jardin");

        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newparcelle.setSize(400, 400);
        newparcelle.setVisible(true);
        newparcelle.setResizable(false);

    }
}