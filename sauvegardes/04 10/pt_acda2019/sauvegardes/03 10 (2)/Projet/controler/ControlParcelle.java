/**
*
* @version 1.1
* @author Aurélien Castel
*/
package controller;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import view.*;

@SuppressWarnings("serial")

public class ControlParcelle extends VueParcelle {

    String gender = "";

    Object[][] data;

    int serialNo;

    String SHOW = "Show";

    VueParcelle formGUIObject;

    // Defining Constructor

    public ControlParcelle() {

        nameField.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {

                if (nameField.getText().length() >= 15)

                    e.consume();

            }

        });

        male.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                gender = "Male";

            }
        });

        female.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                gender = "Female";

            }

        });

        addressField.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {

                if (addressField.getText().length() > 50)

                    e.consume();

            }

        });

        exitButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                try {

                    System.exit(0);

                } catch (Exception ex) {

                    System.out.println(ex.getMessage());

                }

            }

        });

        // Registering Anonymous Listener Class

        table.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent arg0) {
            }

            // @Override

            public void mouseReleased(MouseEvent arg0) {
            }

            // @Override

            public void mousePressed(MouseEvent arg0) {
            }

            // @Override

            public void mouseExited(MouseEvent arg0) {
            }

            // @Override

            public void mouseEntered(MouseEvent arg0) {
            }

        });

    }

    // main() method

    public static void main(String[] args) {

        new DynamicRegForm();

    }
}
