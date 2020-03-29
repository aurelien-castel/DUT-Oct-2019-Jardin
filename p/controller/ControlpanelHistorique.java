/**
*
* @version 1.1
* @author Aurélien Castel
*/
package controller;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;
import java.util.List;
import java.util.Arrays;

//time
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import view.*;
import models.*;

/**
 * controleur pour le panel historique on fait toutes les intréractions boutons
 * (action Listener) et base pour changer l'affichage du calendrier et de
 * l'affichage des actions
 */
public class ControlpanelHistorique implements ActionListener {

  private VuepanelHistorique view;

  // calendrier
  private final JTextField text;
  private JButton[] button;
  private int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
  private int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
  private String day;

  // label ex: decembre 2018
  private JLabel l;

  private Parcelle p0;

  // factory
  private AbstractActionFactory aabd;
  private ArrayList<String> allDate;

  /**
   * constructeur
   * 
   * @param view
   * @param p0
   * @param aabd
   */
  public ControlpanelHistorique(VuepanelHistorique view, Parcelle p0, AbstractActionFactory aabd) {

    this.view = view;

    this.p0 = p0;
    this.aabd = aabd;
    allDate = aabd.getAllDate();

    text = view.getText();
    button = view.getButton();
    l = view.getL();
    day = view.getDay();

    view.getConfirmerDate().addActionListener(this);

    for (int x = 0; x < button.length; x++) {
      final int selection = x;
      if (x > 6) {
        button[x].addActionListener(this);
      } /*
         * button[x].addActionListener(new ActionListener() { public void
         * actionPerformed(ActionEvent ae) { day = button[selection].getActionCommand();
         * } });
         */
    }

    view.getPrevious().addActionListener(this);

    view.getNext().addActionListener(this);

    view.getPreviousAn().addActionListener(this);

    view.getNextAn().addActionListener(this);

  }

  @Override
  public void actionPerformed(ActionEvent ae) {

    Object source = ae.getSource();

    if (source == view.getConfirmerDate()) {

      text.setText(setPickedDate());
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      formatter = formatter.withLocale(Locale.FRANCE);
      LocalDate date = LocalDate.parse(setPickedDate(), formatter);
      System.out.println("date " + date);
      ArrayList<String> arrListAction = aabd.getAllActions(p0, date);
      
      for (String s : arrListAction) {

        System.out.println(s);

      }
      view.setArrStringAction(arrListAction);
      // prepareforBD.parseLocalDate(setPickedDate()));
      // montrer les actions:
      // view.setArrStringAction(arrList);

    }

    for (int x = 0; x < button.length; x++) {
      final int selection = x;
      if (x > 6) {

        if (source == button[x]) {

          day = button[selection].getActionCommand();

        }

      }
    }

    if (source == view.getPrevious()) {

      month--;
      displayDate();
      colorise();
    }

    if (source == view.getNext()) {

      month++;
      displayDate();
      colorise();
    }

    if (source == view.getPreviousAn()) {

      year--;
      displayDate();
      colorise();
    }

    if (source == view.getNextAn()) {

      year++;
      displayDate();
      colorise();
    }

  }

  /**
   * 
   */
  public void colorise() {

    // colorisation des < Mois > < An >
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
          view.getPrevious().setBackground(new Color(255, 207, 64));

        } else if (thisMonth < ld.getMonthValue()) {
          mustColoriseMonth = true;
          view.getNext().setBackground(new Color(255, 207, 64));

          // Il ne faut pas setBackground null si on doit colorier pour une autre date
        } else if ((thisMonth == ld.getMonthValue()) && (mustColoriseMonth == false)) {
          view.getPrevious().setBackground(null);
          view.getNext().setBackground(null);

        }

        if (thisYear > ld.getYear()) {
          mustColoriseYear = true;
          view.getPreviousAn().setBackground(new Color(255, 207, 64));

        } else if (thisYear < ld.getYear()) {
          mustColoriseYear = true;
          view.getNextAn().setBackground(new Color(255, 207, 64));

          // Il ne faut pas setBackground null si on doit colorier pour une autre date
        } else if ((thisYear == ld.getYear()) && (mustColoriseYear == false)) {
          view.getPreviousAn().setBackground(null);
          view.getNextAn().setBackground(null);

        }

      } catch (java.lang.NullPointerException e) {

      }

    }

  }

  /**
   * on change l'affichage du calendrier
   */
  public void displayDate() {

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

        // System.out.println("action: " + s);
        // System.out.println("day: " + thisDay);

        if (s.equals(thisDay)) {
          // System.out.println("----> actionDat: " + s);
          button[x].setBackground(new Color(255, 207, 64));
        }

      }

    }
    // System.out.println("_________________");
    l.setText(sdf.format(cal.getTime()));
  }

  /**
   * on recupere la date choisie du calendrier
   * 
   * @return sdf.format(cal.getTime())
   */
  public String setPickedDate() {
    if (day.equals(""))
      return day;
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
    java.util.Calendar cal = java.util.Calendar.getInstance();
    cal.set(year, month, Integer.parseInt(day));
    return sdf.format(cal.getTime());
  }

}