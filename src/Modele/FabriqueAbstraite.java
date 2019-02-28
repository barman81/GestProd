package Modele;


public interface FabriqueAbstraite {

    I_ProduitDAO createProduitDAO();

    I_CatalogueDAO createCatalogueDAO();
}
