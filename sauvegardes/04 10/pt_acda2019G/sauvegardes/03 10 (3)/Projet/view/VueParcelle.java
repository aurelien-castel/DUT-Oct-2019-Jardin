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
		nav_control=new ControlnavParcelle(fond,layout,nomboutons);
		nav = new VuenavParcelle(nomboutons[0],nomboutons[2],nav_control);
		
		this.add(nav);

		grille_control = new ControlpanelParcelle();
		grille= new VuepanelParcelle(grille_control);
		
        this.add(grille);
     
	}
}