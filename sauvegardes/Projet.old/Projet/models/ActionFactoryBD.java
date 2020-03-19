package models;

import java.util.*;
import java.time.Month;
import java.time.LocalDate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

// singleton class pour gérer la persistance des légumes avec une base de données
public class ActionFactoryBD extends MethodeBD implements AbstractActionFactory  {

    // static variable single_instance of type Singleton 
    private static ActionFactoryBD single_instance = null; 
    
    // private constructor restricted to this class itself 
    private ActionFactoryBD(){
        // rien à faire, permet juste de rendre privé le constructeur par défaut.
    } 

    // static method to create instance of Singleton class 
    public static ActionFactoryBD getActionFactoryBD(){ 
        if (single_instance == null) 
            single_instance = new ActionFactoryBD();     
        return single_instance; 
    }
    public void AddAction(Parcelle parcelle,Parcelle parcelleracine,String type,Legume legume){
        int idlegume = 0;
        int idparcelle=parcelle.getidbd(),idracine=parcelleracine.getidbd();
        String leg= null; 
        try{
            idlegume = legume.getidbd();
            leg = Integer.toString(idlegume);
        }catch(NumberFormatException e){}
        setConnection();
        String sql = "INSERT INTO Action(Date ,parcelle ,parcelleracine, Type,Legume) " +
        "VALUES (\""+LocalDate.now()+ "\","
        +idparcelle+","+
        +idracine+","
        +"\""+type +"\","
        +leg
        +");";

        execute(sql,true,false);
        // ici retourne un JardinJ
         // Action a = this.newJardin(id, nomJardin, 0,x1,0, y1,null,null);
        //return a;
    }


    // retourne tous les noms des jardins présent dans la base
    public ArrayList<String> getallAction(Parcelle parcelle, LocalDate date){
        setConnection();
        String sql = "SELECT * FROM `Action` WHERE `parcelle` ="+parcelle.getidbd() +" AND Date = "+ date+";";

        execute(sql,true,false);
        if (vide) {

            return null;
        }else{
            return resultat;
        }
    }
    public ArrayList<String> getAllDate(){
        setConnection();  
        String sql = "SELECT DISTINCT Date FROM `Action`";
        execute(sql,true,true); 
        if (vide) {

            return null;
        }else{
            return resultat;
        }  
    }
    
}
