package Modele;

public class ProduitFactoryDAO {

    public static I_ProduitDAO createProduitDAO_Oracle(){
        return new ProduitDAO_Oracle();
    }

//    public static I_ProduitDAO createProduitDAO_XML(){
//        return new AdapatateurDAO_XML();
//    }

}
