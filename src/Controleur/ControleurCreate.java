package Controleur;
import Metier.Catalogue;
import Metier.I_Catalogue;

public class ControleurCreate {

    public static I_Catalogue cat = new Catalogue();

    public static void acheterNouveauProduit(String nomProduit, double prix, int qte){
        cat.addProduit(nomProduit, prix, qte);
    }
}
