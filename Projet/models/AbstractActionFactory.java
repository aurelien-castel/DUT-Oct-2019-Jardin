package models;

import java.util.*;
import java.time.LocalDate;

public interface AbstractActionFactory{
	public Action AddAction(Parcelle parcelle,ActionLegumeType type,Legume legume);
    public Action AddAction(Parcelle parcelle,ActionSolType type);
    public Action AddAction(Parcelle parcelle,ActionParcelleType type);
	public ArrayList<String> getAllDate();

    public ArrayList<String> getActions(Parcelle parcelle, LocalDate date);
    public ArrayList<Action> getActions(Parcelle parcelle);
    public ArrayList<String> getAllActions(Parcelle parcelle, LocalDate date);

}