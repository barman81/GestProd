package Modele;

public class CatalogueFactoryDAO {
    public static I_CatalogueDAO createCatalogueDAO_Oracle(){
        return new CatalogueDAO();
    }


}
