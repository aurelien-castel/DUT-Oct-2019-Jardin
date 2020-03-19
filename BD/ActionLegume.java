import java.util.*;
import java.time.Month;
//import java.time.format.TextStyle;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Cette classe n'est pas mutable.

public class ActionLegume extends Action {

    private Legume legume;

    private ActionLegumeType type;

    public Legume getLegume(){ return legume; }

    public ActionLegumeType getType(){ return type; }
    
    public ActionLegume(LocalDate date, Parcelle parcelle, Legume legume, ActionLegumeType type){
        this.date = date;
        this.parcelle = parcelle;
        this.legume = legume;
        this.type = type;
    }

    public String toString(){
        return this.date.format(DateTimeFormatter.ofPattern("dd-MM-YYYY")) + " dans "+ parcelle.myToString() + " : " + type + " " + legume.getNom() + "%n";
    }
}