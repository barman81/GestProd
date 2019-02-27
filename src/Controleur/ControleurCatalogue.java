package Controleur;

import Affichage.FenetrePrincipale;
import Metier.Catalogue;
import Metier.I_Catalogue;
import Modele.CatalogueFactoryDAO;
import Modele.I_CatalogueDAO;
import Modele.I_ProduitDAO;
import Modele.ProduitFactoryDAO;

import java.util.ArrayList;
import java.util.List;

public class ControleurCatalogue {
    public ArrayList listeCata = new ArrayList();
    public static I_CatalogueDAO catalogueDAO = CatalogueFactoryDAO.createCatalogueDAO_Oracle();

    public static String[] afficherLesCatalogue() throws ClassNotFoundException {
        List<I_Catalogue> listeCatalogue = catalogueDAO.getListeCatalogues();
        String[] tab = new String[listeCatalogue.size()];
        int i = 0;
        for(I_Catalogue unCatalogue : listeCatalogue){
            tab[i] = unCatalogue.getNom();
            i++;
        }
        return tab;
    }

    public static String[] nb_Produits_by_Catalogue() throws ClassNotFoundException {
        List<I_Catalogue> listeCatalogue = catalogueDAO.getListeCatalogues();

        String[] tab = new String[listeCatalogue.size()];
        int i = 0;
        for(I_Catalogue unCatalogue : listeCatalogue){
            String cataNbProduit =  unCatalogue.getNom()+ " : "+catalogueDAO.getNbProduits(unCatalogue.getNom()) ;
            tab[i] = cataNbProduit;
            i++;
        }
        return tab;
    }

    public static int getNbCatalogue() throws ClassNotFoundException {
        List<I_Catalogue> listeCatalogue = catalogueDAO.getListeCatalogues();
        return listeCatalogue.size();
    }
}
