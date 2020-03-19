import java.util.*;
import java.time.*;
import java.time.LocalDate;


import java.time.format.TextStyle;

public class Test{

	public static void main(String[] args) {
		AbstractJardinFactory jfbd = JardinFactoryBD.getJardinFactoryBD(); 
		AbstractLegumeFactory lfbd = LegumeFactoryBD.getLegumeFactoryBD();
		jfbd.setLegumeFactory(lfbd);
		AbstractActionFactory afbd = ActionFactoryBD.getActionFactoryBD();

		Parcelle p0 = jfbd.getJardin("Test");
		Legume poireauBD = lfbd.getLegume("Poireau de Carentan");

		afbd.AddAction(p0,p0.getRoot(),"sgfsdgf",poireauBD);
		System.out.println(afbd.getallAction(p0,LocalDate.now()));

		//Parcelle p0 = jfbd.AddJardin("testlegume",100,300);

		//jfbd.setlegume(p0,poireauBD);

		//Récupération noms jardin 

		//ArrayList<String> allparcelle= jfbd.getallJardin();
		//Creation d'un jardin 
		//jfbd.AddJardin("Arbe",100,300);

		//Création d'un Legume
		//Legume poireauBD = lfbd.AddLegume("Poireau de Carentan", FamilleLegume.ALLIACEES, 0b000000000100, 0b011111111000);
		
		//Récuperation d'un jardin
		//Parcelle p0 = jfbd.getJardin("Test");

		//Suppresion d'un Jardin 
		//jfbd.DeleteJardin("Arbe");

		//Split d'une parcelle 
		//jfbd.SplitParcelle(p0,Orientation.HORIZONTAL);

		//Récupération des sous parcelles 
		//getSecond est égalment disponibles
		//Parcelle p01 = p0.getFirst();
		//jfbd.SplitParcelle(p01,Orientation.VERTICAL);

		//Parcelle p011 = p01.getFirst();
		//jfbd.SplitParcelle(p011,Orientation.HORIZONTAL);


		//Parcelle p0111 =p011.getFirst();
		//jfbd.SplitParcelle(p0111,Orientation.VERTICAL);

		//rest d'une parcelle
		//jfbd.reset(p01);
	}   
}
