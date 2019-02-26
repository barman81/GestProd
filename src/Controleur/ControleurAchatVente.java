package Controleur;

import Affichage.FenetrePrincipale;
import Metier.I_Catalogue;
import Metier.I_Produit;

import java.sql.SQLException;

public class ControleurAchatVente {

    static I_Catalogue cat = FenetrePrincipale.cat;

    public static String[] afficherNomsProduits(){
        return cat.getNomProduits();
    }

    public static void vendreProduit(String nomProduit, int qte) throws SQLException, ClassNotFoundException {
        cat.vendreStock(nomProduit, qte);
        I_Produit produit = FenetrePrincipale.produitDAO.getUnProduit(nomProduit);
        produit.enlever(qte);
        FenetrePrincipale.produitDAO.updateProduit(produit);
    }

    public static void acheterStockProduit(String nomProduit, int qte) throws SQLException, ClassNotFoundException {
        cat.acheterStock(nomProduit, qte);
        I_Produit produit = FenetrePrincipale.produitDAO.getUnProduit(nomProduit);
        produit.ajouter(qte);
        FenetrePrincipale.produitDAO.updateProduit(produit);
    }

    public static String afficherQuantiteStock(){
        return cat.toString();
    }
}
