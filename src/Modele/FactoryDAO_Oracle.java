package Modele;

public class FactoryDAO_Oracle implements FabriqueAbstraite {

    @Override
    public I_ProduitDAO createProduitDAO(){
        return new ProduitDAO_Oracle();
    }

    @Override
    public  I_CatalogueDAO createCatalogueDAO(){
        return new CatalogueDAO_Oracle();
    }


}
