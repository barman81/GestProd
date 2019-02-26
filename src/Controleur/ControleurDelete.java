package Controleur;

import Affichage.FenetrePrincipale;
import Metier.I_Catalogue;

public class ControleurDelete {
    static I_Catalogue cat = FenetrePrincipale.cat;

    public static String[] afficherNomsProduits(){
        return cat.getNomProduits();
    }

    public static void supprimerProduit(String nom){
        cat.removeProduit(nom);
    }
}
