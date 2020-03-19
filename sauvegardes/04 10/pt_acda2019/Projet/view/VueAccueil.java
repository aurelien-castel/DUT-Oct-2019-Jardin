/**
*
* @version 1.1
* @author Aurélien Castel
*/

package view;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import javax.swing.event.*;
import java.util.ArrayList;

import controller.*;
import view.*;

public class VueAccueil extends JPanel {
    public VueAccueil(ControlAccueil controlaccueil) {

        JButton button;
        JLabel label;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        add(buttonsPanel);
        /* ===================== */
        c.fill = GridBagConstraints.HORIZONTAL;

        JButton add = new JButton("Ajouter un nouveau jardin");
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 40;
        c.gridwidth = 2;
        c.insets = new Insets(10,0,0,0);
        buttonsPanel.add(add, c);

        add.addActionListener(controlaccueil);

        for(int i = 2; i<5 ; i++){
            button = new JButton("Jardin n°"+i+"");//changer ici pour la db
            c.anchor = GridBagConstraints.LINE_START;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = i;
            c.ipady = 40;
            c.gridwidth = 2;
            c.insets = new Insets(10,0,0,0);
            buttonsPanel.add(button, c);
        }
        buttonsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Gestionnaire de jardins"));
    }
}