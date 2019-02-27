package Modele;

import Metier.I_Catalogue;
import Metier.I_Produit;

import java.sql.SQLException;
import java.util.List;

public interface I_CatalogueDAO {

    boolean addCatalogue(I_Catalogue catalogue) throws ClassNotFoundException;

    boolean deleteCatalogue(I_Catalogue catalogue) throws SQLException, ClassNotFoundException;

    List<I_Catalogue> getListeCatalogues() throws ClassNotFoundException;

    int getNbProduits(String nom);
}
