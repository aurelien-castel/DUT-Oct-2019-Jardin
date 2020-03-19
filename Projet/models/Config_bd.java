package models;

// détails connexion base de donnée
public final class Config_bd{
    private Config_bd() {}
	private static String bd_url  = "jdbc:mariadb://dwarves.iut-fbleau.fr/";
    private static String bd_user = "guillemo";
    private static String bd_pwd  = "XyKGcnOF7AyneqAU";

    public static String geturl(){
    	return bd_url;
    }

    public static String getuser(){
    	return bd_user;
    }
    public static String getpwd(){
    	return bd_pwd;
    }
}

