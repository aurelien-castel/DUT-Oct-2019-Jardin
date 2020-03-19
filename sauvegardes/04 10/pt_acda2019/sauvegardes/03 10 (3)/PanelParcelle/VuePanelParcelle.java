
/**
*
* @version 1.1
* @author Aurélien Castel
*/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class VuePanelParcelle extends JPanel {

      private JLabel jardinName;

      private JButton Horizontal, Vertical, updateButton, refresh;

      private JPanel menu;
      private JPanel leftpanel;
      private JPanel panel;
      private JTable table;

      private DefaultTableModel model;
      private JScrollPane scrollpane;

      private JLabel nameJardin;
      private String name;

      public VuePanelParcelle(ControlPanelParcelle control) {

            setLayout(new BorderLayout());

            menu = new JPanel();
            menu.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 0; // make this component tall
            c.insets = new Insets(0,0,10,0);
            c.weightx = .5;
            c.weighty = .5;
            c.gridwidth = 0;
            c.gridx = 0;
            c.gridy = 0;
            nameJardin = new JLabel("Jardin:"+name);
            menu.add(nameJardin, c);
            nameJardin.setHorizontalAlignment(JLabel.CENTER);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 0; // make this component tall
            c.insets = new Insets(0,5,2,5);
            c.gridwidth = 0;
            c.gridx = 0;
            c.gridy = 1;
            menu.add(new JLabel("Gestion des directions:"), c);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 20; // make this component tall
            c.insets = new Insets(0,5,15,5);
            c.weightx = 0.0;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 2;
            Horizontal = new JButton("Horizontale");
            menu.add(Horizontal, c);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 20; // make this component tall
            c.insets = new Insets(0,5,15,5);
            c.weightx = 0.0;
            c.gridwidth = 1;
            c.gridx = 1;
            c.gridy = 2;
            Vertical = new JButton("Verticale");
            menu.add(Vertical, c);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 20; // make this component tall
            c.insets = new Insets(0,5,0,5);
            c.weightx = 0.0;
            c.gridwidth = 0;
            c.gridx = 0;
            c.gridy = 3;
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

            leftpanel.add(menu,c);
            add(leftpanel,BorderLayout.WEST);

            // Defining Panel
            panel = new JPanel();
            panel.setLayout(new GridLayout());
            panel.setBounds(250, 20, 480, 330);
            panel.setBorder(BorderFactory.createDashedBorder(Color.blue));
            add(panel);

            // Defining Model for table
            model = new DefaultTableModel();

            // Adding object of DefaultTableModel into JTable
            table = new JTable(model);

            // Fixing Columns move
            table.getTableHeader().setReorderingAllowed(false);

            // Defining Column Names on model
            model.addColumn("S.No");
            model.addColumn("ID");
            model.addColumn("Name");
            model.addColumn("Gender");
            model.addColumn("Address");
            model.addColumn("Contact");

            // Enable Scrolling on table
            scrollpane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            panel.add(scrollpane);

            // Displaying Frame in Center of the Screen
            /*
             * Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
             * this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 -
             * this.getSize().height / 2); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             * setResizable(false); setVisible(true);
             */

      }

      // Connection with Database

      public static void main(String[] args) {

            JFrame f = new JFrame();

            ControlPanelParcelle control = new ControlPanelParcelle();

            VuePanelParcelle vue = new VuePanelParcelle(control);

            f.setSize(300, 300);
            f.add(vue);

            f.setVisible(true);
      }

}