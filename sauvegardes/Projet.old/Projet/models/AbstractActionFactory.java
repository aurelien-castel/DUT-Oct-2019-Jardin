package models;
import java.util.*;
import java.time.LocalDate;

public interface AbstractActionFactory{
	public void AddAction(Parcelle parcelle,Parcelle parcelleracine,String type,Legume legume);
	public ArrayList<String> getAllDate();
    public ArrayList<String> getallAction(Parcelle parcelle, LocalDate date);
}