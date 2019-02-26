package Controleur;
import Affichage.FenetrePrincipale;
import Metier.Catalogue;
import Metier.I_Catalogue;

public class ControleurCreate {

    public static I_Catalogue cat = FenetrePrincipale.cat;

    public static void acheterNouveauProduit(String nomProduit, double prix, int qte){
        cat.addProduit(nomProduit, prix, qte);
    }
}
