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
import models.*;
import view.*;

/**
 * vue pour l'accueil
 */
public class VueAccueil extends JPanel {

    JButton add;
    ArrayList<String> listString;
    ArrayList<JButton> listButton;

    JPanel buttonsPanel;

    JardinFactoryBD jbd;

    /**
     * constructeur
     */
    public VueAccueil() {

        JButton button;
        JLabel label;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        buttonsPanel= new JPanel(new GridBagLayout());
        
        add(buttonsPanel);
        /* ===================== */
        c.fill = GridBagConstraints.HORIZONTAL;

        add = new JButton("Ajouter un nouveau jardin");
        add.setBackground(new Color(169, 223, 191));
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 40;
        c.gridwidth = 2;
        c.insets = new Insets(10, 0, 0, 0);
        buttonsPanel.add(add, c);

        AbstractJardinFactory jfbd = JardinFactoryBD.getJardinFactoryBD();
        listString = jfbd.getallJardinname();
        /*
         * for(String e : listString){ System.out.println(e); }
         */
        listButton = new ArrayList<>();

        int i = 2;
        int j = 0;
        for (String element : listString) {
            button = new JButton(element);// changer ici pour la db
            listButton.add(button);
            c.anchor = GridBagConstraints.LINE_START;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = i;
            c.ipady = 40;
            c.gridwidth = 2;
            c.insets = new Insets(10, 0, 0, 0);
            buttonsPanel.add(button, c);
            i++;
            j++;
        }

        for (JButton element : listButton) {

            System.out.println(element.getText());
        }

        JScrollPane scroller = new JScrollPane(buttonsPanel);
        scroller.setPreferredSize(new Dimension(300, 350));

        add(scroller);

        scroller.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Gestionnaire de jardins"));
    }

    /**
     * @return the add
     */
    public JButton getAdd() {
        return add;
    }

    /**
     * @return the listButton
     */
    public ArrayList<JButton> getListButton() {
        return listButton;
    }

}