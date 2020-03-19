import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import javax.swing.event.*;
import java.util.ArrayList;

import controller.*;
import view.*;

/**
 * le main du projet
 * Commandes sous windows:
 *   del /s *.class
 *   javac .\Main.java
 *   java -cp ".;models/mariadb-client.jar" Main
 */
public class Main {

    public static void main(String[] arg) {
        VueMenu menu = new VueMenu("Projet IHM", 1400, 800);
    }

}
