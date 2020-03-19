package models;

import java.util.*;
import java.time.Month;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class JardinFactoryBD extends  MethodeBD implements AbstractJardinFactory  {

    // static variable single_instance of type Singleton 
    private static JardinFactoryBD single_instance = null; 
    private static AbstractLegumeFactory lfbd = null;
    private static AbstractActionFactory afbd = null;

    
    // private constructor restricted to this class itself 
    private JardinFactoryBD(){
        // rien à faire, permet juste de rendre privé le constructeur par défaut.
    } 

    // static method to create instance of Singleton class 
    public static JardinFactoryBD getJardinFactoryBD(){ 
        if (single_instance == null){
            single_instance = new JardinFactoryBD();
            lfbd = LegumeFactoryBD.getLegumeFactoryBD();
            afbd = ActionFactoryBD.getActionFactoryBD();

        }     
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

    /*Métodes pour les Jardins*/

    /*
     * Pseudo Constructeur pour un nouveau jardin. Retourne la parcelle initiale du jardin
     */
    private Parcelle newJardin(int id_bd,String nomJardin, int x0, int x1,int y0, int y1,Legume legume){
        return new ParcelleBD(id_bd,nomJardin, x0, x1, y0,y1,legume);
    }

    // retourne (si existant) le jardin.
    private Parcelle searchJardin(String nomJardin){
        setConnection(); 

        String sql = "SELECT idparcelle,x0,x1,y0,y1,idLegume FROM `Parcelle` WHERE `ParcelleMere` = `idparcelle` AND nomJardin =\"" 
        +nomJardin +"\";";
        int id=0, x0=0 ,x1=0,y0=0 ,y1=0, idLegume=0;
        Legume legume = null;
        String orientation = null;
        Parcelle parcelle;
        execute(sql,true,false);

        if (vide) {
            return null;
        }else{
            id =  Integer.parseInt(resultat.get(0));
            x0 =  Integer.parseInt(resultat.get(1));
            x1 =  Integer.parseInt(resultat.get(2));
            y0 =  Integer.parseInt(resultat.get(3));
            y1 =  Integer.parseInt(resultat.get(4));
            try{
                idLegume =  Integer.parseInt(resultat.get(5));
                legume  = lfbd.getLegume(idLegume);

            }
            catch(NumberFormatException e){
                idLegume = 0;
                legume = null;
            }
            parcelle = this.newJardin(id, nomJardin, x0, x1, y0, y1,legume);
            return parcelle;            
        }
    }

    //ajoute (si n' existe pas) un jardin
    public Parcelle AddJardin(String nomJardin, int x1, int y1){
        int id =0;
        Parcelle parcelle = null;
        Parcelle test = searchJardin(nomJardin);
        if (test !=null){
            throw new IllegalStateException("Le jardin "+nomJardin+" existe déja !");
        }else{
            setConnection();
            String sql = "INSERT INTO Parcelle(nomJardin ,x0 ,x1, y0 ,y1) " +
            "VALUES (\""+
            nomJardin + "\"," +
            "\"0\","+
            "\""+x1 +"\"," +
            "\"0\","+
            "\""+y1 +"\""+
            ");";

            execute(sql,true,true);

            id = Integer.parseInt(resultat.get(0)); 

            String sql2 =  "UPDATE Parcelle set `ParcelleRacine` ="+id+" ,"+
            "`ParcelleMere` = "+id+" WHERE `idparcelle` = "+id+";";
            execute(sql2,false,false);
        }
        // ici retourne un Jardin
        parcelle = this.newJardin(id, nomJardin, 0,x1,0, y1,null);
        return parcelle;
    }

    // retourne le jardin entier (avec les sous parcelles) 
    public Parcelle getJardin(String nomJardin){
        Parcelle jardin = this.searchJardin(nomJardin);
        if (Objects.nonNull(jardin)){
            if (getOrientationParcelle(jardin.getid())!= null ){
                this.changechilds(jardin,getOrientationParcelle(jardin.getid()));
            }
            //System.out.println(jardin);
            return jardin;
        }else{
            throw new IllegalStateException("Le jardin "+nomJardin+" n'existe pas !");
        }
    }

    // retourne tous les noms des jardins présent dans la base
    public ArrayList<String> getallJardinname(){
        setConnection();
        String sql = "SELECT `nomJardin`FROM `Parcelle` WHERE `idparcelle` =`ParcelleMere` ";

        execute(sql,true,false);

        if (vide) {
            return null;
        }else{
            return resultat;
        }
    }

    // supprime (si existant) le jardin.
    public void DeleteJardin(String nomJardin){
        Parcelle test = searchJardin(nomJardin);
        if (test == null) {
            throw new IllegalStateException("Le jardin "+nomJardin+" n'existe pas !");
        }else{
            setConnection();
            String sql = "DELETE FROM Parcelle WHERE nomJardin =  \""+ nomJardin+ "\";";
            execute(sql,false,false);  
        }
    }

    //Créé les fils de chaque parcellle de facon récursive
    private void changechilds(Parcelle parcelle,Orientation orientation){
        Legume leg = null; 
        int id_sp0 = 0;
        Parcelle sousParcelle0 = null;
        Orientation orientationsp0;

        int id_sp1 = 0;
        Parcelle sousParcelle1 = null;
        Orientation orientationsp1;

        parcelle.SplitParcelle(orientation);
        
        /* Sous Parcelle 0 */
        sousParcelle0 = parcelle.getFirst();
        id_sp0 = getIDSousParcelle(parcelle.getid(),true);
        sousParcelle0.setid(id_sp0);

        leg  = getLegume(sousParcelle0.getid());
        sousParcelle0.addActionLegume(ActionLegumeType.SEMER,leg);

        orientationsp0 = getOrientationParcelle(id_sp0);
        if (Objects.nonNull(orientationsp0)){
            changechilds(sousParcelle0,orientationsp0);
        }

        /* Sous Parcelle 0 */
        sousParcelle1 = parcelle.getSecond();
        id_sp1 = getIDSousParcelle(parcelle.getid(),false);
        sousParcelle1.setid(id_sp1);

        leg  = getLegume(sousParcelle1.getid());
        sousParcelle1.addActionLegume(ActionLegumeType.SEMER,leg);


        orientationsp1 = getOrientationParcelle(id_sp1);
        if (Objects.nonNull(orientationsp1)){
            changechilds(sousParcelle1,orientationsp1);
        }

    }

    //renvoi l'id de la sousParcelle0 (true) ou de la sousParcelle1 (false)
    private int getIDSousParcelle(int id_mere,boolean fils){
        int sousParcelle;
        if (fils) {
            sousParcelle=0;
        }else{
            sousParcelle=1;
        }
        int id= 0;
        setConnection();
        String sql = "SELECT `sousParcelle"+sousParcelle
        +"` FROM `Parcelle` WHERE `idparcelle` = "+id_mere;
        execute(sql,true,false);


        id =  Integer.parseInt(resultat.get(0));

        return id;
    }

    //renvoi l'orientation d'une parcelle 
    private Orientation getOrientationParcelle(int id){
        setConnection();
        String orientation=" ";
        String sql = "SELECT `Orientation` FROM `Parcelle` WHERE `idparcelle` = "+id;
        execute(sql,true,false);

        if (vide == true) {
            return null;
        }else{
            orientation= resultat.get(0);
            if (Objects.nonNull(orientation)) {
                return Orientation.valueOf(orientation);         
            }else{
                return null;
            }
        }
    }

    private Legume getLegume(int idparcelle){
        setConnection();
        String sql = "SELECT `idLegume` FROM `Parcelle` WHERE `idparcelle` =" +idparcelle;
        Legume legume = null;
        int id = 0;
        execute(sql,true,false);
        if (!Objects.nonNull(resultat.get(0))) {
            return null;
        }else{
            id = Integer.parseInt(resultat.get(0));
            legume = lfbd.getLegume(id);
            return legume;
        }  
    }


    //split une parcelle en deux
    public void SplitParcelle(Parcelle parcelle){
        if (classeAppelante()=="models.ParcelleBD"){
            this.AddParcelle(parcelle.getFirst());
            this.AddParcelle(parcelle.getSecond());
            this.setMother(parcelle,parcelle.getSplit());
        }else{
            throw new IllegalStateException("Do nothing !");
        }
    }

    //ajoute une sous parcelle à un parcelle
    private void AddParcelle(Parcelle parcelle){
        setConnection();

        int id=0, id_racine = parcelle.getRoot().getid();
        int id_mere = parcelle.getMother().getid();
        int x0 = parcelle.getx0();
        int y0 = parcelle.gety0();
        int x1 = parcelle.getx1();
        int y1 = parcelle.gety1();

        String sql = "INSERT INTO Parcelle(nomJardin ,x0 ,x1, y0 ,y1,ParcelleRacine,ParcelleMere) " 
        +"VALUES (\""+
        parcelle.getNomJardin() + "\"," +
        "\""+x0 +"\","+
        "\""+x1 +"\","+
        "\""+y0 +"\","+
        "\""+y1 +"\","+
        "\""+id_racine +"\","+
        "\""+id_mere +"\""+
        ");";

        execute(sql,true,true);  

        id = Integer.parseInt(resultat.get(0)); 
        parcelle.setid(id);
    }

    //Modifie une Parcelle pour qu'elle devienne une mére
    private void setMother(Parcelle parcelle, Orientation orientation){
        setConnection();

        String sql = "UPDATE `Parcelle` SET `Orientation`  =\""
        +orientation+"\", `sousParcelle0` =\""
        +parcelle.getFirst().getid()+"\" ,`sousParcelle1` =\""
        +parcelle.getSecond().getid()+"\" ,idLegume=null  WHERE `idparcelle` = "
        +parcelle.getid()+";";

        execute(sql,false,false);
    }

    public void reset(Parcelle parcelle){
        if (classeAppelante()=="models.ParcelleBD"){
            this.DeleteParcelle(parcelle.getFirst());
            this.DeleteParcelle(parcelle.getSecond());
            this.setFille(parcelle);       
        }else{
            throw new IllegalStateException("Do nothing !");
        }
    }

    /*Supprime une parcelle (ces filles sont supprimé implisitement par la base) marche aussi 
    techniquement pour un jardin qui est aussi une parcelle
    */
    private void DeleteParcelle(Parcelle parcelle){
        int id = parcelle.getid();
        setConnection();

        String sql = "DELETE FROM Parcelle WHERE idparcelle = "+id+";";
        execute(sql,false,false);     
    }

    //Modifie une Parcelle pour qu'elle devienne une mére (pas de filles)
    private void setFille(Parcelle parcelle){
        setConnection();
        String sql = "UPDATE `Parcelle` SET `Orientation`=null WHERE `idparcelle` = "
        +parcelle.getid()+";";

        execute(sql,false,false); 
    }


    public void setlegume(ParcelleBD parcelle){
        if (classeAppelante()=="models.ParcelleBD"){ 
            setConnection();
            String sql = "UPDATE `Parcelle` SET `idLegume`="+ parcelle.getLegume().getid()
            +" WHERE `idparcelle` = "+parcelle.getid()+";";

            execute(sql,false,false);
        }else{
            throw new IllegalStateException("Do nothing !");
        }
    }
}