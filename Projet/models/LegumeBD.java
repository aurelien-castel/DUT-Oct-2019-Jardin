package models;

import java.util.*;

import java.time.Month;
import java.time.format.TextStyle;

/**
 * 
 */
public class LegumeBD implements Legume {

    private String nom;
    private int id;

    private FamilleLegume famille;
    
    // Map dont la clé est un enum (ici des mois) et la valeur correspondant à chaque clé est un booléen indiquant si on peut semer à ce mois.
    private EnumMap<Month, Boolean> mapSemis = new EnumMap<Month, Boolean>(Month.class);

    // Map dont la clé est un enum (ici des mois) et la valeur correspondant à chaque clé est un booléen indiquant si on peut récolter à ce mois.
    private EnumMap<Month, Boolean> mapRecolte = new EnumMap<Month, Boolean>(Month.class);

    // constructeur
    public LegumeBD(String nom, FamilleLegume famille){
        this.nom = nom;
        this.famille = famille;
        // par défaut les mois ne sont pas OK pour le semis ou la récolte (c'est à l'utilisateur de mettre à vrai les mois correct avec recolteOK).
        for (Month m : Month.values()){
            this.mapSemis.put(m,false);
            this.mapRecolte.put(m,false);
        }
    }

    // teste si le kième bit est 1 (en comptant à partir de la droite)
    public static boolean isKthBitSet(int n, int k){
        return (((n >> (k-1)) & 1 ) == 1);
    }

    // Autre constructeur
    // @param semis, recolte : entiers sur 12 bits. De droite à gauche les mois (decembre bit 12 ... janvier bit 1).
    public LegumeBD(int id, String nomLegume, FamilleLegume famille, int semis, int recolte){
        this(nomLegume,famille);        // appel du constructeur ci-dessus
        this.id = id;
        if (semis < 0 || semis > 0b111111111111){
            throw new IllegalStateException("semis doit être la valeur entière dont l'écriture binaire est sur 12 bits, un bit par mois de janvier à décembre de droite à gauche");
        }
        if (recolte < 0 || recolte > 0b111111111111){
            throw new IllegalStateException("recolte doit être la valeur entière dont l'écriture binaire est sur 12 bits, un bit par mois de janvier à décembre de droite à gauche");
        }
        // Gestion des mois avec semis et recolte
        // on utilise le fait que les valeurs des mois sont 1, 2, 3, ...
        for (Month m : Month.values()){
            if (this.isKthBitSet(semis,m.getValue()))
                { this.semisOK(m); }
            if (this.isKthBitSet(recolte,m.getValue()))
                { this.recolteOK(m); }
        }

    }
    
    /** 
     * autorise le semis pour le mois donné en argument
     * @param m un mois (voir Month.java)
     * @return void
     */
    public void semisOK(Month m){
        this.mapSemis.put(m, true);
    }

    /** 
     * autorise le semis pour le mois donné en argument
     * @param m un mois (voir Month.java)
     * @return void
     */
    public void recolteOK(Month m){
        this.mapRecolte.put(m, true);
    }


    public Boolean getSemis(Month m){
        return this.mapSemis.get(m);
    }

    public Boolean getRecolte(Month m){
        return this.mapRecolte.get(m);
    }

    public String getNom(){ 
        return nom; 
    }

    public  int getid(){
        return id;
    }

    public FamilleLegume getFamille(){ 
        return famille; 
    }

    
    public String toString(TextStyle t){
        // Stringbuilder is the most efficient method of building a String like datastructure incrementally. 
        StringBuilder sb = new StringBuilder( this.nom + " (" + this.famille + "), semis ");
        for (Month m : Month.values()){
            if (this.mapSemis.get(m))
                sb.append(String.format("%s ",m.getDisplayName(t, Locale.FRANCE)));
        }
        sb.append(", récolte ");
        for (Month m : Month.values()){
            if (this.mapRecolte.get(m))
                sb.append(String.format("%s ",m.getDisplayName(t, Locale.FRANCE)));
        }
        sb.append("%n");
        return sb.toString();
    }
}