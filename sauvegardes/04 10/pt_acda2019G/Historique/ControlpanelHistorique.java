/**
*
* @version 1.1
* @author Aurélien Castel
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ControlpanelHistorique extends JDialog {
  int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
  int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);;
  JLabel l = new JLabel("", JLabel.CENTER);
  String day = "";
  JButton[] button = new JButton[49];

  public ControlpanelHistorique(JFrame parent) {

    setModal(true);
    String[] header = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };
    JPanel p1 = new JPanel(new GridLayout(7, 7));
    p1.setPreferredSize(new Dimension(430, 120));

    for (int x = 0; x < button.length; x++) {
      final int selection = x;
      button[x] = new JButton();
      button[x].setFocusPainted(false);
      button[x].setBackground(Color.white);
      if (x > 6)
        button[x].addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
            day = button[selection].getActionCommand();
            dispose();
          }
        });
      if (x < 7) {
        button[x].setText(header[x]);
        button[x].setForeground(Color.red);
      }
      p1.add(button[x]);
    }
    JPanel p2 = new JPanel(new GridLayout(1, 3));
    JButton previous = new JButton("<< Previous");
    previous.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        month--;
        displayDate();
      }
    });
    p2.add(previous);
    p2.add(l);
    JButton next = new JButton("Next >>");
    next.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        month++;
        displayDate();
      }
    });
    p2.add(next);
    add(p1, BorderLayout.CENTER);
    add(p2, BorderLayout.SOUTH);
    pack();
    setLocationRelativeTo(parent);
    displayDate();
    setVisible(true);
  }

  public void displayDate() {
    for (int x = 7; x < button.length; x++)
      button[x].setText("");
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
        "MMMM yyyy");
    java.util.Calendar cal = java.util.Calendar.getInstance();
    cal.set(year, month, 1);
    int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
    int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
    for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
      button[x].setText("" + day);
    l.setText(sdf.format(cal.getTime()));
    setTitle("Date Picker");
  }

  public String setPickedDate() {
    if (day.equals(""))
      return day;
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
        "dd-MM-yyyy");
    java.util.Calendar cal = java.util.Calendar.getInstance();
    cal.set(year, month, Integer.parseInt(day));
    return sdf.format(cal.getTime());
  }
}

class Picker {

  Picker(){
    JLabel label = new JLabel("Selected Date:");
    final JTextField text = new JTextField(20);
    JButton b = new JButton("popup");
    JPanel p = new JPanel();
    p.add(label);
    p.add(text);
    p.add(b);
    final JFrame f = new JFrame("Picker");
    f.getContentPane().add(p);
    f.pack();
    f.setVisible(true);
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        text.setText(new DatePicker(f).setPickedDate());
      }
    });
  }
  public static void main(String[] args) {
    new Picker();
  }
}