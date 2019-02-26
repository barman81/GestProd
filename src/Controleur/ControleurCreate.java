package Controleur;
import Affichage.FenetrePrincipale;
import Metier.Catalogue;
import Metier.I_Catalogue;
import Metier.I_Produit;
import Metier.Produit;

public class ControleurCreate {

    public static I_Catalogue cat = FenetrePrincipale.cat;

    public static void acheterNouveauProduit(String nomProduit, double prix, int qte) throws ClassNotFoundException {
        I_Produit produit = new Produit(nomProduit, prix, qte);
        cat.addProduit(nomProduit, prix, qte);
        FenetrePrincipale.produitDAO.addProduit(produit);
    }
}
