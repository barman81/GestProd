package Modele;

public class ProduitFactoryDAO {

    public static void createProduitDAO_Oracle(){
        //TODO: create produit DAO
        new ProduitDAO_Oracle();
    }

    public static void createProduitDAO_XML(){
        new modele.ProduitDAO_XML();
    }

}
