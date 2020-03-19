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
public class JardinFactoryBD implements AbstractJardinFactory {

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


    // détails connexion base de donnée
    String bd_url  = Config_bd.geturl();
    String bd_user = Config_bd.getuser();
    String bd_pwd  = Config_bd.getpwd();

    //Métode pour les Jardins

     /*
     * Pseudo Constructeur pour un nouveau jardin. Retourne la parcelle initiale du jardin
    */
     public Parcelle newJardin(int id_bd,String nomJardin, int x0, int x1,int y0, int y1,Orientation orientation){
        return new ParcelleJ(id_bd,nomJardin, x0, x1, y0,y1,orientation);
    }

    //ajoute (si n' existe pas) un jardin
    public Parcelle AddJardin(String nomJardin, int x1, int y1){
        Parcelle test = searchJardin(nomJardin);
        if (test !=null){
            throw new IllegalStateException("Le jardin "+nomJardin+" existe déja !");

        }else{
            Boolean problem = false;
            StringBuilder sb = new StringBuilder("Erreur : Il y a eu un problème. Ci-dessous le log de connexion à la base de données.%n");
            Connection con = null;
            Statement stmt = null;
            ResultSet res = null;
            int id_bd =0;
            String sql = "INSERT INTO Parcelle(nomJardin ,x0 ,x1, y0 ,y1) " +
            "VALUES (\""+
            nomJardin + "\"," +
            "\"0\","+
            "\""+x1 +"\"," +
            "\"0\","+
            "\""+y1 +"\""+
            ");";
            String sql2 = "SELECT LAST_INSERT_ID();";

            try {
            // pas nécessaire mais utile pour détecter un mauvais classpath
                Class.forName("org.mariadb.jdbc.Driver");

                try {
                    con = DriverManager.getConnection(bd_url+bd_user, bd_user, bd_pwd);

                    if(!con.isClosed()) {
                        sb.append("   OK : BD connexion réussie.%n");
                        try {
                            stmt = con.createStatement();
                            sb.append("   OK : BD création de la déclaration réussie.%n");
                            try{
                                stmt.executeUpdate(sql);
                                
                                res=stmt.executeQuery(sql2);
                                res.next();
                                id_bd = res.getInt(1);
                                String sql3 ="UPDATE Parcelle set `ParcelleRacine` = "+id_bd+",`ParcelleMere` = "+id_bd+" WHERE `idparcelle` =  "+id_bd+";";
                                stmt.executeUpdate(sql3);

                                sb.append("   OK : BD exécution de la requête réussie.%n");
                            } catch(SQLException e) {
                                sb.append("   KO : insertion ratée (exception SQL ci-dessous).%n");    
                                sb.append(e);
                                problem = true;
                            } finally {
                                try {
                                    stmt.close();
                                    sb.append("   OK : BD fermeture de la déclaration réussie.%n");
                                } catch(SQLException e) {
                                    sb.append("   KO : BD fermeture de la déclaration ratée.%n");
                                    problem = true;
                                }
                                stmt = null;
                            }
                        } catch(SQLException e) {
                            sb.append("   KO : BD creation de la déclaration ratée (exception SQL ci-dessous).%n");    
                            sb.append(e);
                            problem = true;
                        }
                    } else {
                        sb.append("   KO : BD connexion prématurément fermée.%n");    
                        problem = true;
                    }
                    try {
                        con.close();
                        sb.append("   OK : BD fermeture de la connexion réussie.%n");    
                    } catch(SQLException e) {
                        sb.append("   KO : BD fermeture de la connexion ratée.%n");    
                        problem = true;
                    }
                } catch(SQLException e) {
                    sb.append("   KO : BD connexion ratée.%n");    
                    problem = true;
                }
            }catch(ClassNotFoundException e) {
                sb.append("   KO : BD pas de pilote MariaDB dans les chemins de classes disponibles!%n");
                sb.append("        passez à java le bon classpath. À l'iut il faut :%n");
                sb.append("        -cp \"/export/documents/mariadb-client.jar:.\"%n");
                problem = true;
            }

            // ouf on a fini les try catch
            if (problem){ 
                throw new IllegalStateException(sb.toString()); 
            }
            // ici retourner un LegumeJ
            Parcelle p = this.newJardin(id_bd, nomJardin, 0,x1,0, y1,null);
            return p;
        }
    }

    // supprime (si existant) le jardin.
    public void DeleteJardin(String nomJardin){
        Parcelle test = searchJardin(nomJardin);
        if (test == null) {
            throw new IllegalStateException("Le jardin "+nomJardin+" n'existe pas !");
        }else{
            Boolean problem = false;
            StringBuilder sb = new StringBuilder("Erreur : Il y a eu un problème. Ci-dessous le log de connexion à la base de données.%n");
            Connection con = null;
            Statement stmt = null;

            String sql = "DELETE FROM Parcelle WHERE nomJardin =  \""+ nomJardin+ "\";";
            try {
            // pas nécessaire mais utile pour détecter un mauvais classpath
                Class.forName("org.mariadb.jdbc.Driver");

                try {
                    con = DriverManager.getConnection(bd_url+bd_user, bd_user, bd_pwd);

                    if(!con.isClosed()) {
                        sb.append("   OK : BD connexion réussie.%n");
                        try {
                            stmt = con.createStatement();
                            sb.append("   OK : BD création de la déclaration réussie.%n");
                            try{
                                stmt.executeUpdate(sql);
                                sb.append("   OK : BD exécution de la requête réussie.%n");
                            } catch(SQLException e) {
                                sb.append("   KO : insertion ratée (exception SQL ci-dessous).%n");    
                                sb.append(e);
                                problem = true;
                            } finally {
                                try {
                                    stmt.close();
                                    sb.append("   OK : BD fermeture de la déclaration réussie.%n");
                                } catch(SQLException e) {
                                    sb.append("   KO : BD fermeture de la déclaration ratée.%n");
                                    problem = true;
                                }
                                stmt = null;
                            }
                        } catch(SQLException e) {
                            sb.append("   KO : BD creation de la déclaration ratée (exception SQL ci-dessous).%n");    
                            sb.append(e);
                            problem = true;
                        }
                    } else {
                        sb.append("   KO : BD connexion prématurément fermée.%n");    
                        problem = true;
                    }
                    try {
                        con.close();
                        sb.append("   OK : BD fermeture de la connexion réussie.%n");    
                    } catch(SQLException e) {
                        sb.append("   KO : BD fermeture de la connexion ratée.%n");    
                        problem = true;
                    }
                } catch(SQLException e) {
                    sb.append("   KO : BD connexion ratée.%n");    
                    problem = true;
                }
            // ouf on a fini les try catch
            }
            catch(ClassNotFoundException e) {
                sb.append("   KO : BD pas de pilote MariaDB dans les chemins de classes disponibles!%n");
                sb.append("        passez à java le bon classpath. À l'iut il faut :%n");
                sb.append("        -cp \"/export/documents/mariadb-client.jar:.\"%n");
                problem = true;
            } 
            if (problem) { throw new IllegalStateException(sb.toString()); }
        }
    }

    // retourne (si existant) le jardin.
    private Parcelle searchJardin(String nomJardin){
        Boolean problem = false;
        Boolean vide = false;
        StringBuilder sb = new StringBuilder("Erreur : Il y a eu un problème. Ci-dessous le log de connexion à la base de données.%n");
        Connection con = null;
        Statement stmt = null;
        ResultSet res = null;
        String sql = "SELECT idparcelle,x0,x1,y0,y1,Orientation FROM `Parcelle` WHERE `ParcelleMere` = `idparcelle` AND nomJardin =\"" +nomJardin +"\";";
        int id_bd=0, x0=0 ,x1=0,y0=0 ,y1=0;
        String orientation = null;
        try {
            // pas nécessaire mais utile pour détecter un mauvais classpath
            Class.forName("org.mariadb.jdbc.Driver");
            try {
                con = DriverManager.getConnection(bd_url+bd_user, bd_user, bd_pwd);
                if(!con.isClosed()) {
                    sb.append("   OK : BD connexion réussie.%n");
                    try {
                        stmt = con.createStatement();
                        sb.append("   OK : BD création de la déclaration réussie.%n");
                        try{
                            res =stmt.executeQuery(sql);
                            if (res.next() == false){
                                vide = true;
                            }else{
                                id_bd = res.getInt(1);
                                x0 = res.getInt(2);
                                x1 = res.getInt(3);
                                y0 = res.getInt(4);
                                y1 = res.getInt(5);
                                orientation = res.getString(6);

                            }
                            sb.append("   OK : BD exécution de la requête réussie.%n");
                        }catch(SQLException e){
                            sb.append("   KO : insertion ratée (exception SQL ci-dessous).%n");    
                            sb.append(e);
                            problem = true;
                        }finally {
                            try {
                                stmt.close();
                                sb.append("   OK : BD fermeture de la déclaration réussie.%n");
                            } catch(SQLException e) {
                                sb.append("   KO : BD fermeture de la déclaration ratée.%n");
                                problem = true;
                            }
                            stmt = null;
                        }
                    } catch(SQLException e) {
                        sb.append("   KO : BD creation de la déclaration ratée (exception SQL ci-dessous).%n");    
                        sb.append(e);
                        problem = true;
                    }
                }else {
                    sb.append("   KO : BD connexion prématurément fermée.%n");    
                    problem = true;
                }
                try {
                    con.close();
                    sb.append("   OK : BD fermeture de la connexion réussie.%n");    
                } catch(SQLException e) {
                    sb.append("   KO : BD fermeture de la connexion ratée.%n");    
                    problem = true;
                }
            }catch(SQLException e) {
                sb.append("   KO : BD connexion ratée.%n");    
                problem = true;
            }
            // ouf on a fini les try catch
            if (problem) { throw new IllegalStateException(sb.toString()); }
        }catch(ClassNotFoundException e) {
            sb.append("   KO : BD pas de pilote MariaDB dans les chemins de classes disponibles!%n");
            sb.append("        passez à java le bon classpath. À l'iut il faut :%n");
            sb.append("        -cp \"/export/documents/mariadb-client.jar:.\"%n");
            problem = true;
        } 
        if (vide == true) {
            return null;
        }else{
            if (Objects.nonNull(orientation)){
                Parcelle p = this.newJardin(id_bd, nomJardin, x0, x1, y0, y1,Orientation.valueOf(orientation));
                return p; 

            }else{
                Parcelle p = this.newJardin(id_bd, nomJardin, x0, x1, y0, y1,null);
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
        Boolean problem = false;
        StringBuilder sb = new StringBuilder("Erreur : Il y a eu un problème. Ci-dessous le log de connexion à la base de données.%n");
        Connection con = null;
        Statement stmt = null;
        int id_bd;
        String sql = "UPDATE `Parcelle` SET `Orientation`  =\""+orientation+"\", `sousParcelle0` =\""+parcelle.getFirst().getidbd()+"\" ,`sousParcelle1` =\""+parcelle.getSecond().getidbd()+"\"  WHERE `idparcelle` = "+parcelle.getidbd()+";";
        try {
            // pas nécessaire mais utile pour détecter un mauvais classpath
            Class.forName("org.mariadb.jdbc.Driver");

            try {
                con = DriverManager.getConnection(bd_url+bd_user, bd_user, bd_pwd);

                if(!con.isClosed()) {
                    sb.append("   OK : BD connexion réussie.%n");
                    try {
                        stmt = con.createStatement();
                        sb.append("   OK : BD création de la déclaration réussie.%n");
                        try{
                            stmt.executeUpdate(sql);

                            sb.append("   OK : BD exécution de la requête réussie.%n");
                        } catch(SQLException e) {
                            sb.append("   KO : insertion ratée (exception SQL ci-dessous).%n");    
                            sb.append(e);
                            problem = true;
                        } finally {
                            try {
                                stmt.close();
                                sb.append("   OK : BD fermeture de la déclaration réussie.%n");
                            } catch(SQLException e) {
                                sb.append("   KO : BD fermeture de la déclaration ratée.%n");
                                problem = true;
                            }
                            stmt = null;
                        }
                    } catch(SQLException e) {
                        sb.append("   KO : BD creation de la déclaration ratée (exception SQL ci-dessous).%n");    
                        sb.append(e);
                        problem = true;
                    }
                } else {
                    sb.append("   KO : BD connexion prématurément fermée.%n");    
                    problem = true;
                }
                try {
                    con.close();
                    sb.append("   OK : BD fermeture de la connexion réussie.%n");    
                } catch(SQLException e) {
                    sb.append("   KO : BD fermeture de la connexion ratée.%n");    
                    problem = true;
                }
            } catch(SQLException e) {
                sb.append("   KO : BD connexion ratée.%n");    
                problem = true;
            }
        }catch(ClassNotFoundException e) {
            sb.append("   KO : BD pas de pilote MariaDB dans les chemins de classes disponibles!%n");
            sb.append("        passez à java le bon classpath. À l'iut il faut :%n");
            sb.append("        -cp \"/export/documents/mariadb-client.jar:.\"%n");
            problem = true;
        }

            // ouf on a fini les try catch
        if (problem){ 
            throw new IllegalStateException(sb.toString()); 
        }   
    }
    //Modifie une Parcelle pour qu'elle devienne une mére (pas de filles)
    private void setFille(Parcelle parcelle){
        Boolean problem = false;
        StringBuilder sb = new StringBuilder("Erreur : Il y a eu un problème. Ci-dessous le log de connexion à la base de données.%n");
        Connection con = null;
        Statement stmt = null;
        int id_bd;
        String sql = "UPDATE `Parcelle` SET `Orientation`=null WHERE `idparcelle` = "+parcelle.getidbd()+";";
        try {
            // pas nécessaire mais utile pour détecter un mauvais classpath
            Class.forName("org.mariadb.jdbc.Driver");

            try {
                con = DriverManager.getConnection(bd_url+bd_user, bd_user, bd_pwd);

                if(!con.isClosed()) {
                    sb.append("   OK : BD connexion réussie.%n");
                    try {
                        stmt = con.createStatement();
                        sb.append("   OK : BD création de la déclaration réussie.%n");
                        try{
                            stmt.executeUpdate(sql);

                            sb.append("   OK : BD exécution de la requête réussie.%n");
                        } catch(SQLException e) {
                            sb.append("   KO : insertion ratée (exception SQL ci-dessous).%n");    
                            sb.append(e);
                            problem = true;
                        } finally {
                            try {
                                stmt.close();
                                sb.append("   OK : BD fermeture de la déclaration réussie.%n");
                            } catch(SQLException e) {
                                sb.append("   KO : BD fermeture de la déclaration ratée.%n");
                                problem = true;
                            }
                            stmt = null;
                        }
                    } catch(SQLException e) {
                        sb.append("   KO : BD creation de la déclaration ratée (exception SQL ci-dessous).%n");    
                        sb.append(e);
                        problem = true;
                    }
                } else {
                    sb.append("   KO : BD connexion prématurément fermée.%n");    
                    problem = true;
                }
                try {
                    con.close();
                    sb.append("   OK : BD fermeture de la connexion réussie.%n");    
                } catch(SQLException e) {
                    sb.append("   KO : BD fermeture de la connexion ratée.%n");    
                    problem = true;
                }
            } catch(SQLException e) {
                sb.append("   KO : BD connexion ratée.%n");    
                problem = true;
            }
        }catch(ClassNotFoundException e) {
            sb.append("   KO : BD pas de pilote MariaDB dans les chemins de classes disponibles!%n");
            sb.append("        passez à java le bon classpath. À l'iut il faut :%n");
            sb.append("        -cp \"/export/documents/mariadb-client.jar:.\"%n");
            problem = true;
        }

            // ouf on a fini les try catch
        if (problem){ 
            throw new IllegalStateException(sb.toString()); 
        }   
    }

    //ajoute une sous parcelle à un parcelle (valabe aussi pour des jardins), renvoie l'id de la parcelle crée
    private void AddParcelle(Parcelle parcelle){
        Boolean problem = false, vide;
        StringBuilder sb = new StringBuilder("Erreur : Il y a eu un problème. Ci-dessous le log de connexion à la base de données.%n");
        Connection con = null;
        Statement stmt = null;
        int  id_bd=0, id_racine = parcelle.getRoot().getidbd();
        int id_mere = parcelle.getMother().getidbd();
        int x0 =parcelle.getx0();
        int y0 =parcelle.gety0();
        int x1 =parcelle.getx1();
        int y1 =parcelle.gety1();

        ResultSet res = null;


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

        String sql2 = "SELECT LAST_INSERT_ID();";
        try {
            // pas nécessaire mais utile pour détecter un mauvais classpath
            Class.forName("org.mariadb.jdbc.Driver");

            try {
                con = DriverManager.getConnection(bd_url+bd_user, bd_user, bd_pwd);

                if(!con.isClosed()) {
                    sb.append("   OK : BD connexion réussie.%n");
                    try {
                        stmt = con.createStatement();
                        sb.append("   OK : BD création de la déclaration réussie.%n");
                        try{
                            stmt.executeUpdate(sql);
                            res=stmt.executeQuery(sql2);

                            if (res.next() == false){
                                vide = true;
                            }else{
                                id_bd = res.getInt(1);
                            }

                            sb.append("   OK : BD exécution de la requête réussie.%n");
                        } catch(SQLException e) {
                            sb.append("   KO : insertion ratée (exception SQL ci-dessous).%n");    
                            sb.append(e);
                            problem = true;
                        } finally {
                            try {
                                stmt.close();
                                sb.append("   OK : BD fermeture de la déclaration réussie.%n");
                            } catch(SQLException e) {
                                sb.append("   KO : BD fermeture de la déclaration ratée.%n");
                                problem = true;
                            }
                            stmt = null;
                        }
                    } catch(SQLException e) {
                        sb.append("   KO : BD creation de la déclaration ratée (exception SQL ci-dessous).%n");    
                        sb.append(e);
                        problem = true;
                    }
                } else {
                    sb.append("   KO : BD connexion prématurément fermée.%n");    
                    problem = true;
                }
                try {
                    con.close();
                    sb.append("   OK : BD fermeture de la connexion réussie.%n");    
                } catch(SQLException e) {
                    sb.append("   KO : BD fermeture de la connexion ratée.%n");    
                    problem = true;
                }
            } catch(SQLException e) {
                sb.append("   KO : BD connexion ratée.%n");    
                problem = true;
            }
        }catch(ClassNotFoundException e) {
            sb.append("   KO : BD pas de pilote MariaDB dans les chemins de classes disponibles!%n");
            sb.append("        passez à java le bon classpath. À l'iut il faut :%n");
            sb.append("        -cp \"/export/documents/mariadb-client.jar:.\"%n");
            problem = true;
        }

            // ouf on a fini les try catch
        if (problem){ 
            throw new IllegalStateException(sb.toString()); 
        }
        parcelle.setidbd(id_bd);
    }

    //Supprime une parcelle (ces filles sont aussi suprimmé par des triggers impleùenté dans la base)
    public void DeleteParcelle(Parcelle parcelle){
        int id = parcelle.getidbd();
        Boolean problem = false;
        StringBuilder sb = new StringBuilder("Erreur : Il y a eu un problème. Ci-dessous le log de connexion à la base de données.%n");
        Connection con = null;
        Statement stmt = null;

        String sql = "DELETE FROM Parcelle WHERE idparcelle = "+id+";";
        try {
            // pas nécessaire mais utile pour détecter un mauvais classpath
            Class.forName("org.mariadb.jdbc.Driver");

            try {
                con = DriverManager.getConnection(bd_url+bd_user, bd_user, bd_pwd);

                if(!con.isClosed()) {
                    sb.append("   OK : BD connexion réussie.%n");
                    try {
                        stmt = con.createStatement();
                        sb.append("   OK : BD création de la déclaration réussie.%n");
                        try{
                            stmt.executeUpdate(sql);
                            sb.append("   OK : BD exécution de la requête réussie.%n");
                        } catch(SQLException e) {
                            sb.append("   KO : insertion ratée (exception SQL ci-dessous).%n");    
                            sb.append(e);
                            problem = true;
                        } finally {
                            try {
                                stmt.close();
                                sb.append("   OK : BD fermeture de la déclaration réussie.%n");
                            } catch(SQLException e) {
                                sb.append("   KO : BD fermeture de la déclaration ratée.%n");
                                problem = true;
                            }
                            stmt = null;
                        }
                    } catch(SQLException e) {
                        sb.append("   KO : BD creation de la déclaration ratée (exception SQL ci-dessous).%n");    
                        sb.append(e);
                        problem = true;
                    }
                } else {
                    sb.append("   KO : BD connexion prématurément fermée.%n");    
                    problem = true;
                }
                try {
                    con.close();
                    sb.append("   OK : BD fermeture de la connexion réussie.%n");    
                } catch(SQLException e) {
                    sb.append("   KO : BD fermeture de la connexion ratée.%n");    
                    problem = true;
                }
            } catch(SQLException e) {
                sb.append("   KO : BD connexion ratée.%n");    
                problem = true;
            }
            // ouf on a fini les try catch
        }
        catch(ClassNotFoundException e) {
            sb.append("   KO : BD pas de pilote MariaDB dans les chemins de classes disponibles!%n");
            sb.append("        passez à java le bon classpath. À l'iut il faut :%n");
            sb.append("        -cp \"/export/documents/mariadb-client.jar:.\"%n");
            problem = true;
        } 
        if (problem) { throw new IllegalStateException(sb.toString()); }
        
    }

    //renvoi l'id de la sousParcelle0( true) ou de la sousParcelle1 (false)
    private int getIDParcelle(int id_mere,boolean fils){
        int sousParcelle;
        if (fils) {
            sousParcelle=0;
        }else{
            sousParcelle=1;
        }
        Boolean problem = false;
        Boolean vide = false;
        StringBuilder sb = new StringBuilder("Erreur : Il y a eu un problème. Ci-dessous le log de connexion à la base de données.%n");
        Connection con = null;
        Statement stmt = null;
        ResultSet res = null;
        String sql = "SELECT `sousParcelle"+sousParcelle+"` FROM `Parcelle` WHERE `idparcelle` = "+id_mere;
        int id_sp= 0;
        try {
            // pas nécessaire mais utile pour détecter un mauvais classpath
            Class.forName("org.mariadb.jdbc.Driver");
            try {
                con = DriverManager.getConnection(bd_url+bd_user, bd_user, bd_pwd);
                if(!con.isClosed()) {
                    sb.append("   OK : BD connexion réussie.%n");
                    try {
                        stmt = con.createStatement();
                        sb.append("   OK : BD création de la déclaration réussie.%n");
                        try{
                            res =stmt.executeQuery(sql);
                            if (res.next() == false){
                                vide = true;
                            }else{
                                id_sp = res.getInt(1);
                            }
                            sb.append("   OK : BD exécution de la requête réussie.%n");
                        }catch(SQLException e){
                            sb.append("   KO : insertion ratée (exception SQL ci-dessous).%n");    
                            sb.append(e);
                            problem = true;
                        }finally {
                            try {
                                stmt.close();
                                sb.append("   OK : BD fermeture de la déclaration réussie.%n");
                            } catch(SQLException e) {
                                sb.append("   KO : BD fermeture de la déclaration ratée.%n");
                                problem = true;
                            }
                            stmt = null;
                        }
                    } catch(SQLException e) {
                        sb.append("   KO : BD creation de la déclaration ratée (exception SQL ci-dessous).%n");    
                        sb.append(e);
                        problem = true;
                    }
                }else {
                    sb.append("   KO : BD connexion prématurément fermée.%n");    
                    problem = true;
                }
                try {
                    con.close();
                    sb.append("   OK : BD fermeture de la connexion réussie.%n");    
                } catch(SQLException e) {
                    sb.append("   KO : BD fermeture de la connexion ratée.%n");    
                    problem = true;
                }
            }catch(SQLException e) {
                sb.append("   KO : BD connexion ratée.%n");    
                problem = true;
            }
            // ouf on a fini les try catch
            if (problem) { throw new IllegalStateException(sb.toString()); }
        }catch(ClassNotFoundException e) {
            sb.append("   KO : BD pas de pilote MariaDB dans les chemins de classes disponibles!%n");
            sb.append("        passez à java le bon classpath. À l'iut il faut :%n");
            sb.append("        -cp \"/export/documents/mariadb-client.jar:.\"%n");
            problem = true;
        } 
        if (vide == true) {
            return 0;
        }else{
            return id_sp;
        }
    }

    //renvoi l'orientation d'une parcelle 
    private Orientation getOrientationParcelle(int id){

        Boolean problem = false;
        Boolean vide = false;
        StringBuilder sb = new StringBuilder("Erreur : Il y a eu un problème. Ci-dessous le log de connexion à la base de données.%n");
        Connection con = null;
        Statement stmt = null;
        ResultSet res = null;
        String sql = "SELECT `Orientation` FROM `Parcelle` WHERE `idparcelle` = "+id;
        String orientation=" ";
        try {
            // pas nécessaire mais utile pour détecter un mauvais classpath
            Class.forName("org.mariadb.jdbc.Driver");
            try {
                con = DriverManager.getConnection(bd_url+bd_user, bd_user, bd_pwd);
                if(!con.isClosed()) {
                    sb.append(" OK : BD connexion réussie.%n");
                    try{
                        stmt = con.createStatement();
                        sb.append("   OK : BD création de la déclaration réussie.%n");
                        try{
                            res =stmt.executeQuery(sql);
                            if (res.next() == false){
                                vide = true;
                            }else{
                                orientation = res.getString(1);
                            }
                            sb.append("   OK : BD exécution de la requête réussie.%n");
                        }catch(SQLException e){
                            sb.append("   KO : insertion ratée (exception SQL ci-dessous).%n");    
                            sb.append(e);
                            problem = true;
                        }finally{
                            try {
                                stmt.close();
                                sb.append("   OK : BD fermeture de la déclaration réussie.%n");
                            }catch(SQLException e){
                                sb.append("   KO : BD fermeture de la déclaration ratée.%n");
                                problem = true;
                            }
                            stmt = null;
                        }
                    }catch(SQLException e){
                        sb.append("   KO : BD creation de la déclaration ratée (exception SQL ci-dessous).%n");    
                        sb.append(e);
                        problem = true;
                    }
                }else{
                    sb.append("   KO : BD connexion prématurément fermée.%n");    
                    problem = true;
                }
                try {
                    con.close();
                    sb.append("   OK : BD fermeture de la connexion réussie.%n");    
                }catch(SQLException e) {
                    sb.append("   KO : BD fermeture de la connexion ratée.%n");    
                    problem = true;
                }
            }catch(SQLException e){
                sb.append("   KO : BD connexion ratée.%n");    
                problem = true;
            }
            // ouf on a fini les try catch
            if (problem) { 
                throw new IllegalStateException(sb.toString()); 
            }
        }catch(ClassNotFoundException e) {
            sb.append("   KO : BD pas de pilote MariaDB dans les chemins de classes disponibles!%n");
            sb.append("        passez à java le bon classpath. À l'iut il faut :%n");
            sb.append("        -cp \"/export/documents/mariadb-client.jar:.\"%n");
            problem = true;
        } 
        if (vide == true) {
            return null;
        }else{
            if (Objects.nonNull(orientation)) {
                return  Orientation.valueOf(orientation);         
            }
            return null;
        }
    }
}