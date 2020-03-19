/**
*@author Thomas Guillemot
*/
package controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ControlnavHistorique implements ActionListener{
	private JPanel fond;
	private CardLayout layout;
	private final String nomboutons [];

	public ControlnavHistorique(JPanel fond,CardLayout layout,String nomboutons[]){
		this.fond = fond;
		this.layout = layout;
		this.nomboutons = nomboutons;
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals(nomboutons[0])){
			layout.show(fond,nomboutons[0]);
		}
		else if(e.getActionCommand().equals(nomboutons[1])){
			layout.show(fond,nomboutons[1]);

		}
		else if(e.getActionCommand().equals(nomboutons[2])){
			layout.show(fond,nomboutons[2]);

		}
	}
}