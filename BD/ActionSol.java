import java.util.*;

import java.time.Month;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ActionSol extends Action {

    private ActionSolType type;

    public ActionSolType getType() { return type; }

    public ActionSol(LocalDate date, Parcelle parcelle, ActionSolType type){
        this.date=date;
        this.parcelle = parcelle;
        this.type=type;
    }

    public String toString(){
        return this.date.format(DateTimeFormatter.ofPattern("dd-MM-YYYY")) + " dans "+ parcelle.myToString() + " : " + type + " " + "%n";
    }
}


