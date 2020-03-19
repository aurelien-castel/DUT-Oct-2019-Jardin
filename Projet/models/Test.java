package models;

import java.util.*;
import java.time.*;
import java.time.LocalDate;
import models.*;

import java.time.format.TextStyle;

public class Test{

	public static void main(String[] args) {
		AbstractJardinFactory jfbd = JardinFactoryBD.getJardinFactoryBD(); 
		AbstractLegumeFactory lfbd = LegumeFactoryBD.getLegumeFactoryBD();
		AbstractActionFactory afbd = ActionFactoryBD.getActionFactoryBD();

		Parcelle p0 = jfbd.getJardin("2");
		ArrayList<String> allSousActions = new ArrayList<String>();
		allSousActions =(afbd.getAllActions(p0,LocalDate.now()));
		System.out.println("Actions :");
		for (String a : allSousActions ) {
			System.out.println(a);
		}

		//Parcelle p0 = jfbd.AddJardin("testlegume",100,300);
		//p0.SplitParcelle(Orientation.HORIZONTAL);
		//p0.reset();
		//Legume poireauBD = lfbd.getLegume("Poireau de Carentan");

		//afbd.AddAction(p0,p0.getRoot(),ActionLegumeType.ARRACHER,poireauBD);

		//System.out.println("rsqR");
	}   
}