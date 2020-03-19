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

public class ControlpanelHistorique implements ActionListener {

  private VuepanelHistorique view;

  private final JTextField text;
  private JButton[] button;
  private int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
  private int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
  private JLabel l;
  private String day;

  public ControlpanelHistorique(VuepanelHistorique view) {

    this.view = view;

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
      view.setDateChoisie(setPickedDate());

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
    }

    if (source == view.getNext()) {

      month++;
      displayDate();
    }

    if (source == view.getPreviousAn()) {

      year--;
      displayDate();

    }

    if (source == view.getNextAn()) {

      year++;
      displayDate();

    }

  }

  public void displayDate() {
    for (int x = 7; x < button.length; x++)
      button[x].setText("");
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMMM yyyy");
    java.util.Calendar cal = java.util.Calendar.getInstance();
    cal.set(year, month, 1);
    int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
    int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
    for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
      button[x].setText("" + day);
    l.setText(sdf.format(cal.getTime()));
  }

  public String setPickedDate() {
    if (day.equals(""))
      return day;
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
    java.util.Calendar cal = java.util.Calendar.getInstance();
    cal.set(year, month, Integer.parseInt(day));
    return sdf.format(cal.getTime());
  }

}