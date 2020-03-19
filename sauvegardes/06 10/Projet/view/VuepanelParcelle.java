
/**
*
* @version 1.1
* @author Aurélien Castel
*/
package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.border.Border;
import javax.swing.*;

import java.util.*;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

import javax.imageio.ImageIO;

import controller.*;

@SuppressWarnings("serial")
public class VuepanelParcelle extends JPanel {

      private JRadioButton Horizontal, Vertical, Supprimer;
      private ButtonGroup bgroup;
      private JButton updateButton;

      private JPanel menu, jardinPanel;
      private JPanel leftpanel, rightpanel;
      private JScrollPane scrollpane;

      private JLabel nameJardin;
      private String name;

      public VuepanelParcelle() {

            setLayout(new BorderLayout());

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
            nameJardin = new JLabel("Jardin:" + name);
            menu.add(nameJardin, c);
            nameJardin.setHorizontalAlignment(JLabel.CENTER);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 0; // make this component tall
            c.insets = new Insets(0, 5, 1, 5);
            c.weightx = .5;
            c.weighty = .5;
            c.gridwidth = 0;
            c.gridx = 0;
            c.gridy = 1;
            menu.add(new JLabel("Actions:"), c);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 20; // make this component tall
            c.insets = new Insets(0, 5, 0, 5);
            c.weightx = 0.0;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 2;
            Horizontal = new JRadioButton("Couper horizontalement");
            menu.add(Horizontal, c);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 20; // make this component tall
            c.insets = new Insets(0, 5, 0, 5);
            c.weightx = 0.0;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 3;
            Vertical = new JRadioButton("Couper verticalement");
            menu.add(Vertical, c);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 20; // make this component tall
            c.insets = new Insets(0, 5, 15, 5);
            c.weightx = 0.0;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 4;
            Supprimer = new JRadioButton("Supprimer la parcelle");
            menu.add(Supprimer, c);

            bgroup = new ButtonGroup();
            bgroup.add(Horizontal);
            bgroup.add(Vertical);
            bgroup.add(Supprimer);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 20; // make this component tall
            c.insets = new Insets(0, 5, 0, 5);
            c.weightx = 0.0;
            c.gridwidth = 0;
            c.gridx = 0;
            c.gridy = 5;
            updateButton = new JButton("Update");
            menu.add(updateButton, c);

            leftpanel = new JPanel();
            leftpanel.setLayout(new GridBagLayout());

            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 0; // make this component tall
            c.weightx = 0.0;
            c.gridwidth = 0;
            c.gridx = 0;
            c.gridy = 0;

            leftpanel.add(menu, c);
            add(leftpanel, BorderLayout.WEST);

            // Defining Panel
            rightpanel = new JPanel();
            rightpanel.setLayout(new GridLayout());
            add(rightpanel);

            // Defining Panel
            jardinPanel = new JPanel();
            jardinPanel.setLayout(new GridBagLayout());

            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 0; // make this component tall
            c.insets = new Insets(0, 0, 0, 0);
            c.weightx = 0.0;
            c.gridwidth = 0;
            c.gridx = 0;
            c.gridy = 0;

            ImageIcon imgIcon = new ImageIcon();
            try {
                  imgIcon = new ImageIcon(getClass().getResource("resources/terre.jpg"));
            } catch (Exception ex) {
                  System.out.println("Image execption:" + ex);
            }

            JButton button = new JButton(imgIcon);
            button.setPreferredSize(new Dimension(500, 400)); // <-- taille du jardin
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);

            button.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent ae) {
                        System.out.println(getSelectedButtonText());
                  }
                });

            jardinPanel.add(button, c);

            // Enable Scrolling on table
            scrollpane = new JScrollPane(jardinPanel);
            rightpanel.add(scrollpane);

            // Displaying Frame in Center of the Screen
            /*
             * Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
             * this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 -
             * this.getSize().height / 2); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             * setResizable(false); setVisible(true);
             */

      }

      /**
       * Avoir l'element du radio
       */

      public String getSelectedButtonText() {

            for (Enumeration<AbstractButton> buttons = bgroup.getElements(); buttons.hasMoreElements();) {
                  AbstractButton button = buttons.nextElement();

                  if (button.isSelected()) {
                        return button.getText();
                  }
            }

            return null;
      }

      /**
       * @return the updateButton
       */
      public JButton getUpdateButton() {
            return updateButton;
      }

}
