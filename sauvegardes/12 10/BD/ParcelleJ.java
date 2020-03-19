import java.util.*;
import java.time.*;

/**
 * ParcelleJ ne garantie pas la persistance des données.
 * Les données sont des objets java.
 * À utiliser pour des tests mais pas en production.
 */
public class ParcelleJ implements Parcelle{
    /**
     * Nom du jardin à laquelle la parcelle appartient
     */
    private String nomJardin;

    /**
    * Id de la parcelle dans la base
    */
    private int id_bd;

    /**
     * abscisse coin en haut à gauche.
     */
    private int x0;
    /**
     * ordonnée coin en haut à gauche.
     */
    private int y0;
    /**
     * abscisse coin en bas à droite.
     */
    private int x1;
    /**
     * ordonnée coin en bas à droite.
     */
    private int y1;

    /**
     * En cas de découpage de la parcelle, indique si le découpage est horizontal ou vertical
     */
    private Orientation split;

    /**
     * sous parcelle gauche (si split vertical) ou haute (si split horizontal).
     */
    private ParcelleJ sousParcelle0;

    /**
     * sous parcelle droite (si split vertical) ou basse (si split horizontal).
     */
    private ParcelleJ sousParcelle1;


    /**
     * parcelle racine du jardin
     */
    private ParcelleJ tilde;
    
     /**     
      * parcelle mère
      */
     private ParcelleJ pointPoint;

    /**     
     * stocke les actions réalisées sur la parcelle.
     */
    private ArrayList<Action> actions = new ArrayList<Action>();

    /**     
     * retourne un itérateur sur les actions réalisées sur la parcelle mais pas sur une ancêtre de celle-ci.
     */
    @SuppressWarnings("unchecked")
    public Iterator<Action> getActions(){
        // shallow copy. Si quelqu'un détruisait les actions ça pourrait faire mal (mais les actions ne sont pas mutables).
        ArrayList<Action> copieActions = (ArrayList<Action>) actions.clone();
        // nécessaire pour plus de sûreté car Iterator permet de faire un remove sur action.
        return copieActions.iterator();
    }

    /**     
     * retourne un itérateur sur les actions vraiment réalisées sur la parcelle (y compris sur une ancêtre de celle-ci).
     */
    @SuppressWarnings("unchecked")
    public Iterator<Action> getAllActions(){
        ParcelleJ walkingUp = this;
        ArrayList<Action> allActions = (ArrayList<Action>) actions.clone();
        while (walkingUp.pointPoint != walkingUp) {
            walkingUp = walkingUp.pointPoint; // moveUp
            ArrayList<Action> copiesActions = (ArrayList<Action>) walkingUp.actions.clone();
            allActions.addAll(copiesActions);// un peu moche ça retourne un booléen
        }
        return allActions.iterator();
    }

    /**     
     * ajoute une action à la parcelle.
     */
    public void addAction(Action a){
        actions.add(a);
    }
    


    /**
     * retourne le type de split de la parcelle (voir Orientation.java) null si pas de sous-parcelle
     */
    public Orientation getSplit(){
        return split;
    }

    public void setSplit(Orientation o){
        if (Objects.nonNull(this.split)){
            this.split = split;
        }
    }

    /**
     * retourne la sous parcelle gauche (si split vertical) ou haute (si split horizontal).
     */
    public Parcelle getFirst(){
        if (this.split == null || this.sousParcelle0 == null) throw new IllegalStateException("La parcelle n\'a pas de sous-parcelle");
        return sousParcelle0;
    }



    /**
     * retourne la sous parcelle droite (si split vertical) ou basse (si split horizontal).
     */
    public Parcelle getSecond(){
        if (this.split == null || this.sousParcelle1 == null) throw new IllegalStateException("La parcelle n\'a pas de sous-parcelle");
        return sousParcelle1;
    }


    /**
     * réunit les sous-parcelles (un quasi destructeur. on oublie les sous-parcelles, le garbage collector se chargera de les détruire)
     */
    public void reset(){
        if (this.split == null || this.sousParcelle0 == null || this.sousParcelle1 == null) throw new IllegalStateException("Pas possible de détruire le contenu de la parcelle car elle ne contient pas deux sous-parcelles.");
        this.split=null;
        this.sousParcelle0=null;
        this.sousParcelle1=null;
    }

    /**
     *  retourne la parcelle mère de la parcelle (self si parcelle racine du jardin)
     */
    public Parcelle getMother(){
        return this.pointPoint;
    }

    /**
     *  retourne la parcelle racine de la parcelle (self si racine du jardin)
     */
    public Parcelle getRoot(){
        return this.tilde;
    }

    /**
     *  retourne l'abscisse du coin en haut à gauche de la parcelle
     */
    public int getx0(){
        return this.x0;
    }
    /**
     *  retourne l'ordonnée du coin en haut à gauche de la parcelle
     */
    public int gety0(){
        return this.y0;
    }
    /**
     *  retourne l'abscisse du coin en bas à droite de la parcelle
     */
    public int getx1(){
        return this.x1;
    }
    /**
     *  retourne l'ordonnée du coin en bas à droite de la parcelle
     */
    public int gety1(){
        return this.y1;
    }

    /**
     *  retourne l'id de la parcelle dans la base
     */
    public int getidbd(){
        return this.id_bd;
    }

    /**
    * modifie (si non inisialiser) l'id de la parcelle présent dans la base
    */
    public void setidbd(int id){
        if (this.id_bd == 0) {
           this.id_bd = id;      
       }
   }
    /**
     *  retourne le nom du jardin de la parcelle
     */
    public String getNomJardin(){
        return this.nomJardin;
    }
    
    /**
     * Constructeur pour un nouveau jardin
     */
    public ParcelleJ(int id_bd,String nomJardin, int x0, int x1,int y0, int y1, Orientation orientation){
        if (x1 <= 0 || y1 <=0) throw new IllegalArgumentException("Les dimensions du nouveau jardin doivent être strictement positives");
        if (nomJardin.length()==0) throw new IllegalArgumentException("Le nom du nouveau jardin ne peut pas être vide");
        this.id_bd = id_bd;
        this.nomJardin = nomJardin;
        this.x0=x0;
        this.x1=x1;
        this.y0=y0;
        this.y1=y1;
        this.sousParcelle0=null;
        this.sousParcelle1=null;
        this.tilde = this;
        this.pointPoint = this;

        if (Objects.nonNull(orientation)) {
            this.SplitParcelle(orientation);
        }
    }


    /**
     * Constructeur pour une nouvelle parcelle qui n'a pas de descendant (attention doit rester privé, usage interne seulement)
     */
    private ParcelleJ(String nomJardin, int x0, int x1, int y0, int y1, ParcelleJ tilde, ParcelleJ pointPoint){
       this.id_bd = 0;
       this.nomJardin = nomJardin;
       this.x0=x0;
       this.x1=x1;
       this.y0=y0;
       this.y1=y1;
       this.sousParcelle0=null;
       this.sousParcelle1=null;
       this.split= null;
       this.tilde = tilde;
       this.pointPoint = pointPoint;
   }

    /**
     * découpe la parcelle actuelle en crééant deux sous-parcelles selon l'orientation passée en argument.
     */
    public void SplitParcelle(Orientation o){
        Objects.requireNonNull(o, "Orientation o ne doit pas être null");
        if (Objects.nonNull(this.split) || Objects.nonNull(this.sousParcelle0) || Objects.nonNull(this.sousParcelle1)) throw new IllegalStateException("La parcelle ne peut pas être découpée car elle contient des sous-parcelles.");
        this.split = o;
        ParcelleJ p0, p1;
        if (o == Orientation.HORIZONTAL){
            int xsplit = (x1-x0) /2 + (x1-x0) %2; // arrondi supérieur histoire de réviser le modulo.
            p0 = new ParcelleJ(this.nomJardin, this.x0, this.y0,  xsplit, this.y1, this.tilde, this);
            p1 = new ParcelleJ(this.nomJardin,  xsplit, this.y0, this.x1, this.y1, this.tilde, this);
            this.sousParcelle0 = p0;
            this.sousParcelle1 = p1;
        }
        if (o == Orientation.VERTICAL){
            int ysplit = (y1-y0) /2 + (y1-y0) %2; // arrondi supérieur histoire de réviser le modulo.
            p0 = new ParcelleJ(this.nomJardin, this.x0, this.y0, this.x1,  ysplit, this.tilde, this);
            p1 = new ParcelleJ(this.nomJardin, this.x0,  ysplit, this.x1, this.y1, this.tilde, this);
            this.sousParcelle0 = p0;
            this.sousParcelle1 = p1;
        }
    }   
}