package Modele;

public class FactoryDAO_XML implements FabriqueAbstraite{


    public I_ProduitDAO createProduitDAO(){
        return new ProduitAdaptateurDAO_XML();
    }

    public I_CatalogueDAO createCatalogueDAO(){
        CatalogueDAO_XML catalogueDAO_xml = new CatalogueDAO_XML();
        return new CatalogueDAO_XML();
    }
}
