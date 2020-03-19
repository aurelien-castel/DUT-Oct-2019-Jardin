import java.util.*;
import java.time.Month;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/* Permet au factory BD d'utilser des métodes redeondante */
public class MethodeBD{
    String bd_url  = Config_bd.geturl();
    String bd_user = Config_bd.getuser();
    String bd_pwd  = Config_bd.getpwd();

    String sql;
    Connection con;
    Statement stmt;
    ResultSet res;
    int nbResultat;

    StringBuilder sb;
    Boolean problem, vide;

    ArrayList<String> resultat = new ArrayList<String>();


    public void setConnection(){
        problem = false;
        sb = new StringBuilder("Erreur : Il y a eu un problème. Ci-dessous le log de connexion à la base de données.%n");
        con = null;
        stmt = null;
        resultat.clear();
    }
    public void execute(String sql, boolean typeRequete,boolean lastinsert){
        vide = true;
        res = null;
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
                            if (typeRequete){
                                res=stmt.executeQuery(sql);
                                while(res.next()){
                                    nbResultat=1;
                                    try{  
                                        while(true){
                                            vide = false;

                                            resultat.add(res.getString(nbResultat));
                                            nbResultat++;
                                        }
                                    }catch (SQLException e){
                                    }                                   
                                }

                                if (lastinsert) {
                                 nbResultat=1;
                                 String sqli = "SELECT LAST_INSERT_ID()";
                                 try{ 
                                    res=stmt.executeQuery(sqli);
                                    res.next();
                                    resultat.add(res.getString(nbResultat));
                                }catch (SQLException e){
                                    System.out.println("execgfj");
                                } 

                            }
                        }else{
                            stmt.executeUpdate(sql);
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
                        }catch(SQLException e) {
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
            }else {
                sb.append("   KO : BD connexion prématurément fermée.%n");    
                problem = true;
            }
                /*try {
                    con.close();
                    sb.append("   OK : BD fermeture de la connexion réussie.%n");    
                } catch(SQLException e) {
                    sb.append("   KO : BD fermeture de la connexion ratée.%n");    
                    problem = true;
                }*/
            }catch(SQLException e) {
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
        close();
    }

    public void close(){
        try {
            con.close();
            sb.append("   OK : BD fermeture de la connexion réussie.%n");    
        } catch(SQLException e) {
            sb.append("   KO : BD fermeture de la connexion ratée.%n");    
            problem = true;
        }      
    }
}