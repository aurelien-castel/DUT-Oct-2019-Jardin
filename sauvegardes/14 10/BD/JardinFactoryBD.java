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
public class JardinFactoryBD extends  MethodeBD implements AbstractJardinFactory  {

    // static variable single_instance of type Singleton 
    private static JardinFactoryBD single_instance = null; 
    
    // private constructor restricted to this class itself 
    private JardinFactoryBD(){
        // rien à faire, permet juste de rendre privé le constructeur par défaut.
    } 

    // static method to create instance of Singleton class 
    public static JardinFactoryBD getJardinFactoryBD(){ 
        if (single_instance == null) 
            single_instance = new JardinFactoryBD(); 
        return single_instance; 
    }

    //Métode pour les Jardins

     /*
     * Pseudo Constructeur pour un nouveau jardin. Retourne la parcelle initiale du jardin
    */
     public Parcelle newJardin(int id_bd,String nomJardin, int x0, int x1,int y0, int y1,Orientation orientation){
        return new ParcelleJ(id_bd,nomJardin, x0, x1, y0,y1,orientation);
    }

    //ajoute (si n' existe pas) un jardin
    public Parcelle AddJardin(String nomJardin, int x1, int y1){
        int id =0;
        Parcelle test = searchJardin(nomJardin);
        //System.out.println(test.getNomJardin());
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

            close();
        }
        // ici retourner un JardinJ
        Parcelle p = this.newJardin(id, nomJardin, 0,x1,0, y1,null);
        return p;
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
            close();
        }
    }

    // retourne (si existant) le jardin.
    private Parcelle searchJardin(String nomJardin){
        setConnection(); 

        String sql = "SELECT idparcelle,x0,x1,y0,y1,Orientation ,idLegume FROM `Parcelle` WHERE `ParcelleMere` = `idparcelle` AND nomJardin =\"" +nomJardin +"\";";
        int id=0, x0=0 ,x1=0,y0=0 ,y1=0, idLegume=0;
        String orientation = null;
        execute(sql,true,false);
        close();

        if (vide) {
            System.out.println("c vide");
            return null;
        }else{
            id =  Integer.parseInt(resultat.get(0));
            x0 =  Integer.parseInt(resultat.get(1));
            x1 =  Integer.parseInt(resultat.get(2));
            y0 =  Integer.parseInt(resultat.get(3));
            y1 =  Integer.parseInt(resultat.get(4));
            orientation = resultat.get(5);
            try{
                idLegume =  Integer.parseInt(resultat.get(6));
            }
            catch(NumberFormatException e){
                idLegume = 0;
            }
            

            if (Objects.nonNull(orientation)){
                Parcelle p = this.newJardin(id, nomJardin, x0, x1, y0, y1,Orientation.valueOf(orientation));
                return p; 

            }else{
                Parcelle p = this.newJardin(id, nomJardin, x0, x1, y0, y1,null);
                return p; 
            }
        }
    }

    // retourne le jardin entier (avec les sous parcelles) 
    public Parcelle getJardin(String nomJardin){
        Parcelle jardin = this.searchJardin(nomJardin);
        if (Objects.nonNull(jardin)){
            if (Objects.nonNull(jardin.getSplit())){
                this.changechilds(jardin);
            }
            return jardin;
        }else{
            throw new IllegalStateException("Le jardin "+nomJardin+" n'existe pas !");
        }
    }

    // retourne tous les jardins présent dans la base
    public ArrayList<String> getallJardin(){
        ArrayList<String> allparcelles = new ArrayList<String>();
        setConnection();
        String sql = "SELECT `nomJardin`FROM `Parcelle` WHERE `idparcelle` =`ParcelleMere` ";

        execute(sql,true,false);
        close(); 
        if (vide) {
            
            return null;
        }else{
            return resultat;
        }
    }

    //Créé les fils de chaque parcellle de facon récursive
    private void changechilds(Parcelle parcelle){

        Parcelle sousParcelle0 = parcelle.getFirst();
        int id_sp0 = getIDParcelle(parcelle.getidbd(),true);
        sousParcelle0.setidbd(id_sp0);

        Orientation orientationsp0 = getOrientationParcelle(id_sp0);
        if (Objects.nonNull(orientationsp0)){
            sousParcelle0.SplitParcelle(orientationsp0);
            changechilds(sousParcelle0);
        }

        Parcelle sousParcelle1 = parcelle.getSecond();
        int id_sp1 = getIDParcelle(parcelle.getidbd(),false);
        sousParcelle1.setidbd(id_sp1);

        Orientation orientationsp1 = getOrientationParcelle(id_sp1);
        if (Objects.nonNull(orientationsp1)){
            sousParcelle1.SplitParcelle(orientationsp1);
            changechilds(sousParcelle1);
        }
    }



    //Métodes pour les parcelle

    //split une parcelle en deux
    public void SplitParcelle(Parcelle parcelle,Orientation o){
        if (!Objects.nonNull(parcelle.getSplit())){
            parcelle.SplitParcelle(o);
            this.AddParcelle(parcelle.getFirst());
            this.AddParcelle(parcelle.getSecond());
            this.setMother(parcelle,o);
        }else{
            throw new IllegalStateException("La parcelle ne peut pas être découpée car elle contient des sous-parcelles.");
        }
    }
    public void reset(Parcelle parcelle){
        this.DeleteParcelle(parcelle.getFirst());
        this.DeleteParcelle(parcelle.getSecond());
        this.setFille(parcelle);
        parcelle.reset();

    }

    //Modifie une Parcelle pour qu'elle devienne une mére
    private void setMother(Parcelle parcelle, Orientation orientation){
        setConnection();
        String sql = "UPDATE `Parcelle` SET `Orientation`  =\""+orientation+"\", `sousParcelle0` =\""+parcelle.getFirst().getidbd()+"\" ,`sousParcelle1` =\""+parcelle.getSecond().getidbd()+"\"  WHERE `idparcelle` = "+parcelle.getidbd()+";";
        execute(sql,false,false);
        close();
    }
    //Modifie une Parcelle pour qu'elle devienne une mére (pas de filles)
    private void setFille(Parcelle parcelle){
        setConnection();
        String sql = "UPDATE `Parcelle` SET `Orientation`=null WHERE `idparcelle` = "+parcelle.getidbd()+";";
        execute(sql,false,false);
        close();  
    }

    //ajoute une sous parcelle à un parcelle (valabe aussi pour des jardins), renvoie l'id de la parcelle crée
    private void AddParcelle(Parcelle parcelle){
        setConnection();

        int id=0, id_racine = parcelle.getRoot().getidbd();
        int id_mere = parcelle.getMother().getidbd();
        int x0 =parcelle.getx0();
        int y0 =parcelle.gety0();
        int x1 =parcelle.getx1();
        int y1 =parcelle.gety1();

        String sql = "INSERT INTO Parcelle(nomJardin ,x0 ,x1, y0 ,y1,ParcelleRacine,ParcelleMere) " +
        "VALUES (\""+
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

        close();
        parcelle.setidbd(id);
    }

    //Supprime une parcelle (ces filles sont aussi suprimmé par des triggers impleùenté dans la base)
    public void DeleteParcelle(Parcelle parcelle){
        int id = parcelle.getidbd();
        setConnection();

        String sql = "DELETE FROM Parcelle WHERE idparcelle = "+id+";";
        execute(sql,false,false);     
    }

    //renvoi l'id de la sousParcelle0 (true) ou de la sousParcelle1 (false)
    private int getIDParcelle(int id_mere,boolean fils){
        int sousParcelle;
        if (fils) {
            sousParcelle=0;
        }else{
            sousParcelle=1;
        }
        int id= 0;
        setConnection();
        String sql = "SELECT `sousParcelle"+sousParcelle+"` FROM `Parcelle` WHERE `idparcelle` = "+id_mere;
        execute(sql,true,false);
        close();


        id =  Integer.parseInt(resultat.get(0));

        return id;
    }

    //renvoi l'orientation d'une parcelle 
    private Orientation getOrientationParcelle(int id){
        setConnection();
        String orientation=" ";
        String sql = "SELECT `Orientation` FROM `Parcelle` WHERE `idparcelle` = "+id;
        execute(sql,true,false);
        close();

        if (vide == true) {
            return null;
        }else{
            orientation= resultat.get(0);
            if (Objects.nonNull(orientation)) {
                return  Orientation.valueOf(orientation);         
            }else{
                return null;
            }
        }
    }

    private void setlegume(Legume legume){
        setConnection();

    }
}