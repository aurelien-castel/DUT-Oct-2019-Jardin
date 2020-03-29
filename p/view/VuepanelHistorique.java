/**
*
* @version 1.1
* @author Aurélien Castel
*/
package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;
import java.util.List;
import java.util.Arrays;

//time
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import controller.*;
import models.*;

/**
 * vue du calendrier/historique
 */
public class VuepanelHistorique extends JPanel {

  // main pane
  private JLabel Instruction;
  private JPanel panel;
  private JPanel leftpanel, rightpanel;

  // pane for historique
  private JPanel menu;
  private JPanel centredCalendar;
  private JPanel calendar;

  // historique
  private int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
  private int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
  // label ex: decembre 2018
  private JLabel l = new JLabel("", JLabel.CENTER);
  private String day = "";
  private final JTextField text;
  private JButton[] button = new JButton[49];

  private JButton confirmerDate, previous, next, previousAn, nextAn;

  // pane for action
  private JScrollPane scroller;
  private JPanel actionContent;
  private ArrayList<String> arrStringAction;

  // nom du jardin
  private JLabel nameJardin;

  // factory
  private AbstractActionFactory aabd;

  /**
   * constructeur
   * 
   * @param p0
   * @param aabd
   */
  public VuepanelHistorique(Parcelle p0, AbstractActionFactory aabd) {

    this.aabd = aabd;

    setLayout(new GridLayout());

    panel = new JPanel();
    panel.setLayout(new GridBagLayout());

    menu = new JPanel();
    menu.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    nameJardin = new JLabel("Historique du jardin: " + p0.getNomJardin() + " (" + p0.getx1() + "x" + p0.gety1() + ")");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 0; // make this component tall
    c.insets = new Insets(0, 0, 17, 0);
    c.weightx = .5;
    c.weighty = .5;
    c.gridwidth = 0;
    c.gridx = 0;
    c.gridy = 0;
    menu.add(nameJardin, c);
    nameJardin.setHorizontalAlignment(JLabel.CENTER);

    JLabel label = new JLabel("Date selectionnee:");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 0; // make this component tall
    c.insets = new Insets(0, 5, 25, 5);
    c.weightx = 0;
    c.weighty = 0;
    c.gridwidth = 1;
    c.gridx = 0;
    c.gridy = 1;
    menu.add(label, c);
    text = new JTextField(10);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 0; // make this component tall
    c.insets = new Insets(0, 5, 25, 5);
    c.weightx = 0;
    c.weighty = 0;
    c.gridwidth = 1;
    c.gridx = 1;
    c.gridy = 1;
    menu.add(text, c);
    confirmerDate = new JButton("Afficher les actions de cette date");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 0; // make this component tall
    c.insets = new Insets(0, 5, 25, 5);
    c.weightx = 0;
    c.weighty = 0;
    c.gridwidth = 1;
    c.gridx = 2;
    c.gridy = 1;
    menu.add(confirmerDate, c);

    // Instructions
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 0; // make this component tall
    c.insets = new Insets(0, 5, 5, 5);
    c.weightx = .5;
    c.weighty = .5;
    c.gridwidth = 0;
    c.gridx = 0;
    c.gridy = 2;
    menu.add(new JLabel("Cliquez sur une date coloriee, puis sur le bouton \"Afficher les actions de cette date\"."),
        c);

    /*
     * confirmerDate.addActionListener(new ActionListener() { public void
     * actionPerformed(ActionEvent ae) { text.setText(setPickedDate()); } });
     */

    // Defining panel
    leftpanel = new JPanel();
    leftpanel.setLayout(new GridBagLayout());

    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 0; // make this component tall
    c.weightx = 0.0;
    c.gridwidth = 0;
    c.gridx = 0;
    c.gridy = 0;

    leftpanel.add(menu, c);

    // Partie calendar

    centredCalendar = new JPanel();
    centredCalendar.setLayout(new GridBagLayout());

    calendar = new JPanel();
    calendar.setLayout(new BorderLayout());

    String[] header = { "Di", "Lu", "Ma", "Me", "Je", "Ve", "Sa" };
    JPanel p1 = new JPanel(new GridLayout(7, 7));
    p1.setPreferredSize(new Dimension(500, 190));

    for (int x = 0; x < button.length; x++) {
      // final int selection = x;
      button[x] = new JButton();
      button[x].setFocusPainted(false);
      // button[x].setBackground(new Color (255, 207, 64));
      button[x].setBackground(Color.white);
      /*
       * if (x > 6) button[x].addActionListener(new ActionListener() { public void
       * actionPerformed(ActionEvent ae) { day = button[selection].getActionCommand();
       * } });
       */
      if (x < 7) {
        button[x].setText(header[x]);
        button[x].setForeground(Color.red);
      }
      p1.add(button[x]);
    }
    JPanel p2 = new JPanel(new GridLayout(1, 5));
    previousAn = new JButton("< An");
    previous = new JButton("< Mois");
    /*
     * previous.addActionListener(new ActionListener() { public void
     * actionPerformed(ActionEvent ae) { month--; displayDate(); } });
     */
    p2.add(previousAn);
    p2.add(previous);
    p2.add(l);
    next = new JButton("Mois >");
    nextAn = new JButton("An >");
    /*
     * next.addActionListener(new ActionListener() { public void
     * actionPerformed(ActionEvent ae) { month++; displayDate(); } });
     */
    p2.add(next);
    p2.add(nextAn);
    calendar.add(p1, BorderLayout.CENTER);
    calendar.add(p2, BorderLayout.SOUTH);

    displayDate();

    centredCalendar.add(calendar);

    // leftpanel
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 0; // make this component tall
    c.insets = new Insets(0, 5, 0, 5);
    c.weightx = 0.0;
    c.gridwidth = 0;
    c.gridx = 0;
    c.gridy = 1;
    leftpanel.add(centredCalendar, c);

    // main
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 0; // make this component tall
    c.insets = new Insets(0, 0, 0, 0);
    c.weightx = 0.0;
    c.gridwidth = 1;
    c.gridx = 0;
    c.gridy = 0;
    panel.add(leftpanel, c);

    // Defining panel
    rightpanel = new JPanel();
    rightpanel.setLayout(new GridBagLayout());

    arrStringAction = new ArrayList<>();
    arrStringAction.add("Choisissez un jour.");
    arrStringAction.add("Cliquez sur un jour colorie a gauche.");
    arrStringAction.add("Puis cliquez sur \"Afficher les actions de cette date\".");
    // String arrStringAction[] = { "Eden", "Agartha", "Arcadia", "Atlantis",
    // "Asgard", "0", "1", "2", "3", "4", "5" };

    int i = 0;
    int j = 0;
    for (String element : arrStringAction) {
      label = new JLabel(element);// changer ici pour la db
      c.fill = GridBagConstraints.BOTH;
      c.anchor = GridBagConstraints.WEST;
      c.gridx = 0;
      c.gridy = i;
      c.ipady = 17;
      c.gridheight = 1;
      c.weightx = 0.1;
      c.weighty = 1;
      c.gridwidth = 2;
      c.insets = new Insets(3, 10, 3, 10);
      rightpanel.add(label, c);
      i++;
      j++;
    }

    rightpanel.setBackground(Color.WHITE);

    scroller = new JScrollPane(rightpanel);
    scroller.setPreferredSize(new Dimension(400, 350));
    scroller.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Description des actions"));

    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 0; // make this component tall
    c.insets = new Insets(0, 75, 0, 0);
    c.weightx = 0.0;
    c.gridwidth = 1;
    c.gridheight = 0;
    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 0;
    c.weighty = 0;
    c.gridx = 1;
    panel.add(scroller, c);

    add(panel);

  }

  /**
   * affichage du calendrier
   */
  public void displayDate() {

    ArrayList<String> allDate = aabd.getAllDate();

    for (int x = 7; x < button.length; x++)
      button[x].setText("");

    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMMM yyyy");
    java.util.Calendar cal = java.util.Calendar.getInstance();
    java.text.SimpleDateFormat comparatorSdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
    java.util.Calendar comparatorCal = java.util.Calendar.getInstance();

    cal.set(year, month, 1);

    int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
    int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);

    for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++) {
      button[x].setBackground(Color.WHITE);
      button[x].setText("" + day);
      comparatorCal.set(year, month, day);

      String thisDay = comparatorSdf.format(comparatorCal.getTime());

      for (String s : allDate) {

        /*
         * System.out.println("action: " + s); System.out.println("day: " + thisDay);
         */

        if (s.equals(thisDay)) {
          // System.out.println("----> actionDat: " + s);
          button[x].setBackground(new Color(255, 207, 64));
        }

      }

    }
    // System.out.println("_________________");
    l.setText(sdf.format(cal.getTime()));

    // colorisation des boutons < Mois > < An > si besoins
    int thisMonth = month + 1;
    int thisYear = year;

    Boolean mustColoriseMonth = false;
    Boolean mustColoriseYear = false;

    for (String input : allDate) {

      DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
      LocalDate ld = null;
      try {
        // on parse pour avoir les dates en string sous le bon format
        ld = LocalDate.parse(input, f);
      } catch (java.time.format.DateTimeParseException e) {

      }
      try {

        if (thisMonth > ld.getMonthValue()) {
          mustColoriseMonth = true;
          getPrevious().setBackground(new Color(255, 207, 64));

        } else if (thisMonth < ld.getMonthValue()) {
          mustColoriseMonth = true;
          getNext().setBackground(new Color(255, 207, 64));

          // Il ne faut pas setBackground null si on doit colorier pour une autre date
        } else if ((thisMonth == ld.getMonthValue()) && (mustColoriseMonth == false)) {
          getPrevious().setBackground(null);
          getNext().setBackground(null);

        }

        if (thisYear > ld.getYear()) {
          mustColoriseYear = true;
          getPreviousAn().setBackground(new Color(255, 207, 64));

        } else if (thisYear < ld.getYear()) {
          mustColoriseYear = true;
          getNextAn().setBackground(new Color(255, 207, 64));

          // Il ne faut pas setBackground null si on doit colorier pour une autre date
        } else if ((thisYear == ld.getYear()) && (mustColoriseYear == false)) {
          getPreviousAn().setBackground(null);
          getNextAn().setBackground(null);

        }

      } catch (java.lang.NullPointerException e) {

      }

    }

  }

  /**
   * @return the confirmerDate
   */
  public JButton getConfirmerDate() {
    return confirmerDate;
  }

  /**
   * @return the text
   */
  public JTextField getText() {
    return text;
  }

  /**
   * @return the day
   */
  public String getDay() {
    return day;
  }

  /**
   * label ex: decembre 2018
   * 
   * @return the l
   */
  public JLabel getL() {
    return l;
  }

  /**
   * @return the button
   */
  public JButton[] getButton() {
    return button;
  }

  /**
   * @return the previous
   */
  public JButton getPrevious() {
    return previous;
  }

  /**
   * @return the next
   */
  public JButton getNext() {
    return next;
  }

  /**
   * @return the previousAn
   */
  public JButton getPreviousAn() {
    return previousAn;
  }

  /**
   * @return the nextAn
   */
  public JButton getNextAn() {
    return nextAn;
  }

  /**
   * @return the arrStringAction
   */
  public ArrayList<String> getArrStringAction() {
    return arrStringAction;
  }

  /**
   * On revalidate pour changer la liste et le panel de droite
   * 
   * @param arrStringAction the arrStringAction to set
   */
  public void setArrStringAction(ArrayList<String> arrList) {
    this.arrStringAction.clear(); // nettoyage
    this.arrStringAction = arrList;
    rightpanel.removeAll();

    int i = 0;
    int j = 0;

    JLabel label;

    GridBagConstraints c = new GridBagConstraints();
    for (String element : arrStringAction) {
      label = new JLabel(element);// changer ici pour la db
      c.fill = GridBagConstraints.BOTH;
      c.anchor = GridBagConstraints.WEST;
      c.gridx = 0;
      c.gridy = i;
      c.ipady = 17;
      c.gridheight = 1;
      c.weightx = 0.1;
      c.weighty = 1;
      c.gridwidth = 2;
      c.insets = new Insets(3, 10, 3, 10);
      rightpanel.add(label, c);
      i++;
      j++;
    }

    rightpanel.setBackground(Color.WHITE);

    rightpanel.revalidate();
    rightpanel.repaint();
    displayDate();
  }

}