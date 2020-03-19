/**
*
* @version 1.1
* @author Thomas Guillemot
*/
package view;

import javax.swing.*;
import java.awt.*;

import controller.*;
import models.*;

public class VueAddNewLegume {

    private JDialog newparcelle;
    private Fenetre fenetre;

    private JPanel fond, menu;
    private JButton ajouter;
    private JLabel nom, type, semis, recolte, Instructions;
    private JTextField nom_text;
    private JComboBox<FamilleLegume> type_enum;
    private JComboBox<String> semis_date1, semis_date2, recolte_date1, recolte_date2;

    private String[] choices = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };

    private ControlAddNewLegume control;

    public VueAddNewLegume(JPanel f) {
        fenetre = (Fenetre) SwingUtilities.windowForComponent(f);

        newparcelle = new JDialog(fenetre, "Ajout d'un nouveau legume", true);
        newparcelle.setSize(400, 400);

        nom = new JLabel("Nom legume :");
        nom_text = new JTextField(10);

        type = new JLabel("Type de legume :");
        type_enum = new JComboBox<FamilleLegume>(FamilleLegume.values());

        semis = new JLabel("Periode semis :");
        semis_date1 = new JComboBox<String>(choices);
        semis_date2 = new JComboBox<String>(choices);

        recolte = new JLabel("Periode recoltes :");
        recolte_date1 = new JComboBox<String>(choices);
        recolte_date2 = new JComboBox<String>(choices);

        ajouter = new JButton("Ajouter ");
        //control
        control = new ControlAddNewLegume(this);

        menu = new JPanel();
        menu.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0; // make this component tall
        c.insets = new Insets(0, 0, 15, 0);
        c.weightx = .5;
        c.weighty = .5;
        c.gridwidth = 0;
        c.gridx = 0;
        c.gridy = 0;
        Instructions = new JLabel("Veuillez rentrer les champs ci-dessous");
        menu.add(Instructions, c);
        Instructions.setHorizontalAlignment(JLabel.CENTER);

        // nom
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0; // make this component tall
        c.insets = new Insets(0, 5, 5, 5);
        c.weightx = .5;
        c.weighty = .5;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        menu.add(nom, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10; // make this component tall
        c.insets = new Insets(0, 5, 5, 5);
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 1;
        menu.add(nom_text, c);

        // type
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0; // make this component tall
        c.insets = new Insets(0, 5, 5, 5);
        c.weightx = .5;
        c.weighty = .5;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        menu.add(type, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10; // make this component tall
        c.insets = new Insets(0, 5, 5, 5);
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 2;
        menu.add(type_enum, c);

        // semis
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0; // make this component tall
        c.insets = new Insets(0, 5, 5, 5);
        c.weightx = .5;
        c.weighty = .5;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        menu.add(semis, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(0, 5, 5, 5);
        menu.add(semis_date1, c);

        c.gridx = 2;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(0, 5, 5, 5);
        menu.add(semis_date2, c);

        // recoltes
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0; // make this component tall
        c.insets = new Insets(0, 5, 15, 5);
        c.weightx = .5;
        c.weighty = .5;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 4;
        menu.add(recolte, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(0, 5, 15, 5);
        menu.add(recolte_date1, c);

        c.gridx = 2;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(0, 5, 15, 5);
        menu.add(recolte_date2, c);

        // confirmer
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10; // make this component tall
        c.insets = new Insets(0, 5, 0, 5);
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 5;
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
     * @return the type_enum
     */
    public JComboBox<FamilleLegume> getType_enum() {
        return type_enum;
    }

    /**
     * @return the semis_date1
     */
    public JComboBox<String> getSemis_date1() {
        return semis_date1;
    }

    /**
     * @return the semis_date2
     */
    public JComboBox<String> getSemis_date2() {
        return semis_date2;
    }

    /**
     * @return the recolte_date1
     */
    public JComboBox<String> getRecolte_date1() {
        return recolte_date1;
    }

    /**
     * @return the recolte_date2
     */
    public JComboBox<String> getRecolte_date2() {
        return recolte_date2;
    }

}