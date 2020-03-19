import java.util.*;
import java.time.Month;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

// singleton class pour gérer la persistance des légumes avec une base de données
//
// pour faire fonctionner cette classe vous devez :
// _ gérer d'une manière ou d'une autre votre mot de passe et votre login ci-dessous
// _ crééer une table de légumes sur votre base (voir fichier legumes.sql)
//
// NB. Je ne l'ai pas fait, mais ça serait probablement une bonne idée d'avoir comme ici un singleton pour toutes les factory. 
public class LegumeFactoryBD extends  MethodeBD implements AbstractLegumeFactory {

    // static variable single_instance of type Singleton 
    private static LegumeFactoryBD single_instance = null; 
    
    // private constructor restricted to this class itself 
    private LegumeFactoryBD(){
        // rien à faire, permet juste de rendre privé le constructeur par défaut.
    } 

    // static method to create instance of Singleton class 
    public static LegumeFactoryBD getLegumeFactoryBD(){ 
        if (single_instance == null) 
            single_instance = new LegumeFactoryBD(); 
        return single_instance; 
    }


    // tente d'ajouter le légume à la base de données
    public Legume AddLegume(String nomLegume, FamilleLegume famille, int semis, int recolte){
        if (semis < 0 || semis > 0b111111111111){
            throw new IllegalStateException("Erreur : semis doit être la valeur entière dont l'écriture binaire est sur 12 bits, un bit par mois de janvier à décembre de droite à gauche");
        }
        if (recolte < 0 || recolte > 0b111111111111){
            throw new IllegalStateException("Erreur : recolte doit être la valeur entière dont l'écriture binaire est sur 12 bits, un bit par mois de janvier à décembre de droite à gauche");
        }
        Iterator<Legume> leg = this.getLegumes(nomLegume);
        if (leg == null) {
            setConnection();

            String sql = "INSERT INTO Legumes(nomLegume, familleLegume, semis, Recolte) " +
            "VALUES (\""+
            nomLegume + "\", " +
            famille.ordinal() + ", " +
            "\"" +Integer.toBinaryString(semis)+ "\","  +
            "\"" +Integer.toBinaryString(recolte)+ "\""  +
            ");";

            execute(sql,true,false);
            close();

             // ici retourner un LegumeJ
            LegumeJ l = new LegumeJ(nomLegume,famille,semis,recolte);
            return l;
            
        }else{
            throw new IllegalStateException("Le Legume "+nomLegume+ " existe déja !");

        }
    }

    // supprime si existant le légume.
    // remarque : legume ne connaît pas parcelle, actionlegumes etc.
    // Il faudra faire attention si vous enlevez un légume
    // Si votre base modélise les autres concepts en lien avec légumes et dispose de clés étrangères adaptées alors un on delete cascade devrait marcher.
    // 
    public void DeleteLegume(String nomLegume){

        if (this.getLegumes(nomLegume).hasNext()) {
            throw new IllegalStateException("Le Legume "+nomLegume+" n'existe pas !");
        }else{
            setConnection();

            String sql = "DELETE FROM Legumes WHERE  nomLegume=  \""+ nomLegume+ "\";";
            execute(sql,false,false);
        }
    }
    // cherche légumes connus.
    public Iterator<Legume> getLegumes(){
        throw new UnsupportedOperationException("Désolé, fonction non supporté.");
    }

    // cherche légumes connus par famille.
    public Iterator<Legume> getLegumes(FamilleLegume famille){
        throw new UnsupportedOperationException("Désolé, fonction non supporté.");
    }
    // cherche légumes connus.
    // retourne un légume connu si la chaîne de caractère mot apparaît dans le nom de ce dernier.
    public Iterator<Legume> getLegumes(String mot){
        setConnection();
        int  familleLegume = 0 , semis=0,recolte=0;
        String nomLegume = null,tst;
        FamilleLegume familleLegumename;

        String sql = "SELECT `nomLegume`, `familleLegume`, `semis`, `recolte`FROM `Legumes` WHERE `nomLegume`  =\"" +mot +"\";";
        execute(sql,true,false);  
        close();

        
        if (vide == true) {
            return null;
        }else{
            nomLegume =  resultat.get(0);
            familleLegume = Integer.parseInt(resultat.get(1));

            semis =  Integer.parseInt(resultat.get(2));
            recolte =  Integer.parseInt(resultat.get(3));

            System.out.println(Integer.toBinaryString(semis));
            System.out.println(Integer.toBinaryString(recolte));     

            familleLegumename= FamilleLegume.values()[familleLegume];

            Legume legume = new LegumeJ(nomLegume,familleLegumename,semis,recolte);
            ArrayList<Legume> leg = new ArrayList<Legume>();
            leg.add(legume);
            Iterator it = leg.iterator();
            return it;
        }
    }
}