/**
*@author Thomas Guillemot
*/
package controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ControlAddNewJardin implements ActionListener{
	private JPanel fond;
	private String nom,taille_x,taille_y;


	public ControlAddNewJardin(JPanel fond,String nom, String taille_x,String taille_y){
		this.fond = fond;
		this.nom = nom;
		this.taille_x = taille_x;
		this.taille_y = taille_y;

	}
	@Override
	public void actionPerformed(ActionEvent e){
		System.out.println("add "+ nom);
	}
}
