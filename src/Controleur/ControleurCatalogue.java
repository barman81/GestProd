package Controleur;

import Metier.Catalogue;
import Metier.I_Catalogue;
import Modele.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControleurCatalogue {
    public static ArrayList listeCata = new ArrayList();
    public static FabriqueAbstraite factoryDAO = new FactoryDAO_Oracle(); // changer ORACLE en XML pour passer de la bdd au xml
    public static I_CatalogueDAO catalogueDAO = factoryDAO.createCatalogueDAO();
    public static I_ProduitDAO produitDAO = factoryDAO.createProduitDAO();
    public static I_Catalogue leCatalogue;

    public static String[] afficherLesCatalogue() throws ClassNotFoundException {
        List<I_Catalogue> listeCatalogue = catalogueDAO.getListeCatalogues();
        String[] tab = new String[listeCatalogue.size()];
        int i = 0;
        for(I_Catalogue unCatalogue : listeCatalogue){
            tab[i] = unCatalogue.getNom();
            i++;
            listeCata.add(unCatalogue);
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

    public static void newCatalogue(String nom) throws ClassNotFoundException {
        I_Catalogue catalogue = new Catalogue(nom);
        listeCata.add(catalogue);
        catalogueDAO.addCatalogue(catalogue);
    }

    public static void supprimerCatalogue(String nom) throws SQLException, ClassNotFoundException {
        I_Catalogue catalogue = catalogueDAO.getCatalogue(nom);
        catalogueDAO.deleteCatalogue(catalogue);
        listeCata.remove(catalogue);
    }
    public static void getCatalogue(String nom) throws SQLException, ClassNotFoundException {
        I_Catalogue catalogue = catalogueDAO.getCatalogue(nom);
        leCatalogue = catalogue;
        listeCata.remove(catalogue);
    }
}
