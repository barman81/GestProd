package Controleur;

import Metier.I_Catalogue;

public class ControleurAchatVente {

    static I_Catalogue cat = ControleurCreate.cat;

    public static String[] afficherNomsProduits(){
        return cat.getNomProduits();
    }

    public static void vendreProduit(String nomProduit, int qte){
        cat.vendreStock(nomProduit, qte);
    }

    public static void acheterStockProduit(String nomProduit, int qte){
        cat.acheterStock(nomProduit, qte);
    }

    public static String afficherQuantiteStock(){
        return cat.toString();
    }
}
