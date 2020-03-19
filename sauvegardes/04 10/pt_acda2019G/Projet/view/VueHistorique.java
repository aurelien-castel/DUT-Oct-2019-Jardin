/**
*@author Thomas Guillemot
*/
package view;
import javax.swing.*;
import java.awt.*;
import view.*;

import controller.*;

public class VueHistorique extends JPanel{
	private VuenavHistorique nav;
	private ControlnavHistorique nav_control;

      //private VuepanelHistorique grille;
      //private ControlpanelHistorique grille_control;

	public VueHistorique(JPanel fond,CardLayout layout,String nomboutons[]){
		nav_control=new ControlnavHistorique(fond,layout,nomboutons);
		nav = new VuenavHistorique(nomboutons[0],nomboutons[1],nav_control);
		this.add(nav);

		/*grille_control = new ContolpanelHistorique()
		grille= new VuepanelHistorique();
		
        this.add(grille);*/
   
	}
}