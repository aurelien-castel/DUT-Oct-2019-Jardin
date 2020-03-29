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
    private static AbstractLegumeFactory lfbd = null;
    
    // private constructor restricted to this class itself 
    private ActionFactoryBD(){
        // rien à faire, permet juste de rendre privé le constructeur par défaut.
    } 

    // static method to create instance of Singleton class 
    public static ActionFactoryBD getActionFactoryBD(){ 
        if (single_instance == null) 
            single_instance = new ActionFactoryBD();  
        lfbd = LegumeFactoryBD.getLegumeFactoryBD();   
        return single_instance; 
    }
    private static String classeAppelante(){
        Class appelante = null;
        String name = null;
        try{  
            Exception e = new Exception();
            name = ((e.getStackTrace())[2]).getClassName();
            appelante = Class.forName(name);
        }
        catch(Exception e2) { 
            appelante = null;
        }
        return name; 
    }

    private Action newActionSol(LocalDate date, Parcelle parcelle, ActionSolType type){
        return new ActionSol(date,parcelle,type);
    }
    private Action newActionParcelle(LocalDate date, Parcelle parcelle, ActionParcelleType type){
        return new ActionParcelle(date,parcelle,type);
    }
    private Action newActionLegume(LocalDate date, Parcelle parcelle, ActionLegumeType type,Legume legume){
        //System.out.println("qsf");
        return new ActionLegume(date,parcelle,legume,type);
    }


    public Action AddAction(Parcelle parcelle,ActionLegumeType type,Legume legume){
        if (classeAppelante()=="models.ParcelleBD") {
            defaultaddAction(parcelle,parcelle.getRoot(),type.toString(),legume);
            Action a = this.newActionLegume(LocalDate.now(),parcelle,type,legume);
            return a;         
        }
        return null;
    }

    public Action AddAction(Parcelle parcelle,ActionSolType type){
        if (classeAppelante()=="models.ParcelleBD") {
            defaultaddAction(parcelle,parcelle.getRoot(),type.toString(),null);
            Action a = this.newActionSol(LocalDate.now(),parcelle,type);
            return a;
        }
        return null;
    }
    public Action AddAction(Parcelle parcelle,ActionParcelleType type){
        if (classeAppelante()=="models.ParcelleBD") {
            defaultaddAction(parcelle,parcelle.getRoot(),type.toString(),null);
            Action a = this.newActionParcelle(LocalDate.now(),parcelle,type);
            return a;
        }
        return null;
    }


    private void defaultaddAction(Parcelle parcelle, Parcelle parcelleracine,String type,Legume legume){
        int idlegume = 0;
        int idparcelle=parcelle.getid(),idracine=parcelleracine.getid();
        String leg= null; 
        try{
            idlegume = legume.getid();
            leg = Integer.toString(idlegume);
        }catch(NullPointerException e){
        }
        setConnection();
        String sql = "INSERT INTO Action(Date ,parcelle ,parcelleracine, type,Legume) " +
        "VALUES (\""+LocalDate.now()+ "\","
        +idparcelle+","+
        +idracine+","
        +"\""+type +"\","
        +leg
        +");";
        execute(sql,true,false);
    }
    public ArrayList<String> getActions(Parcelle parcelle, LocalDate date){
        setConnection();
        boolean find = false;
        Legume legume = null;
        String sql = "SELECT Date,Type ,Legume FROM `Action` WHERE `parcelle` ="+parcelle.getid() +" AND Date = \""+ date+"\";";
        execute(sql,true,false);
        if (vide) {
            return new ArrayList<String>();
        }else{
            int position = 0, j = 0;
            ArrayList<String> actions = new ArrayList<String>();
            for (int i = 0 ; i < resultat.size()/3 ;i++){
                find = false;
                j = position;
                date = LocalDate.parse(resultat.get(j));
                try{ 
                    j++;
                    ActionLegumeType actionlegume = ActionLegumeType.valueOf(resultat.get(j));
                    j++;
                    legume = getlegume(resultat.get(j));
                    Action a= newActionLegume(date,parcelle,actionlegume,legume);
                    actions.add(a.toString());
                    find = true;
                }catch(IllegalArgumentException a){

                }
                if (find == false) {
                    j = position;
                    try{
                        j++;
                        ActionSolType actionsol = ActionSolType.valueOf(resultat.get(j));
                        Action a= newActionSol(date,parcelle,actionsol);
                        actions.add(a.toString());
                        find = true;
                    }catch(IllegalArgumentException a){}
                }
                if (find == false){
                    j = position;
                    try{
                        j++;
                        ActionParcelleType actionparcelle = ActionParcelleType.valueOf(resultat.get(j));
                        Action a= newActionParcelle(date,parcelle,actionparcelle);
                        actions.add(a.toString());
                        find = true;
                    }catch(IllegalArgumentException a){}
                }else if(find == false){
                    throw new IllegalArgumentException("Erreur type action base");
                }
                position = position +3;  
            }
            return actions;
        }
    }
    public ArrayList<String> getAllActions(Parcelle parcelle, LocalDate date){
        ArrayList<String> allSousActions = new ArrayList<String>();
        ArrayList<String> tmp = new ArrayList<String>();
        if (Objects.nonNull(parcelle.getSplit())) {
            tmp = getAllActions(parcelle.getFirst(),date);
            for (String a : tmp) {
                allSousActions.add(a);      
            }

            tmp = getAllActions(parcelle.getSecond(),date);
            for (String a : tmp) {
                allSousActions.add(a);      
            }

            tmp = getActions(parcelle,date);
            for (String a : tmp) {
                allSousActions.add(a.toString());      
            }

            return allSousActions;
        }else{
            tmp = getActions(parcelle,date);
            return tmp;
        }   
    }
    public ArrayList<Action> getActions(Parcelle parcelle){
        setConnection();
        boolean problem_enum = false,find = false;
        Legume legume= null;
        ArrayList<Action> actions_temp;
        String sql = "SELECT Date,Type,Legume FROM `Action` WHERE `parcelle` ="+parcelle.getid() +";";
        execute(sql,true,false);
        LocalDate date = null; 
        if (vide) {
            return null;
        }else{
            int position = 0, j = 0;
            actions_temp = new ArrayList<Action>();
            for (int i = 0 ; i < resultat.size()/3 ;i++){
                find = false;
                j = position;
                try{ 
                    date = LocalDate.parse(resultat.get(j));
                    j++;
                    ActionLegumeType actionlegume = ActionLegumeType.valueOf(resultat.get(j));
                    j++;
                    legume = getlegume(resultat.get(j));
                    actions_temp.add(newActionLegume(date,parcelle,actionlegume,legume));
                    find = true;
                }catch(IllegalArgumentException a){

                }
                if (find == false) {
                    j = position;
                    try{
                        date = LocalDate.parse(resultat.get(j));
                        j++;
                        ActionSolType actionsol = ActionSolType.valueOf(resultat.get(j));
                        j++;
                        actions_temp.add(newActionSol(date,parcelle,actionsol));
                        find = true;
                    }catch(IllegalArgumentException b){
                    }                  
                }
                if (find == false) {
                    j = position;
                    try{
                        date = LocalDate.parse(resultat.get(j));
                        j++;
                        ActionParcelleType actionparcelle = ActionParcelleType.valueOf(resultat.get(j));
                        j++;
                        actions_temp.add(newActionParcelle(date,parcelle,actionparcelle));
                        find = true;
                    }catch(IllegalArgumentException c){
                        problem_enum = true;
                        System.out.println("prb");
                    }
                }
                position = position +3;              
            }
        }

        return actions_temp;
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

    private Legume getlegume(String id){
        try{
            int id_temp = Integer.parseInt(id);
            Legume legume = lfbd.getLegume(id_temp);
            return legume;
        }catch(NumberFormatException e){
            return null;            
        }
    }
}