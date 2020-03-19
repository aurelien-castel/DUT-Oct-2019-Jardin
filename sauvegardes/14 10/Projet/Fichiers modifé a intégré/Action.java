import java.util.*;
import java.time.LocalDate;

/*
** Une action est quelque chose qu'on fait à un moment sur une parcelle.
*/

public abstract class Action {

    protected LocalDate date;

    protected Parcelle parcelle;
        
    public LocalDate getDate() {return date;}

    public Parcelle getParcelle() {return parcelle;}

    public abstract String toString();
    
}

