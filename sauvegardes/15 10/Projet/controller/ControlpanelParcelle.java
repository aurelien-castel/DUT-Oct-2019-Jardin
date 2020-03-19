/**
*
* @version 1.1
* @author Aurélien Castel
*/

package controller;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import view.*;
import jardin.*;

public class ControlpanelParcelle {

    VuepanelParcelle view;

    private JPanel fond;
    private CardLayout layout;
    private final String[] nomboutons;

    public ControlpanelParcelle(VuepanelParcelle view, VueMenu viewMenu) {

        fond = viewMenu.getFond();
        layout = viewMenu.getLayout();
        nomboutons = viewMenu.getNomboutons();

        this.view = view;

        view.getRetour().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                if (view.getStackOfParcelle().size() > 1) {

                    view.getStackOfParcelle().pop();

                    view.affichageNavigation(view.getStackOfParcelle().peek());

                }

            }
        });

        view.getHome().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                if (view.getStackOfParcelle().size() > 1) {

                    // while this element is not the first (mother)
                    while (view.getStackOfParcelle().firstElement() != view.getStackOfParcelle().peek()) {
                        view.getStackOfParcelle().pop();
                    }

                    view.affichageNavigation(view.getStackOfParcelle().peek());

                }
            }
        });

        view.getDeleteButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                if (view.getStackOfParcelle().peek().getSplit() != null) { // si le dernier peek n'est pas un null
                    // split
                    view.getCurrentParcelle().reset();
                    view.affichageNavigation(view.getCurrentParcelle());
                } else if (view.getStackOfParcelle().size() == 1) {

                    System.out.println("remove jardin");

                    int reponse = JOptionPane.showConfirmDialog(null, "Vous allez supprimer ce jardin, etes vous sur ?",
                            "Attention", JOptionPane.YES_NO_OPTION);

                    if (reponse == JOptionPane.YES_OPTION) {
                        view.getStackOfParcelle();
                        // supprimer jardin

                        // retour accueil
                        layout.show(fond, nomboutons[0]);

                        JOptionPane.showMessageDialog(null, "Le jardin a ete supprime");
                    } else if (reponse == JOptionPane.NO_OPTION) {
                        // rien
                    }

                }
            }
        });

        view.getAddLegume().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				VueAddNewLegume vaddnewlegume = new VueAddNewLegume(fond);
			}
		});

    }

}
