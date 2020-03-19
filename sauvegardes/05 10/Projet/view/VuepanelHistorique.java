
/**
*
* @version 1.1
* @author Aurélien Castel
*/
package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.*;

public class VuepanelHistorique extends JPanel {

  private JLabel Instruction;
  private JPanel menu;
  private JPanel centredCalendar, centredDelete;
  private JPanel calendar;
  private JPanel leftpanel;
  private JPanel panel;

  private int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
  private int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);;
  private JLabel l = new JLabel("", JLabel.CENTER);
  private String day = "";
  private JButton[] button = new JButton[49];

  private JButton confirmerDate, deleteButton, previous, next;

  public VuepanelHistorique() {

    setLayout(new BorderLayout());

    menu = new JPanel();
    menu.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    Instruction = new JLabel("Instruction");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 0; // make this component tall
    c.insets = new Insets(0, 0, 10, 0);
    c.weightx = .5;
    c.weighty = .5;
    c.gridwidth = 0;
    c.gridx = 0;
    c.gridy = 0;
    menu.add(Instruction, c);
    Instruction.setHorizontalAlignment(JLabel.CENTER);

    JLabel label = new JLabel("Selected Date:");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 0; // make this component tall
    c.insets = new Insets(0, 5, 10, 5);
    c.weightx = 0;
    c.weighty = 0;
    c.gridwidth = 1;
    c.gridx = 0;
    c.gridy = 1;
    menu.add(label, c);
    final JTextField text = new JTextField(10);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 0; // make this component tall
    c.insets = new Insets(0, 5, 10, 5);
    c.weightx = 0;
    c.weighty = 0;
    c.gridwidth = 1;
    c.gridx = 1;
    c.gridy = 1;
    menu.add(text, c);
    confirmerDate = new JButton("confirmer la date");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 0; // make this component tall
    c.insets = new Insets(0, 5, 10, 5);
    c.weightx = 0;
    c.weighty = 0;
    c.gridwidth = 1;
    c.gridx = 2;
    c.gridy = 1;
    menu.add(confirmerDate, c);

    confirmerDate.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        text.setText(setPickedDate());
      }
    });

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
    p1.setPreferredSize(new Dimension(370, 120));

    for (int x = 0; x < button.length; x++) {
      final int selection = x;
      button[x] = new JButton();
      button[x].setFocusPainted(false);
      button[x].setBackground(Color.white);
      if (x > 6)
        button[x].addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
            day = button[selection].getActionCommand();
          }
        });
      if (x < 7) {
        button[x].setText(header[x]);
        button[x].setForeground(Color.red);
      }
      p1.add(button[x]);
    }
    JPanel p2 = new JPanel(new GridLayout(1, 3));
    previous = new JButton("<< Previous");
    previous.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        month--;
        displayDate();
      }
    });
    p2.add(previous);
    p2.add(l);
    next = new JButton("Next >>");
    next.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        month++;
        displayDate();
      }
    });
    p2.add(next);
    calendar.add(p1, BorderLayout.CENTER);
    calendar.add(p2, BorderLayout.SOUTH);

    displayDate();

    centredCalendar.add(calendar);

    // leftpanel
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 0; // make this component tall
    c.insets = new Insets(0, 5, 100, 5);
    c.weightx = 0.0;
    c.gridwidth = 0;
    c.gridx = 0;
    c.gridy = 1;
    leftpanel.add(centredCalendar, c);

    centredDelete = new JPanel();
    centredDelete.setLayout(new GridBagLayout());
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 5; // make this component tall
    c.insets = new Insets(0, 5, 0, 5);
    c.weightx = 0.0;
    c.gridwidth = 0;
    c.gridx = 0;
    c.gridy = 0;
    deleteButton = new JButton("Supprimer definitivement ce jardin");
    deleteButton.setBackground(new Color(217, 136, 128));
    centredDelete.add(deleteButton);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 5; // make this component tall
    c.insets = new Insets(0, 5, 0, 5);
    c.weightx = 0.0;
    c.gridwidth = 0;
    c.gridx = 0;
    c.gridy = 2;
    leftpanel.add(centredDelete, c);

    add(leftpanel, BorderLayout.WEST);
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

  /**
   * @return the confirmerDate
   */
  public JButton getConfirmerDate() {
    return confirmerDate;
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
   * @return the deleteButton
   */
  public JButton getDeleteButton() {
    return deleteButton;
  }

}