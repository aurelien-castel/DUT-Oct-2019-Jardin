
/**
*
* @version 1.1
* @author Aurélien Castel
*/
package view;

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

import controller.*;
import jardin.*;

@SuppressWarnings("serial")
public class VuepanelParcelle extends JPanel {

      // constraints
      private GridBagConstraints c;

      // boutons
      private JButton Home, Retour, infoLegume, infoAction, deleteButton;
      private JRadioButton Deplacement, Horizontal, Vertical;
      private ButtonGroup bgroup;

      // special components
      private RadioCombo<String> radioComboLegume, radioComboActionSol, radioComboActionLegume, radioComboCouper;
      private JComboBox<String> ComboLegume;
      private DefaultComboBoxModel<String> model;

      // textfield to add and delete legumes
      private JTextField tfLegume;
      private JButton b0, b01;

      // panels
      private JPanel menu, jardinPanel, map, mapContent;
      private JLabel mapLabel;
      private JPanel leftpanel, rightpanel;
      private JScrollPane scrollpane;

      // nom jardin
      private JLabel nameJardin;
      private String name;

      // stack
      private Stack<Parcelle> stackOfParcelle;
      // the current parcelle
      private static Parcelle currentParcelle;

      public VuepanelParcelle() {

            setLayout(new BorderLayout());

            menu = new JPanel();
            menu.setLayout(new GridBagLayout());
            c = new GridBagConstraints();

            // Nom jardin
            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 0; // make this component tall
            c.insets = new Insets(0, 0, 10, 0);
            c.weightx = .5;
            c.weighty = .5;
            c.gridwidth = 0;
            c.gridx = 0;
            c.gridy = 0;
            nameJardin = new JLabel("Gestion du jardin: " + name);
            menu.add(nameJardin, c);
            nameJardin.setHorizontalAlignment(JLabel.CENTER);

            // Actions
            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 0; // make this component tall
            c.insets = new Insets(0, 5, 1, 5);
            c.weightx = .5;
            c.weighty = .5;
            c.gridwidth = 0;
            c.gridx = 0;
            c.gridy = 1;
            menu.add(new JLabel("Deplacements:"), c);

            // Retour arriere
            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 20; // make this component tall
            c.insets = new Insets(0, 5, 1, 5);
            c.weightx = 0.0;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 2;
            Retour = new JButton("Retour parcelle d'avant");
            menu.add(Retour, c);

            // creating a label for the map
            mapLabel = new JLabel();

            Retour.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent ae) {

                        if (stackOfParcelle.size() > 1) {

                              stackOfParcelle.pop();

                              affichageNavigation(stackOfParcelle.peek());

                        }

                  }
            });

            // Retour Home
            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 20; // make this component tall
            c.insets = new Insets(0, 5, 1, 5);
            c.weightx = 0.0;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 3;
            Home = new JButton("Retour au debut du jardin");
            menu.add(Home, c);

            Home.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent ae) {

                        if (stackOfParcelle.size() > 1) {

                              // while this element is not the first (mother)
                              while (stackOfParcelle.firstElement() != stackOfParcelle.peek()) {
                                    stackOfParcelle.pop();
                              }

                              affichageNavigation(stackOfParcelle.peek());

                        }
                  }
            });

            // Go to this
            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 20; // make this component tall
            c.insets = new Insets(0, 5, 25, 5);
            c.weightx = 0.0;
            c.gridwidth = 2;
            c.gridx = 0;
            c.gridy = 4;
            Deplacement = new JRadioButton("Se deplacer vers cette parcelle");
            menu.add(Deplacement, c);

            // Actions
            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 0; // make this component tall
            c.insets = new Insets(0, 5, 1, 5);
            c.weightx = .5;
            c.weighty = .5;
            c.gridwidth = 0;
            c.gridx = 0;
            c.gridy = 5;
            menu.add(new JLabel("Modifier la parcelle:"), c);

            // Legumes
            model = new DefaultComboBoxModel<>();
            model.addElement("Carotte");
            model.addElement("Poireau");
            radioComboLegume = new RadioCombo<>("Ajouter un legume", model);
            ComboLegume = new JComboBox<>(model);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 20; // make this component tall
            c.insets = new Insets(0, 5, 0, 5);
            c.weightx = 0.0;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 6;
            menu.add(radioComboLegume.getRadioBtn(), c);

            c.gridx = 1;
            c.anchor = GridBagConstraints.EAST;
            c.insets = new Insets(0, 5, 0, 5);
            menu.add(radioComboLegume.getCombo(), c);

            // Actions legume
            model = new DefaultComboBoxModel<>();
            model.addElement("Fumier");
            model.addElement("Rabotter");
            radioComboActionLegume = new RadioCombo<>("Ajouter une action legume", model);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 20; // make this component tall
            c.insets = new Insets(0, 5, 0, 5);
            c.weightx = 0.0;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 7;
            menu.add(radioComboActionLegume.getRadioBtn(), c);

            c.gridx = 1;
            c.anchor = GridBagConstraints.EAST;
            c.insets = new Insets(0, 5, 0, 5);
            menu.add(radioComboActionLegume.getCombo(), c);

            // Actions sol
            model = new DefaultComboBoxModel<>();
            model.addElement("Fumier");
            model.addElement("Rabotter");
            radioComboActionSol = new RadioCombo<>("Ajouter une action sol", model);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 20; // make this component tall
            c.insets = new Insets(0, 5, 0, 5);
            c.weightx = 0.0;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 8;
            menu.add(radioComboActionSol.getRadioBtn(), c);

            c.gridx = 1;
            c.anchor = GridBagConstraints.EAST;
            c.insets = new Insets(0, 5, 0, 5);
            menu.add(radioComboActionSol.getCombo(), c);

            // Couper
            model = new DefaultComboBoxModel<>();
            model.addElement("Verticalement");
            model.addElement("Horizontalement");
            radioComboCouper = new RadioCombo<>("Couper", model);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 20; // make this component tall
            c.insets = new Insets(0, 5, 5, 5);
            c.weightx = 0.0;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 9;
            menu.add(radioComboCouper.getRadioBtn(), c);

            c.gridx = 1;
            c.anchor = GridBagConstraints.EAST;
            c.insets = new Insets(0, 5, 5, 5);
            menu.add(radioComboCouper.getCombo(), c);

            // Reset la parcelle courante
            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 5; // make this component tall
            c.insets = new Insets(0, 5, 25, 5);
            c.weightx = 0.0;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 10;
            deleteButton = new JButton("Reset la parcelle courante");
            deleteButton.setBackground(new Color(217, 136, 128));
            menu.add(deleteButton, c);

            deleteButton.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent ae) {

                        if (stackOfParcelle.peek().getSplit() != null) { // si le dernier peek n'est pas un null
                                                                         // split
                              currentParcelle.reset();
                              affichageNavigation(currentParcelle);
                        } else if (stackOfParcelle.size() == 1) {

                              System.out.println("remove jardin");

                              JDialog d = new JDialog();
                              d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                              d.setTitle("Attention");
                              d.setPreferredSize(new Dimension(200, 200));
                              d.setLocation(300, 300);
                              d.setAlwaysOnTop(true);
                              d.setVisible(true);

                        }
                  }
            });

            // Actions
            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 0; // make this component tall
            c.insets = new Insets(0, 5, 1, 5);
            c.weightx = .5;
            c.weighty = .5;
            c.gridwidth = 0;
            c.gridx = 0;
            c.gridy = 11;
            menu.add(new JLabel("Gestion des legumes:"), c);

            // Legumes
            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 20; // make this component tall
            c.insets = new Insets(0, 5, 0, 5);
            c.weightx = 0.0;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 12;
            menu.add(ComboLegume, c);

            tfLegume = new JTextField(10);
            c.gridx = 1;
            c.anchor = GridBagConstraints.EAST;
            c.insets = new Insets(0, 5, 0, 5);
            menu.add(tfLegume, c);

            // create add and remove buttons
            b0 = new JButton("Ajouter");
            b01 = new JButton("Supp.");
            c.gridx = 2;
            c.anchor = GridBagConstraints.EAST;
            c.insets = new Insets(0, 5, 0, 0);
            menu.add(b0, c);

            c.gridx = 3;
            c.anchor = GridBagConstraints.EAST;
            c.insets = new Insets(0, 0, 0, 5);
            menu.add(b01, c);

            bgroup = new ButtonGroup();
            bgroup.add(Deplacement);
            bgroup.add(radioComboLegume.getRadioBtn());
            bgroup.add(radioComboActionLegume.getRadioBtn());
            bgroup.add(radioComboActionSol.getRadioBtn());
            bgroup.add(radioComboCouper.getRadioBtn());
            bgroup.setSelected(Deplacement.getModel(), true);

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
            rightpanel.setLayout(new BorderLayout());

            // Defining Panel
            jardinPanel = new JPanel();
            jardinPanel.setLayout(new GridBagLayout());

            // a enlever test parcelle
            // Il faut une factory pour faire des jardins
            AbstractJardinFactory jf = new JardinFactoryJ();
            // Il faut une factory pour faire des légumes
            AbstractLegumeFactory lf = new LegumeFactoryJ();

            Parcelle p0 = jf.AddJardin("Eden", 100, 300); // parcelle mere
            stackOfParcelle = new Stack<>();
            stackOfParcelle.push(p0); // au debut on push la parcelle mere

            p0.SplitParcelle(Orientation.HORIZONTAL);

            affichageNavigation(p0);

            // Enable Scrolling on table
            scrollpane = new JScrollPane(jardinPanel);
            rightpanel.add(scrollpane, BorderLayout.CENTER);

            // Defining panel map
            map = new JPanel();
            map.setLayout(new GridBagLayout());

            // Defining panel content map
            mapContent = new JPanel();
            mapContent.add(mapLabel);

            map.add(mapContent, c);
            rightpanel.add(map, BorderLayout.NORTH);

            // add to final panel
            add(rightpanel);

      }

      public void affichageNavigation(Parcelle p) {

            jardinPanel.removeAll();
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
                        jardinPanel.add(button0, c);

                        c.fill = GridBagConstraints.HORIZONTAL;
                        c.ipady = 0; // make this component tall
                        c.insets = new Insets(0, 0, 0, 0);
                        c.weightx = 0.0;
                        c.gridwidth = 1;
                        c.gridx = 1;
                        c.gridy = 0;
                        jardinPanel.add(button1, c);

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
                        jardinPanel.add(button0, c);

                        c.fill = GridBagConstraints.HORIZONTAL;
                        c.ipady = 0; // make this component tall
                        c.insets = new Insets(0, 0, 0, 0);
                        c.weightx = 0.0;
                        c.gridwidth = 1;
                        c.gridx = 0;
                        c.gridy = 1;
                        jardinPanel.add(button1, c);
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
                  jardinPanel.add(button, c);
            }

            jardinPanel.revalidate();
            jardinPanel.repaint();

            mappage();

      }

      public JButton buttonParcelle(Parcelle p) {

            JButton button = new JButton();
            ImageIcon imgIcon = new ImageIcon();

            BufferedImage image = null;
            try {
                  image = ImageIO.read(getClass().getResource("resources/terre.jpg"));
            } catch (IOException e) {
                  System.out.println("Image execption:" + e);
            }

            button.setPreferredSize(new Dimension(400, 300)); // <-- taille d'une parcelle

            button.add(new JLabel(new ImageIcon(dye(image, new Color(175, 195, 210, 128)))));

            button.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent ae) {
                        System.out.println(getSelectedButtonText());

                        if (getSelectedButtonText() == "Se deplacer vers cette parcelle") {

                              if (stackOfParcelle.peek().getSplit() != null) { // si le dernier peek n'est pas un null
                                                                               // split
                                    stackOfParcelle.push(p);

                                    affichageNavigation(p);

                              }

                        }

                        if (stackOfParcelle.peek().getSplit() == null) { // si le dernier peek est un null split

                              if (getSelectedButtonText() == "Horizontalement") {

                                    p.SplitParcelle(Orientation.HORIZONTAL);
                                    affichageNavigation(p);

                              } else if (getSelectedButtonText() == "Verticalement") {

                                    p.SplitParcelle(Orientation.VERTICAL);
                                    affichageNavigation(p);

                              }

                        }
                        bgroup.setSelected(Deplacement.getModel(), true); // par default le deplacement est remis

                  }
            });

            return button;
      }

      /**
       * Coloration
       * 
       * @param image
       * @param color
       * @return
       */

      private static BufferedImage dye(BufferedImage image, Color color) {
            int w = image.getWidth();
            int h = image.getHeight();
            BufferedImage dyed = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = dyed.createGraphics();

            g.drawImage(image, 0, 0, null);
            g.setComposite(AlphaComposite.SrcAtop);
            g.setColor(color);
            g.fillRect(0, 0, w, h);

            List<String> list = new ArrayList<>();
            list.add("one");
            list.add("two");
            list.add("three");
            list.add("four");
            list.add("five");

            paintText(g, w, h, list);

            g.dispose();
            return dyed;

      }

      public static void paintText(Graphics2D g, int width, int height, List<String> list) {

            Graphics2D g2d = (Graphics2D) g.create();

            FontMetrics fm = g.getFontMetrics();

            int newHeight = ((height - fm.getHeight())) + fm.getAscent();

            // double scale = height / (fm.getHeight());

            Font font = g.getFont().deriveFont(Font.PLAIN, AffineTransform.getScaleInstance(4, 2));
            g2d.setFont(font);
            g2d.setColor(new Color(26, 26, 26));
            fm = g.getFontMetrics(font);

            int yPos = ((height - fm.getHeight()) / 2) + fm.getAscent();

            int currentY = yPos - fm.getAscent() * (list.size() / 2);

            for (String text : list) {

                  int xPos = (width - fm.stringWidth(text)) / 2;
                  System.out.println("y: " + yPos + " height: " + fm.getAscent());
                  g2d.drawString(text, xPos, currentY);
                  currentY = currentY + fm.getAscent();

            }

            g2d.dispose();

      }

      public void mappage() {

            mapLabel.removeAll();

            List<Parcelle> list = new ArrayList<Parcelle>(stackOfParcelle);

            String text;
            text = "<html><h4>";

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

            text = text + "</h4></html>";

            mapLabel.setText(text);
            mapLabel.revalidate();
            mapLabel.repaint();

      }

      /**
       * methodes getters
       */

      /**
       * Avoir l'element du radio, return null si rien
       */

      public String getSelectedButtonText() {

            String string = new String();

            for (Enumeration<AbstractButton> buttons = bgroup.getElements(); buttons.hasMoreElements();) {
                  AbstractButton button = buttons.nextElement();

                  if (button.isSelected()) {
                        switch (button.getText()) {

                        case "Ajouter un legume":
                              string = radioComboLegume.getCombo().getSelectedItem().toString();
                              break;

                        case "Ajouter une action sol":
                              string = radioComboActionSol.getCombo().getSelectedItem().toString();
                              break;

                        case "Ajouter une action legume":
                              string = radioComboActionLegume.getCombo().getSelectedItem().toString();
                              break;

                        case "Couper":
                              string = radioComboCouper.getCombo().getSelectedItem().toString();
                              break;

                        default:
                              string = button.getText();
                              break;
                        }

                        return string;
                  }
            }

            return null;
      }

}
