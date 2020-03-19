/**
*@author Thomas Guillemot
*/
package controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ControlAddNewJardin implements ActionListener{
	private JPanel fond;
	private JTextField nom,taille_x,taille_y;


	public ControlAddNewJardin(JPanel fond,JTextField nom, JTextField taille_x,JTextField taille_y){
		this.fond = fond;
		this.nom = nom;
		this.taille_x = taille_x;
		this.taille_y = taille_y;

	}
	@Override
	public void actionPerformed(ActionEvent e){
		try{
			int x = Integer.parseInt(taille_x.getText());
			int y = Integer.parseInt(taille_y.getText());
			if (x <= 0 ||  y<=0){
				System.out.println("Entrez des dimensions positives !");			
			}else{
				if (nom.getText().equals("")) {
					System.out.println("Entrez un nom valide !");				
				}else{
					System.out.println("Création du jardin \""+nom.getText()+"\" de "+x+" par "+y);
				}
			}
			
		}catch(NumberFormatException n){
			System.out.println("Les dimensions ne sont pas des nombres !");
		}
		
	}
}
