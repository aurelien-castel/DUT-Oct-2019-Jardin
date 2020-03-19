import java.util.*;
import java.time.*;

import java.time.format.TextStyle;

public class Test{

	public static void main(String[] args) {
		AbstractJardinFactory jfbd =JardinFactoryBD.getJardinFactoryBD(); 

		//Cration d'un jardin 
		//Parcelle p0 = jfbd.AddJardin("Arbe", 100, 300);

		//Récuperation d'un jardin
		Parcelle p0 = jfbd.getJardin("Arbe");

		//Suppresion d'un Jardin 
		//jfbd.DeleteJardin("Arbe");

		//Split d'une parcelle 
		//jfbd.SplitParcelle(p0,Orientation.HORIZONTAL);

		//Récupération des sous parcelles 
		//getSecond est égalment disponibles
		Parcelle p01 = p0.getFirst();
		//jfbd.SplitParcelle(p01,Orientation.VERTICAL);

		Parcelle p011 = p01.getFirst();
		//jfbd.SplitParcelle(p011,Orientation.HORIZONTAL);


		Parcelle p0111 =p011.getFirst();
		//jfbd.SplitParcelle(p0111,Orientation.VERTICAL);

		//rest d'une parcelle
		//jfbd.reset(p01);
	}   
}
