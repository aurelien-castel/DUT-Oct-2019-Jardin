import java.util.*;
import java.time.*;

import java.time.format.TextStyle;

public class Test{

	public static void main(String[] args) {
		AbstractJardinFactory jfbd =JardinFactoryBD.getJardinFactoryBD(); 
		AbstractLegumeFactory lfbd = LegumeFactoryBD.getLegumeFactoryBD();

		//ArrayList<String> allparcelle= jfbd.getallJardin();
		//Creation d'un jardin 
		//Parcelle p0 = jfbd.AddJardin("Test", 100, 300);
		//System.out.println(allparcelle);

		//Récuperation d'un jardin
		// Parcelle p0 = jfbd.getJardin("Test");

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

		//Legume poireauBD = null;
		//Legume beetBD = null;
		/*try {
			poireauBD = lfbd.AddLegume("Poireau de Carentan", FamilleLegume.ALLIACEES, 0b000000000100, 0b011111111000);
			System.out.printf(poireauBD.toString(TextStyle.FULL));
		} catch(IllegalStateException e) {
			System.out.printf(e.getMessage());
		}
		try {
			beetBD = lfbd.AddLegume("Betterave Crapaudine", FamilleLegume.CHENOPODES,0b000000011000, 0b011111000000);
			System.out.printf(beetBD.toString(TextStyle.FULL));
		} catch(IllegalStateException e) {
			System.out.printf(e.getMessage());
		}*/
		Iterator<Legume> leg  = lfbd.getLegumes("Betterave Crapaudine");
		Legume tst = leg.next();
		System.out.println(tst);
		//System.out.println(FamilleLegume.values()[1]);

	}   
}
