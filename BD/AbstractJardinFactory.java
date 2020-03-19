import java.util.*;

public interface AbstractJardinFactory{

    // ajoute si possible un nouveau jardin et retourne la parcelle mère de ce jardin.
    // Si pas possible lance une IllegalStateException
    public Parcelle AddJardin(String nomJardin, int dimx, int dimy);

    // supprime (si existant) le jardin.
    // Si pas possible lance une IllegalStateException
    public void DeleteJardin(String nomJardin);

    // retourne (si existant) le jardin.
    // Si pas possible lance une IllegalStateException
    public Parcelle getJardin(String nomJardin);

    // Split la Parcelle (fonctionne pour un jardin)
    public void SplitParcelle(Parcelle parcelle,Orientation o);

    // réunit les sous-parcelles entre elles
    public void reset(Parcelle parcelle);


    //Métodes rajouté 
    public void setLegumeFactory(AbstractLegumeFactory lfbd);

    public ArrayList<String> getallJardin();

    public void DeleteParcelle(Parcelle parcelle);

    public void setlegume(Parcelle parcelle,Legume legume);
}
