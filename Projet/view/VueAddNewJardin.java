/**
*
* @version 1.1
* @author Thomas Guillemot
*/
package view;

import javax.swing.*;
import java.awt.*;

import controller.*;

/**
 * vue pour ajouter un nouveau jardin
 */
public class VueAddNewJardin {

    private JDialog newparcelle;
    private Fenetre fenetre;

    private JPanel fond, menu;
    private JButton ajouter;
    private JLabel nom, taille_x, taille_y, Instructions;
    private JTextField nom_text, taille_x_text, taille_y_text;

    private ControlAddNewJardin control;

    /**
     * contructeur
     * @param f
     */
    public VueAddNewJardin(JPanel f) {
        fenetre = (Fenetre) SwingUtilities.windowForComponent(f);

        newparcelle = new JDialog(fenetre, "Ajout d'un nouveau jardin", true);
        newparcelle.setSize(400, 400);

        nom = new JLabel("Nom :");
        nom_text = new JTextField(10);

        taille_x = new JLabel("Largeur :");
        taille_x_text = new JTextField(10);

        taille_y = new JLabel("Hauteur :");
        taille_y_text = new JTextField(10);

        ajouter = new JButton("Ajouter ");
        //control
        control = new ControlAddNewJardin(this);

        menu = new JPanel();
        menu.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0; // make this component tall
        c.insets = new Insets(0, 0, 10, 0);
        c.weightx = .5;
        c.weighty = .5;
        c.gridwidth = 0;
        c.gridx = 0;
        c.gridy = 0;
        Instructions = new JLabel("Veuillez rentrer les champs ci-dessous");
        menu.add(Instructions, c);
        Instructions.setHorizontalAlignment(JLabel.CENTER);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0; // make this component tall
        c.insets = new Insets(0, 5, 1, 5);
        c.weightx = .5;
        c.weighty = .5;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        menu.add(nom, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10; // make this component tall
        c.insets = new Insets(0, 5, 1, 5);
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 1;
        menu.add(nom_text, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0; // make this component tall
        c.insets = new Insets(0, 5, 1, 5);
        c.weightx = .5;
        c.weighty = .5;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        menu.add(taille_x, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10; // make this component tall
        c.insets = new Insets(0, 5, 1, 5);
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 2;
        menu.add(taille_x_text, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0; // make this component tall
        c.insets = new Insets(0, 5, 10, 5);
        c.weightx = .5;
        c.weighty = .5;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        menu.add(taille_y, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10; // make this component tall
        c.insets = new Insets(0, 5, 10, 5);
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 3;
        menu.add(taille_y_text, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10; // make this component tall
        c.insets = new Insets(0, 5, 1, 5);
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 4;
        menu.add(ajouter, c);

        fond = new JPanel();
        fond.setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0; // make this component tall
        c.weightx = 0.0;
        c.gridwidth = 0;
        c.gridx = 0;
        c.gridy = 0;

        fond.add(menu, c);

        centreWindow(newparcelle);
        newparcelle.setContentPane(fond);
        newparcelle.setResizable(false);
        newparcelle.setVisible(true);

    }

    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    /**
     * @return the fond
     */
    public JPanel getFond() {
        return fond;
    }

    /**
     * @return the ajouter
     */
    public JButton getAjouter() {
        return ajouter;
    }

    /**
     * @return the nom_text
     */
    public JTextField getNom_text() {
        return nom_text;
    }

    /**
     * @return the taille_x_text
     */
    public JTextField getTaille_x_text() {
        return taille_x_text;
    }

    /**
     * @return the taille_y_text
     */
    public JTextField getTaille_y_text() {
        return taille_y_text;
    }

    /**
     * @return the newparcelle
     */
    public JDialog getNewparcelle() {
        return newparcelle;
    }
}