/**
*
* @version 1.1
* @author Aurélien Castel
*/

package controller;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.border.Border;

import java.io.File;
import java.io.IOException;

import java.util.*;
import java.util.List;
import java.util.Arrays;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

import javax.imageio.ImageIO;

import view.*;
import models.*;

/**
 * Classe pour controler la vue des parcelles et de leur gestion
 */
public class ControlpanelParcelle implements ActionListener {

    private VuepanelParcelle view;

    // constraints
    private GridBagConstraints c;
    // parcelle
    private Parcelle currentParcelle;

    // Menu
    private JPanel fond;
    private CardLayout layout;
    private final String[] nomboutons;

    // stack
    private Stack<Parcelle> stackOfParcelle;

    // factory
    private AbstractJardinFactory jfbd;
    private AbstractLegumeFactory jlbd;
    private Parcelle p0;

    /**
     * constructeur
     * 
     * @param view
     * @param viewMenu
     * @param p0
     * @param jfbd
     * @param jlbd
     */
    public ControlpanelParcelle(VuepanelParcelle view, VueMenu viewMenu, Parcelle p0, AbstractJardinFactory jfbd,
            AbstractLegumeFactory jlbd) {

        this.jfbd = jfbd;
        this.jlbd = jlbd;
        this.p0 = p0;

        fond = viewMenu.getFond();
        layout = viewMenu.getLayout();
        nomboutons = viewMenu.getNomboutons();

        c = new GridBagConstraints();

        this.view = view;

        // *Parcelle mere*//
        stackOfParcelle = new Stack<>();
        stackOfParcelle.push(p0); // au debut on push la parcelle mere

        affichageNavigation(p0);

        view.getRetour().addActionListener(this);

        view.getHome().addActionListener(this);

        view.getDeleteButton().addActionListener(this);

        view.getAddLegume().addActionListener(this);

        view.getDeleteLegume().addActionListener(this);

    }

    /**
     * affichage de la navigation (changement du panel de droite)
     * 
     * @param p
     */
    public void affichageNavigation(Parcelle p) {

        view.getJardinPanel().removeAll();
        Orientation orient = p.getSplit();
        currentParcelle = p;

        if (orient != null) {

            Parcelle p00 = p.getFirst();
            Parcelle p01 = p.getSecond();

            if (orient == Orientation.VERTICAL) {

                System.out.println("vertical");
                JButton button0 = buttonParcelle(p00);
                JButton button1 = buttonParcelle(p01);

                c.fill = GridBagConstraints.HORIZONTAL;
                c.ipady = 0; // make this component tall
                c.insets = new Insets(0, 0, 0, 0);
                c.weightx = 0.0;
                c.gridwidth = 1;
                c.gridx = 0;
                c.gridy = 0;
                view.getJardinPanel().add(button0, c);

                c.fill = GridBagConstraints.HORIZONTAL;
                c.ipady = 0; // make this component tall
                c.insets = new Insets(0, 0, 0, 0);
                c.weightx = 0.0;
                c.gridwidth = 1;
                c.gridx = 1;
                c.gridy = 0;
                view.getJardinPanel().add(button1, c);

            } else if (orient == Orientation.HORIZONTAL) {

                System.out.println("horizontal");
                JButton button0 = buttonParcelle(p00);
                JButton button1 = buttonParcelle(p01);

                c.fill = GridBagConstraints.HORIZONTAL;
                c.ipady = 0; // make this component tall
                c.insets = new Insets(0, 0, 0, 0);
                c.weightx = 0.0;
                c.gridwidth = 1;
                c.gridx = 0;
                c.gridy = 0;
                view.getJardinPanel().add(button0, c);

                c.fill = GridBagConstraints.HORIZONTAL;
                c.ipady = 0; // make this component tall
                c.insets = new Insets(0, 0, 0, 0);
                c.weightx = 0.0;
                c.gridwidth = 1;
                c.gridx = 0;
                c.gridy = 1;
                view.getJardinPanel().add(button1, c);
            }

        } else if (orient == null) {
            System.out.println("none");
            JButton button = buttonParcelle(p); // d'abord on affiche la parcelle mere elle est un bouton

            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 0; // make this component tall
            c.insets = new Insets(0, 0, 0, 0);
            c.weightx = 0.0;
            c.gridwidth = 0;
            c.gridx = 0;
            c.gridy = 0;
            view.getJardinPanel().add(button, c);
        }

        view.getJardinPanel().revalidate();
        view.getJardinPanel().repaint();

        mappage();

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        Object source = ae.getSource();

        if (source == view.getRetour()) {
            if (stackOfParcelle.size() > 1) {

                stackOfParcelle.pop();

                affichageNavigation(stackOfParcelle.peek());

            }
        }

        if (source == view.getHome()) {

            if (stackOfParcelle.size() > 1) {

                // while this element is not the first (mother)
                while (stackOfParcelle.firstElement() != stackOfParcelle.peek()) {
                    stackOfParcelle.pop();
                }

                affichageNavigation(stackOfParcelle.peek());

            }

        }

        if (source == view.getDeleteButton()) {

            if (stackOfParcelle.peek().getSplit() != null) { // si le dernier peek n'est pas un null
                // split
                currentParcelle.reset();
                affichageNavigation(currentParcelle);
            } else if (stackOfParcelle.size() == 1) {

                System.out.println("remove jardin");

                int reponse = JOptionPane.showConfirmDialog(null, "Vous allez supprimer ce jardin, etes vous sur ?",
                        "Attention", JOptionPane.YES_NO_OPTION);

                if (reponse == JOptionPane.YES_OPTION) {
                    // supprimer jardin

                    // retour accueil
                    layout.show(fond, nomboutons[0]);
                    boolean success = false;
                    try {
                        jfbd.DeleteJardin(p0.getNomJardin());
                        success = true;
                    } catch (Exception ex) {

                        JOptionPane.showMessageDialog(null,
                                "Probleme cela n'est plus existant redemarrez l'application pour ne plus le voir");

                    }
                    if (success) {

                        JOptionPane.showMessageDialog(null, "Le jardin a ete supprime");
                    }
                } else if (reponse == JOptionPane.NO_OPTION) {
                    // rien
                }

            }

        }

        if (source == view.getAddLegume()) {

            VueAddNewLegume vaddnewlegume = new VueAddNewLegume(fond);

        }

        if (source == view.getDeleteLegume()) {

            String selected = view.getComboLegume().getSelectedItem().toString();

            boolean success = false;
            try {
                jlbd.DeleteLegume(selected);
                success = true;
            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null,
                        "Probleme cela n'est plus existant redemarrez l'application pour ne plus le voir");

            }
            if (success) {

                JOptionPane.showMessageDialog(null, "Le legume " + selected + " a ete supprime");

            }

        }

    }

    /**
     * fabrication des boutons qui caractérisent des parcelles
     * @param p
     * @return button
     */
    public JButton buttonParcelle(Parcelle p) {

        JButton button = new JButton();
        ImageIcon imgIcon = new ImageIcon();

        BufferedImage image = new BufferedImage(1000, 1000, BufferedImage.TYPE_BYTE_INDEXED);
        try {
            image = ImageIO.read(getClass().getResource("resources/terre.jpg"));
        } catch (IOException e) {

            System.out.println("Image execption:" + e);

        } catch (IllegalArgumentException e) {

            // Image est grise si pas trouvee

        }

        button.setPreferredSize(new Dimension(400, 300)); // <-- taille d'une parcelle

        List<String> list = new ArrayList<>();
        list.add("point en haut: " + p.getx0() + "," + p.gety0());
        list.add("point en bas: " + p.getx1() + "," + p.gety1());

        try {
            list.add(p.getLegume().getNom());
        } catch (NullPointerException e) {
            list.add("aucun legume");
        }

        button.add(new JLabel(new ImageIcon(view.dye(image, new Color(175, 195, 210, 128), list))));

        button.addActionListener(new buttonParcelleAction(p));

        return button;
    }

    /**
     * @return the jlbd
     */
    public AbstractLegumeFactory getJlbd() {
        return jlbd;
    }

    /**
     * @return the stackOfParcelle
     */
    public Stack<Parcelle> getStackOfParcelle() {
        return stackOfParcelle;
    }

    /**
     * @return the view
     */
    public VuepanelParcelle getView() {
        return view;
    }

    /**
     * mappage: construction du MapLabel selon l'avancée 
     * dans la navigation. On utilise pour cela la pile
     * de Parcelles ArrayList<Parcelle>(stackOfParcelle)
     */
    public void mappage() {

        // pour aller plus loin, si le temps pour améliorer
        // mettre un JTree ou un binary tree

        view.getMapLabel().removeAll();

        List<Parcelle> list = new ArrayList<Parcelle>(stackOfParcelle);

        String text;

        text = "<html>";

        for (Parcelle p : list) {
            Orientation orient = p.getSplit();
            System.out.println(orient);

            if (orient != null) {

                if (orient == Orientation.VERTICAL) {
                    text = text + "(Split Verti.)";
                } else if (orient == Orientation.HORIZONTAL) {
                    text = text + "(Split Hori.)";
                }

                text = text + "---";

            } else {

                text = text + "(x)";

            }
        }

        text = text + "</html>";

        view.getMapLabel().setText(text);

        view.getMapLabel().revalidate();
        view.getMapLabel().repaint();

    }

    /**
     * Classe pour le controle de chaque bouton parcelle (1 ou 2)
     */
    class buttonParcelleAction implements ActionListener {

        private Parcelle p;

        buttonParcelleAction(Parcelle p) {

            this.p = p;

        }

        public void actionPerformed(ActionEvent ae) {
            System.out.println(view.getSelectedButtonText());

            if (view.getSelectedButtonText() == "Se deplacer vers cette parcelle") {

                if (stackOfParcelle.peek().getSplit() != null) { // si le dernier peek n'est pas un null
                    // split
                    stackOfParcelle.push(p);

                    affichageNavigation(p);

                } else {

                    JOptionPane.showMessageDialog(null, "Vous ne pouvez pas aller plus loin");

                }

            }

            if (stackOfParcelle.peek().getSplit() == null) { // si le dernier peek est un null split

                if (view.getSelectedButtonText() == "HORIZONTAL") {

                    p.SplitParcelle(Orientation.HORIZONTAL);

                } else if (view.getSelectedButtonText() == "VERTICAL") {

                    p.SplitParcelle(Orientation.VERTICAL);

                }

                // LEGUMES

                if (view.getSelectedButtonText() == "Ajouter un legume") {

                    String selecLegume = view.getSelectedButtonTextDetail();
                    System.out.println(selecLegume);
                    Legume mylegume = jlbd.getLegume(selecLegume);
                    p.addActionLegume(ActionLegumeType.SEMER, mylegume);

                } else if (view.getSelectedButtonText() == "Ajouter une action legume") {

                    String selecActionLegume = view.getSelectedButtonTextDetail();
                    System.out.println(selecActionLegume);
                    ActionLegumeType actionLeg = ActionLegumeType.valueOf(selecActionLegume);

                    String selectLegumeOfAction = view.getRadioComboLegume().getCombo().getSelectedItem().toString();
                    //System.out.println(selectLegumeOfAction);
                    Legume mylegumeA = jlbd.getLegume(selectLegumeOfAction);
                    p.addActionLegume(actionLeg, mylegumeA);
                    //p.addActionLegume(actionLeg);

                } else if (view.getSelectedButtonText() == "Ajouter une action sol") {

                    if (p.getLegume() == null) {

                        String selecActionSol = view.getSelectedButtonTextDetail();
                        System.out.println(selecActionSol);
                        ActionSolType actionSol = ActionSolType.valueOf(selecActionSol);
                        p.addActionSol(actionSol);

                    } else {

                        JOptionPane.showMessageDialog(null,
                                "Il ne faut pas de legume present sur la parcelle pour faire une action sol.");

                    }

                    /*
                     * if (p.getLegume() == null) {
                     * 
                     * JOptionPane.showMessageDialog(null,
                     * "Faites une action legume avant de faire une action pour le sol");
                     * 
                     * } else {
                     * 
                     * }
                     */

                }

                // affichage (repaint, revalidate)
                affichageNavigation(p);

            } else if ((stackOfParcelle.peek().getSplit() != null)
                    && (view.getSelectedButtonText() != "Se deplacer vers cette parcelle")) {
                JOptionPane.showMessageDialog(null, "Ne faites ca seulement dans une parcelle non decoupee");
            }

            view.getBgroup().setSelected(view.getDeplacement().getModel(), true); // par default le deplacement est
                                                                                  // remis

        }
    }

}
