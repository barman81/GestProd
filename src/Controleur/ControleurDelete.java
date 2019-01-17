package Controleur;

import Metier.I_Catalogue;

public class ControleurDelete {
    static I_Catalogue cat = ControleurCreate.cat;

    public static String[] afficherNomsProduits(){
        return cat.getNomProduits();
    }

    public static void supprimerProduit(String nom){
        cat.removeProduit(nom);
    }
}
