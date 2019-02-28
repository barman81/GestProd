package Modele;

public class FactoryDAO_XML implements FabriqueAbstraite{


    public I_ProduitDAO createProduitDAO(){
        return new ProduitAdaptateurDAO_XML();
    }

    public I_CatalogueDAO createCatalogueDAO(){
        return null;
    }
}
