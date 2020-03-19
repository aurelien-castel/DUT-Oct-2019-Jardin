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

    public Parcelle AddJardin(String nomJardin, int dimx, int dimy){
        Parcelle test = getJardin(nomJardin);
        if (test !=null) {
            System.out.println("Le jardin "+nomJardin+" existe déja !");
        }else{
            Boolean problem = false;
            StringBuilder sb = new StringBuilder("Erreur : Il y a eu un problème. Ci-dessous le log de connexion à la base de données.%n");
            Connection con = null;
            Statement stmt = null;
            String sql = "INSERT INTO Jardin(nomJardin ,dimx ,dimy) " +
            "VALUES (\""+
            nomJardin + "\"," +
            "\""+dimx +"\"," +
            "\""+dimy +"\""+
            ");";
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
            }
            catch(ClassNotFoundException e) {
                sb.append("   KO : BD pas de pilote MariaDB dans les chemins de classes disponibles!%n");
                sb.append("        passez à java le bon classpath. À l'iut il faut :%n");
                sb.append("        -cp \"/export/documents/mariadb-client.jar:.\"%n");
                problem = true;
            }

        // ouf on a fini les try catch
            if (problem) { throw new IllegalStateException(sb.toString()); }
        // ici retourner un LegumeJ
        }
        Parcelle p = this.newJardin(nomJardin, dimx, dimy);
        return p;
    }
    // supprime (si existant) le jardin.
    public void DeleteJardin(String nomJardin){
        Parcelle test = getJardin(nomJardin);
        if (test == null) {
            System.out.println("Le jardin "+nomJardin+" n'existe pas !");
        }else{
            Boolean problem = false;
            StringBuilder sb = new StringBuilder("Erreur : Il y a eu un problème. Ci-dessous le log de connexion à la base de données.%n");
            Connection con = null;
            Statement stmt = null;
            String sql = "DELETE FROM Jardin WHERE nomJardin =  \""+ nomJardin+ "\";";
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
    public Parcelle getJardin(String nomJardin){
        Boolean problem = false;
        Boolean vide = false;
        StringBuilder sb = new StringBuilder("Erreur : Il y a eu un problème. Ci-dessous le log de connexion à la base de données.%n");
        Connection con = null;
        Statement stmt = null;
        ResultSet res = null;
        String sql = "SELECT dimx,dimy FROM Jardin WHERE nomJardin =\"" +nomJardin +"\";";
        int dimx=0 ,dimy=0;
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
                                dimx = res.getInt(1);
                                dimy = res.getInt(2);
                            }

                            sb.append("   OK : BD exécution de la requête réussie.%n");
                        } catch(SQLException e) {
                         System.out.println(res.getString(1));
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
        if (problem) { throw new IllegalStateException(sb.toString()); }
    }
    catch(ClassNotFoundException e) {
        sb.append("   KO : BD pas de pilote MariaDB dans les chemins de classes disponibles!%n");
        sb.append("        passez à java le bon classpath. À l'iut il faut :%n");
        sb.append("        -cp \"/export/documents/mariadb-client.jar:.\"%n");
        problem = true;
    } 
    if (vide == true) {
        return null;
    }else{
      Parcelle p = this.newJardin(nomJardin, dimx, dimy);
      return p;  
  }

}

    /*
     * Pseudo Constructeur pour un nouveau jardin. Retourne la parcelle initiale du jardin
     
    */
    public Parcelle newJardin(String nomJardin, int dimx, int dimy){
        return new ParcelleJ(nomJardin, dimx, dimy);
    }
}