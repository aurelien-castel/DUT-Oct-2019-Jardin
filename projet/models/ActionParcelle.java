package models;

import java.util.*;

import java.time.Month;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ActionParcelle extends Action {

    private ActionParcelleType type;

    public ActionParcelleType getType() { return type; }

    public ActionParcelle(LocalDate date, Parcelle parcelle, ActionParcelleType type){
        this.date=date;
        this.parcelle = parcelle;
        this.type=type;
    }

    public String toString(){
        return this.date.format(DateTimeFormatter.ofPattern("dd-MM-YYYY")) + " dans "+ parcelle.myToString() + " : " + type + " " + "%n";
    }
}


