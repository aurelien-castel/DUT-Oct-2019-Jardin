import java.util.*;
import java.time.*;

import java.time.format.TextStyle;

public class Test{

	public static void main(String[] args) {
		AbstractJardinFactory jfbd =JardinFactoryBD.getJardinFactoryBD(); 
		Parcelle p0 = jfbd.AddJardin("Eden", 100, 300);
		//Parcelle p1 = jfbd.AddJardin("Eden", 100, 300);
		jfbd.DeleteJardin("Eden");
		jfbd.DeleteJardin("Eden");
		//Parcelle p0 = jfbd.AddJardin("Eden", 100, 300);
		//Parcelle p0 = jfbd.getJardin("Ede");

	}   
}
