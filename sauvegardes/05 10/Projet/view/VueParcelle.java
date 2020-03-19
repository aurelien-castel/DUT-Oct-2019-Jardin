/**
*@author Thomas Guillemot
*/
package view;
import javax.swing.*;
import java.awt.*;
import view.*;

import controller.*;

public class VueParcelle extends JPanel{

	private VuenavParcelle nav;
	private ControlnavParcelle nav_control;

     private VuepanelParcelle grille;
     private ControlpanelParcelle grille_control;

	public VueParcelle(JPanel fond,CardLayout layout,String nomboutons[]){

		setLayout(new BorderLayout());

		nav_control=new ControlnavParcelle(fond,layout,nomboutons);
		nav = new VuenavParcelle(nomboutons[0],nomboutons[2],nav_control);
		
		this.add(nav, BorderLayout.NORTH);

		//MVC models
		grille= new VuepanelParcelle(); 
		grille_control = new ControlpanelParcelle(grille); //--> controller a besoins de la vue et du modele
		
        this.add(grille);
     
	}
}