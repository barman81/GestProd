package Controleur;

import Affichage.FenetrePrincipale;
import Metier.I_Catalogue;
import Metier.I_Produit;

import java.sql.SQLException;

public class ControleurDelete {
    static I_Catalogue cat = FenetrePrincipale.cat;

    public static String[] afficherNomsProduits(){
        return cat.getNomProduits();
    }

    public static void supprimerProduit(String nom) throws SQLException, ClassNotFoundException {
        cat.removeProduit(nom);
        I_Produit produit = FenetrePrincipale.produitDAO.getUnProduit(nom, cat);
        FenetrePrincipale.produitDAO.deleteProduit(produit, cat);
    }
}
